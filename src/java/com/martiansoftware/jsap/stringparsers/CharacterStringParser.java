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
 * A {@link com.martiansoftware.jsap.StringParser} for parsing Characters.  The parse() method requires an
 * argument of length exactly
 * equal to 1 in order to perform the conversion; otherwise, a ParseException
 * is thrown.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Character
 */
public class CharacterStringParser extends StringParser {

	private static final CharacterStringParser INSTANCE = new CharacterStringParser();	

	/** Returns a {@link CharacterStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#CHARACTER_PARSER}.
	 *  
	 * @return a {@link CharacterStringParser}.
	 */

    public static CharacterStringParser getParser() {
		return INSTANCE;
	}

	/**
     * Creates a new CharacterStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#CHARACTER_PARSER}.
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
