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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TimeZone;

/**
 * A configuration object used when reading or creating a CSV document.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public final class CSVConfiguration {

    /**
     * The name of the property containing the comment-line character to use. Default is <i>#</i>.
     */
    public static final String COMMENT_CHAR_PROPERTY_NAME = "csv.comment.value";

    /**
     * The name of the property containing the end-of-line character to use. Default is the system-dependent line
     * separator string:
     * {@code System.getProperty("line.separator") }
     */
    public static final String EOL_PROPERTY_NAME = "csv.eol.value";

    /**
     * The name of the property containing the escape character to use. Default is <i>\</i>.
     */
    public static final String ESCAPE_CHAR_PROPERTY_NAME = "csv.escape.value";

    /**
     * The name of the property containing the substitution string to use when a value is {@code null}.
     */
    public static final String NULL_VALUE_PROPERTY_NAME = "csv.null.value";

    /**
     * The name of the property containing the quote character to use. Default is <i>"</i>.
     */
    public static final String QUOTE_CHAR_PROPERTY_NAME = "csv.quote.value";

    /**
     * The name of the property containing the string to use as field separator. Default is a comma (<i>,</i>).
     */
    public static final String SEPARATOR_PROPERTY_NAME = "csv.separator.value";

    /**
     * The name of the property containing the string to print when a boolean is TRUE. Default value is <i>true</i>.
     */
    public static final String TRUE_PROPERTY_NAME = "csv.true.value";

    /**
     * The name of the property containing the string to print when a boolean is FALSE. Default value is
     * <i>false</i>.
     */
    public static final String FALSE_PROPERTY_NAME = "csv.false.value";

    /**
     * The name of the property containing the date format to use. Default value is {@code yyyy-MM-dd'T'HH:mm:ss
     * .SSSXXX}.
     */
    public static final String DATE_FORMAT_PROPERTY_NAME = "csv.format.java.util.date";

    /**
     * The name of the property containing the date format to use. Default value is {@code yyyy-MM-dd}.
     */
    public static final String SQL_DATE_FORMAT_PROPERTY_NAME = "csv.format.java.sql.date";

    /**
     * The name of the property containing the time format to use. Default value is {@code HH:mm:ss.S}.
     */
    public static final String TIME_FORMAT_PROPERTY_NAME = "csv.format.java.sql.time";

    /**
     * The name of the property containing the time format to use. Default value is {@code yyyy-MM-dd'T'HH:mm:ss
     * .SSSXXX}.
     */
    public static final String TIMESTAMP_FORMAT_PROPERTY_NAME = "csv.format.java.sql.timestamp";

    /**
     * The name of the property containing the time zone to use.
     */
    public static final String TIMEZONE_NAME_PROPERTY_NAME = "csv.timezone";

    private final char commentChar;
    private final String eol;
    private final char escapeChar;
    private final String nullValue;
    private final String trueValue;
    private final String falseValue;
    private final char quoteChar;
    private final DateFormat dateFormat;
    private final DateFormat sqlDateFormat;
    private final DateFormat timeFormat;
    private final DateFormat timestampFormat;
    private final String separator;

    /**
     * Builder class used to build a {@link CSVConfiguration} instance.
     */
    public static final class CsvConfigurationBuilder {

        /**
         * The default comment-line character to use.
         */
        private static final char DEFAULT_COMMENT_CHAR = '#';

        /**
         * The end-of-line character to use.
         */
        private static final String DEFAULT_EOL = System.getProperty("line.separator");

        /**
         * The default escape character to use if none is supplied.
         */
        private static final char DEFAULT_ESCAPE_CHARACTER = '\\';

        /**
         * This is the "null" character - if a value is set to this then it is ignored.
         */
        private static final String DEFAULT_NULL_VALUE = "";

        /**
         * The default quote character to use if none is supplied.
         */
        private static final char DEFAULT_QUOTE_CHARACTER = '"';

        /**
         * The default separator to use if none is supplied.
         */
        private static final String DEFAULT_SEPARATOR = ",";

        private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        private static final String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd";

        private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss.SSS";

        private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        // ---------------------------------------------------------------------


        private char commentChar;
        private String eol;
        private char escapeChar;
        private String nullValue;
        private char quoteChar;
        private String separator;
        private String trueValue;
        private String falseValue;
        private String dateFormat;
        private String sqlDateFormat;
        private String timeFormat;
        private String timestampFormat;
        private String timeZoneName;

        /**
         * Class constructor using custom configuration.
         *
         * @param properties the object containing
         */
        private CsvConfigurationBuilder(Properties properties) {
            this.commentChar = getChar(properties.getProperty(COMMENT_CHAR_PROPERTY_NAME), DEFAULT_COMMENT_CHAR);
            this.separator = properties.getProperty(SEPARATOR_PROPERTY_NAME, DEFAULT_SEPARATOR);
            this.nullValue = properties.getProperty(NULL_VALUE_PROPERTY_NAME, DEFAULT_NULL_VALUE);
            this.quoteChar = getChar(properties.getProperty(QUOTE_CHAR_PROPERTY_NAME), DEFAULT_QUOTE_CHARACTER);
            this.escapeChar = getChar(properties.getProperty(ESCAPE_CHAR_PROPERTY_NAME), DEFAULT_ESCAPE_CHARACTER);
            this.eol = properties.getProperty(EOL_PROPERTY_NAME, DEFAULT_EOL);
            this.trueValue = properties.getProperty(TRUE_PROPERTY_NAME, Boolean.TRUE.toString());
            this.falseValue = properties.getProperty(FALSE_PROPERTY_NAME, Boolean.FALSE.toString());
            this.dateFormat = properties.getProperty(DATE_FORMAT_PROPERTY_NAME, DEFAULT_DATE_FORMAT);
            this.sqlDateFormat = properties.getProperty(SQL_DATE_FORMAT_PROPERTY_NAME, DEFAULT_SQL_DATE_FORMAT);
            this.timeFormat = properties.getProperty(TIME_FORMAT_PROPERTY_NAME, DEFAULT_TIME_FORMAT);
            this.timestampFormat = properties.getProperty(TIMESTAMP_FORMAT_PROPERTY_NAME, DEFAULT_TIMESTAMP_FORMAT);
            this.timeZoneName = properties.getProperty(TIMEZONE_NAME_PROPERTY_NAME, null);
        }

        private char getChar(String value, char defaultValue) {
            if (value == null) {
                return defaultValue;
            }
            return value.charAt(0);
        }

        /**
         * Class constructor using default configuration.
         */
        private CsvConfigurationBuilder() {
            this.commentChar = DEFAULT_COMMENT_CHAR;
            this.separator = DEFAULT_SEPARATOR;
            this.nullValue = DEFAULT_NULL_VALUE;
            this.quoteChar = DEFAULT_QUOTE_CHARACTER;
            this.escapeChar = DEFAULT_ESCAPE_CHARACTER;
            this.trueValue = Boolean.TRUE.toString();
            this.falseValue = Boolean.FALSE.toString();
            this.eol = DEFAULT_EOL;
            this.dateFormat = DEFAULT_DATE_FORMAT;
            this.sqlDateFormat = DEFAULT_SQL_DATE_FORMAT;
            this.timeFormat = DEFAULT_TIME_FORMAT;
            this.timestampFormat = DEFAULT_TIMESTAMP_FORMAT;
            this.timeZoneName = null;
        }

        public CSVConfiguration build() {
            return new CSVConfiguration(this);
        }

        /**
         * Sets the date format to apply to instance of java.util.Date.
         *
         * @param format the format expression
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withDateFormat(final String format) {
            if (format != null) {
                this.dateFormat = format;
            }
            return this;
        }

        /**
         * Sets the date format to apply to instance of java.sql.Date.
         *
         * @param format the format expression
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withSqlDateFormat(final String format) {
            if (format != null) {
                this.sqlDateFormat = format;
            }
            return this;
        }

        /**
         * Sets the time format.
         *
         * @param format the format expression
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withTimeFormat(final String format) {
            if (format != null) {
                this.timeFormat = format;
            }
            return this;
        }

        /**
         * Sets the timestamp format.
         *
         * @param format the format expression
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withTimestampFormat(final String format) {
            if (format != null) {
                this.timestampFormat = format;
            }
            return this;
        }

        /**
         * Sets the name of the TimeZone.
         *
         * @param timeZoneName the name of the time zone
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withTimeZoneName(final String timeZoneName) {
            if (timeZoneName != null) {
                this.timeZoneName = timeZoneName;
            }
            return this;
        }

        /**
         * Sets the comment character delimiter.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withCommentChar(final char value) {
            this.commentChar = value;
            return this;
        }

        /**
         * Sets the end-of-line mark.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withEol(final String value) {
            this.eol = value;
            return this;
        }

        /**
         * Sets the escape character.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withEscapeChar(final char value) {
            this.escapeChar = value;
            return this;
        }

        /**
         * Sets the comment character delimiter.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withNullValue(final String value) {
            this.nullValue = value;
            return this;
        }

        /**
         * Sets the TRUE value.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withTrueValue(final String value) {
            this.trueValue = value;
            return this;
        }

        /**
         * Sets the FALSE value.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withFalseValue(final String value) {
            this.falseValue = value;
            return this;
        }

        /**
         * Sets the quote character.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withQuoteChar(final char value) {
            this.quoteChar = value;
            return this;
        }

        /**
         * Sets the separator character.
         *
         * @param value the value to set
         * @return the instance of CSVConfigurationBuilder
         */
        public CsvConfigurationBuilder withSeparator(final String value) {
            this.separator = value;
            return this;
        }
    }

    private CSVConfiguration(final CsvConfigurationBuilder builder) {
        this.commentChar = builder.commentChar;
        this.separator = builder.separator;
        this.nullValue = builder.nullValue;
        this.trueValue = builder.trueValue;
        this.falseValue = builder.falseValue;
        this.quoteChar = builder.quoteChar;
        this.escapeChar = builder.escapeChar;
        this.eol = builder.eol;
        this.dateFormat = new SimpleDateFormat(builder.dateFormat);
        this.sqlDateFormat = new SimpleDateFormat(builder.sqlDateFormat);
        this.timeFormat = new SimpleDateFormat(builder.timeFormat);
        this.timestampFormat = new SimpleDateFormat(builder.timestampFormat);
        if (builder.timeZoneName != null) {
            this.dateFormat.setTimeZone(TimeZone.getTimeZone(builder.timeZoneName));
            this.timestampFormat.setTimeZone(TimeZone.getTimeZone(builder.timeZoneName));
        }
    }

    public static CsvConfigurationBuilder custom() {
        return new CsvConfigurationBuilder();
    }

    public static CsvConfigurationBuilder custom(final Properties properties) {
        return new CsvConfigurationBuilder(properties);
    }

    public char getCommentChar() {
        return commentChar;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public DateFormat getSqlDateFormat() {
        return sqlDateFormat;
    }

    public DateFormat getTimeFormat() {
        return timeFormat;
    }

    public DateFormat getTimestampFormat() {
        return timestampFormat;
    }

    public String getEol() {
        return eol;
    }

    public char getEscapeChar() {
        return escapeChar;
    }

    public String getNullStr() {
        return nullValue;
    }

    public String getTrueValue() {
        return trueValue;
    }

    public String getFalseValue() {
        return falseValue;
    }

    public char getQuoteChar() {
        return quoteChar;
    }

    public String getSeparator() {
        return separator;
    }
}
