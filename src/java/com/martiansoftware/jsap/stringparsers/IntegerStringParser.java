/*
 * Copyright (C) 2002-2021, Martian Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing Integers.  The parse() method delegates the actual
 * parsing to Integer.decode(String).
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Integer
 */
public class IntegerStringParser extends StringParser {
	
	private static final IntegerStringParser INSTANCE = new IntegerStringParser();	

	/** Returns a {@link IntegerStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#INTEGER_PARSER}.
	 *  
	 * @return a {@link IntegerStringParser}.
	 */
    public static IntegerStringParser getParser() {
		return new IntegerStringParser();
	}

	/**
     * Creates a new IntegerStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#INTEGER_PARSER}.
     */
    public IntegerStringParser() {
        super();
    }

    /**
     * Parses the specified argument into an Integer.  This method delegates
     * the parsing to <code>Integer.decode(arg)</code>.  If
     * <code>Integer.decode()</code> throws a
     * NumberFormatException, it is encapsulated into a ParseException and
     * re-thrown.
     *
     * @param arg the argument to parse
     * @return an Integer object with the value contained in the specified
     * argument.
     * @throws ParseException if <code>Integer.decode(arg)</code> throws a
     * NumberFormatException.
     * @see java.lang.Integer
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        Integer result = null;
        try {
            result = Integer.decode(arg);
        } catch (NumberFormatException nfe) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to an Integer.",
                    nfe));
        }
        return (result);
    }
}
