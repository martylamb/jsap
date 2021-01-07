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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Provides support for loading JSAP configurations at runtime
 * via an xml file.  You don't need to access this class directly;
 * instead, use JSAP's constructors that support xml.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
class JSAPXStream extends XStream {

	public JSAPXStream() {
		super(new DomDriver());
		alias("jsap", JSAPConfig.class);
		alias("flaggedOption", FlaggedOptionConfig.class);
		alias("unflaggedOption", UnflaggedOptionConfig.class);
		alias("property", Property.class);
		alias("qualifiedSwitch", QualifiedSwitchConfig.class);
		alias("switch", SwitchConfig.class);
	}

}
