package co.proteus.java;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java10 {

    private void localTypeInference() {
        var list = new ArrayList<String>();
        var var = "var is a reserved type; it is not an identifier. This is not recommended.";

    }

    private void nowHasCharSetConstructor() throws IOException {
        var example1 = new PrintWriter("foo.txt", StandardCharsets.UTF_8);
        var example2 = new PrintStream("foo.txt", StandardCharsets.UTF_8);
        var example3 = new Formatter("foo.txt", StandardCharsets.UTF_8, Locale.ENGLISH);
        var example4 = new Scanner(new File("foo.txt"), StandardCharsets.UTF_8);
    }

    private void transferTo() throws IOException {
        var reader = new StringReader("content");
        var writer = new StringWriter();
        reader.transferTo(writer);
    }

    private void localizedBy() {
        var dateTimeFormatter = DateTimeFormatter.RFC_1123_DATE_TIME.localizedBy(Locale.ENGLISH);
    }

    private void collections() {
        List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toUnmodifiableList());
        collect.add(5);
    }
}

// JDK
//  Application CDS - http://openjdk.java.net/jeps/310
//  Unicode improvements - http://openjdk.java.net/jeps/314
//  Parallel GC for G1 - http://openjdk.java.net/jeps/307