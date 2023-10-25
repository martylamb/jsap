/*
 * Copyright (C) 2002-2023, Martian Software, Inc.
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

package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.ParseException;
import com.martiansoftware.jsap.StringParser;

/**
 * A {@link com.martiansoftware.jsap.StringParser} that enforces valid {@link Enum} entries.
 *
 * @param <E> type of the enum to enforce
 */
public class EnumStringParser<E extends Enum<E>> extends StringParser {

    /**
     *  Class instance of the Enum class to enforce.
     */
    private final Class<E> clazz;

    /**
     * Construct a new instance of the EnumStringParser for the given kind of {@link Enum}.
     *
     * @param enumClass enum that should be parsed
     */
    private EnumStringParser(final Class<E> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("Given class does not represent an enum: " + enumClass);
        }
        this.clazz = enumClass;
    }

    /**
     * Create a {@link StringParser} that parses {@link Enum} values.
     *
     * @param enumClass enum to parse
     * @return a StringParser parsing the enum
     * @param <E> enum type that should be parsed
     * @throws IllegalArgumentException if the provided type is not an Enum
     */
    public static <E extends Enum<E>> EnumStringParser<E> getParser(final Class<E> enumClass) throws IllegalArgumentException {
        return new EnumStringParser<>(enumClass);
    }

    @Override
    public E parse(final String optionValue) throws ParseException {
        if (optionValue == null) {
            return null;
        }
        try {
            return Enum.valueOf(clazz, optionValue);
        } catch (IllegalArgumentException exception) {
            throw new ParseException("Invalid option value '" + optionValue + "'; valid values are: " + String.join(", ", validEnumValues()), exception);
        }
    }

    /**
     * Get a string array of all enum value names of the enum type that is parsed by this StringParser.
     *
     * @return array of valid enum values
     */
    private String[] validEnumValues() {
        final E[] possibleEnumConstants = clazz.getEnumConstants();
        final String[] validValues = new String[possibleEnumConstants.length];
        for (int index = 0; index < possibleEnumConstants.length; index++) {
            validValues[index] = possibleEnumConstants[index].toString();
        }
        return validValues;
    }
}
