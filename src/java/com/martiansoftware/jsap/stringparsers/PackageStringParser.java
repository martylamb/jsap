/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.StringParser;
import com.martiansoftware.jsap.ParseException;

/**
 * A StringParser for parsing Packages.  The parse() method delegates the actual
 * parsing to <code>Package.getPackage(String)</code>, and returns the resulting
 * Package object.
 * If <code>Package.getPackage()</code> returns null, a ParseException is
 * thrown.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.StringParser
 * @see java.lang.Package
 */
public class PackageStringParser extends StringParser {

    /**
     * Creates a new PackageStringParser
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
