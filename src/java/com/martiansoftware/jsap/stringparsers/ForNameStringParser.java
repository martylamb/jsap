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

import java.lang.reflect.Method;

import com.martiansoftware.jsap.ParseException;
import com.martiansoftware.jsap.StringParser;


/** A {@link com.martiansoftware.jsap.StringParser} that passes the
 * argument to a static method of signature <code>forName(String)</code> of a specified class.
 * 
 * <P>Note that, for instance, this parser can be used with {@link java.lang.Class} (resulting in a 
 * string parser identical to {@link com.martiansoftware.jsap.stringparsers.ClassStringParser}),
 * but also {@link java.nio.charset.Charset}, and more generally, any class using the <code>forName(String)</code>
 * convention.
 *
 * @author Sebastiano Vigna
 */

public class ForNameStringParser extends StringParser {

	/** The class array describing the parameters (a string) of <code>forName</code>. */
	private final static Class[] PARAMETERS = new Class[] { String.class };
	
	/** The class given to the constructor. */
	private final Class klass;
	/** The <code>forName(String)</code> static method of {@link #klass}. */
	private final Method forName;

	private ForNameStringParser( final Class klass ) throws SecurityException, NoSuchMethodException {
		this.klass = klass;
		forName = klass.getMethod( "forName", PARAMETERS );
	}
	
	/** Returns a class <code>forName()</code> string parser.
	 *
	 * <p>When required to parse an argument, the returned string parser will return the
	 * object obtain by means of a call to a static method of <code>klass</code> of signature
	 * <code>forName(String)</code>.
	 *  
	 * @param klass a class with a static method of signature <code>forName(String)</code>.
	 */
	
	public static ForNameStringParser getParser( final Class klass ) throws SecurityException, NoSuchMethodException {
		return new ForNameStringParser( klass );
	}
	
	public Object parse( String arg ) throws ParseException {
		try {
			return forName.invoke( klass, new Object[] { arg } );
		}
		catch ( Exception e ) {
			throw new ParseException ( e );
		}
	}
}
