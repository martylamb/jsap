package com.martiansoftware.jsap.xml;

import com.martiansoftware.jsap.AbstractParameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mlamb
 */
public abstract class AbstractParameterConfig {

	private String id = null;
	private String help = null;
	private String usageName = null;
	private java.util.List defaults = null;
	
	public AbstractParameterConfig() {
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the usageName.
	 */
	public String getUsageName() {
		return usageName;
	}
	/**
	 * @param usageName The usageName to set.
	 */
	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}
	
	protected void configure(AbstractParameter param) {
		param.setHelp(getHelp());
		for (Iterator i = getDefaults().iterator(); i.hasNext();) {
			String def = (String) i.next();
			param.addDefault(def);
		}
	}
	public abstract AbstractParameter getConfiguredParameter();
}
