/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;
import java.math.BigInteger;

/**
 * A StringParser for parsing BigIntegers.  The parse() method delegates the
 * actual
 * parsing to BigInteger's constructor.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.BigInteger
 */
public class BigIntegerStringParser extends StringParser {

    /**
     * Creates a new BigIntegerStringParser.
     */
    public BigIntegerStringParser() {
        super();
    }

    /**
    * Parses the specified argument into a BigInteger.  This method simply
    * delegates
    * the parsing to <code>new BigInteger(String)</code>.  If BigInteger
    * throws a
    * NumberFormatException, it is encapsulated into a ParseException and
    * re-thrown.
    *
    * @param arg the argument to parse
    * @return a BigInteger object with the value contained in the specified
    * argument.
    * @throws ParseException if <code>new BigInteger(arg)</code> throws a
    * NumberFormatException.
    * @see java.lang.BigInteger
    * @see com.martiansoftware.jsap.StringParser#parse(String)
    */
    public Object parse(String arg) throws ParseException {
        BigInteger result = null;
        try {
            result = new BigInteger(arg);
        } catch (NumberFormatException e) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a BigInteger.",
                    e));
        }
        return (result);
    }
}
