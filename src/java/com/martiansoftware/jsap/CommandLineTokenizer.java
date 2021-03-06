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
/**
 * <p>A utility class to parse a command line contained in a single String into
 * an array of argument tokens, much as the JVM (or more accurately, your
 * operating system) does before calling your programs' <code>public static
 * void main(String[] args)</code>
 * methods.</p>
 *
 * <p>This class has been developed to parse the command line in the same way
 * that MS Windows 2000 does.  Arguments containing spaces should be enclosed
 * in quotes. Quotes that should be in the argument string should be escaped
 * with a preceding backslash ('\') character.  Backslash characters that
 * should be in the argument string should also be escaped with a preceding
 * backslash character.</p>
 *
 * Whenever <code>JSAP.parse(String)</code> is called, the specified String is
 * tokenized by this class, then forwarded to <code>JSAP.parse(String[])</code>
 * for further processing.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.JSAP#parse(String)
 * @see com.martiansoftware.jsap.JSAP#parse(String[])
 */
public class CommandLineTokenizer {

    /**
     * Hide the constructor.
     */
    private CommandLineTokenizer() {
    }

    /**
     * Goofy internal utility to avoid duplicated code.  If the specified
     * StringBuffer is not empty, its contents are appended to the resulting
     * array (temporarily stored in the specified ArrayList).  The StringBuffer
     * is then emptied in order to begin storing the next argument.
     * @param resultBuffer the List temporarily storing the resulting
     * argument array.
     * @param buf the StringBuffer storing the current argument.
     */
    private static void appendToBuffer(
        List resultBuffer,
        StringBuffer buf) {
        if (buf.length() > 0) {
            resultBuffer.add(buf.toString());
            buf.setLength(0);
        }
    }

    /**
     * Parses the specified command line into an array of individual arguments.
     * Arguments containing spaces should be enclosed in quotes.
     * Quotes that should be in the argument string should be escaped with a
     * preceding backslash ('\') character.  Backslash characters that should
     * be in the argument string should also be escaped with a preceding
     * backslash character.
     * @param commandLine the command line to parse
     * @return an argument array representing the specified command line.
     */
    public static String[] tokenize(String commandLine) {
        List resultBuffer = new java.util.ArrayList();

        if (commandLine != null) {
            int z = commandLine.length();
            boolean insideQuotes = false;
            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < z; ++i) {
                char c = commandLine.charAt(i);
                if (c == '"') {
                    appendToBuffer(resultBuffer, buf);
                    insideQuotes = !insideQuotes;
                } else if (c == '\\') {
                    if ((z > i + 1)
                        && ((commandLine.charAt(i + 1) == '"')
                            || (commandLine.charAt(i + 1) == '\\'))) {
                        buf.append(commandLine.charAt(i + 1));
                        ++i;
                    } else {
                        buf.append("\\");
                    }
                } else {
                    if (insideQuotes) {
                        buf.append(c);
                    } else {
                        if (Character.isWhitespace(c)) {
                            appendToBuffer(resultBuffer, buf);
                        } else {
                            buf.append(c);
                        }
                    }
                }
            }
            appendToBuffer(resultBuffer, buf);

        }

        String[] result = new String[resultBuffer.size()];
        return ((String[]) resultBuffer.toArray(result));
    }

}
