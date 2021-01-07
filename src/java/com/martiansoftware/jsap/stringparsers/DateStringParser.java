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

import com.martiansoftware.jsap.ParseException;
import com.martiansoftware.jsap.PropertyStringParser;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing java.util.Date objects.  By default, arguments
 * are parsed using the
 * java.text.SimpleDateFormat for the default locale.  The format can be
 * overridden using this StringParser's
 * setProperties() method, supplying a java.util.Properties object with a
 * property key named "format".
 * The value associated with the "format" property is used to create a new
 * java.text.SimpleDateFormat
 * to parse the argument.
 * 
 * <p>A ParseException is thrown if a SimpleDateFormat cannot be constructed with
 * the specified format, or if the SimpleDateFormat throws a
 * java.text.ParseException during parsing.
 * 
 * <p>The SimpleDateFormat object is instantiated when an option referencing this
 * DateStringParser is
 * registered with a JSAP object.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.util.Date
 * @see java.text.SimpleDateFormat
 */
public class DateStringParser extends PropertyStringParser {

    /**
     * The SimpleDateFormat used to do the parsing.
     */
    private SimpleDateFormat format = null;

    /** Returns a {@link DateStringParser}.
	 * 
	 * @return a {@link DateStringParser}.
	 */
    
	public static DateStringParser getParser() {
		return new DateStringParser();
	}

    /**
     * Creates a new DateStringParser.
     * @deprecated Use {@link #getParser()}.
     */
    public DateStringParser() {
        super();
    }

    /**
     * Instantiates the SimpleDateFormat to use for parsing.
     * @throws ParseException if a SimpleDateFormat cannot be instantiated with
     * the contents of the
     * "format" property.
     */
    public void setUp() throws ParseException {
        String formatString = this.getProperty("format");
        if (formatString == null) {
            format = new SimpleDateFormat();
        } else {
        	try {
        		format = new SimpleDateFormat(formatString);
        	}
        	catch( RuntimeException e ) {
        		throw new ParseException( e );
        	}
        }
    }

    /**
     * Destroys the SimpleDateFormat used for parsing.
     */
    public void tearDown() {
        format = null;
    }

    /**
     * Parses the specified argument using either the java.text.SimpleDateFormat
     * for the current locale
     * (by default) or a java.text.SimpleDateFormat as defined by this
     * PropertyStringParser's "format"
     * property.
     *
     * If the specified argument cannot be parsed by the current format, a
     * ParseException is thrown.
     *
     * @param arg the argument to convert to a Date.
     * @return a Date as described above.
     * @throws ParseException if the specified argument cannot be parsed by the
     * current format..
     */
    public Object parse(String arg) throws ParseException {
        Date result = null;
        try {
            result = format.parse(arg);
        } catch (java.text.ParseException e) {
            throw (
                new ParseException(
                    "Unable to convert '" + arg + "' to a Date.",
                    e));
        }
        return (result);
    }

}
