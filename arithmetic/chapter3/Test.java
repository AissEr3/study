package arithmetic.chapter3;

public class Test {
    public static void main(String[] args) {
        OrderedSequentialSearchST<Integer,String> test = new OrderedSequentialSearchST();
        test.put(10,"10");
        test.put(20,"20");
        test.put(15,"15");
        test.put(7,"7");
        test.put(100,"100");
        test.put(1,"1");
        for(int i = 0; i < test.size(); i++){
            System.out.print(test.get(test.select(i))+" ");
        }
        System.out.println();
        System.out.println(test.keys(1,100));
        System.out.println(test.keys(8,20));
        System.out.println(test.keys(10,101));
        System.out.println(test.keys(8,71));
        System.out.println(test.keys(0,101));
    }
}
