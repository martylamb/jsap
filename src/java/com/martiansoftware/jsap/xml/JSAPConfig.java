package com.martiansoftware.jsap.xml;

import java.util.Iterator;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;

/**
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class JSAPConfig {

	private java.util.List parameters = new java.util.ArrayList();
	private String help = null;
	private String usage = null;
	
	/**
	 * @return Returns the help.
	 */
	public String getHelp() {
		return help;
	}
	/**
	 * @param help The help to set.
	 */
	public void setHelp(String help) {
		this.help = help;
	}
	/**
	 * @return Returns the usage.
	 */
	public String getUsage() {
		return usage;
	}
	/**
	 * @param usage The usage to set.
	 */
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
