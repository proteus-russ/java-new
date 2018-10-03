package co.proteus.java;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Java11 {

    // shebang and single file application compilation view java -  http://openjdk.java.net/jeps/330

    private void localTypeInference() {
        Consumer<String> example1 = (@Nonnull var s) -> {
            // Do something
        };

    }

    private void nowHasCharSetConstructor() throws IOException {
        var example1 = new FileReader("foo.txt", StandardCharsets.UTF_8);
        var example2 = new FileWriter("foo.txt", StandardCharsets.UTF_8);
    }

    private void nullStream() {
        var outputStream = OutputStream.nullOutputStream();
        var inputStream = InputStream.nullInputStream();
        var writer = Writer.nullWriter();
        var reader = Reader.nullReader();
    }

    private void stringGoodies() {
        var myString = "some string that was created";
        var strip = myString.strip(); // what's the difference with trim?
        var stripLeading = myString.stripLeading();
        var stripTrailing = myString.stripTrailing();
        boolean blank = myString.isBlank();
        Stream<String> lines = myString.lines();
        var indent = " ".repeat(4);
    }

    private void filesString() throws IOException {
        Path path = Path.of("foo.txt");
        String string = Files.readString(path, StandardCharsets.UTF_8);
        Files.writeString(path, "some string", StandardCharsets.UTF_8);
    }

    private void collection() {
        Integer[] objects = List.of(1, 2, 3, 4).toArray(Integer[]::new);
    }

    private void timeUnit() {
        long converted = TimeUnit.MILLISECONDS.convert(Duration.ofDays(2));
    }

    private void predicate() {
        Predicate<String> isNotBlank = Predicate.not(String::isBlank);
    }

    private void pattern() {
        Pattern fooPattern = Pattern.compile("foo");
        Predicate<String> predicate = fooPattern.asMatchPredicate();
    }

    private void httpClient() {
        // HTTP 1 & 2
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
        // WebSocket support too
    }

    public static void main(String[] args) {
        var java11 = new Java11();
        java11.httpClient();
    }
}

// JDK
//  Application CDS - http://openjdk.java.net/jeps/310
//  Unicode improvements - http://openjdk.java.net/jeps/314
//  Removed
//      corba
//      transaction
//      activation
//      xml.bind
//      xml.ws
//      xml.ws.annotation
//  Reminder for me to update ReflectUtil - http://openjdk.java.net/jeps/181
//  Epsilon GC - http://openjdk.java.net/jeps/318
//  Flight Recorder - http://openjdk.java.net/jeps/328
//  Low Overhead Heap Profiler - http://openjdk.java.net/jeps/331
//  TLS 1.3 - http://openjdk.java.net/jeps/332