/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;

/**
 * A StringParser for parsing Characters.  The parse() method requires an
 * argument of length exactly
 * equal to 1 in order to perform the conversion; otherwise, a ParseException
 * is thrown.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Character
 */
public class CharacterStringParser extends StringParser {

    /**
     * Creates a new CharacterStringParser.
     */
    public CharacterStringParser() {
        super();
    }

    /**
     * Parses the specified argument into a Character.  The conversion is
     * performed by
     * checking that the specified argument is exactly 1 character long, then
     * encapsulating
     * that char in a Character object.  If the specified argument is not
     * exactly 1 character long,
     * a ParseException is thrown.
     *
     * @param arg the argument to parse
     * @return a Character object with the value contained in the specified
     * argument.
     * @throws ParseException if ( (arg==null) || (arg.length()!=1) )
     * @see java.lang.Character
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        if ((arg == null) || (arg.length() != 1)) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a Character."));
        }
        return (new Character(arg.charAt(0)));
    }
}
