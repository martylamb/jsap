/*
 * Created on Jun 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.martiansoftware.jsap.xml;

import java.util.ArrayList;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;

/**
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TryXML {

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
		
		JSAP jsap = jc.getJSAP();
		JSAPResult result = jsap.parse("-f abc");
		System.out.println(result.getString("flagged"));
	}
}
