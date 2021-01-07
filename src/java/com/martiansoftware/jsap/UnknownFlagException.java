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
 * An exception indicating that a unknown flag has been specified.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.Flagged
 */
public class UnknownFlagException extends JSAPException {

    /**
     * The unknown flag that was encountered.
     */
    private String flag = null;

    /**
     * Creates a new UnknownFlagException referencing the specified parameter.
     * @param flag the unknown flag that was encountered.
     */
    public UnknownFlagException(String flag) {
        super("Unknown flag '" + flag + "'.");
        this.flag = flag;
    }

    /**
     * Creates a new UnknownFlagException referencing the specified parameter.
     * @param flag the unknown flag that was encountered.
     */
    public UnknownFlagException(Character flag) {
        super("Unknown flag '" + flag + "'.");
        this.flag = flag.toString();
    }

    /**
     * Returns the unknown flag that was encountered.
     * @return the unknown flag that was encountered.
     */
    public String getFlag() {
        return (this.flag);
    }

}
