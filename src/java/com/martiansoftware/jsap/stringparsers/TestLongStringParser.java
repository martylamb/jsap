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

import com.martiansoftware.jsap.JSAPException;

import junit.framework.TestCase;
/**
 * A series of tests for the LongStringParser
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.stringparsers.ColorStringParser
 */
public class TestLongStringParser extends TestCase {

    /**
     * Constructor for TestLongStringParser.
     * @param arg0 name of this test
     */
    public TestLongStringParser(String arg0) {
        super(arg0);
    }

    /**
     * Tests the ability to parse tuples of integers representing RGB values.
     */
    public void testBasicParse() {

        LongStringParser lsp = LongStringParser.getParser();

        assertEquals(456, Long.decode("456").longValue());
        try {
            Long result = (Long) lsp.parse("123");
            assertEquals(123, result.longValue());
        } catch (JSAPException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

}
