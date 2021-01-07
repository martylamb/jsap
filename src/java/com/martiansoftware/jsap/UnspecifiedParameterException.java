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

/** An exception thrown when an argument that requires a conversion
 * (e.g., an integer) has no associated value, but it is retrieved
 * by means of a type-specified method (e.g., {@link com.martiansoftware.jsap.JSAPResult#getInt(String)}).
 * 
 * @author Sebastiano Vigna
 */
public class UnspecifiedParameterException extends RuntimeException {

    /**
     * The unique ID of the parameter whose retrieval was attempted.
     */
    private String id = null;

    /** Creates a new {@link UnspecifiedParameterException} referencing the
     * specified parameter.
     * @param paramID the unique ID of the parameter whose retrieval was attempted.
     */
    public UnspecifiedParameterException(String paramID) {
        super("Parameter '" + paramID + "' has no associated value.");
        this.id = paramID;
    }

    /**
     * Returns the unique ID of the parameter whose retrieval was attempted.
     * @return the unique ID of the parameter whose retrieval was attempted.
     */
    public String getID() {
        return (this.id);
    }
}
