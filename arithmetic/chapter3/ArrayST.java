package arithmetic.chapter3;

import arithmetic.chapter1.Queue;

//无序数组符号表
public class ArrayST <Key extends Comparable<Key>,Value> {
    private Key[] key;
    private Value[] value;
    private int n;

    private static final int DEFAULT_CAPACITY = 32;
    private final int INCREASE_CAPACITY;

    public ArrayST() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayST(int capacity) {
        n = capacity;
        INCREASE_CAPACITY = capacity / 2;
        key = (Key[]) new Comparable[capacity];
        value = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value value){
        //如果值为空，从表中删除
        if(value == null){
            delete(key);
        }
        /*
            如果键不存在，插入键、值
            如果键存在，更新值
         */
        int i = ArraySTHelper.indexOfArray(key,this.key);
        if(i == -1){
            if(n >= this.key.length){
                boostCapacity();
            }
            this.key[n] = key;
            this.value[n] = value;
            n += 1;
        }
        else{
            this.value[i] = value;
        }
    }

    public Value get(Key key){
        int i = ArraySTHelper.indexOfArray(key,this.key);
        return i != -1 ? value[i] : null;
    }

    public void delete(Key key){
        delete(ArraySTHelper.indexOfArray(key,this.key));
    }

    private void delete(int index){
        for(int i = index; i < n - 1; i++){
            key[i] = key[i+1];
            value[i] = value[i+1];
        }
        n -= 1;
    }

    public boolean contains(Key key){
        return ArraySTHelper.indexOfArray(key,this.key) != -1 ? true : false;
    }

    public boolean isEmpty(){
        return n == 0 ? true : false;
    }

    public int size(){
        return n;
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new Queue<>();
        for(int i = 0; i < n; i++){
            q.enqueue(key[i]);
        }
        return q;
    }

    private void boostCapacity(){
        Key[] key1 = (Key[]) new Comparable[key.length + INCREASE_CAPACITY];
        Value[] value1 = (Value[]) new Object[value.length + INCREASE_CAPACITY];
        for(int i = 0; i < n ; i++) {
            key1[i] = key[i];
            value1[i] = value[i];
        }
        key = key1;
        value = value1;
    }
}

class ArraySTHelper {
    static int indexOfArray(Comparable target, Comparable[] key) {
        for(int i = 0; i < key.length; i++){
            if(target.compareTo(key[i]) == 0){
                return i;
            }
        }
        return -1;
    }
}
