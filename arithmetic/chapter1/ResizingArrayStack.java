package arithmetic.chapter1;
/*
    《算法（第四版）》，算法 1.1，下压（LIFO）栈（能够动态调整数组大小的实现）
 */

//导入接口Iterator
import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    //a数组存储栈的元素
    private Item[] a = (Item[]) new Object[1];
    //N记录栈的大小，即元素的数量
    private int N = 0;

    //检查当前栈是否为空
    public boolean isEmpty(){
        return N == 0;
    }

    //查看当前栈的大小
    public int size(){
        return N;
    }

    //私有方法，为栈改变指定的大小
    private void resize(int max){
        //定义一个指定大小的数组，来替换栈存储数据的数据，实现改变栈容量的大小
        Item[] temp = (Item[]) new Object[max];
        //将栈中原有的值给新的数组
        for(int i = 0; i < N; i++){
            temp[i] = a[i];
        }
        //让a数组指向新的数组，实现改变大小
        a = temp;
    }

    //入栈
    public void push(Item item){
        //如果栈已满，则增加当前容量的一倍容量
        if(N == a.length)
            resize(2 * a.length);
        //将指定的值入栈
        a[N++] = item;
    }

    //出栈
    public Item pop(){
        //记录要出栈的数据
        Item item = a[N--];
        //删除出栈的数据
        a[N] = null;
        //如果栈存在数据，存储栈数据的空间只用了1/4，则将空间缩小一半
        if(N > 0 && N == a.length / 4)
            resize(a.length / 2);
        //返回出栈的值
        return item;
    }

    /*
        迭代
     */
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public Item next(){
            return a[--i];
        }
        public void remove(){}
    }
}
