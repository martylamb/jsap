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
package com.martiansoftware.jsap.xml;

import java.util.Iterator;
import java.util.List;

import com.martiansoftware.jsap.PropertyStringParser;
import com.martiansoftware.jsap.StringParser;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
class StringParserConfig {
	
	String classname = null;
	List properties = null;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public List getProperties() {
		return properties;
	}

	public void setProperties(List properties) {
		this.properties = properties;
	}

	public StringParser getConfiguredStringParser() {
		try {
			StringParser result = null;
			if (classname.indexOf('.') >= 0) {
				result = (StringParser) Class.forName(classname).newInstance();
			} else {
				result = (StringParser) Class.forName("com.martiansoftware.jsap.stringparsers." + classname).newInstance();
			}
			if ((properties != null) && (properties.size() > 0)) {
				PropertyStringParser p = (PropertyStringParser) result;
				for (Iterator i = properties.iterator(); i.hasNext(); ) {
					Property property = (Property) i.next();
					p.setProperty(property.getName(), property.getValue());
				}
			}
			return (result);
		} catch (Throwable t) {
			throw (new RuntimeException("Unable to create StringParser " + classname + ": " + t.getMessage(), t));
		}
	}

}
