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
 * An exception indicating that a required parameter was missing from the
 * supplied arguments and defaults.
 *
 * @see FlaggedOption#setAllowMultipleDeclarations(boolean)
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.Option#required()
 */
public class RequiredParameterMissingException extends JSAPException {

    /**
     * The unique ID of the parameter that was missing.
     */
    private String id = null;

    /**
     * Creates a new RequiredParameterMissingException referencing the
     * specified parameter.
     * @param paramID the unique ID of the parameter that was missing.
     */
    public RequiredParameterMissingException(String paramID) {
        super("Parameter '" + paramID + "' is required.");
        this.id = paramID;
    }

    /**
     * Returns the unique ID of the parameter that was missing.
     * @return the unique ID of the parameter that was missing.
     */
    public String getID() {
        return (this.id);
    }

}
