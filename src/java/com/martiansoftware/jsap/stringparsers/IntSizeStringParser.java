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
import com.martiansoftware.jsap.StringParser;

/** A {@link com.martiansoftware.jsap.StringParser} that works like {@link LongSizeStringParser}, but
 * additionally checks that the result is not larger than {@link Integer#MAX_VALUE}.
 * 
 * @author Sebastiano Vigna
 */

public class IntSizeStringParser extends StringParser {

	/** The only instance of this parser. Aliased to <code>JSAP.INT_SIZE_PARSER</code>. */
	final static IntSizeStringParser INSTANCE = new IntSizeStringParser();
	
	private IntSizeStringParser() {}
	
	
	/** Returns the only instance of an {@link IntSizeStringParser}.
	 * 
	 * <p>Convenient access to the only instance returned by
	 * this method is available through
	 * {@link com.martiansoftware.jsap.JSAP#INTSIZE_PARSER}.
	 *  
	 * @return the only instance of an {@link IntSizeStringParser}.
	 */
	public static IntSizeStringParser getParser() {
		return INSTANCE;
	}

	public Object parse( String arg ) throws ParseException {
		final long size = LongSizeStringParser.parseSize( arg );
		if ( size > Integer.MAX_VALUE ) throw new ParseException( "Integer size '" + arg + "' is too big." );
		return new Integer( (int)size );
	}
}
