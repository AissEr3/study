package chapter2;

import java.util.Arrays;

public class Merge {
    private static Comparable[] aux;//归并所需的辅助数组

    public static void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N; sz += sz){
            for(int lo = 0; lo < N - sz; lo += sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length - 1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if(hi <= lo){
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    private static void merge(Comparable[] a,int lo,int mid, int hi){
        int i = lo,j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                a[k] = aux[j++];
            }else if(j > hi){
                a[k] = aux[i++];
            }else if(less(aux[j],aux[i])){
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];
            }
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
