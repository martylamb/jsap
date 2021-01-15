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

package com.martiansoftware.jsap;

/**
 * Class responsible for converting Strings into Objects.  Each subclass of
 * StringParser
 * is capable of parsing a String into a different class of object.  <b>To
 * extend JSAP to
 * recognize new data types, a new StringParser must be created for the new
 * type.</b><br>
 * <br>
 * "List" options (such as your environment's PATH and CLASSPATH variables)
 * that contain
 * multiple values are split into individual value tokens prior to the calling
 * of the
 * StringParser's parse() method.  For example, if you had a StringStringParser
 * parsing
 * your PATH environment variable, that StringStringParser's parse() method
 * would be
 * called once for each item in the list.  As a result, each StringParser only
 * needs
 * to know how to create an object based upon a single, simple token.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public abstract class StringParser {

    /**
     * Performs any initialization not handled by this StringParser's
     * constructor.
     * The contract for this method is that it will be called AT LEAST once
     * before
     * this object's parse() method is called.<br><br>
     * In the JSAP API, this method is called every time an Option containing
     * this StringParser is
     * registered with a JSAP.  If there is an initialization error, this method
     * should throw a JSAPException to prevent the Option from being registered.
     * @throws Exception if an initialization error occurs that should prevent
     * the Option containing this StringParser from being registered.
     */
    public void setUp() throws Exception {
    }

    /**
     * Performs any cleanup necessary for this StringParser.  The contract for
     * this method is that it MAY be called at any time after the setUp method
     * has been called.  It may be called more than once.<br>
     * <br>
     * In the JSAP API, this method is called every
     * time an Option containing this StringParser is unregistered from a JSAP.
     * During
     * finalization, any registered Options are unregistered from a JSAP.
     */
    public void tearDown() {
    }

    /**
     * Parses the specified argument into an Object of the appropriate type.
     * If
     * the specified argument cannot be converted into the desired Object, a
     * ParseException
     * should be thrown.<br>
     * <br>
     * <b>Note:</b> this method MAY BE CALLED with a <b>null</b> argument.
     * Take this
     * into consideration when subclassing!
     * @param arg the argument to convert to an Object of class appropriate to
     * the StringParser subclass.
     * @return the Object resulting from the parsed argument.
     * @throws ParseException if the specified argument cannot be parsed.
     */
    public abstract Object parse(String arg) throws ParseException;

}
