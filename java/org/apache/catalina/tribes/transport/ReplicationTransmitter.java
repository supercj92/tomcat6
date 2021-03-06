/*
 * Copyright 1999,2004-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.catalina.tribes.transport;

import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.ChannelSender;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.util.StringManager;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;

/**
 * Transmit message to other cluster members
 * Actual senders are created based on the replicationMode
 * type 
 * 
 * @author Filip Hanik
 * @version $Revision: 467222 $ $Date: 2006-10-24 05:17:11 +0200 (mar., 24 oct. 2006) $
 */
public class ReplicationTransmitter implements ChannelSender {
    private static org.apache.juli.logging.Log log = org.apache.juli.logging.LogFactory.getLog(ReplicationTransmitter.class);

    /**
     * The descriptive information about this implementation.
     */
    private static final String info = "ReplicationTransmitter/3.0";

    /**
     * The string manager for this package.
     */
    protected StringManager sm = StringManager.getManager(Constants.Package);

    

    public ReplicationTransmitter() {
    }

    private MultiPointSender transport = new PooledParallelSender();

    /**
     * Return descriptive information about this implementation and the
     * corresponding version number, in the format
     * <code>&lt;description&gt;/&lt;version&gt;</code>.
     */
    public String getInfo() {
        return (info);
    }

    public MultiPointSender getTransport() {
        return transport;
    }

    public void setTransport(MultiPointSender transport) {
        this.transport = transport;
    }
    
    // ------------------------------------------------------------- public
    
    /**
     * Send data to one member
     * @see org.apache.catalina.tribes.ClusterSender#sendMessage(org.apache.catalina.tribes.ClusterMessage, org.apache.catalina.tribes.Member)
     */
    public void sendMessage(ChannelMessage message, Member[] destination) throws ChannelException {
        MultiPointSender sender = getTransport();
        sender.sendMessage(destination,message);
    }
    
    
    /**
     * start the sender and register transmitter mbean
     * 
     * @see org.apache.catalina.tribes.ClusterSender#start()
     */
    public void start() throws java.io.IOException {
        getTransport().connect();
    }

    /*
     * stop the sender and deregister mbeans (transmitter, senders)
     * 
     * @see org.apache.catalina.tribes.ClusterSender#stop()
     */
    public synchronized void stop() {
        getTransport().disconnect();
    }

    /**
     * Call transmitter to check for sender socket status
     * 
     * @see SimpleTcpCluster#backgroundProcess()
     */

    public void heartbeat() {
        
    }

    /**
     * add new cluster member and create sender ( s. replicationMode) transfer
     * current properties to sender
     * 
     * @see org.apache.catalina.tribes.ClusterSender#add(org.apache.catalina.tribes.Member)
     */
    public synchronized void add(Member member) {
        getTransport().memberAdded(member);
    }

    /**
     * remove sender from transmitter. ( deregister mbean and disconnect sender )
     * 
     * @see org.apache.catalina.tribes.ClusterSender#remove(org.apache.catalina.tribes.Member)
     */
    public synchronized void remove(Member member) {
        getTransport().memberDisappeared(member);
    }

    // ------------------------------------------------------------- protected

    

}
