/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests the runtime xml config file initialization.
 */
public class TestJSAPXMLConfig extends TestCase {
    
	private static final String TESTCONFIG = "testConfig.xml";
    private JSAP jsap = null;
    private JSAPResult config = null;
    private String cmdline = "";
    
    /**
     * Returns a suite of tests defined by this class.
     * @return a suite of tests defined by this class.
     */
    public static Test suite() {
        return (new TestSuite(TestJSAPXMLConfig.class));
    }
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        jsap = new JSAP(TESTCONFIG, Locale.ENGLISH);
        config = jsap.parse(cmdline);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        jsap = null;
        config = null;
    }

    /*
     * Tests the result of Usage() in english.
     */
    public void testUsageEnglish() {
        String expected = "[-v|--verbose] -n <number> [(-d|--date) <date>] [(-s|--switch)[:value1,value2,...,valueN ]] [file1 file2 ... fileN ]";
        assertEquals("Usage message does not match!", expected, jsap.getUsage());
    }

    /*
     * Tests the result of Usage() in german.
     */
    public void testUsageGerman() throws Exception {
        jsap = new JSAP(TESTCONFIG, Locale.GERMAN);
        config = jsap.parse(cmdline);
        String expected = "[-v|--verbose] -n <Zahl> [(-d|--date) <Datum>] [(-s|--switch)[:Wert1,Wert2,...,WertN ]] [Datei1 Datei2 ... DateiN ]";
        assertEquals("Usage message does not match!", expected, jsap.getUsage());
    }

    /*
     * Tests the result of getHelp() in english.
     */
    public void testHelpEnglish() {
        String expected =	"  [-v|--verbose]                                 Verbose output               \n" +                     
//							"\n" +
							"  -n <number>                                    An integer value             \n" +
//							"\n" +
							"  [(-d|--date) <date>]                           Some date                    \n" +
//							"\n" +
							"  [(-s|--switch)[:value1,value2,...,valueN ]]    Some value                   \n" +
//							"\n" +
							"  [file1 file2 ... fileN ]                       Files to be processed        ";
        assertEquals("Help message does not match!", expected, jsap.getHelp());
    }
    
    /*
     * Tests the result of getHelp() in german.
     */
    public void testHelpGerman() throws Exception {
        jsap = new JSAP(TESTCONFIG, Locale.GERMAN);
        config = jsap.parse(cmdline);
        String expected =	"  [-v|--verbose]                              Ausführliche Ausgabe            \n" +                     
//							"\n" +
							"  -n <Zahl>                                   Ein Integer Wert                \n" +
//							"\n" +
							"  [(-d|--date) <Datum>]                       Irgend ein Datum                \n" +
//							"\n" +
							"  [(-s|--switch)[:Wert1,Wert2,...,WertN ]]    Irgend ein Wert                 \n" +
//							"\n" +
							"  [Datei1 Datei2 ... DateiN ]                 Zu behandelnde Dateien          ";
        assertEquals("Help message does not match!", expected, jsap.getHelp());
    }
}
