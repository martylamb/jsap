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

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.martiansoftware.jsap.stringparsers.StringStringParser;

/**
 * Tests the Option class
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestOption extends TestCase {

    /**
     * Returns a suite of tests defined by this class.
     * @return a suite of tests defined by this class.
     */
    public static Test suite() {
        return (new TestSuite(TestOption.class));
    }

    /**
     * Tests the various setters and getters on an option.
     */
    public void testSettersAndGetters() {
        FlaggedOption option = new FlaggedOption("testOption");

        assertEquals("testOption", option.getID());
        option.setList(true);
        assertEquals(true, option.isList());
        option.setListSeparator('W');
        assertEquals('W', option.getListSeparator());
        option.setAllowMultipleDeclarations(true);
        assertEquals(true, option.allowMultipleDeclarations());
        option.setRequired(true);
        assertEquals(true, option.required());
        assertNull(option.getStringParser());
        StringStringParser sop = StringStringParser.getParser();
        option.setStringParser(sop);
        assertEquals(sop, option.getStringParser());
    }

    /**
     * Tests the ability to parse via the option's parse() method.
     */
    public void testParsing() {
        FlaggedOption option = new FlaggedOption("testOption");

        option.setListSeparator(' ');
        option.setList(true);
        List parseResult = null;

        try {
            parseResult = option.parse("this is a test");
        } catch (JSAPException e) {
            fail(e.getMessage());
        }
        assertEquals(4, parseResult.size());
        assertEquals("this", parseResult.get(0));
        assertEquals("test", parseResult.get(3));
    }
}
