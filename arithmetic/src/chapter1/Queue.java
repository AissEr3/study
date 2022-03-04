package chapter1;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        Node oldFirst = last;
        last = new Node();
        last.item = item;
        if(isEmpty())
            first = last;
        else
            oldFirst.next = last;
        N++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        N--;

        return item;
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        for(Item item : this){
            string.append(item);
            string.append(" ");
        }
        return string.toString();
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    public class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){

        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
