/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;

/**
 * A StringParser for parsing Bytes.  The parse() method delegates the actual
 * parsing to Byte.decode(String).
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Byte
 */
public class ByteStringParser extends StringParser {

    /**
     * Creates a new ByteStringParser.
     */
    public ByteStringParser() {
        super();
    }

    /**
     * Parses the specified argument into a Byte.  This method simply delegates
     * the parsing to <code>Byte.decode(String)</code>.  If Byte throws a
     * NumberFormatException, it is encapsulated into a ParseException and
     * re-thrown.
     *
     * @param arg the argument to parse
     * @return a Byte object with the value contained in the specified argument.
     * @throws ParseException if <code>Byte.decode(arg)</code> throws a
     * NumberFormatException.
     * @see java.lang.Byte
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        Byte result = null;
        try {
            result = Byte.decode(arg);
        } catch (NumberFormatException e) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a Byte.",
                    e));
        }
        return (result);
    }
}
