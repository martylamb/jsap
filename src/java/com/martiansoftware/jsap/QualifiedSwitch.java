/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

import com.martiansoftware.jsap.FlaggedOption;

import com.martiansoftware.jsap.stringparsers.BooleanStringParser;
import com.martiansoftware.jsap.stringparsers.StringStringParser;
import com.martiansoftware.jsap.stringparsers.EnumeratedStringParser;
import java.util.List;

/**
 * A QualifiedSwitch is a parameter that has something in common with a Switch,
 * i.e., its presence or absence is significant, but different from a "pure"
 * Switch it can have an additional value prefixed by a ':' sign that qualifies
 * the Switch - making it behave like a FlaggedOption if a value is specified.
 * <p>
 * The following possibilities are provided for ExtendedSwitches:<br>
 * "-s:value" is an ExtendedSwitch with only one qualifying value.
 * Depending on the constructor parameter (see constructor javadoc)
 * this value can be an arbitrary string without spaces or with spaces
 * if the value is surrounded by '"' signs. An example is -d:"C:\Program Files\test".<br>
 * On the other hand this value can be a predefined name provided by the programmer in
 * the constructor, thus only this value is allowed in the command line. An example is
 * "-g:none".
 * <p>
 * "-s:value1|value2|..|valuen according to SUN' s java -verbose[:class|gc|jni] 
 * where the ExtendedSwitch is allowed to take only one of the predefinded values provided
 * ba the programmer in the constructor.<br>
 * For the exact format of defining possible values for ExtendedSwitches see
 * constructor's javadoc.
 * <p>
 * ExtendedSwitches use StringStringParser, BooleanParser and EnumeratedParser internally,
 * so their results can be obtained using several JSAPResult methods:
 * <p>
 * getObjectArray() in order to check first for presence or absence of the QualifiedSwitch,
 * and second, if the QualifiedSwitch is ON, to get the qualifying value.<br>
 * <code>
 * Object[] qualifiedSwitchResult = jsapResult.getObjectArray("id"); 
 * boolean qualifiedSwitchIsOn = ((Boolean)qualifiedSwitchResult[0]).booleanValue();
 * if (qualifiedSwitchIsOn) {
 *    // we have a value
 *    String qualifiedSwitch_Value = (String)qualifiedSwitchResult[1];
 * }
 * </code>
 * <p>
 * Another method is to make use of the fact that QualifiedSwitch extends Switch, so we can code<br>
 * <code>
 * boolean qualifiedSwitchIsOn = jsapResult.getBoolean("id");
 * String qualifiedSwitch_Value = jsapResult.getQualifiedSwitchValue("id"); // may be null is switch is off
 * </code>
 * 
 * @author  Klaus P. Berg, Siemens AG, Munich, Germany
 * @version @@VERSION@@
 * @see com.martiansoftware.jsap.Flagged
 * @see com.martiansoftware.jsap.stringparsers.StringStringParser
 * @see com.martiansoftware.jsap.stringparsers.BooleanStringParser
 * @see com.martiansoftware.jsap.stringparsers.EnumeratedStringParser
 */
public final class QualifiedSwitch extends FlaggedOption {

    /**
     * The current short flag for this UnflaggedOption.  
         * Default is JSAP.NO_SHORTFLAG.
     */
//    private char shortFlag = JSAP.NO_SHORTFLAG;

    /**
     * The current long flag for this UnflaggedOption.  
         *Default is JSAP.NO_LONGFLAG.
     */
//    private String longFlag = JSAP.NO_LONGFLAG;

//    private String qualifyingValues; // the qualifying values that are allowed for the switch
//    boolean checkEnumeratedValues = false; // tells the parser if the qualifyingValues are enumerated values

	
	/**
	 * A shortcut constructor that creates a new QualifiedSwitch and configures
	 * its most commonly used settings.
	 * @param id the unique ID for this FlaggedOption.
	 * @param stringParser the StringParser this FlaggedOption should use.
	 * @param defaultValue the default value for this FlaggedOption (may be
	 * null).
	 * @param required if true, this FlaggedOption is required.
	 * @param shortFlag the short flag for this option (may be set to
	 * JSAP.NO_SHORTFLAG for none).
	 * @param longFlag the long flag for this option (may be set to
	 * JSAP.NO_LONGFLAG for none).
	 */
	public QualifiedSwitch(
			String id,
			StringParser stringParser,
			String defaultValue,
			boolean required,
			char shortFlag,
			String longFlag) {
	
			super(id, stringParser, defaultValue, required, shortFlag, longFlag);
	}


    /**
     * A shortcut constructor that creates a new QualifiedSwitch
     * 
     * @param id the unique ID for this QualifiedSwitch.         
     */
    public QualifiedSwitch(String id) {
    	super(id);
    }   

    /**
     * Creates a new QualifiedSwitchValuesParser to which it delegates the parsing of 
     * the specified argument.
     * The result is as follows:<br>
     *  ArrayList[0] contains a single Boolean that tells the
     *  user whether this QualifiedSwitch is present or not.<br>
     *  ArrayList[1] is a string that contains the qualifying value.
     * 
     * @param arg the argument to parse.
     * @return an ArrayList containing the parse results.
     * @throws ParseException if the specified parameter cannot be parsed.
     */
//    protected final List parse(String arg) throws ParseException {
//    	List result = new java.util.ArrayList();
//        if (arg != null && arg.startsWith(":")) {
//            result.add(Boolean.TRUE); // flag is present
//            if (checkEnumeratedValues) {
//                result.add((new EnumeratedStringParser(qualifyingValues)).parse(arg.substring(1))); // ad the value allowed
//            }
//            else {
//                result.add((new StringStringParser()).parse(arg.substring(1))); // add arbitrary string value
//            }
//        }
//        else {
//            result.add((new BooleanStringParser()).parse(arg));
//        }
//        return result;
//    }

//    /**
//     * Returns usage instructions for this Switch.
//     * @return usage instructions for this Switch based upon its current 
//     * configuration.
//     */
//    public String getSyntax() {
//        StringBuffer buf = new StringBuffer();
//        boolean shortFlag = false;
//        buf.append("[");
//        if (getShortFlag() != JSAP.NO_SHORTFLAG) {
//            buf.append("-" + getShortFlag());
//            shortFlag = true;
//        }
//        if (getLongFlag() != JSAP.NO_LONGFLAG) {
//            if (shortFlag) {
//                buf.append("|");
//            }
//            buf.append("--" + getLongFlag());
//        }
//        buf.append("[:");
//        if (checkEnumeratedValues) {
//            buf.append(qualifyingValues.replace(';', '|'));
//        }
//        else {
//            buf.append(qualifyingValues);
//        }
//        buf.append("]]");
//        return(buf.toString());
//    }
}
