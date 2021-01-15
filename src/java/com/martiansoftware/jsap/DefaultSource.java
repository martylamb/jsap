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
 * An interface describing an object as being able to produce a set of default
 * values.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.Defaults
 */
public interface DefaultSource {

    /**
     * Returns a set of default values given the configuration described by the
     * specified IDMap.  Any encountered exceptions are stored in the specified
     * JSAPResult.
     * @param idMap an IDMap containing JSAP configuration information.
     * @param exceptionMap the ExceptionMap object within which any Exceptions
     * will be returned.
     * @return a set of default values given the configuration described by the
     * specified IDMap.
     * @see com.martiansoftware.jsap.ExceptionMap#addException(String,Exception)
     */
    Defaults getDefaults(IDMap idMap, ExceptionMap exceptionMap);

}
