/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

import com.martiansoftware.jsap.stringparsers.BooleanStringParser;
import com.martiansoftware.jsap.stringparsers.StringStringParser;
import com.martiansoftware.jsap.stringparsers.EnumeratedStringParser;
import java.util.ArrayList;

/**
 * An ExtenedSwitch is a parameter that has something in comming with a Switch,
 * i.e., its presence or absence is significant, but different from a "pure"
 * Switch it can have an additional value prefixed by a ':' sign that qualifies
 * this Switch.
 * <p>
 * The following possibilities are provided for ExtendedSwitches:<br>
 * "-s:value" is an ExtendedSwitch with only one qualifying value.
 * Depending on the constructor parameter (see constructor javadoc)
 * this value can be an arbitray string without spaces or with spaces
 * if the value is surrounded by '"' signs. An example is -d:"C:\Program Files\test".<br>
 * On the other hand this value can be a predifined name provided by the programmer in
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
public final class QualifiedSwitch extends Switch {

    /**
     * The current short flag for this UnflaggedOption.  
         * Default is JSAP.NO_SHORTFLAG.
     */
    private char shortFlag = JSAP.NO_SHORTFLAG;

    /**
     * The current long flag for this UnflaggedOption.  
         *Default is JSAP.NO_LONGFLAG.
     */
    private String longFlag = JSAP.NO_LONGFLAG;

    private String qualifyingValues; // the qualifying values that are allowed for the switch
    boolean checkEnumeratedValues = false; // tells the parser if the qualifyingValues are enumerated values
    private ArrayList result = new ArrayList(); // takes the parse results

    /**
     * Creates a new ExtendSwitch with the specified unique ID
     * and a qualifying value.
     * 
     * @param id the unique ID for this QualifiedSwitch.
     * @param values the qualifying values; if the format is "<value>" that value
     *               can be an aritrary string without spaces or with spaces
     *               if the value is surrounded by '"' in the command line<br> 
     *               if the format is "value1", "value1;" or "value1;value2;..valuen"
     *               than only one or these predifined values is allowed to appear in the command line.
     */
    public QualifiedSwitch(String id, String values) {
        super(id);
        setDefault("FALSE");
        qualifyingValues = values;
		if (qualifyingValues.startsWith("<") && !qualifyingValues.endsWith(">")) {
			throw new IllegalArgumentException("Qualifying value for a switch starts with '<' but does not end with '>'");
		}
		if (qualifyingValues.endsWith(">") && !qualifyingValues.startsWith("<")) {
			throw new IllegalArgumentException("Qualifying value for a switch ends with '>' but does not start with '<'");
		}
        checkEnumeratedValues = !(qualifyingValues.startsWith("<") && !qualifyingValues.endsWith(">"));
    }

    /**
     * A shortcut constructor that creates a new Switch and configures all of 
     * its settings.
     * 
     * @param id the unique ID for this Switch.         
     * @param shortFlag the short flag for this Switch (may be set to 
     *                  JSAP.NO_SHORTFLAG for none).
     * @param longFlag the long flag for this Switch (may be set to 
     *                 JSAP.NO_LONGFLAG for none).
     * @param values the qualifying value; if the format is "value" that value
     *               can be an aritrary string without spaces or with spaces
     *               if the value is surrounded by '"' in the command line<br> 
     *               if the format is "value1", "value1;" or "value1;value2;..valuen"
     *               than only one or these predifined values is allowed to appear in the command line.
     * */
    public QualifiedSwitch(String id, char shortFlag, String longFlag, String values) {
        this(id, values);
        setShortFlag(shortFlag);
        setLongFlag(longFlag);
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
    protected final ArrayList parse(String arg) throws ParseException {
        if (arg != null && arg.startsWith(":")) {
            result.add(Boolean.TRUE); // flag is present
            if (checkEnumeratedValues) {
                result.add((new EnumeratedStringParser(qualifyingValues)).parse(arg.substring(1))); // ad the value allowed
            }
            else {
                result.add((new StringStringParser()).parse(arg.substring(1))); // add arbitrary string value
            }
        }
        else {
            result.add((new BooleanStringParser()).parse(arg));
        }
        return result;
    }

    /**
     * Returns usage instructions for this Switch.
     * @return usage instructions for this Switch based upon its current 
     * configuration.
     */
    public String getSyntax() {
        StringBuffer buf = new StringBuffer();
        boolean shortFlag = false;
        buf.append("[");
        if (getShortFlag() != JSAP.NO_SHORTFLAG) {
            buf.append("-" + getShortFlag());
            shortFlag = true;
        }
        if (getLongFlag() != JSAP.NO_LONGFLAG) {
            if (shortFlag) {
                buf.append("|");
            }
            buf.append("--" + getLongFlag());
        }
        buf.append("[:");
        if (checkEnumeratedValues) {
            buf.append(qualifyingValues.replace(';', '|'));
        }
        else {
            buf.append(qualifyingValues);
        }
        buf.append("]]");
        return(buf.toString());
    }
}
