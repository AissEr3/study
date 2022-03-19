package StudyJunit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.Test;
class AssumptionsDemo {

    @Test
    // 通过,记录结果
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals("CI"));
    }

    @Test
    // 不通过，不记录结果
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
            // remainder of test
    }

    @Test
    // 假设结果：不通过；断言结果：通过；最终结果：通过
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                // perform these assertions only on the CI server
                assertEquals(2, 2);
        });
        // perform these assertions in all environments
        assertEquals("a string", "a string");
    }
}