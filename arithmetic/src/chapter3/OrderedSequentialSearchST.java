package chapter3;

import chapter1.Queue;

/*
    通过有序链表实现有序符号表
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value>{
    private Node head;
    private int size;

    private class Node{
        private final Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public OrderedSequentialSearchST(){
        head = new Node(null,null,null);
        size = 0;
    }

    /*
        将键值对存入表中（若值为空则将键key从表中删除）
        注意：插入时要实现有序，同时key不能重复，
        如果key重复，意味着修改对应key的value值
     */
    public void put(Key key, Value value){
        if(key == null){
            delete(key);
        }
        sortPut(key,value);
    }

    //  实现有序插入和修改value的值
    private void sortPut(Key key,Value value){
        Node newNode = new Node(key,value,null);
        Node h = this.head;
        while(h.next != null){
            int com = h.next.key.compareTo(key);
            if(com == 0){
                h.next.value = value;
                return;
            }
            if(com > 0){
                break;
            }
            h = h.next;
        }
        newNode.next = h.next;
        h.next = newNode;
        size += 1;
    }

    /*
        通过键得到键对应的值
     */
    public Value get(Key key){
        Node findNode = selectNextNodeIs(key);
        if(findNode != null){
            return findNode.next.value;
        }
        return null;
    }

    /*
        删除key所对应的键值对
     */
    public void delete(Key key){
        Node delNode = selectNextNodeIs(key);
        if(delNode != null){
            delNode.next = delNode.next.next;
            size -= 1;
        }
    }

    /*
        查找下一个结点的键是否为指定的key
        如果是：返回对应key的上一个节点
        如果不是：返回null
     */
    private Node selectNextNodeIs(Key key){
        Node h = head;
        while(h.next != null){
            int com = h.next.key.compareTo(key);
            if(com == 0){
                return h;
            }
            else if(com > 0){
                break;
            }
            h = h.next;
        }
        return null;
    }

    /*
        查看符号表中是否存在对应的键
     */
    public boolean contains(Key key){
        return selectNextNodeIs(key) != null ? true : false;
    }

    //判断是否为空
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    //返回符号表中键值对的数量
    public int size(){
        return size;
    }

    //返回最小键
    public Key min(){
        return head.next.key;
    }

    //返回最大键
    public Key max(){
        return select(size-1);
    }

    //小于等于key的最大键
    public Key floor(Key key){
        Node h = head;
        Key max = null;
        while(h.next != null){
            if(h.next.key.compareTo(key) <= 0){
                max = h.next.key;
            }
            else {
                break;
            }
            h = h.next;
        }
        return max;
    }

    //大于等于key的最小建
    public Key ceiling(Key key){
        Node h = head;
        while(h.next != null){
            if(h.next.key.compareTo(key) >= 0){
                return h.next.key;
            }
            h = h.next;
        }
        return null;
    }

    //小于key的键的数量
    public int rank(Key key){
        Node h = head;
        int count = 0;
        while(h.next != null && h.next.key.compareTo(key) < 0){
            count++;
            h = h.next;
        }
        return count;
    }

    //排名为k的键
    public Key select(int k){
        Node h = head;
        for(int i = 0; i <= k; i++){
            h = h.next;
        }
        return h.key;
    }

    //删除最小的建
    public void deleteMin(){
        delete(min());
    }

    //删除最大的键
    public void deleteMax(){
        delete(max());
    }

    //[lo..hi]之间键的数量
    public int size(Key lo,Key hi){
        if(lo.compareTo(hi) > 0 ){
            return 0;
        }
        Node h = head;
        int count = 0,loCompel,hiCompel;
        while(h.next != null){
            Key key = h.next.key;
            loCompel = key.compareTo(lo);
            hiCompel = key.compareTo(hi);
            if(loCompel >= 0 && hiCompel <= 0){
                count++;
            }
            h = h.next;
        }
        return count;
    }

    //[lo..hi]之间的所有键，已排序
    public Iterable<Key> keys(Key lo, Key hi){
        if(lo.compareTo(hi) > 0){
            return null;
        }
        Node h = head;
        Queue<Key> queue = new Queue<>();
        int count = 0,loCompel,hiCompel;
        while(h.next != null){
            Key key = h.next.key;
            loCompel = key.compareTo(lo);
            hiCompel = key.compareTo(hi);
            if(loCompel >= 0 && hiCompel <= 0){
                queue.enqueue(key);
            }
            h = h.next;
        }
        return queue;
    }

    //表中的所有键的集合，已排序
    public Iterable<Key> keys(){
        Node h = head;
        Queue<Key> queue = new Queue<>();
        while(h.next != null){
            queue.enqueue(h.next.key);
        }
        return queue;
    }

}