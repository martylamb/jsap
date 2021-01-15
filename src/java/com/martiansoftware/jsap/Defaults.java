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

import java.util.HashMap;
import java.util.Iterator;

/**
 * Stores a collection of default values, associated with their respective
 * parameters by the parameters' unique IDs.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class Defaults {

    /**
     * Hashmap associating arrays of Strings (containing default values) with
     * parameter IDs.
     */
    private HashMap defaults = null;

    /**
     *  Creates a new, empty Defaults object.
     */
    public Defaults() {
        defaults = new HashMap();
    }

    /**
     * Sets a single default value for the parameter with the specified ID.
     * This replaces any default values currently associated with the specified
     * parameter if any exist.
     * @param paramID the unique ID of the parameter for which the specified
     * value is the default.
     * @param defaultValue the new default value for the specified parameter.
     */
    public void setDefault(String paramID, String defaultValue) {
        String[] newArray = new String[1];
        newArray[0] = defaultValue;
        setDefault(paramID, newArray);
    }

    /**
     * Sets an array of default values for the parameter with the specified ID.
     * These replace any default values currently associated with the specified
     * parameter if any exist.
     * @param paramID the unique ID of the parameter for which the specified
     * values are the defaults.
     * @param defaultValue the new default values for the specified parameter.
     */
    public void setDefault(String paramID, String[] defaultValue) {
        defaults.put(paramID, defaultValue);
    }

    /**
     * Adds a single default value to any that might already be defined for the
     * parameter with the the specified ID.
     * @param paramID the unique ID of the parameter for which the specified
     * value is an additional default.
     * @param defaultValue the default value to add to the specified parameter.
     */
    public void addDefault(String paramID, String defaultValue) {
        String[] newArray = new String[1];
        newArray[0] = defaultValue;
        addDefault(paramID, newArray);
    }

    /**
     * Adds an array of default values to any that might already be defined for
     * the parameter with the the specified ID.
     * @param paramID the unique ID of the parameter for which the specified
     * value is an additional default.
     * @param defaultValue the default values to add to the specified parameter.
     */
    public void addDefault(String paramID, String[] defaultValue) {
        String[] curDefault = getDefault(paramID);
        if (curDefault == null) {
            curDefault = new String[0];
        }
        String[] newDefault =
            new String[curDefault.length + defaultValue.length];
        int index = 0;
        for (int i = 0; i < curDefault.length; ++i) {
            newDefault[index] = curDefault[i];
            ++index;
        }
        for (int i = 0; i < defaultValue.length; ++i) {
            newDefault[index] = defaultValue[i];
            ++index;
        }
        setDefault(paramID, newDefault);
    }

    /**
     * Sets a single default value for the parameter with the specified ID if
     * and only if the specified parameter currently has no default values.
     * @param paramID the unique ID of the parameter for which the specified
     * value is the default.
     * @param defaultValue the new default value for the specified parameter.
     */
    protected void setDefaultIfNeeded(String paramID, String defaultValue) {
        if (!defaults.containsKey(paramID)) {
            setDefault(paramID, defaultValue);
        }
    }

    /**
     * Sets an array of default values for the parameter with the specified ID
     * if and only if the specified parameter currently has no default values.
     * @param paramID the unique ID of the parameter for which the specified
     * value is the default.
     * @param defaultValue the new default values for the specified parameter.
     */
    protected void setDefaultIfNeeded(String paramID, String[] defaultValue) {
        if (!defaults.containsKey(paramID)) {
            setDefault(paramID, defaultValue);
        }
    }

    /**
     * Returns an array of the default values defined for the parameter with
     * the specified ID, or null if no default values are defined.
     * @param paramID the unique ID of the parameter for which default values
     * are desired.
     * @return an array of the default values defined for the parameter with
     * the specified ID, or null if no default values are defined.
     */
    public String[] getDefault(String paramID) {
        return ((String[]) defaults.get(paramID));
    }

    /**
     * Returns an Iterator over the unique IDs of all parameters with defaults
     * defined in this Defaults object.
     * @return an Iterator over the unique IDs of all parameters with defaults
     * defined in this Defaults object.
     * @see java.util.Iterator
     */
    public Iterator idIterator() {
        return (defaults.keySet().iterator());
    }

}
