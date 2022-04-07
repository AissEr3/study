package problem3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

/*
    判断闰年有bug，是4的倍数 且 不是100的倍数时（如2020年）错误判断为false
 */
public class TestDate {
    private Date date;
    private static Method setDayReflect;

    // 辅助设置Date类属性的值
    private static class TestDateHelper{
        private static Field yearReflect;
        private static Field monthReflect;
        private static Field dayReflect;

        // 初始化反射的属性
        public static void init(){
            try{
                yearReflect = Date.class.getDeclaredField("year");
                monthReflect = Date.class.getDeclaredField("month");
                dayReflect = Date.class.getDeclaredField("day");
            }catch (Exception e){
                e.printStackTrace();
            }
            yearReflect.setAccessible(true);
            monthReflect.setAccessible(true);
            dayReflect.setAccessible(true);
        }

        public static void setDate(Date obj,Integer year,Integer month, Integer day){
            setYear(obj, year);
            setMonth(obj,month);
            setDat(obj,day);
        }

        public static void setYear(Date obj,Integer year){
            try {
                yearReflect.set(obj,year);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public static void setMonth(Date obj,Integer month){
            try {
                monthReflect.set(obj,month);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public static void setDat(Date obj,Integer day){
            try {
                dayReflect.set(obj,day);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // 参数化数据
    private static Stream<Arguments> nullData(){
        return Stream.of(
                Arguments.of(null,1,1),
                Arguments.of(1,null,1),
                Arguments.of(1,1,null));
    }

    // 开始测试
    @BeforeAll
    public static void beforeAll() throws NoSuchMethodException {
        setDayReflect = Date.class.getDeclaredMethod("setDay", int.class);
        setDayReflect.setAccessible(true);
        TestDateHelper.init();
    }

    /*
        ---------- 测试 daysOfYear 方法 ----------
    */
    @ParameterizedTest
    @DisplayName("年的多少天_错误")
    @MethodSource("nullData") // null,1,1 ; 1,null,1 ; 1,1,null
    public void testDaysOfYear_ErrorDate(Integer year,Integer month, Integer day){
        date = new Date(1,1,1);
        TestDateHelper.setDate(date,year,month,day);
        assertThat(date.daysOfYear()).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("年的多少天_闰年")
    @CsvSource({"2020,1,1, 1" , "2020,2,29, 60" , "2020,12,31, 366"})
    public void testDaysOfYear_LeapYear(int year, int month, int day, int expected){
        date = spy(new Date(year, month, day));
        when(date.isLeapYear()).thenReturn(true);

        assertThat(date.daysOfYear()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("年的多少天_平年")
    @CsvSource({"2021,1,1, 1" , "2021,2,28, 59" , "2021,12,31, 365"})
    public void testDaysOfYear_NotLeapYear(int year, int month, int day, int expected){
        date = spy(new Date(year, month, day));
        when(date.isLeapYear()).thenReturn(false);

        assertThat(date.daysOfYear()).isEqualTo(expected);
    }

    /*
        ---------- 测试 dayOfWeek 方法 ----------
     */
    @ParameterizedTest
    @DisplayName("周的第几天_错误日期")
    @MethodSource("nullData") // null,1,1 ; 1,null,1 ; 1,1,null
    public void testDaysOfWeak_ErrorDate(Integer year, Integer month, Integer day){
        date = new Date(1,1,1);
        TestDateHelper.setDate(date,year,month,day);
        assertThat(date.dayOfWeek()).isEqualTo(-1);
    }

    @ParameterizedTest
    @DisplayName("周的第几天")
    @CsvSource({"2021,1,1, 1,5" , "2020,12,31, 366,4"})
    public void testDayOfWeek(int year, int month, int day, int daysOfYear,int expected){
        date = spy(new Date(year, month, day));
        when(date.daysOfYear()).thenReturn(daysOfYear);

        assertThat(date.dayOfWeek()).isEqualTo(expected);
    }


    /*
         ---------- 测试 isLeapYear 方法 ----------
     */
    @ParameterizedTest
    @DisplayName("判断闰年方法_异常")
    @ValueSource(ints = {-1,0})
    @NullSource
    public void testIsLeapYear_exception(Integer year){
        date = new Date(1, 1, 1);
        TestDateHelper.setYear(date,year);
        Assertions.assertThrows(ItemOutOfRange.class,() -> date.isLeapYear());
    }

    @ParameterizedTest
    @DisplayName("判断闰年方法")
    @CsvSource({"2020,1,1, true","2021,1,1, false","2000,1,1, true"})
    public void testIsLeapYear_function(int year ,int month, int day,boolean expected){
        date = new Date(year, month, day);
        assertThat(date.isLeapYear()).isEqualTo(expected);
    }

    /*
         ---------- 测试 setDay 方法 ----------
     */
    private void assertSetDay(Integer year, Integer month, Integer day, Integer expected){
        TestDateHelper.setYear(date,year);
        TestDateHelper.setMonth(date,month);
        try{
            setDayReflect.invoke(date,day);
            assertThat(TestDateHelper.dayReflect.get(date)).isEqualTo(expected);
        }catch (Exception e){
            fail("no this method");
        }
    }

    @ParameterizedTest
    @DisplayName("设置日期_年月为null")
    @MethodSource("nullData") // null,1,1 ; 1,null,1 ; 1,1,null
    public void testSetDay_Error(Integer year, Integer month, Integer day){
        date = new Date(1,1,1);
        if(day != null){
            assertSetDay(year,month,day,null);
        }
    }

    @ParameterizedTest
    @DisplayName("设置日期_日出错_非闰年")
    @CsvSource({"2021,1,0","2021,1,32","2021,2,29","2021,4,31"})
    public void testSetDay_Error_LeapYear(int year ,int month, int day){
        date = spy(new Date(1,1,1));
        when(date.isLeapYear()).thenReturn(false);
        assertSetDay(year,month,day,null);
    }

    @Test
    @DisplayName("设置日期_日出错_闰年")
    public void testSetDay_Error_NotLeapYear(){
        date = spy(new Date(1,1,1));
        when(date.isLeapYear()).thenReturn(true);
        assertSetDay(2000,2,30,null);
    }

    @ParameterizedTest
    @DisplayName("设置日期_非闰年")
    @CsvSource({"2021,1,1","2021,4,30","2021,2,28","2021,12,31"})
    public void testSetDay_eapYear(int year ,int month, int day){
        date = spy(new Date(1,1,1));
        when(date.isLeapYear()).thenReturn(false);
        assertSetDay(year,month,day,day);
    }

    @Test
    @DisplayName("设置日期_闰年")
    public void testSetDay_NotLeapYear(){
        date = spy(new Date(1,1,1));
        when(date.isLeapYear()).thenReturn(true);
        assertSetDay(2000,2,29,29);
    }
}
