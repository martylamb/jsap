package com.martiansoftware.jsap.xml;

import java.util.Iterator;

import com.martiansoftware.jsap.AbstractParameter;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;

/**
 * @author mlamb
 */
public class FlaggedOptionConfig extends FlaggedConfig {

	private boolean allowMultipleDeclarations = JSAP.NO_MULTIPLEDECLARATIONS;
	private boolean list = JSAP.NOT_LIST;
	private char listSeparator = JSAP.DEFAULT_LISTSEPARATOR;
	private StringParserConfig stringParser = null;
	private boolean required = JSAP.NOT_REQUIRED;
	public FlaggedOptionConfig() {
		super();
	}
	/**
	 * @return Returns the allowMultipleDeclarations.
	 */
	public boolean allowMultipleDeclarations() {
		return allowMultipleDeclarations;
	}
	/**
	 * @param allowMultipleDeclarations The allowMultipleDeclarations to set.
	 */
	public void setAllowMultipleDeclarations(boolean allowMultipleDeclarations) {
		this.allowMultipleDeclarations = allowMultipleDeclarations;
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
	
	protected void configure(FlaggedOption option) {
		super.configure(option);
		option.setUsageName(getUsageName());

		option.setShortFlag(getShortFlag());
		option.setLongFlag(getLongFlag());
		option.setAllowMultipleDeclarations(allowMultipleDeclarations());
		option.setListSeparator(getListSeparator());
		option.setList(isList());
		option.setRequired(isRequired());

		if (stringParser != null) {
			option.setStringParser(stringParser.getConfiguredStringParser());
		}
	}
	
	public AbstractParameter getConfiguredParameter() {
		FlaggedOption result = new FlaggedOption(getId());
		configure(result);
		return (result);
	}
}
