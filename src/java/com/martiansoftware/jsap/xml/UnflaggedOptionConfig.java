package com.martiansoftware.jsap.xml;

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
}
