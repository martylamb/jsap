/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;

/**
 * <p>A StringParser for parsing Booleans.  This StringParser is also used
 * internally
 * by the Switch class.</p>
 *
 * When parsing, the following arguments are interpreted as TRUE:<br>
 * <ul>
 *         <li>null</i>
 *         <li>"t" (case-insensitive)</li>
 *         <li>"true" (case-insensitive)</li>
 *         <li>"y" (case-insensitive)</li>
 *         <li>"yes" (case-insensitive)</li>
 *         <li>"1"</li>
 * <ul><br>
 * The following arguments are interpreted as FALSE:<br>
 * <ul>
 *         <li>"f" (case-insensitive)</li>
 *         <li>"false" (case-insensitive)</li>
 *         <li>"n" (case-insensitive)</li>
 *         <li>"no" (case-insensitive)</li>
 *         <li>"0"</li>
 * </ul><br>
 * <br>
 * All other input throws a ParseException.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Boolean
 */
public class BooleanStringParser extends StringParser {

    /**
     *  Creates a new BooleanStringParser
     */
    public BooleanStringParser() {
        super();
    }

    /**
     * Converts the specified argument into a Boolean.<br>
         * <br>
     * When parsing, the following arguments are interpreted as TRUE:<br>
     * <ul>
     *         <li>null</i>
     *         <li>"t" (case-insensitive)</li>
     *         <li>"true" (case-insensitive)</li>
     *         <li>"y" (case-insensitive)</li>
     *         <li>"yes" (case-insensitive)</li>
     *         <li>"1"</li>
     * <ul><br>
     * The following arguments are interpreted as FALSE:<br>
     * <ul>
     *         <li>"f" (case-insensitive)</li>
     *         <li>"false" (case-insensitive)</li>
     *         <li>"n" (case-insensitive)</li>
     *         <li>"no" (case-insensitive)</li>
     *         <li>"0"</li>
     * </ul><br>
     * <br>
     * All other input throws a ParseException.
     * @param arg the argument to convert to a Boolean.
     * @return a Boolean as described above.
     * @throws ParseException if none of the above cases are matched.
     */
    public Object parse(String arg) throws ParseException {
        boolean result = false;
        if ((arg == null)
            || arg.equalsIgnoreCase("t")
            || arg.equalsIgnoreCase("true")
            || arg.equalsIgnoreCase("y")
            || arg.equalsIgnoreCase("yes")
            || arg.equals("1")) {
            result = true;
        } else if (
            arg.equalsIgnoreCase("f")
                || arg.equalsIgnoreCase("false")
                || arg.equalsIgnoreCase("n")
                || arg.equalsIgnoreCase("no")
                || arg.equals("0")) {
            result = false;
        } else {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a boolean value."));
        }
        return (new Boolean(result));
    }
}
