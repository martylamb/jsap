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

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing Strings.  This is the simplest possible
 * StringParser, simply returning
 * the specified argument in all cases.  This class never throws a
 * ParseException under any circumstances.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.String
 */
public class StringStringParser extends StringParser {

	private static final StringStringParser INSTANCE = new StringStringParser();	

	/** Returns a {@link StringStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#STRING_PARSER}.
	 *  
	 * @return a {@link StringStringParser}.
	 */

    public static StringStringParser getParser() {
		return INSTANCE;
	}

	/**
     * Creates a new StringStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#STRING_PARSER}.
     */
    public StringStringParser() {
        super();
    }

    /**
     * Returns the specified argument as a String.
     *
     * @param arg the argument to parse
     * @return the specified argument as a String.
     * @see java.lang.String
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) {
        return (arg);
    }
}
