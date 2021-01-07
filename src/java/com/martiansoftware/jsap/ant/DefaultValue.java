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
 * Provides a means of specifying default values for the jsap task containing
 * &lt;default&gt; elements.
 * For detailed information on using the jsap task, see the documentation for
 * JSAPAntTask.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see JSAPAntTask
 */
public class DefaultValue {

    /**
     * The default value.
     */
    private String value = null;

    /**
     * Sets the default value.
     * @param text the default value.
     */
    public void addText(String text) {
        this.value = text;
    }

    /**
     * Returns the default value.
     * @return the default value.
     */
    public String getValue() {
        return (value);
    }
}
