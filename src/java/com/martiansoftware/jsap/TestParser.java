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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.martiansoftware.jsap.stringparsers.IntegerStringParser;
import com.martiansoftware.jsap.stringparsers.StringStringParser;

/**
 * Tests the Parser class.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestParser extends TestCase {

    /**
     * Returns a suite of tests defined by this class.
     * @return a suite of tests defined by this class.
     */
    public static Test suite() {
        return (new TestSuite(TestParser.class));
    }

    /**
     * Tests the ability to parse when allowMultipleDeclarations is set to true.
     */
    public void testMultipleDeclarations() {
        FlaggedOption opt = new FlaggedOption("flagged");
        opt.setShortFlag('f');
        opt.setLongFlag("longflag");
        JSAP jsap = new JSAP();

        try {
            jsap.registerParameter(opt);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        String[] args = { "-f", "testone" };
        JSAPResult result = null;
        try {
            result = jsap.parse(args);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("testone", (String) result.getObject("flagged"));

        String[] args2 = { "-f", "testone", "-f", "testtwo" };
        result = jsap.parse(args2);
        assertEquals(false, result.success());

        jsap.unregisterParameter(opt);
        opt.setAllowMultipleDeclarations(true);

        try {
            jsap.registerParameter(opt);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            result = jsap.parse(args2);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        String[] testresult =
            (String[]) result.getObjectArray("flagged", new String[0]);
        assertEquals(2, testresult.length);
        assertEquals("testone", testresult[0]);
        assertEquals("testtwo", testresult[1]);
    }

    /**
     * Tests the parser with both a String and an Integer option.
     */
    public void testParser() {
        FlaggedOption opt = new FlaggedOption("flagged");
        opt.setShortFlag('f');
        opt.setLongFlag("longflag");
        opt.setStringParser(StringStringParser.getParser());
        JSAP jsap = new JSAP();

        FlaggedOption opt2 = new FlaggedOption("flaggedInteger");
        opt2.setLongFlag("integer");
        opt2.setStringParser(IntegerStringParser.getParser());

        try {
            jsap.registerParameter(opt);
            jsap.registerParameter(opt2);
        } catch (JSAPException je) {
            fail(je.getMessage());
        }

        String[] args = { "-f", "myflagthing", "--integer", "42" };

        JSAPResult result = null;
        try {
            result = jsap.parse(args);
        } catch (Exception je2) {
            je2.printStackTrace();
            fail(je2.getMessage());
        }

        assertEquals("myflagthing", (String) result.getObject("flagged"));
        assertEquals(
            42,
            ((Integer) result.getObject("flaggedInteger")).intValue());

    }
}
