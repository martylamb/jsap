package com.martiansoftware.jsap.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlamb
 */
public class AbstractParameterConfig {

	private String id = null;
	private String help = null;
	private String usage = null;
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
}
