package chapter3;

public class Test {
    public static void main(String[] args) {
        BTS<Integer,String> test = new BTS<>();
        test.put(10,"10");
        test.put(3,"3");
        test.put(11,"11");
        test.put(1,"1");
        test.put(7,"7");
        test.put(9,"9");

        System.out.println(test.keys());
    }
}
