package chapter2;

public class MaxPQ<Key extends Comparable<? super Key>>{
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxLength){
        pq = (Key[]) new Comparable[maxLength];
    }

    public Key delMax(){
        Key max = pq[1];
        exchange(pq,1,N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    private void swim(int k){
        while(k > 1 && less(k/2 , k) ){
            exchange(pq,k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while(k*2 < N){
            int j = k *2;
            if(j < N && less(pq[j], pq[j+1])) {
                j++;
            }
            if(!less(k , j))
                break;
            exchange(pq,k , j); k = j;
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
}
