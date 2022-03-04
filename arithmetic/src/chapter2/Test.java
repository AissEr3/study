package chapter2;

public class Test {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{21,15,32,64,85,25,32,54,12,11,65};
        Merge.sort(a);
        System.out.println(Insertion.show(a));
    }
}
