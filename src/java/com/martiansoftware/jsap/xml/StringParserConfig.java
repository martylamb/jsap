package com.martiansoftware.jsap.xml;

import java.util.List;

/**
 * @author mlamb
 */
public class StringParserConfig {
	
	String classname = null;
	List properties = null;
	/**
	 * @return Returns the classname.
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * @param classname The classname to set.
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/**
	 * @return Returns the properties.
	 */
	public List getProperties() {
		return properties;
	}
	/**
	 * @param properties The properties to set.
	 */
	public void setProperties(List properties) {
		this.properties = properties;
	}
}
