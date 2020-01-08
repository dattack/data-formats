/*
 * Copyright (c) 2020, The Dattack team (http://www.dattack.com)
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

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public final class CSVBuilderTest {

    @Test
    public void testClear() {

        CSVConfiguration configuration = new CSVConfiguration.CsvConfigurationBuilder().build();
        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        String csv = csvBuilder
                .append("simple text")
                .comment()
                .append("another comment")
                .eol()
                .clear()
                .toString();

        String expected = "";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithEmptyProperties() {

        Properties properties = new Properties();

        CSVConfiguration configuration = new CSVConfiguration.CsvConfigurationBuilder(properties).build();
        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        LocalDateTime localDateTime =
                Instant.ofEpochMilli(1577970855000L) // 2020-01-02T13:14:15+00:00
                        .atZone(ZoneId.of("UTC")).toLocalDateTime();

        String csv = csvBuilder.comment("comment line").eol() //
                .append(1000L) // Long
                .append(true)
                .append(1000.5) // Double
                .append(false)
                .append("simple text")
                .append("text with \"special\" chars") // quote
                .append(java.sql.Timestamp.valueOf(localDateTime))
                .append(10.2F) // Float
                .append(1) // Integer
                .comment()
                .append("another comment")
                .eol()
                .toString();

        String expected = "#comment line\n" + //
                "\n" + //
                "1000,\"true\",1000.5,\"false\",\"simple text\",\"text with \\\"special\\\" chars\"" +
                ",2020/01/02 13:14:15.0,10.2,1\n#another comment\n";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithCustomBuilder() {

        CSVConfiguration configuration = new CSVConfiguration.CsvConfigurationBuilder().withCommentChar('@') //
                .withEol("<EOL>\n") //
                .withEscapeChar('/') //
                .withNullValue("(NULL)") //
                .withQuoteChar('#') //
                .withSeparator("\t") //
                .withTrueValue("T_R_U_E") //
                .withFalseValue("F_A_L_S_E") //
                .withDateFormat("yyyy-MM-dd'T'HH:mm:ss") //
                .build();

        executeTestWithCustomConfiguration(configuration);
    }

    private void executeTestWithCustomConfiguration(CSVConfiguration configuration) {

        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        LocalDateTime localDateTime =
                Instant.ofEpochMilli(1577970855000L) // 2020-01-02T13:14:15+00:00
                        .atZone(ZoneId.of("UTC")).toLocalDateTime();

        String csv = csvBuilder.comment("comment line").eol() //
                .append(1000L)
                .append(true)
                .append(1000.5)
                .append(false)
                .append("simple text")
                .append("text with #special# chars")
                .append(java.sql.Timestamp.valueOf(localDateTime))
                .append(10.2F)
                .append(1)
                .eol()
                .toString();

        String expected = "@comment line<EOL>\n" + //
                "<EOL>\n" + //
                "1000\t#T_R_U_E#\t1000.5\t#F_A_L_S_E#\t#simple text#\t#text with /#special/# chars#" +
                "\t2020-01-02T13:14:15\t10.2\t1<EOL>\n";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithCustomProperties() {
        Properties properties = new Properties();
        properties.setProperty("csv.comment.value", "@");
        properties.setProperty("csv.eol.value", "<EOL>\n");
        properties.setProperty("csv.escape.value", "/");
        properties.setProperty("csv.null.value", "(NULL)");
        properties.setProperty("csv.quote.value", "#");
        properties.setProperty("csv.separator.value", "\t");
        properties.setProperty("csv.true.value", "T_R_U_E");
        properties.setProperty("csv.false.value", "F_A_L_S_E");
        properties.setProperty("csv.datetime.format", "yyyy-MM-dd'T'HH:mm:ss");

        CSVConfiguration configuration = new CSVConfiguration.CsvConfigurationBuilder(properties).build();

        executeTestWithCustomConfiguration(configuration);
    }

    @Test
    public void testEscapeQuotaCharContainingQuotaChar() {
        CSVStringBuilder csvBuilder = new CSVStringBuilder(getCsvConfiguration());
        csvBuilder.append("hello");
        csvBuilder.append("world");
        csvBuilder.append("prefix\"suffix");
        assertEquals("\"hello\",\"world\",\"prefix\\\"suffix\"", csvBuilder.toString());
        System.out.println(csvBuilder.toString());
    }

    private CSVConfiguration getCsvConfiguration() {
        return new CSVConfiguration.CsvConfigurationBuilder()
                .withEscapeChar('\\')
                .build();
    }
}
