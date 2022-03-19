//package StudyJunit5;
//
//import static java.time.Duration.ofMillis;
//import static java.time.Duration.ofMinutes;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTimeout;
//import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;
//
//public class AssertTest {
//
//    @Test
//    void standardAssertions() {
//        // 相对断言
//        assertEquals(2, 2);
//        // 多了错误信息
//        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
//        // 布尔表达式+错误信息
//        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated ‐‐ "
//                + "to avoid constructing complex messages unnecessarily.");
//    }
//
//    @Test
//    void groupedAssertions() {
//        // 标题，+若干断言即可（可以不用Lambda）
//        assertAll("person",
//                () -> assertEquals("John", person.getFirstName()),
//                () -> assertEquals("Doe", person.getLastName())
//        );
//    }
//
//    @Test
//    void dependentAssertions() {
//        // Within a code block, if an assertion fails the
//        // subsequent code in the same block will be skipped.
//        assertAll("properties",
//            () -> {
//                String firstName = person.getFirstName();assertNotNull(firstName);
//                // Executed only if the previous assertion is valid.
//                assertAll("first name",
//                        () -> assertTrue(firstName.startsWith("J")),
//                        () -> assertTrue(firstName.endsWith("n")));
//            },
//            () -> {
//                // Grouped assertion, so processed independently
//                // of results of first name assertions.
//                String lastName = person.getLastName();
//                assertNotNull(lastName);
//                // Executed only if the previous assertion is valid.
//                assertAll("last name",
//                        () -> assertTrue(lastName.startsWith("D")),
//                        () -> assertTrue(lastName.endsWith("e")));
//            }
//        );
//    }
//
//    @Test
//    void exceptionTesting() {
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
//            throw new IllegalArgumentException("a message");
//        });
//        assertEquals("a message", exception.getMessage());
//    }
//
//    @Test
//    void timeoutNotExceeded() {
//        // The following assertion succeeds.
//        assertTimeout(ofMinutes(2), () -> {
//        // Perform task that takes less than 2 minutes.
//        });
//    }
//
//    @Test
//    void timeoutNotExceededWithResult() {
//        // The following assertion succeeds, and returns the supplied object.
//        String actualResult = assertTimeout(ofMinutes(2), () -> {
//            return "a result";
//        });
//        assertEquals("a result", actualResult);
//    }
//
//    @Test
//    void timeoutNotExceededWithMethod() {
//
//    }
//
//    @Test
//    void timeoutExceeded() {
//        // The following assertion fails with an error message similar to:
//        // execution exceeded timeout of 10 ms by 91 ms
//        assertTimeout(ofMillis(10), () -> {
//        // Simulate task that takes more than 10 ms.
//                Thread.sleep(100);
//        });
//    }
//
//    @Test
//    void timeoutExceededWithPreemptiveTermination() {
//        // The following assertion fails with an error message similar to:
//        // execution timed out after 10 ms
//        assertTimeoutPreemptively(ofMillis(10), () -> {
//        // Simulate task that takes more than 10 ms.
//                Thread.sleep(100);
//        });
//    }
//
//    private static String greeting() {
//        return "hello world!";
//    }
//}
