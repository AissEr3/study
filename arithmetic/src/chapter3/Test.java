package chapter3;

public class Test {
    public static void main(String[] args) {
        BTS<Integer,Integer> test = new BTS<>();
        for(int i = 0; i < 100; i++){
            test.put(i,i);
        }
        System.out.println(test.height());
    }
}
