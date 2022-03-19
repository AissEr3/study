import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BloodPressureTest {
    private static final String[] results = {"正常","正常高值","1级高血压","2级高血压","3级高血压"};
    private BloodPressure pressure;

    public BloodPressureTest(){
        pressure = new BloodPressure();
    }

    @Tag("healthy")
    @ParameterizedTest
    @CsvSource({"119,70"})
    @DisplayName("正常测试")
    void testNormal(int s,int d){
        pressure.setParams(s,d);
        assertEquals(results[0],pressure.getPressureLevel());
    }

    @Tag("healthy")
    @ParameterizedTest
    @CsvSource({"120,85","121,85","139,85"})
    @DisplayName("正常高值测试")
    void testNormal_height(int s,int d){
        pressure.setParams(s,d);
        assertEquals(results[1],pressure.getPressureLevel());
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"140,85","141,85","159,85"})
    @DisplayName("1级高血压测试")
    void testOneHeightBloodPressure(int s,int d){
        pressure.setParams(s,d);
        assertEquals(results[2],pressure.getPressureLevel());
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"160,85","179,85"})
    @DisplayName("2级高血压测试")
    void testTwoHeightBloodPressure(int s,int d){
        pressure.setParams(s,d);
        assertEquals(results[3],pressure.getPressureLevel());
    }

    @Tag("unhealthy")
    @ParameterizedTest
    @CsvSource({"180,85","181,85"})
    @DisplayName("2级高血压测试")
    void testThreeHeightBloodPressure(int s,int d){
        pressure.setParams(s,d);
        assertEquals(results[4],pressure.getPressureLevel());
    }
}
