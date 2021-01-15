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

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.Parameter;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
class FlaggedOptionConfig extends FlaggedConfig {

	private boolean allowMultipleDeclarations = JSAP.NO_MULTIPLEDECLARATIONS;
	private boolean list = JSAP.NOT_LIST;
	private char listSeparator = JSAP.DEFAULT_LISTSEPARATOR;
	private StringParserConfig stringParser = null;
	private boolean required = JSAP.NOT_REQUIRED;
	public FlaggedOptionConfig() {
		super();
	}

	public boolean allowMultipleDeclarations() {
		return allowMultipleDeclarations;
	}

	public void setAllowMultipleDeclarations(boolean allowMultipleDeclarations) {
		this.allowMultipleDeclarations = allowMultipleDeclarations;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public char getListSeparator() {
		return listSeparator;
	}

	public void setListSeparator(char listSeparator) {
		this.listSeparator = listSeparator;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public StringParserConfig getStringParser() {
		return stringParser;
	}

	public void setStringParser(StringParserConfig stringParser) {
		this.stringParser = stringParser;
	}
	
	protected void configure(FlaggedOption option) {
		super.configure(option);
		option.setUsageName(getUsageName());

		option.setShortFlag(getShortFlag());
		option.setLongFlag(getLongFlag());
		option.setAllowMultipleDeclarations(allowMultipleDeclarations());
		option.setListSeparator(getListSeparator());
		option.setList(isList());
		option.setRequired(isRequired());

		if (stringParser != null) {
			option.setStringParser(stringParser.getConfiguredStringParser());
		}
	}
	
	public Parameter getConfiguredParameter() {
		FlaggedOption result = new FlaggedOption(getId());
		configure(result);
		return (result);
	}
}
