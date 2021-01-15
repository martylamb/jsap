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
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestFlaggedOption extends TestCase {

    /**
     * Creates a TestCase with the specified name.
     * @param name the name for this TestCase.
     */
    public TestFlaggedOption(String name) {
        super(name);
    }

    /**
     * Returns a suite of all tests defined in this class.
     * @return a suite of all tests defined in this class.
     */
    public static Test suite() {
        return (new TestSuite(TestFlaggedOption.class));
    }

    /**
     * Tests the ability to set/get short flags.
     */
    public void testShortFlag() {
        FlaggedOption option = new FlaggedOption("testOption");
        option.setShortFlag('x');
        assertEquals('x', option.getShortFlag());
        assertEquals(new Character('x'), option.getShortFlagCharacter());
    }

    /**
     * Tests the ability to set/get long flags.
     */
    public void testLongFlag() {
        FlaggedOption option = new FlaggedOption("testOption");
        option.setLongFlag("test");
        assertEquals("test", option.getLongFlag());
    }

}
