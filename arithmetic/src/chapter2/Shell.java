package chapter2;

import java.util.Arrays;

public class Shell {
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h < N / 3){
            h = h * 3 + 1;
        }
        while(h >= 1){
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && less(a[j],a[j - h]); j -= h){
                    exchange(a,j,j-h);
                }
            }
            h /= 3;
        }
    }

    public static boolean check(Comparable[] a){
        Comparable[] aClone = a.clone();
        sort(a);
        int count = a.length;
        for(int i = 0; i < aClone.length; i++){
            int c = count;
            for(int j = 0; j < count; j++){
                if(aClone[i] == a[j]){
                    count--;
                    break;
                }
            }
            if(c == count){
                return false;
            }
        }

        return isSort(a);
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
