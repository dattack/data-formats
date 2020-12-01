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

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Convenience class for reading CSV files using a provided CSVConfiguration.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class CSVReader implements Closeable {

    private final CSVConfiguration configuration;
    private final File dataFile;
    private BufferedReader bufferedReader;

    public CSVReader(final CSVConfiguration configuration, final File dataFile) {
        this.configuration = configuration;
        this.dataFile = dataFile;
    }

    @Override
    public void close() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

    private BufferedReader getReader() throws IOException {

        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile),
                    StandardCharsets.UTF_8));
        }
        return bufferedReader;
    }

    private boolean isComment(final String line) {
        return line.trim().startsWith(Character.toString(configuration.getCommentChar()));
    }

    /**
     * Iterate over the reader and returns the next object.
     *
     * @return the next object or <code>null</code> if no one exists
     * @throws IOException if an I/O error occurs
     */
    public synchronized CSVObject next() throws IOException {

        String line = "";
        CSVObject object = null;
        while (object == null && (line = getReader().readLine()) != null) {

            line = line.trim();
            if (line.isEmpty() || isComment(line)) {
                continue;
            }
            object = new CSVObject(configuration.getSeparator(), line);
        }

        return object;
    }
}
