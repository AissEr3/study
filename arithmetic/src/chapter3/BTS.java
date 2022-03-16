package chapter3;

import chapter1.Queue;

/*
    二叉查找树
    每个结点的左子结点小于父结点，右子结点大于父结点（如果存在子结点）
 */
public class BTS <Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;          // 键
        private Value value;      // 值
        private Node left, right; // 二叉树的子结点
        private int N;            // 以该结点为根的子树中的结点总数

        public Node(Key key, Value value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    // 返回这个二叉查找树的大小
    public int size(){
        return size(root);
    }

    // 查找指定结点为根的结点数量
    private int size(Node x){
        if(x == null) {
            return 0;
        }
        else {
            return x.N;
        }
    }

    // 获取二叉查找树指定键的值
    public Value get(Key key){
        return get(root, key);
    }

    /*
        功能：递归查找指定结点指定键的值
        步骤：
        和指定的结点的键比较，如果相等，直接返回值
        如果小于，和其左子结点比较
        如果大于，和其右子结点比较
     */
    private Value get(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return get(x.left,key);
        }
        else if (cmp > 0){
            return get(x.right, key);
        }
        else {
            return x.value;
        }
    }

    // 二叉查找树插入操作
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    /*
        功能：递归查找并插入并插入键值对，并返回新的指定根结点
        步骤：
        1、判断指定结点是否为空，为空创建新的结点
        2、指定结点不为空，与当前结点比较
            等于，修改值
            小于，与其左子结点比较，找出正确的位置
            大于，与其右子结点比较，找出正确的位置
         插入键值对后重新计算大小
     */
    private Node put(Node x, Key key, Value value){
        if(x == null){
            return new Node(key, value , 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = put(x.left, key, value);
        }
        else if(cmp > 0){
            x.right = put(x.right, key, value);
        }
        else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 查找最小键
    public Key min(){
        return min(root).key;
    }


    // 从根结点开始一直找其左子结点，直到其左子结点为空
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
    }

    // 查找小于等于指定键的最大键
    public Key floor(Key key){
        Node x = floor(root ,key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    /*
        功能：查找小于等于指定键的最大键的结点
        步骤：
        1、如果指定结点为空，返回null
        2、不为空，指定键与当前结点的键进行比较
            指定键 = 当前结点键，返回当前结点
            指定键 < 当前结点键，和其左子结点比较，找更小的键，以符合功能
            指定键 > 当前结点键，和其右子结点比较，判断是否还大于右子结点
     */
    private Node floor(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        }
        if(cmp < 0){
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if(t != null){
            return t;
        }
        else {
            return x;
        }
    }

    // 和min方法相似
    public Key max(){
        return max(root).key;
    }

    // 和min方法相似
    private Node max(Node x){
        if(x.right == null){
            return x;
        }
        return max(x.right);
    }

    // 和floor方法相似
    public Key ceiling(Key key){
        Node x = ceiling(root ,key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    // 和floor方法相似
    private Node ceiling(Node x, Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        }
        if(cmp > 0){
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if(t != null){
            return t;
        }
        else {
            return x;
        }
    }

    /*
        查找指定排名的键
        k指定的是，树中的键从小到大排序的排名，从0开始
     */
    public Key select(int k){
        return select(root, k).key;
    }

    /*
         功能：查找指定排名的结点
         步骤：
         根结点的排名是，左子树的结点个数+1
         一、首先查看根结点x的左子树结点数量，令其为t，则根结点排名为t+1
            因为是从小到大排名查找，且从0开始排名，所以可以得出以下结论；
            1、如果 t > k，证明要找的排名在左子树里，所以到左子树中继续寻找k；
            2、如果 t < k，证明要找的排名在右子树里，所以在右子树中继续寻找k，
               但是，因为右子树的任何结点排名都比根结点排名大，所以需要减去（t+1），
               以便在子树中正确查找，即 k = k-(t+1)；
            3、如果 t = k，且从0开始排名，所以根结点x就是当前排名，返回当前根结点
         二、接下来，将根结点x的 左子结点/右子结点 当做根结点
            继续步骤一的操作，直到找到结点，或者找不到返回null
     */
    private Node select(Node x, int k){
        if(x == null){
            return null;
        }
        int t = size(x.left);
        if(t > k){
            return select(x.left, k);
        }
        else if(t < k){
            return select(x.right, k-t-1);
        }
        else {
            return x;
        }
    }

    // 小于key键的数量
    public int rank(Key key){
        return rank(key, root);
    }

    // 与select类似，找到指定排名的结点，返回大小，具体原理查看select方法注释
    private int rank(Key key, Node x){
        if(x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return rank(key,x.left);
        }
        else if(cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }
        else {
            return size(x.left);
        }
    }

    // 删除最小结点
    public void deleteMin(){
        root = deleteMin(root);
    }

    /*
         功能：删除最小结点
         步骤：
         1、不断检索左子树，直到遇见空的左链接
         2、返回该结点的右链接
         3、让被删除的结点的父结点，指向返回的右链接结点
         原理：一直查找左子树，会查找到最小的结点；
              如果这个最小结点有右子节点，让这个右子节点代替最小结点；
              如果没有右子节点，直接删除最小结点，直接指向null
     */
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 删除最大结点，与删除最小结点类似，找到最大结点，替换成其左节点即可
    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if(x.right == null){
            return x.left;
        }
        x.right = deleteMin(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    // 二叉查找树的删除结点操作，是所有方法中最复杂的方法，详细过程看私有的删除方法
    public void delete(Key key){
        root = delete(root, key);
    }

    /*
        功能：删除一个制定 key 的结点
        原理：
        1、将指向即将被删除的结点的链接保存为t
        2、将x指向它的后继结点min（t.right）
        3、将x的右链接（原本指向一颗所有结点都大于x.key的二叉查找树）指向deleteMin（t.right）。
           也就是删除后所有结点仍然都大于x.key的子二叉查找树
        4、将x的左连接（本为空）设为t.left（其下所有的键都小于被删除的结点和它的后继结点）

        在删除结点x后用它的后继结点填补它的位置。因为x有一个右结点，因此它的后继结点就是其右子树中的最小结点。
        这样的替换仍然能够保证树的有序性。
     */
    private Node delete(Node x, Key key){
        if(x == null){
            return null;
        }
        // 找到要删除的结点
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = delete(x.left, key);
        }
        else if(cmp > 0){
            x.right = delete(x.right, key);
        }
        // 找到
        else {
            // 如果这个删除的结点右链接为空，直接将左子结点替换删除结点
            if(x.right == null){
                return x.left;
            }
            // 如果这个删除的结点做链接为空，直接将右子结点替换删除结点
            if(x.left == null){
                return x.right;
            }
            // 如果左右都有，找到右子结点的最小结点替换删除结点，并删除原位置的最小结点
            Node t = x;
            x = min(t.right);// 返回最小结点
            x.right = deleteMin(t.right);// 返回删除的最小结点
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null){
            return;
        }
        int comLo = lo.compareTo(x.key);
        int comHi = hi.compareTo(x.key);
        if(comLo < 0){
            keys(x.left,queue,lo,hi);
        }
        if(comLo <= 0 && comHi >= 0){
            queue.enqueue(x.key);
        }
        if(comHi > 0){
            keys(x.right, queue, lo, hi);
        }
    }

    // 练习3.2.6，查找树的高度
    public int height(){
        return height(root,0,0);
    }

    private int height(Node x,int lo, int hi){
        if(x == null){
            return 0;
        }
        else {
            lo = height(x.left,lo+1,hi);
            hi = height(x.right,lo,hi+1);
        }
        return lo > hi ? lo+1 : hi+1;
    }
}
