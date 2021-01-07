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

import java.util.Properties;

/**
 * A {@link com.martiansoftware.jsap.StringParser} subclass that provides a means for setting/getting properties.
 * This
 * is intended to support StringParsers that might requires some configuration,
 * such
 * as DateStringParser (which needs a format String).
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see com.martiansoftware.jsap.stringparsers.DateStringParser
 */
public abstract class PropertyStringParser extends StringParser {

    /**
     * This PropertyStringParser's properties.
     */
    private Properties properties = null;

    /**
     * Replaces all properties in this PropertyStringParser with the
     * specified Properties.
     * @param properties the new properties for this PropertyStringParser.
     */
    private void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Returns the internal Properties object.  If none is currently defined, a
     * new Properties object is created.
     * @return the internal Properties object.  If none is currently defined, a
     * new Properties object is created.
     */
    private Properties getProperties() {
        if (properties == null) {
            setProperties(new Properties());
        }
        return (properties);
    }

    /**
     * Sets the property with the specified key to the specified value.
     * @param key the key of the property to set.
     * @param value the value to associated with the specified key.
     * @see java.util.Properties#setProperty(String,String)
     */
    public void setProperty(String key, String value) {
        Properties properties = getProperties();
        properties.setProperty(key, value);
    }

    /**
     * Returns the property associated with the specified key, or null if no
     * such
     * property exists.
     * @param key the key of the desired property
     * @return the property associated with the specified key, or null if no
     * such
     * property exists.
     */
    public String getProperty(String key) {
        return (getProperties().getProperty(key));
    }
    
    /**
     * Returns the property associated with the specified key, or the specified
     * default value if no such property exists.
     * @param key the key of the desired property
     * @param defaultValue the default value to return if no such property exists
     * @return the requested property, or the specified default value if no such property exists.
     */
    public String getProperty(String key, String defaultValue) {
    	String result = getProperty(key);
    	return (result == null ? defaultValue : result);
    }

}
