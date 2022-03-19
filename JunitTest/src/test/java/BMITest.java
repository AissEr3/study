import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BMITest {
    private static final String[] results = {"∆´ ›","’˝≥£","∆´≈÷","∑ ≈÷"};
    private BMI bmi;

    public BMITest(){
        bmi = new BMI();
    }

    @Tag("importance")
    @Tag("abnormalNumber")
    @ParameterizedTest
    @MethodSource("abnormalNumberSource")
    @DisplayName("“Ï≥£ ˝æ›≤‚ ‘")
    void testAbnormalNumber(double w,double h,String expected){
        bmi.setParams(w,h);
        assertFalse(expected.equals(bmi.getBMIType()));
    }

    static Stream<Arguments> abnormalNumberSource(){
        return Stream.of(
                Arguments.of(Double.MAX_VALUE,Double.MAX_VALUE,results[0]),
                Arguments.of(4737,16,results[1]),
                Arguments.of(6145,16,results[2]),
                Arguments.of(7169,16,results[3])
        );
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"45,1.6","53.17,1.7","47.10,1.6"})
    @DisplayName("∆´ ›≤‚ ‘")
    void testThinnish(double w,double h){
        bmi.setParams(w,h);
        assertEquals(results[0],bmi.getBMIType());
    }

    @Tag("healthy")
    @ParameterizedTest
    @CsvSource({"47.37,1.6","60.18,1.7","61.18,1.6"})
    @DisplayName("’˝≥£ÃÂ÷ÿ≤‚ ‘")
    void testNormal(double w,double h){
        bmi.setParams(w,h);
        assertEquals(results[1],bmi.getBMIType());
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"61.45,1.6","75.55,1.7","71.42,1.6"})
    @DisplayName("∆´≈÷≤‚ ‘")
    void testChubby(double w,double h){
        bmi.setParams(w,h);
        assertEquals(results[2],bmi.getBMIType());
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"71.69,1.6","150,1.7","71.99,1.6"})
    @DisplayName("∑ ≈÷≤‚ ‘")
    void testFat(double w,double h){
        bmi.setParams(w,h);
        assertEquals(results[3],bmi.getBMIType());
    }


}
