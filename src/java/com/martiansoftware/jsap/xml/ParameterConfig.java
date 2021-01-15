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

import com.martiansoftware.jsap.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
abstract class ParameterConfig {

	private String id = null;
	private String help = null;
	private String usageName = null;
	private java.util.List defaults = null;
	
	public ParameterConfig() {
		defaults = new ArrayList();
	}
	
	public void addDefault(String defaultValue) {
		getDefaults().add(defaultValue);
	}
	
	public List getDefaults() {
		if (defaults == null) {
			defaults = new ArrayList();
		}
		return (defaults);
	}
	
	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsageName() {
		return usageName;
	}

	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}
	
	protected void configure(Parameter param) {
		param.setHelp(getHelp());
		for (Iterator i = getDefaults().iterator(); i.hasNext();) {
			String def = (String) i.next();
			param.addDefault(def);
		}
	}
	public abstract Parameter getConfiguredParameter();
}
