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

/**
 * Tests the ability to automatically create usage information.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestUsageString extends TestCase {

    /**
     * Creates a new TestCase with the specified name.
     * @param arg0 the name for this TestCase.
     */
    public TestUsageString(String arg0) {
        super(arg0);
    }

    /**
     * Returns a suite of tests defined by this class.
     * @return a suite of tests defined by this class.
     */
    public static Test suite() {
        return (new TestSuite(TestUsageString.class));
    }

    /**
     * Tests usage info for a single option, both required and not required.
     */
    public void testUsage1() {
        JSAP config = new JSAP();

        FlaggedOption opt1 = new FlaggedOption("flaggedOption");
        opt1.setShortFlag('f');
        opt1.setLongFlag("flagged");
        opt1.setRequired(JSAP.REQUIRED);
        try {
            config.registerParameter(opt1);
        } catch (JSAPException e) {
            fail("Unable to register opt1");
        }
        assertEquals("(-f|--flagged)" + JSAP.SYNTAX_SPACECHAR + "<flaggedOption>", config.getUsage());

        config.unregisterParameter(opt1);
        opt1.setRequired(JSAP.NOT_REQUIRED);
        try {
            config.registerParameter(opt1);
        } catch (JSAPException e) {
            fail("Unable to register opt1");
        }
        assertEquals("[(-f|--flagged)" + JSAP.SYNTAX_SPACECHAR + "<flaggedOption>]", config.getUsage());
    }

    /**
     * Tests usage info for a Switch.
     */
    public void testUsage2() {
        JSAP config = new JSAP();
        Switch sw = new Switch("testSwitch");
        sw.setShortFlag('s');
        sw.setLongFlag("switch");
        try {
            config.registerParameter(sw);
        } catch (JSAPException e) {
            fail("Unable to register sw");
        }
        assertEquals("[-s|--switch]", config.getUsage());
    }
}
