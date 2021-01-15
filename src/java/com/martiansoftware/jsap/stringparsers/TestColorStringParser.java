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

import junit.framework.TestCase;
import java.awt.Color;
import com.martiansoftware.jsap.JSAPException;

/**
 * A series of tests for the ColorStringParser
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.stringparsers.ColorStringParser
 */
public class TestColorStringParser extends TestCase {

    /**
     * Constructor for TestColorStringParser.
     * @param arg0 name of this test
     */
    public TestColorStringParser(String arg0) {
        super(arg0);
    }

    /**
     * Tests the ability to parse tuples of integers representing RGB values.
     */
    public void testIntegerRGB() {

        Color c = new Color(12, 34, 56);
        ColorStringParser parser = ColorStringParser.getParser();

        Color result = null;
        try {
            result = (Color) parser.parse("12,34,56");
        } catch (JSAPException e) {
            fail("12,34,56");
        }
        assertEquals(c, result);

        try {
            result = (Color) parser.parse("12,34,56,");
            fail("12,34,56,");
        } catch (JSAPException e) {
            // this is normal behavior
        }

        try {
            result = (Color) parser.parse("12,3b,56");
            fail("12,3b,56");
        } catch (JSAPException e) {
            // this is normal behavior
        }

    }

    /**
     * Tests the ability to parse tuples of floats representing RGB values.
     */
    public void testFloatRGB() {
        Color c = new Color(0.12f, 0.34f, 0.56f);
        ColorStringParser parser = ColorStringParser.getParser();

        Color result = null;
        try {
            result = (Color) parser.parse("0.12,.34,00.56");
        } catch (JSAPException e) {
            fail("0.12,.34,00.56");
        }
        assertEquals(c, result);

        try {
            result = (Color) parser.parse("0.12,.34");
            fail("0.12,.34");
        } catch (JSAPException e) {
            // this is normal behavior
        }
    }

    /**
     * Tests the ability to parse hexadecimal strings as RGB values.
     */
    public void testHexRGB() {
        Color c = new Color(255, 255, 255);
        ColorStringParser parser = ColorStringParser.getParser();

        Color result = null;
        try {
            result = (Color) parser.parse("#fFFfFF");
        } catch (JSAPException e) {
            fail("#fFFfFF");
        }
        assertEquals(c, result);
    }

    /**
     * Tests the ability to parse color names as in java.awt.Color fields.
     */
    public void testByName() {
        Color c = new Color(255, 255, 255);
        ColorStringParser parser = ColorStringParser.getParser();

        Color result = null;
        try {
            result = (Color) parser.parse("white");
        } catch (JSAPException e) {
            fail("white");
        }
        assertEquals(c, result);

        try {
            result = (Color) parser.parse("offwhite");
            fail("offwhite");
        } catch (JSAPException e) {
            // this is normal behavior
        }

    }

}
