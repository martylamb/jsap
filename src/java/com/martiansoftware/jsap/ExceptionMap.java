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
 * A class for aggregating exceptions thrown by JSAP's parsing process.  This
 * class is necessary as it is desirable to have information regarding ALL of
 * the reasons for a failed parse rather than just the FIRST reason.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 */
public interface ExceptionMap {

    /**
     * Adds the specified exception to the exception map.  Exceptions are
     * keyed by the ID of the parameters with which they are associated.
     * "General" exceptions not associated with a particular parameter have a
     * null key.
     * @param id the unique ID of the parameter with which the specified values
     * are associated.
     * @param exception the exception to associate with the specified key.
     */
    void addException(String id, Exception exception);

    /**
     * Returns the first exception associated with the specified parameter ID.
     * "General" exceptions can be retrieved with a null id.  If no exceptions
     * are associated with the specified parameter ID, null is returned.
     * @param id the unique ID of the parameter for which the first exception
     * is requested
     * @return the first exception associated with the specified ID, or null if
     * no exceptions are associated with the specified ID.
     */
    Exception getException(String id);

    /**
     * Returns an array of ALL exceptions associated with the specified
     * parameter ID. If no exceptions are associated with the specified
     * parameter ID, an empty (zero-length) array is returned.
     * @param id the unique ID of the parameter for which the exceptions are
     * requested.
     * @return an array of ALL exceptions associated with the specified
     * parameter ID,
     * or an empty (zero-length) array if no exceptions are associated with the
     * specified parameter ID.
     */
    Exception[] getExceptionArray(String id);

}
