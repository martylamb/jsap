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
 * Tests the JSAPConfiguration class
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestJSAPConfiguration extends TestCase {

    /**
     * Returns a suite of tests defined by this class.
     * @return a suite of tests defined by this class.
     */
    public static Test suite() {
        return (new TestSuite(TestJSAPConfiguration.class));
    }

    /**
     * Tests various configuration situations, such as changing properties of
     * registered
     * parameters, retrieving parameters, etc.
     */
    public void testConfigurationGymnastics() {
        JSAP config = new JSAP();
        FlaggedOption option = new FlaggedOption("testOption");

        // this registration should fail - it's a flagged option with no flags
        try {
            config.registerParameter(option);
            fail("Successfully registered a FlaggedOption with no flags!");
        } catch (JSAPException e) {
            // this is normal
        }

        option.setShortFlag('t');
        option.setLongFlag("test");

        // this registration should go fine
        try {
            config.registerParameter(option);
        } catch (JSAPException e) {
            fail(e.getMessage());
        }

        // the option is now locked, so it should be immutable
        try {
            option.setRequired(true);
            fail("locked option was changed!");
        } catch (Exception e) {
            // this is normal
        }

        // duplicate registration should fail
        try {
            config.registerParameter(option);
            fail("Registered the same option ID twice.");
        } catch (JSAPException e) {
            // this is normal
        }

        // this registration should work
        FlaggedOption option2 = new FlaggedOption("test2");
        option2.setShortFlag('2');
        try {
            config.registerParameter(option2);
        } catch (JSAPException e) {
            fail(e.getMessage());
        }

        // registering a duplicate shortflag should fail
        FlaggedOption option3 = new FlaggedOption("test3");
        option3.setShortFlag('2');
        try {
            config.registerParameter(option3);
            fail("Registered the same short flag twice.");
        } catch (JSAPException e) {
            // this is normal
        }

        FlaggedOption option2b = (FlaggedOption) config.getByShortFlag('2');
        assertEquals(option2, option2b);

        FlaggedOption optionb = (FlaggedOption) config.getByLongFlag("test");
        assertEquals(option, optionb);

        FlaggedOption nulloption =
            (FlaggedOption) config.getByLongFlag("nosuchflag");
        assertNull(nulloption);

        config.unregisterParameter(option);
        try {
            option.setRequired(true);
        } catch (Exception e) {
            fail("Unable to modify unlocked option.");
        }

        optionb = (FlaggedOption) config.getByLongFlag("test");
        assertNull(optionb);

        try {
            config.registerParameter(option);
        } catch (Exception e) {
            fail("Unable to register arg that was previously unregistered.");
        }

    }

}
