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

import java.util.ArrayList;

/**
 * Sends a JSAPConfig to System.out as xml (for test purposes)
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TryDumpXML {

	public static void main(String[] args) throws Exception {
		JSAPXStream jsx = new JSAPXStream();
		JSAPConfig jc = new JSAPConfig();
		
		FlaggedOptionConfig foc = new FlaggedOptionConfig();
		foc.setId("flagged");
		foc.setShortFlag('f');
		foc.setHelp("This flag does something, but I'm not sure what.");
		jc.addParameter(foc);
		
		UnflaggedOptionConfig uoc = new UnflaggedOptionConfig();
		uoc.setId("unflagged");
		uoc.setGreedy(true);
		StringParserConfig spc = new StringParserConfig();
		spc.setClassname("DateStringParser");
		ArrayList props = new java.util.ArrayList();
		Property p = new Property();
		p.setName("DateFormat");
		p.setValue("MM/dd/yyyy");
		props.add(p);
		p = new Property();
		p.setName("Another Property");
		p.setValue("123");
		props.add(p);
		spc.setProperties(props);
		uoc.setStringParser(spc);
		
		jc.addParameter(uoc);
		System.out.println(jsx.toXML(jc));
		
//		JSAP jsap = jc.getJSAP();
//		JSAPResult result = jsap.parse("-f abc");
//		System.out.println(result.getString("flagged"));
	}
}
