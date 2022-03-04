package chapter2;

import java.util.Arrays;

public class Selection {
    public static void sort(Comparable[] a){
        for(int i = 0; i < a.length - 1;i++){
            int t = i;
            for(int j = i + 1; j < a.length; j++){
                if(less(a[j],a[t]))
                    t = j;
            }
            exchange(a,i,t);
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static String show(Comparable[] a){
       return Arrays.toString(a);
    }

    public static boolean isSort(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i],a[i - 1]))
                return false;
        }
        return true;
    }
}
