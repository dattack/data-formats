/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
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
 */
package com.dattack.formats.csv;

/**
 * Represents the data row of a CSV file. It also provides methods to access the different columns of data it contains.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class CSVObject {

    private final String[] data;

    CSVObject(final String separator, final String text) {
        this.data = text.split(separator);
    }

    /**
     * Returns the number of elements in this object.
     *
     * @return the number of elements in this object.
     */
    public int getSize() {
        return data.length;
    }

    /**
     * Returns the element at the specified position in this object.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this object
     * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public String get(final int index) throws IndexOutOfBoundsException {
        return data[index];
    }
}
