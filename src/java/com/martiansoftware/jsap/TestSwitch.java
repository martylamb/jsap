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

/**
 * Tests the Switch class.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestSwitch extends TestCase {

    /**
     * Creates a new test case with the specified name.
     * @param name the name for this test case.
     */
    public TestSwitch(String name) {
        super(name);
    }

    /**
     * Returns a suite of tests defined by this class
     * @return a suite of tests defined by this class
     */
    public static Test suite() {
        return (new TestSuite(TestSwitch.class));
    }

    /**
     * Tests the ability to retrieve the Switch's ID.
     */
    public void testID() {
        Switch mySwitch = new Switch("mySwitch");
        assertEquals("mySwitch", mySwitch.getID());
    }

    /**
     * Tests the ability to set/get the Switch's short flag.
     */
    public void testShortFlag() {
        Switch mySwitch = new Switch("mySwitch");
        mySwitch.setShortFlag('c');
        assertEquals('c', mySwitch.getShortFlag());
        assertEquals(new Character('c'), mySwitch.getShortFlagCharacter());
    }

    /**
     * Tests the ability to set/get the Switch's long flag.
     */
    public void testLongFlag() {
        Switch mySwitch = new Switch("mySwitch");
        mySwitch.setLongFlag("thisIsMyLongFlag");
        assertEquals("thisIsMyLongFlag", mySwitch.getLongFlag());
    }

    /**
     * Utility method to ensure that the specified List contains a single,
     * true boolean.
     * @param a the List to test.
     */
    private void ensureTrueList(List a) {
        assertNotNull(a);
        assertEquals(1, a.size());
        assertEquals(a.get(0), new Boolean(true));
    }

    /**
     * Utility method to ensure that the specified List contains a single,
     * false boolean.
     * @param a the List to test.
     */
    private void ensureFalseList(List a) {
        assertNotNull(a);
        assertEquals(1, a.size());
        assertEquals(a.get(0), new Boolean(false));
    }

    /**
     * Tests the parsing ability of the Switch.
     */
    public void testSwitchParse() {
        try {
            Switch mySwitch = new Switch("mySwitch");
            ensureTrueList(mySwitch.parse(null));
            ensureTrueList(mySwitch.parse("true"));
            ensureFalseList(mySwitch.parse("false"));
        } catch (JSAPException e) {
            fail(e.getMessage());
        }

    }
}
