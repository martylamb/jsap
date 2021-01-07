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
 * Runs all of the JSAP tests, including those in sub-packages.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestAll extends TestCase {

    /**
     * Returns a collection of all the JSAP tests, including those in
     * subpackages.
     * @return a collection of all the JSAP tests, including those in
     * subpackages.
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("All JUnit Tests");
        suite.addTest(TestSwitch.suite());
        suite.addTest(TestOption.suite());
        suite.addTest(TestFlaggedOption.suite());
        suite.addTest(TestJSAPConfiguration.suite());
        suite.addTest(TestParser.suite());
        suite.addTest(TestDefaults.suite());
        suite.addTest(TestCommandLineTokenizer.suite());
        suite.addTest(TestUsageString.suite());
        suite.addTest(com.martiansoftware.jsap.stringparsers.TestAll.suite());
        suite.addTest(com.martiansoftware.jsap.defaultsources.TestAll.suite());
        return (suite);
    }

}
