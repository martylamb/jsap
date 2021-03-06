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

/**
 * <p>Marks an argument as being "flagged" - that is, as having its value on the
 * command line preceded by an indicator.  Flagged arguments can be preceded by
 * a "short flag," a hyphen followed by a single character, or a "long flag,"
 * two hyphens followed by a word.</p>
 *
 * <p>For example, a short flag 'i' marking an option as meaning "input file"
 * might result in a command line that contains the substring "-i myfile.txt" or
 * "-i=myfile.txt", whereas the same option with a long flag of "inputfile"
 * might contain a substring such as "--inputfile myfile.txt" or
 * "--inputfile=myfile.txt"</p>
 *
 * Note that the setter methods setShortFlag() and setLongFlag() are not
 * part of this Interface.  This is because including them would prevent the
 * setShortFlag() and setLongFlag() methods in FlaggedOption and Switch from
 * returning references to themselves, which is inconvenient and inconsistent
 * with the rest of the API.
 *
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.Switch
 * @see com.martiansoftware.jsap.FlaggedOption
 */
public interface Flagged {

    //    /**
    //     *  Sets the short flag for this object.
    //     *  @param shortFlag the new short flag for this object.
    //     *  @return the modified Flagged object.
    //     */
    //    Flagged setShortFlag(char shortFlag);

    /**
     *  Returns the short flag for this object in the form of a char.
     *  @return the short flag for this object in the form of a char.
     */
    char getShortFlag();

    /**
     *  Returns the short flag for this object in the form of a Character.
     *  @return the short flag for this object in the form of a Character.
     */
    Character getShortFlagCharacter();

    //    /**
    //     *  Sets the long flag for this object.
    //     *  @param longFlag the new long flag for this object.
    //     *  @return the modified Flagged object.
    //     */
    //    Flagged setLongFlag(String longFlag);

    /**
     *  Returns the long flag for this object.
     *  @return the long flag for this object.
     */
    String getLongFlag();

}
