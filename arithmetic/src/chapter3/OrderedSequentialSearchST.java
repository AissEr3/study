package chapter3;

import chapter1.Queue;

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

    public void put(Key key, Value value){
        if(key == null){
            delete(key);
        }
        sortPut(key,value);
    }

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

    public Value get(Key key){
        Node findNode = selectNextNodeIs(key);
        if(findNode != null){
            return findNode.next.value;
        }
        return null;
    }

    public void delete(Key key){
        Node delNode = selectNextNodeIs(key);
        if(delNode != null){
            delNode.next = delNode.next.next;
            size -= 1;
        }
    }

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

    public boolean contains(Key key){
        return selectNextNodeIs(key) != null ? true : false;
    }

    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    public int size(){
        return size;
    }

    public Key min(){
        return head.next.key;
    }

    public Key max(){
        return select(size-1);
    }

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

    public int rank(Key key){
        Node h = head;
        int count = 0;
        while(h.next != null && h.next.key.compareTo(key) < 0){
            count++;
            h = h.next;
        }
        return count;
    }

    public Key select(int k){
        Node h = head;
        for(int i = 0; i <= k; i++){
            h = h.next;
        }
        return h.key;
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }

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

    public Iterable<Key> keys(){
        Node h = head;
        Queue<Key> queue = new Queue<>();
        while(h.next != null){
            queue.enqueue(h.next.key);
        }
        return queue;
    }

}