/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;
import java.math.BigDecimal;

/**
 * A StringParser for parsing BigDecimals.  The parse() method delegates the
 * actual
 * parsing to BigDecimal's constructor.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.BigDecimal
 */
public class BigDecimalStringParser extends StringParser {

    /**
     * Creates a new BigDecimalStringParser.
     */
    public BigDecimalStringParser() {
        super();
    }

    /**
     * Parses the specified argument into a BigDecimal.  This method simply
     * delegates
     * the parsing to <code>new BigDecimal(String)</code>.  If BigDecimal
     * throws a
     * NumberFormatException, it is encapsulated into a ParseException and
     * re-thrown.
     *
     * @param arg the argument to parse
     * @return a BigDecimal object with the value contained in the specified
     * argument.
     * @throws ParseException if <code>new BigDecimal(arg)</code> throws a
     * NumberFormatException.
     * @see java.lang.BigDecimal
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        BigDecimal result = null;
        try {
            result = new BigDecimal(arg);
        } catch (NumberFormatException e) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a BigDecimal.",
                    e));
        }
        return (result);
    }
}
