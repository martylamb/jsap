/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */
package com.martiansoftware.jsap.xml;

import java.util.Iterator;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class JSAPConfig {

	private java.util.List parameters = new java.util.ArrayList();
	private String help = null;
	private String usage = null;
	
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
	
	public void addParameter(AbstractParameterConfig config) {
		parameters.add(config);
	}
	
	public Iterator parameters() {
		return (parameters.iterator());
	}
	
	public JSAP getJSAP() throws JSAPException {
		JSAP result = new JSAP();
		for (Iterator i = parameters(); i.hasNext();) {
			AbstractParameterConfig cfg = (AbstractParameterConfig) i.next();
			System.out.println(cfg.getClass());
			result.registerParameter(cfg.getConfiguredParameter());
		}
		result.setHelp(getHelp());
		result.setUsage(getUsage());
		return (result);
	}
}
