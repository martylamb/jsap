package com.martiansoftware.jsap.xml;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;

/**
 * @author mlamb
 */
public abstract class FlaggedConfig extends AbstractParameterConfig {
	
	private char shortFlag = JSAP.NO_SHORTFLAG;
	private String longFlag = JSAP.NO_LONGFLAG;
	
	/**
	 * @return Returns the longFlag.
	 */
	public String getLongFlag() {
		return longFlag;
	}
	
	/**
	 * @param longFlag The longFlag to set.
	 */
	public void setLongFlag(String longFlag) {
		this.longFlag = longFlag;
	}
	/**
	 * @return Returns the shortFlag.
	 */
	public char getShortFlag() {
		return shortFlag;
	}
	/**
	 * @param shortFlag The shortFlag to set.
	 */
	public void setShortFlag(char shortFlag) {
		this.shortFlag = shortFlag;
	}

}
