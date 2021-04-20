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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * A utility class for creating CSV data from scratch. This class is not thread-safe and with no guarantee of
 * synchronization. The methods in this class can be chained to add multiple rows/columns to the object.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class CSVStringBuilder {

    private static final int DEFAULT_CAPACITY = 100;

    private boolean isComment;

    private final CSVConfiguration configuration;
    private boolean emptyLine;
    private final StringBuilder rebuild;
    private final DecimalFormat decimalFormat;

    public CSVStringBuilder(final CSVConfiguration configuration) {
        this(configuration, DEFAULT_CAPACITY);
    }

    /**
     * Constructs a CSV builder with an initial capacity specified by the <code>capacity</code> argument.
     *
     * @param configuration the CSV configuration
     * @param capacity      the initial capacity of the internal buffer
     */
    public CSVStringBuilder(final CSVConfiguration configuration, final int capacity) {
        this.configuration = configuration;
        this.rebuild = new StringBuilder(capacity);
        this.emptyLine = true;
        this.isComment = false;

        decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Date value) {
        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else {
            appendDirectValue(configuration.getDateFormat().format(value));
        }
        return this;
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final java.sql.Date value) {
        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else {
            appendDirectValue(configuration.getSqlDateFormat().format(value));
        }
        return this;
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Time value) {
        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else {
            appendDirectValue(configuration.getTimeFormat().format(value));
        }
        return this;
    }


    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Timestamp value) {
        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else {
            appendDirectValue(configuration.getTimestampFormat().format(value));
        }
        return this;
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final BigDecimal value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final BigDecimal value, String format) {
        return appendDirectNumber(value, format);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Double value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final Double value, String format) {
        return appendDirectNumber(value, format);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Float value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final Float value, String format) {
        return appendDirectNumber(value, format);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Integer value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final Integer value, String format) {
        return appendDirectNumber(value, format);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Long value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final Long value, String format) {
        return appendDirectNumber(value, format);
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final String value) {
        return append(value, null);
    }

    /**
     * Appends a formatted string using the specified format string.
     *
     * @param value  the new value to append
     * @param format a format string.
     * @return the instance of CSVStringBuilder
     * @see java.lang.String#format
     */
    public CSVStringBuilder append(final String value, String format) {

        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else if (format == null) {
            appendValue(value, !isComment);
        } else {
            String escapedValue = escape(value, !isComment);
            if (isComment) {
                appendDirectValue(String.format(format, escapedValue));
            } else {
                appendDirectValue(String.format(format, getQuotedString(escapedValue, true)));
            }
        }
        return this;
    }

    /**
     * Appends a new value.
     *
     * @param value the new value to append
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder append(final Boolean value) {
        if (value == null) {
            appendDirectValue(configuration.getNullStr());
        } else {
            if (value) {
                appendDirectValue(configuration.getTrueValue(), !isComment);
            } else {
                appendDirectValue(configuration.getFalseValue(), !isComment);
            }
        }

        return this;
    }

    private CSVStringBuilder appendDirectNumber(final Number value, String format) {

        if (Objects.isNull(value)) {
            appendDirectValue(configuration.getNullStr());
        } else {
            if (Objects.isNull(format)) {
                appendDirectValue(decimalFormat.format(value));
            } else {
                appendDirectValue(String.format(format, value));
            }
        }
        return this;
    }

    private void appendDirectValue(final String escapedValue) {
        appendDirectValue(escapedValue, false);
    }

    private void appendDirectValue(final String escapedValue, final boolean quote) {

        if (!emptyLine) {
            rebuild.append(configuration.getSeparator());
        }

        rebuild.append(getQuotedString(escapedValue, quote));

        emptyLine = false;
    }

    private void appendValue(final String value, final boolean quote) {
        appendDirectValue(escape(value, quote), quote);
    }

    private String getQuotedString(final String escapedValue, final boolean quote) {

        String quotedString = escapedValue;
        if (quote && '\u0000' != configuration.getQuoteChar()) {
            // NOT NULL char
            quotedString = configuration.getQuoteChar() + escapedValue + configuration.getQuoteChar();
        }
        return quotedString;
    }

    private String escape(final String value, final boolean quote) {

        String escapedValue = value;
        if (configuration.getEol().contains("\n")) {
            escapedValue = escapedValue.replaceAll("\n", " ");
        }

        if (quote && '\u0000' != configuration.getQuoteChar()) {
            // NOT NULL char
            escapedValue = escape(escapedValue, configuration.getQuoteChar(), configuration.getEscapeChar());
        } else {
            if (configuration.getSeparator().length() == 1) {
                escapedValue = escape(escapedValue, configuration.getSeparator().charAt(0),
                        configuration.getEscapeChar());
            }
        }

        return escapedValue;
    }

    private String escape(String value, char charToEscape, char escapeChar) {
        String replacement;
        if ('\\' == escapeChar) {
            replacement = "\\\\" + charToEscape;
        } else {
            replacement = Character.toString(escapeChar) + charToEscape;
        }
        return value.replaceAll(Character.toString(charToEscape), replacement);
    }

    /**
     * Clear the internal buffer.
     *
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder clear() {
        rebuild.setLength(0);
        emptyLine = true;
        isComment = false;
        return this;
    }

    /**
     * Starts a new comment block.
     *
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder comment() {
        comment(null, false);
        return this;
    }

    /**
     * Appends a comment.
     *
     * @param message the comment to add
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder comment(final String message) {
        return comment(message, true);
    }

    /**
     * Appends a comment.
     *
     * @param message the comment to add
     * @param eol     when true, it adds an EOL to the end of the comment
     * @return the instance of CSVStringBuilder
     */
    private CSVStringBuilder comment(final String message, final boolean eol) {
        return comment(message, !emptyLine || isComment, eol);
    }

    /**
     * Appends a comment.
     *
     * @param message           the comment to add
     * @param useEolAtBeginning when true, it adds an EOL to the beginning of the comment
     * @param useEolAtEnd       when true, it adds an EOL to the end of the comment
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder comment(final String message, final boolean useEolAtBeginning, final boolean useEolAtEnd) {

        if (useEolAtBeginning) {
            eol();
        }

        rebuild.append(configuration.getCommentChar());
        if (message != null) {
            rebuild.append(message);
        }

        isComment = true;

        if (useEolAtEnd) {
            eol();
        }

        return this;
    }

    /**
     * Appends the end-of-line mark.
     *
     * @return the instance of CSVStringBuilder
     */
    public CSVStringBuilder eol() {
        rebuild.append(configuration.getEol());
        emptyLine = true;
        isComment = false;
        return this;
    }

    @Override
    public String toString() {
        return rebuild.toString();
    }
}
