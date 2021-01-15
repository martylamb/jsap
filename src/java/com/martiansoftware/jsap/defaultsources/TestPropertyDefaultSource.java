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

package com.martiansoftware.jsap.defaultsources;

import junit.framework.TestCase;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.UnflaggedOption;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.stringparsers.StringStringParser;
import java.util.Properties;

/**
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public class TestPropertyDefaultSource extends TestCase {

    public void testUnexpectedProperties() {
        JSAP jsap = new JSAP();
        try {
            jsap.registerParameter(
                new FlaggedOption(
                    "testflagged",
                    StringStringParser.getParser(),
                    JSAP.NO_DEFAULT,
                    JSAP.NOT_REQUIRED,
                    'f',
                    "flagged"));
            jsap.registerParameter(
                new UnflaggedOption(
                    "testunflagged",
                    StringStringParser.getParser(),
                    JSAP.NO_DEFAULT,
                    JSAP.NOT_REQUIRED,
                    JSAP.NOT_GREEDY));
            jsap.registerParameter(new Switch("testswitch", 's', "switch"));
        } catch (Throwable t) {
            fail(t.getMessage());
        }

        Properties p = new Properties();
        p.setProperty("s", "true");
        p.setProperty("flagged", "My Flagged Value");
        PropertyDefaultSource pds = new PropertyDefaultSource(p);
        jsap.registerDefaultSource(pds);

        JSAPResult result = jsap.parse("");
        assertTrue(result.success());

        assertEquals(result.getBoolean("testswitch"), true);
        assertEquals(result.getString("testflagged"), "My Flagged Value");

        p.setProperty("unexpected", "jsap won't know what to do with this");
        result = jsap.parse("");
        assertFalse(result.success());

        /*
        for (java.util.Iterator i1 = result.getBadParameterIDIterator(); i1.hasNext(); ) {
            String badID = (String) i1.next();
            for (java.util.Iterator i2 = result.getExceptionIterator(badID); i2.hasNext(); ) {
                Exception e = (Exception) i2.next();
                System.out.println(e.getMessage());
            }
        }*/

    }

}
