/*
 * Copyright 1999,2004 The Apache Software Foundation.
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

package org.apache.catalina.ha.tcp;

import org.apache.catalina.tribes.Member;

/**
 * @author Peter Rossbach
 * @version $Revision: 467222 $ $Date: 2006-10-24 05:17:11 +0200 (mar., 24 oct. 2006) $
 */
public class SendMessageData {

    private Object message ;
    private Member destination ;
    private Exception exception ;
    
    
    /**
     * @param message
     * @param destination
     * @param exception
     */
    public SendMessageData(Object message, Member destination,
            Exception exception) {
        super();
        this.message = message;
        this.destination = destination;
        this.exception = exception;
    }
    
    /**
     * @return Returns the destination.
     */
    public Member getDestination() {
        return destination;
    }
    /**
     * @param destination The destination to set.
     */
    public void setDestination(Member destination) {
        this.destination = destination;
    }
    /**
     * @return Returns the exception.
     */
    public Exception getException() {
        return exception;
    }
    /**
     * @param exception The exception to set.
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
    /**
     * @return Returns the message.
     */
    public Object getMessage() {
        return message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(Object message) {
        this.message = message;
    }
}
