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

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @see com.martiansoftware.jsap.JSAP#JSAP(URL)
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class JSAPConfig {

	private java.util.List parameters = new java.util.ArrayList();
	private String help = null;
	private String usage = null;
	
	/**
	 * Loads a JSAP configuration from the xml at the specified URL, and configures
	 * the specified JSAP object accordingly
	 * @param jsapToConfigure the JSAP to configure
	 * @param jsapXML the configuration
	 * @throws IOException if an I/O error occurs
	 * @throws JSAPException if the configuration is not valid
	 */
	public static void configure(JSAP jsapToConfigure, URL jsapXML) throws IOException, JSAPException {
		JSAPXStream jsx = new JSAPXStream();
		InputStreamReader in = new InputStreamReader(jsapXML.openStream());

		JSAPConfig config = (JSAPConfig) jsx.fromXML(in);
		in.close();
		
		for (Iterator i = config.parameters(); i.hasNext();) {
			ParameterConfig cfg = (ParameterConfig) i.next();
			jsapToConfigure.registerParameter(cfg.getConfiguredParameter());
		}
		jsapToConfigure.setHelp(config.getHelp());
		jsapToConfigure.setUsage(config.getUsage());
	}
	
	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	public void addParameter(ParameterConfig config) {
		parameters.add(config);
	}
	
	public Iterator parameters() {
		return (parameters.iterator());
	}
	

}
