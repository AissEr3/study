
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackTest {
    // ����ʹ��ջ
    private Stack stack;
    // ��ջ��Ӧ��size��¼�Ƿ���ȷ
    private int size = 0;

    /*
        push������
        ���ܣ���ջ
        ǰ�������ô���ջ�Ķ���׼����Ҫ���Ե�����
        ��������ջ�Ƿ�ɹ������ݵ�������¼�Ƿ���ȷ
        ����������ûָ�ջ
     */
    @Nested
    @DisplayName("����push������")
    class pushTestClass{

        @BeforeEach
        void before(){
            // ��ʼ��ջ
            stack = new Stack();
            size = 0;
        }

        @AfterEach
        void after(){
            assertFalse(stack.isEmpty());
        }

        @DisplayName("����������ջ")
        @ParameterizedTest
        @ValueSource(ints = {1,-1,Integer.MAX_VALUE,Integer.MIN_VALUE})
        void testPushFunction_one(int data){
           assertPush(data);
        }

        @DisplayName("������ݲ���")
        @ParameterizedTest
        @CsvSource({"1,2,3","2,3,4"})
        void testPushFunction_much(String s1,String s2,String s3){
            ArrayList<String> list = new ArrayList<>(Arrays.asList(s1,s2,s3));
            for(int i = 0; i < list.size(); i++){
                assertPush(list.get(i));
            }
        }

        @DisplayName("��ջΪnullֵ")
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

        // �������Բ�������ʹ��
        private void pushData(Collection datas){
            ArrayList list = new ArrayList(datas);
            for(int i = 0; i < list.size(); i++){
                stack.push(list.get(i));
                size++;
            }
        }

        /*
            pop������
            ���ܣ���ջ
            �����ó�ջ�Ƿ�ɹ������������Ƿ���ȷ���쳣���
        */
        @Nested
        @DisplayName("����pop����")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class TestPop{
            private ArrayList data = new ArrayList(Arrays.asList(0,Integer.MAX_VALUE,"3",Double.MIN_VALUE,"6",null));

            @BeforeEach
            void before(){
                // ��ʼ��ջ����������
                stack = new Stack();
                size = 0;
                pushData(data);
            }

            @AfterEach
            void after(){
                assertTrue(stack.isEmpty());
            }

            @DisplayName("��ջ����")
            @ParameterizedTest
            @MethodSource("MethodSource_testPop")
            void testPop_popAll(Object object){
                //����
                assertSame(data.get(size-1),stack.pop(),"data pop error");
                assertEquals(--size,stack.size(),"size error");
            }

            Stream<Collection> MethodSource_testPop(){
                return data.stream();
            }

            @DisplayName("��ջ�쳣���")
            @Test
            void testPopException(){
                try{
                    stack = new Stack();
                    stack.pop();
                    fail("δ�׳��쳣");
                }
                catch(Exception e){
                    assertInstanceOf(EmptyStackException.class,e);
                }
            }

            /*
                peak����
                ���ܣ����ջ����ֵ
                ����ù�����ȷʵ�֣��쳣���
            */
            @Nested
            @DisplayName("����peek����")
            class TestPeak {
                @BeforeEach
                void before(){
                    stack = new Stack();
                }

                @DisplayName("����peek����")
                @ParameterizedTest
                @ValueSource(ints = {1, 0,-1,Integer.MIN_VALUE, Integer.MAX_VALUE})
                void testPeakFunction(int data) {
                    stack.push(data);
                    assertEquals(data,stack.peek(),"data peek error");
                    stack.pop();
                }

                @DisplayName("peek�쳣���")
                @Test
                void testPeekException() {
                    Throwable throwable = assertThrows(EmptyStackException.class,() -> stack.peek());
                }
            }
        }
        /*
            search����
            ���ܣ�����ջ��������λ�þ���
            ǰ��������ջ�����������
            ����ù�����ȷ������������������
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

            @DisplayName("����search����")
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
