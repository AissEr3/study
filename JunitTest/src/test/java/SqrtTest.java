import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqrtTest {
    private static final double NEGATIVE_EXPECTED = Double.NaN;

    //用例包含：-1,0,最小整型数,非数字,负无穷
    @ParameterizedTest
    @ValueSource(doubles = {-1,-2,Integer.MIN_VALUE,Double.NaN,Double.NEGATIVE_INFINITY})
    public void negativeAndNaNTest(double value){
        assertEquals(NEGATIVE_EXPECTED,Math.sqrt(value));
    }

    //用例包含：0,1,4，最大正浮点数，最小正浮点数，最大正整数，正无穷
    @ParameterizedTest
    @ValueSource(doubles = {0,1,4,Double.MAX_VALUE,Double.MIN_VALUE,
                            Integer.MAX_VALUE,Double.POSITIVE_INFINITY})
    public void noneNegativeTest(double value){
        double actual = Math.sqrt(value);
        assertEquals(value,actual*actual,0.01);
    }

}
