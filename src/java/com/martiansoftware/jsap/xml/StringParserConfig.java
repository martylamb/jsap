package com.martiansoftware.jsap.xml;

import java.util.Iterator;
import java.util.List;

import com.martiansoftware.jsap.PropertyStringParser;
import com.martiansoftware.jsap.StringParser;

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

	public StringParser getConfiguredStringParser() {
		try {
			StringParser result = null;
			if (classname.indexOf('.') >= 0) {
				result = (StringParser) Class.forName(classname).newInstance();
			} else {
				result = (StringParser) Class.forName("com.martiansoftware.jsap.stringparsers." + classname).newInstance();
			}
			if (properties.size() > 0) {
				PropertyStringParser p = (PropertyStringParser) result;
				for (Iterator i = properties.iterator(); i.hasNext(); ) {
					Property property = (Property) i.next();
					p.setProperty(property.getName(), property.getValue());
				}
			}
			return (result);
		} catch (Throwable t) {
			throw (new RuntimeException("Unable to create StringParser " + classname + ": " + t.getMessage(), t));
		}
	}

}
