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
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A {@link com.martiansoftware.jsap.StringParser} for parsing java.net.InetAddress objects.  The parse() method
 * delegates the actual
 * parsing to <code>InetAddress.getByName(String)</code>.  If
 * <code>InetAddress.getByName()</code>
 * throws an UnknownHostException, it is encapsulated in a ParseException and
 * re-thrown.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.net.InetAddress
 */
public class InetAddressStringParser extends StringParser {

	private static final InetAddressStringParser INSTANCE = new InetAddressStringParser();	

	/** Returns a {@link InetAddressStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#INETADDRESS_PARSER}.
	 *  
	 * @return a {@link InetAddressStringParser}.
	 */

    public static InetAddressStringParser getParser() {
		return INSTANCE;
	}

	/**
     * Creates a new InetAddressStringParser.
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#INETADDRESS_PARSER}.
     */
    public InetAddressStringParser() {
        super();
    }

    /**
     * Parses the specified argument into an InetAddress.  This method
     * delegates the actual
     * parsing to <code>InetAddress.getByName(arg)</code>.  If
     * <code>InetAddress.getByName(arg)</code>
     * throws an UnknownHostException, it is encapsulated in a ParseException
     * and re-thrown.
     *
     * @param arg the argument to parse
     * @return an InetAddress object representing the specified address.
     * @throws ParseException if <code>InetAddress.getByName(arg)</code> throws
     * an UnknownHostException.
     * @see java.net InetAddress
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        InetAddress result = null;
        try {
            result = InetAddress.getByName(arg);
        } catch (UnknownHostException e) {
            throw (new ParseException("Unknown host: " + arg, e));
        }
        return (result);
    }
}
