# data-formats

[![Travis Badge](https://api.travis-ci.com/dattack/data-formats.svg?branch=develop)](https://travis-ci.com/dattack/data-formats/builds)
[![license](https://img.shields.io/:license-Apache-blue.svg?style=plastic-square)](LICENSE.md)
[![Maven Central](https://img.shields.io/maven-central/v/com.dattack/data-formats.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.dattack/data-formats)
[![javadoc](https://javadoc.io/badge2/com.dattack/data-formats/javadoc.svg)](https://javadoc.io/doc/com.dattack/data-formats)

A very simple Java library for reading and writing data in different formats.
Currently, the available formats are:

* CSV (Comma Separated Values).

## Where can I get the latest release?

You can pull it from the central Maven repositories:

```xml
<dependency>
    <groupId>com.dattack</groupId>
    <artifactId>data-formats</artifactId>
    <version>0.2.1</version>
</dependency>
```

## How-To

### Writing CSV data

1. Create a custom configuration by setting the properties of the CSV to be generated.
2. Instantiate the `CSVStringBuilder` class.
3. Append content to the instance of class `CSVStringBuilder` created in the previous step.

```java

import com.dattack.formats.csv.CSVConfiguration;
import com.dattack.formats.csv.CSVStringBuilder;

public class Example {

    // 1: create your customized configuration
    private CSVConfiguration getConfiguration() {
        return CSVConfiguration
                .custom()
                // .withCommentChar('#')
                // .withSeparator("\t")
                // ...
                .build();
    }

    // 2: instantiate the CSVStringBuilder class 
    private CSVStringBuilder getCsvBuilder() {
        return new CSVStringBuilder(getConfiguration());
    }

    public void writeCsv() {

        // 3: append content
        String csv = getCsvBuilder()
                .comment("comment line").eol() //
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
    }
}
```

### Reading a CSV file

1. Create a custom configuration by setting the properties of the CSV to be generated.
2. Instantiate the `CSVReader` class.
3. Iterate over the content.

```java

import com.dattack.formats.csv.CSVConfiguration;
import com.dattack.formats.csv.CSVObject;
import com.dattack.formats.csv.CSVReader;
import java.util.Objects;

public class Example {

    // 1: create your customized configuration
    private CSVConfiguration getConfiguration() {
        return CSVConfiguration
                .custom()
                // .withCommentChar('#')
                // .withSeparator("\t")
                // ...
                .build();
    }

    // 2: instantiate the CSVReader class
    private CSVReader getCsvReader(final File file) {
        return new CSVReader(getConfiguration(), file);
    }

    public static void readCsv(final File file) throws IOException {

        CSVReader csvReader = getCsvReader(file);

        // 3: iterate over the content
        CSVObject csvObject;
        while (Objects.nonNull(csvObject = csvReader.next())) {
            for (int i = 0; i < csvObject.getSize(); i++) {
                String value = csvObject.get(i);
                // ... 
            }
        }
    }
}
```

## Contributing

Pull requests and stars are always welcome.
For bugs and feature requests, [please create an issue](https://github.com/dattack/data-formats/issues).

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).
