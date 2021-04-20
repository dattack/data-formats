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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static com.dattack.formats.csv.CSVConfiguration.CsvConfigurationBuilder;

public final class CSVBuilderTest {

    @Test
    public void testClear() {

        CSVConfiguration configuration = new CsvConfigurationBuilder().build();
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
    public void testDate() {

        CSVConfiguration configuration = new CsvConfigurationBuilder()
                .withSeparator("\t")
                .withTimeZoneName("UTC")
                .withDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX") //
                .withTimeFormat("hh:mm:ss.SSS a") //
                .withTimestampFormat("yyyy/MM/dd'T'HH:mm:ss.SSSXXX") //
                .build();

        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        ZonedDateTime utc = ZonedDateTime.of(2020, 12, 4, 23, 51, 57, 3_000_000, ZoneOffset.UTC);

        String csv = csvBuilder
                .append(java.util.Date.from(utc.toInstant()))
                .append(java.sql.Date.valueOf(utc.toLocalDate().atStartOfDay().toLocalDate()))
                .append(java.sql.Time.valueOf(utc.toLocalTime()))
                .append(java.sql.Timestamp.valueOf(utc.withZoneSameInstant(ZoneOffset.systemDefault()).toLocalDateTime()))
                .toString();

        String expected = "2020-12-04 23:51:57.003Z\t2020-12-04\t11:51:57.000 PM\t2020/12/04T23:51:57.003Z";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithEmptyProperties() {

        Properties properties = new Properties();

        CSVConfiguration configuration = new CsvConfigurationBuilder(properties)
                .withTimeZoneName("UTC")
                .build();
        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        ZonedDateTime dateTime = ZonedDateTime.of(2020, 1, 2, 13, 14, 15, 3_000_000, ZoneOffset.UTC);

        String csv = csvBuilder.comment("comment line").eol() //
                .append(1000L) // Long
                .append(true)
                .append(1000.5) // Double
                .append(false)
                .append("simple text")
                .append("text with \"special\" chars") // quote
                .append(java.sql.Timestamp.from(dateTime.toInstant()))
                .append(10.2)
                .append(new BigDecimal(BigInteger.valueOf(105), 1))
                .append(1) // Integer
                .comment()
                .append("another comment")
                .eol()
                .toString();

        String expected = "#comment line\n" + //
                "\n" + //
                "1000,\"true\",1000.5,\"false\",\"simple text\",\"text with \\\"special\\\" chars\"" +
                ",2020-01-02T13:14:15.003Z,10.2,10.5,1\n#another comment\n";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithCustomBuilder() {

        CSVConfiguration configuration = new CsvConfigurationBuilder().withCommentChar('@') //
                .withEol("<EOL>\n") //
                .withEscapeChar('/') //
                .withNullValue("(NULL)") //
                .withQuoteChar('#') //
                .withSeparator("\t") //
                .withTrueValue("T_R_U_E") //
                .withFalseValue("F_A_L_S_E") //
                .withDateFormat("yyyy-MM-dd HH:mm:ssXXX") //
                .withTimestampFormat("yyyy-MM-dd HH:mm:ssXXX") //
                .withTimeZoneName("UTC")
                .build();

        executeTestWithCustomConfiguration(configuration);
    }

    private void executeTestWithCustomConfiguration(CSVConfiguration configuration) {

        CSVStringBuilder csvBuilder = new CSVStringBuilder(configuration);

        Instant instant = Instant.ofEpochMilli(1577970855003L); // 2020-01-02T13:14:15+00:00

        String csv = csvBuilder.comment("comment line").eol() //
                .append(1000L)
                .append(true)
                .append(1000.5)
                .append(false)
                .append("simple text")
                .append("text with #special# chars")
                .append(java.sql.Timestamp.from(instant))
                .append(10.2)
                .append(new BigDecimal(BigInteger.valueOf(105), 1))
                .append(1)
                .eol()
                .toString();

        String expected = "@comment line<EOL>\n" + //
                "<EOL>\n" + //
                "1000\t#T_R_U_E#\t1000.5\t#F_A_L_S_E#\t#simple text#\t#text with /#special/# chars#" +
                "\t2020-01-02 13:14:15Z\t10.2\t10.5\t1<EOL>\n";
        assertEquals(expected, csv);
    }

    @Test
    public void testWriteWithCustomProperties() {
        Properties properties = new Properties();
        properties.setProperty(CsvConfigurationBuilder.COMMENT_CHAR_PROPERTY_NAME, "@");
        properties.setProperty(CsvConfigurationBuilder.EOL_PROPERTY_NAME, "<EOL>\n");
        properties.setProperty(CsvConfigurationBuilder.ESCAPE_CHAR_PROPERTY_NAME, "/");
        properties.setProperty(CsvConfigurationBuilder.NULL_VALUE_PROPERTY_NAME, "(NULL)");
        properties.setProperty(CsvConfigurationBuilder.QUOTE_CHAR_PROPERTY_NAME, "#");
        properties.setProperty(CsvConfigurationBuilder.SEPARATOR_PROPERTY_NAME, "\t");
        properties.setProperty(CsvConfigurationBuilder.TRUE_PROPERTY_NAME, "T_R_U_E");
        properties.setProperty(CsvConfigurationBuilder.FALSE_PROPERTY_NAME, "F_A_L_S_E");
        properties.setProperty(CsvConfigurationBuilder.DATE_FORMAT_PROPERTY_NAME, "yyyy-MM-dd HH:mm:ssXXX");
        properties.setProperty(CsvConfigurationBuilder.TIMESTAMP_FORMAT_PROPERTY_NAME, "yyyy-MM-dd HH:mm:ssXXX");
        properties.setProperty(CsvConfigurationBuilder.TIMEZONE_NAME_PROPERTY_NAME, "UTC");

        CSVConfiguration configuration = new CsvConfigurationBuilder(properties).build();

        executeTestWithCustomConfiguration(configuration);
    }

    @Test
    public void testEscapeQuotaCharContainingQuotaChar() {
        CSVStringBuilder csvBuilder = new CSVStringBuilder(getCsvConfiguration());
        csvBuilder.append("hello");
        csvBuilder.append("world");
        csvBuilder.append("prefix\"suffix");
        assertEquals("\"hello\",\"world\",\"prefix\\\"suffix\"", csvBuilder.toString());
    }

    private CSVConfiguration getCsvConfiguration() {
        return new CsvConfigurationBuilder()
                .withEscapeChar('\\')
                .build();
    }
}
