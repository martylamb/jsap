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

package com.martiansoftware.util;

import java.util.List;

/**
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public final class StringUtils {

    public static String noNull(String s) {
        return (s == null ? "" : s);
    }

    public static String padRight(String s, int padCount) {
        if (padCount < 0) {
            throw (new IllegalArgumentException("padCount must be >= 0"));
        }
        StringBuffer buf = new StringBuffer(noNull(s));
        for (int i = 0; i < padCount; ++i) {
            buf.append(' ');
        }
        return (buf.toString());
    }

    public static String padRightToWidth(String s, int desiredWidth) {
        String result = noNull(s);
        if (result.length() < desiredWidth) {
            result = padRight(result, desiredWidth - result.length());
        }
        return (result);
    }

    public static List wrapToList(String s, int width) {
        List result = new java.util.LinkedList();
        if ((s != null) && (s.length() > 0)) {
            StringBuffer buf = new StringBuffer();
            int lastSpaceBufIndex = -1;
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c == '\n') {
                    result.add(buf.toString());
                    buf.setLength(0);
                    lastSpaceBufIndex = -1;
                } else {
                    if (c == ' ') {
                        if (buf.length() >= width - 1) {
                            result.add(buf.toString());
                            buf.setLength(0);
                            lastSpaceBufIndex = -1;
                        }
                        if (buf.length() > 0) {
                            lastSpaceBufIndex = buf.length();
                            buf.append(c);
                        }
                    } else {
                    	if (buf.length() >= width) {
                            if (lastSpaceBufIndex != -1) {
                                result.add(buf.substring(0, lastSpaceBufIndex));
                                buf.delete(0, lastSpaceBufIndex + 1);
                                lastSpaceBufIndex = -1;
                            }
                        }
                        buf.append(c);
                    }
                }
            }
            if (buf.length() > 0) {
                result.add(buf.toString());
            }
        }
        return (result);
    }

    public static void main(String[] args) {
        String s =
            "This is\n a test that I would like to word wrap at 15 characters.";

        System.out.println("123456789012345");
        List l = wrapToList(s, 15);
        for (java.util.Iterator i = l.iterator(); i.hasNext();) {
            String s1 = (String) i.next();
            System.out.println(s1 + "|");
        }
    }
}