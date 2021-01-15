/*
 * Copyright (C) 2002-2021, Martian Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.martiansoftware.jsap;

/**
 * A JSAPException subclass notifying the application of a parse error.
 * Additional information
 * in the form of an exception may be encapsulated in this object, as in other
 * JSAPExceptions.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.JSAPException
 */
public class ParseException extends JSAPException {

    /**
     * Creates a new ParseException.
     */
    public ParseException() {
        super();
    }

    /**
     * Creates a new ParseException with the specified message.
     * @param msg the message for this ParseException.
     */
    public ParseException(String msg) {
        super(msg);
    }

    /**
     * Creates a new ParseException encapsulating the specified Throwable.
     * @param cause the Throwable to encapsulate.
     */
    public ParseException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new ParseException with the specified message encapsulating the
     * specified Throwable.
     * @param msg the message for this ParseException.
     * @param cause the Throwable to encapsulate.
     */
    public ParseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
