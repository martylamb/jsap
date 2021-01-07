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
 * A {@link com.martiansoftware.jsap.StringParser} for parsing Packages.  The parse() method delegates the actual
 * parsing to <code>Package.getPackage(String)</code>, and returns the resulting
 * Package object.
 * If <code>Package.getPackage()</code> returns null, a ParseException is
 * thrown.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Package
 */
public class PackageStringParser extends StringParser {
	
	private static final PackageStringParser INSTANCE = new PackageStringParser();	

	/** Returns a {@link PackageStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#PACKAGE_PARSER}.
	 *  
	 * @return a {@link PackageStringParser}.
	 */
    public static PackageStringParser getParser() {
		return INSTANCE;
	}

	/**
     * Creates a new PackageStringParser
     * @deprecated Use {@link #getParser()} or, even better, {@link com.martiansoftware.jsap.JSAP#PACKAGE_PARSER}.
     */
    public PackageStringParser() {
        super();
    }

    /**
     * Parses the specified argument into a Package object.  This method
     * delegates the
     * parsing to <code>Package.getPackage(String)</code>, and returns the
     * resulting Package object.
     * If <code>Package.getPackage()</code> returns null, a ParseException is
     * thrown.
     *
     * @param arg the argument to parse
     * @return a Package object representing the specified package.
     * @throws ParseException if <code>Package.getPackage(arg)</code> returns
     * null.
     * @see java.lang.Package
     * @see com.martiansoftware.jsap.StringParser#parse(String)
     */
    public Object parse(String arg) throws ParseException {
        Package result = Package.getPackage(arg);
        if (result == null) {
            throw (
                new ParseException("Unable to locate Package '" + arg + "'."));
        }
        return (result);
    }
}
