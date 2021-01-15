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

package com.martiansoftware.jsap.examples;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

/**
 * @author mlamb
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Example1 {

    public static void main(String[] args) throws Exception {
        JSAP jsap = new JSAP();

        FlaggedOption f1 = new FlaggedOption("myflagged");
        f1.setShortFlag('f').setLongFlag("flagged").setRequired(true).setHelp(
            "do flagged stuff");
        jsap.registerParameter(f1);

        UnflaggedOption f2 = new UnflaggedOption("myunflagged");
        f2.setGreedy(JSAP.GREEDY).setHelp("input files");
        jsap.registerParameter(f2);

        Switch sw1 = new Switch("myswitch");
        sw1.setLongFlag("verbose").setHelp(
            "display extra logging information.");
        jsap.registerParameter(sw1);

        System.out.println("Usage: Example1 " + jsap.getUsage());
        System.out.println(jsap.getHelp());
    }
}
