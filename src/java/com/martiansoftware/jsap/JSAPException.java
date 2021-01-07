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
 * The base class for all of JSAP's exceptions.  A JSAPException can
 * encapsulate another Exception, which can be obtained via the getRootCause()
 * method.  This is useful in cases where subclasses might need to
 * throw an exception that JSAP is not expecting, such as an IOException while
 * loading a DefaultSource.  The subclass can in these cases throw a new
 * JSAPException encapsulating the IOException.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.Flagged
 * @see com.martiansoftware.jsap.Option
 */
public class JSAPException extends Exception {

    /**
     * Creates a new JSAPException.
     */
    public JSAPException() {
        super();
    }

    /**
     * Creates a new JSAPException with the specified message.
     * @param msg the message for this JSAPException.
     */
    public JSAPException(String msg) {
        super(msg);
    }

    /**
     * Creates a new JSAPException encapsulating the specified Throwable.
     * @param cause the Throwable to encapsulate.
     */
    public JSAPException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new JSAPException with the specified message encapsulating
     * the specified Throwable.
     * @param msg the message for this JSAPException.
     * @param cause the Throwable to encapsulate.
     */
    public JSAPException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
