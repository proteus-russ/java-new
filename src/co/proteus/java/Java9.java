package co.proteus.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface Java9Interface {
    default void defaultSomething1() {
        IAmPrivate(); // Shared code
    }
    default void defaultSomething2() {
        IAmPrivate(); // Shared code
    }
    private void IAmPrivate() {
        System.out.println("I am private");
    }
}

@SuppressWarnings({"DeprecatedIsStillUsed", "SameParameterValue"})
public class Java9 implements Java9Interface {

    // BIG FEATURE - Modularity

    // jshell

    // JavaDoc search and HTML5

    // Flow - https://docs.oracle.com/javase/9/docs/api/index.html?java/util/concurrent/Flow.html

    // Multi-release jar file

    // Underscore as now a reserved word

    // CompletableFuture enhancements. Delays, Timeouts, Subclassing, stage utility methods.

    public void optional() {

        // .stream
        Stream<Optional<Employee>> emp = getEmployeeStream("Some employee ID");
        Stream<Employee> empStream = emp.flatMap(Optional::stream);

        // .or
        Optional<Employee> or = getEmployee("123").or(() -> Optional.of(new Employee("123")));

        // .ifPresentOrElse
        or.ifPresentOrElse(employee -> {
            System.out.println("Present");
        }, () -> {
            System.out.println("Else");
        });

    }

    private void stream() {
        println("takeWhile");
        Stream.of(1,2,3,4,5,6).takeWhile(integer -> integer <= 3).forEach(this::println);

        println("dropWhile");
        Stream.of(1,2,3,4,5,6).dropWhile(integer -> integer < 4).forEach(this::println);

        println("IntStream.iterate(2, x -> x < 20, x -> x * x).forEach(this::println)");
        IntStream.iterate(2, x -> x < 20, x -> x * x).forEach(this::println);

        Stream.ofNullable(null).forEach(this::println);
    }

    private void file() throws IOException {
        // Memory mapped now
        Files.lines(Path.of(".gitignore")).forEach(this::println);
    }


    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void factoryCollectionMethods() {
        // Immutable
        List<Integer> integers = List.of(1, 2, 3);
        integers.add(4);
        Set<Integer> integerSet = Set.of(1, 2, 3);
        Map<Integer, String> map = Map.of(1, "one");
    }

    public static void main(String[] args) throws Exception {
        Java9 java9 = new Java9();
        java9.file();
    }


    private void println(Object obj) {
        System.out.println(obj);
        System.out.flush();
    }
    @Deprecated(since = "1.0", forRemoval = true)
    private Optional<Employee> getEmployee(String some_employee_id) {
        return Optional.of(new Employee(some_employee_id));
    }
    private Stream<Optional<Employee>> getEmployeeStream(String some_employee_id) {
        return Stream.of(getEmployee(some_employee_id));
    }
}

// JDK
//  Concurrent Class Unloading
//  Compact Strings (old generation)
//  Indified String Concatenation (6+ implementations)
//  Marlin graphics renderer - same performance as closed source implementation
//  Spin-Wait Hint(s)
//  Unsafe Replacements
//      VarHandle
//      Fences to prevent op reordering
//  AOT introduced / experimental
//  ProcessHandle
//  New Version Format - FEATURE.INTERIM.UPDATE.PATCH
//  Directory Layout Has Changed!!!
