package com.martiansoftware.jsap.xml;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class JSAPXStream extends XStream {

	public JSAPXStream() {
		super();
		alias("JSAP", JSAPConfig.class);
		alias("FlaggedOption", FlaggedOptionConfig.class);
		alias("UnflaggedOption", UnflaggedOptionConfig.class);
		alias("property", Property.class);
		alias("QualifiedSwitch", QualifiedSwitchConfig.class);
		alias("Switch", SwitchConfig.class);
	}

}
