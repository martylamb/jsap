package com.martiansoftware.jsap.xml;

import com.martiansoftware.jsap.AbstractParameter;
import com.martiansoftware.jsap.UnflaggedOption;
import com.martiansoftware.jsap.JSAP;

/**
 * @author mlamb
 */
public class UnflaggedOptionConfig extends AbstractParameterConfig {
	
	private boolean greedy = JSAP.NOT_GREEDY;
	private boolean list = JSAP.NOT_LIST;
	private char listSeparator = JSAP.DEFAULT_LISTSEPARATOR;
	private StringParserConfig stringParser = null;
	private boolean required = JSAP.NOT_REQUIRED;
	private String usageName = null;
	
	public UnflaggedOptionConfig() {
		super();
	}
	/**
	 * @return Returns the greedy.
	 */
	public boolean isGreedy() {
		return greedy;
	}
	/**
	 * @param greedy The greedy to set.
	 */
	public void setGreedy(boolean greedy) {
		this.greedy = greedy;
	}
	/**
	 * @return Returns the list.
	 */
	public boolean isList() {
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(boolean list) {
		this.list = list;
	}
	/**
	 * @return Returns the listSeparator.
	 */
	public char getListSeparator() {
		return listSeparator;
	}
	/**
	 * @param listSeparator The listSeparator to set.
	 */
	public void setListSeparator(char listSeparator) {
		this.listSeparator = listSeparator;
	}
	/**
	 * @return Returns the required.
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param required The required to set.
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
	/**
	 * @return Returns the stringParser.
	 */
	public StringParserConfig getStringParser() {
		return stringParser;
	}
	/**
	 * @param stringParser The stringParser to set.
	 */
	public void setStringParser(StringParserConfig stringParser) {
		this.stringParser = stringParser;
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
	
	public AbstractParameter getConfiguredParameter() {
		UnflaggedOption result = new UnflaggedOption(getId());
		super.configure(result);
		result.setGreedy(isGreedy());
		result.setListSeparator(getListSeparator());
		result.setList(isList());
		result.setRequired(isRequired());
		result.setUsageName(getUsageName());

		if (stringParser != null) {
			result.setStringParser(stringParser.getConfiguredStringParser());
		}
		
		return (result);
	}
}
