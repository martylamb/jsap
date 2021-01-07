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
import java.math.BigDecimal;

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing BigDecimals.  The parse() method delegates the
 * actual
 * parsing to BigDecimal's constructor.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.math.BigDecimal
 */
public class BigDecimalStringParser extends StringParser {

	private static final BigDecimalStringParser INSTANCE = new BigDecimalStringParser();

	/** Returns a {@link BigDecimalStringParser}.
	 *
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#BIGDECIMAL_PARSER}.
	 *  
	 * @return a {@link BigDecimalStringParser}.
	 */
	
    public static BigDecimalStringParser getParser() {
		return INSTANCE; 
	}

	/**
     * Creates a new BigDecimalStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#BIGDECIMAL_PARSER}.
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
     * @see BigDecimal
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
