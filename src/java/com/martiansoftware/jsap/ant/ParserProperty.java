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

package com.martiansoftware.jsap.ant;

/**
 * Stores/provides a single name/value pair for a PropertyStringParser
 * For detailed information on using the jsap task, see the documentation for
 * JSAPAntTask.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see JSAPAntTask
 * @see com.martiansoftware.jsap.PropertyStringParser
 */
public class ParserProperty {

    /**
     * The name of this property.
     */
    private String name = null;

    /**
     * The value of this property.
     */
    private String value = null;

    /**
     * Sets the name of this property.
     * @param name the name of this property.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this property.
     * @return the name of this property.
     */
    public String getName() {
        return (name);
    }

    /**
     * Sets the value of this property.
     * @param value the value of this property.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the value of this property.
     * @return the value of this property.
     */
    public String getValue() {
        return (value);
    }

}
