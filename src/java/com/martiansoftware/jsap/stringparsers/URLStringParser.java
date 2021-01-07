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
import java.net.URL;
import java.net.MalformedURLException;

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing java.net.URL objects.  The parse() method
 * delegates the actual
 * parsing to <code>new URL(String)</code>.  If <code>new URL()</code>
 * throws a MalformedURLException, it is encapsulated in a ParseException and
 * re-thrown.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.net.URL
 */
public class URLStringParser extends StringParser {

	private static final URLStringParser INSTANCE = new URLStringParser();	

	/** Returns a {@link URLStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#URL_PARSER}.
	 *  
	 * @return a {@link URLStringParser}.
	 */

    public static URLStringParser getParser() {
		return INSTANCE;
	}

	/**
     * Creates a new URLStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#URL_PARSER}.
     */
    public URLStringParser() {
        super();
    }

    /**
     * Parses the specified argument into a URL.  This method delegates the
     * actual
     * parsing to <code>new URL(arg)</code>.  If <code>new URL(arg)</code>
     * throws a MalformedURLException, it is encapsulated in a ParseException
     * and re-thrown.
     *
     * @param arg the argument to parse
     * @return a URL as specified by arg.
     * @throws ParseException if <code>new URL(arg)</code> throws a
     * MalformedURLException.
     * @see java.net URL
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        URL result = null;
        try {
            result = new URL(arg);
        } catch (MalformedURLException e) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a URL.",
                    e));
        }
        return (result);
    }
}
