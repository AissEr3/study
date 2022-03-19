
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackTest {
    // 测试使用栈
    private Stack stack;
    // 与栈对应的size记录是否正确
    private int size = 0;

    /*
        push方法：
        功能：入栈
        前置条件∶存在栈的对象，准备需要测试的数据
        检查项∶入栈是否成功，数据的数量记录是否正确
        结束后操作∶恢复栈
     */
    @Nested
    @DisplayName("测试push方法类")
    class pushTestClass{

        @BeforeEach
        void before(){
            // 初始化栈
            stack = new Stack();
            size = 0;
        }

        @AfterEach
        void after(){
            assertFalse(stack.isEmpty());
        }

        @DisplayName("单个数据入栈")
        @ParameterizedTest
        @ValueSource(ints = {1,-1,Integer.MAX_VALUE,Integer.MIN_VALUE})
        void testPushFunction_one(int data){
           assertPush(data);
        }

        @DisplayName("多个数据插入")
        @ParameterizedTest
        @CsvSource({"1,2,3","2,3,4"})
        void testPushFunction_much(String s1,String s2,String s3){
            ArrayList<String> list = new ArrayList<>(Arrays.asList(s1,s2,s3));
            for(int i = 0; i < list.size(); i++){
                assertPush(list.get(i));
            }
        }

        @DisplayName("入栈为null值")
        @ParameterizedTest
        @NullSource
        void testPushByNull(Object object){
            assertPush(object);
        }

        private void assertPush(Object data){
            assertAll(
                    () -> assertSame(data,stack.push(data),"data push error"),
                    () -> assertEquals(++size,stack.size(),"size error")
            );
        }

        // 后续测试插入数据使用
        private void pushData(Collection datas){
            ArrayList list = new ArrayList(datas);
            for(int i = 0; i < list.size(); i++){
                stack.push(list.get(i));
                size++;
            }
        }

        /*
            pop方法：
            功能：出栈
            检查项∶出栈是否成功，数据数量是否正确，异常情况
        */
        @Nested
        @DisplayName("测试pop方法")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class TestPop{
            private ArrayList data = new ArrayList(Arrays.asList(0,Integer.MAX_VALUE,"3",Double.MIN_VALUE,"6",null));

            @BeforeEach
            void before(){
                // 初始化栈并插入数据
                stack = new Stack();
                size = 0;
                pushData(data);
            }

            @AfterEach
            void after(){
                assertTrue(stack.isEmpty());
            }

            @DisplayName("出栈测试")
            @ParameterizedTest
            @MethodSource("MethodSource_testPop")
            void testPop_popAll(Object object){
                //断言
                assertSame(data.get(size-1),stack.pop(),"data pop error");
                assertEquals(--size,stack.size(),"size error");
            }

            Stream<Collection> MethodSource_testPop(){
                return data.stream();
            }

            @DisplayName("出栈异常情况")
            @Test
            void testPopException(){
                try{
                    stack = new Stack();
                    stack.pop();
                    fail("未抛出异常");
                }
                catch(Exception e){
                    assertInstanceOf(EmptyStackException.class,e);
                }
            }

            /*
                peak方法
                功能：获得栈顶的值
                检查点∶功能正确实现，异常情况
            */
            @Nested
            @DisplayName("测试peek方法")
            class TestPeak {
                @BeforeEach
                void before(){
                    stack = new Stack();
                }

                @DisplayName("测试peek方法")
                @ParameterizedTest
                @ValueSource(ints = {1, 0,-1,Integer.MIN_VALUE, Integer.MAX_VALUE})
                void testPeakFunction(int data) {
                    stack.push(data);
                    assertEquals(data,stack.peek(),"data peek error");
                    stack.pop();
                }

                @DisplayName("peek异常情况")
                @Test
                void testPeekException() {
                    Throwable throwable = assertThrows(EmptyStackException.class,() -> stack.peek());
                }
            }
        }
        /*
            search功能
            功能：查找栈顶到查找位置距离
            前置条件∶栈对象存在数据
            检查点∶功能正确情况，不存在数据情况
        */
        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class searchTest{
            private ArrayList list = new ArrayList(Arrays.asList(1,"2",4.5,Double.NaN,Integer.MAX_VALUE));
            private int index = 0;

            @BeforeEach
            void before(){
                stack = new Stack();
                pushData(list);
            }

            @DisplayName("测试search方法")
            @ParameterizedTest
            @MethodSource("SearchTest")
            void testSearchFunction(Object data) {
                assertEquals(list.size() - index++,stack.search(data),"data Search error");
            }

            Stream<Collection> SearchTest(){
                return list.stream();
            }
        }
    }

}
