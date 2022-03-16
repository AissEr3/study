package chapter3;

import chapter1.Queue;

/*
    ���������
    ÿ���������ӽ��С�ڸ���㣬���ӽ����ڸ���㣨��������ӽ�㣩
 */
public class BTS <Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;          // ��
        private Value value;      // ֵ
        private Node left, right; // ���������ӽ��
        private int N;            // �Ըý��Ϊ���������еĽ������

        public Node(Key key, Value value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    // �����������������Ĵ�С
    public int size(){
        return size(root);
    }

    // ����ָ�����Ϊ���Ľ������
    private int size(Node x){
        if(x == null) {
            return 0;
        }
        else {
            return x.N;
        }
    }

    // ��ȡ���������ָ������ֵ
    public Value get(Key key){
        return get(root, key);
    }

    /*
        ���ܣ��ݹ����ָ�����ָ������ֵ
        ���裺
        ��ָ���Ľ��ļ��Ƚϣ������ȣ�ֱ�ӷ���ֵ
        ���С�ڣ��������ӽ��Ƚ�
        ������ڣ��������ӽ��Ƚ�
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

    // ����������������
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    /*
        ���ܣ��ݹ���Ҳ����벢�����ֵ�ԣ��������µ�ָ�������
        ���裺
        1���ж�ָ������Ƿ�Ϊ�գ�Ϊ�մ����µĽ��
        2��ָ����㲻Ϊ�գ��뵱ǰ���Ƚ�
            ���ڣ��޸�ֵ
            С�ڣ��������ӽ��Ƚϣ��ҳ���ȷ��λ��
            ���ڣ��������ӽ��Ƚϣ��ҳ���ȷ��λ��
         �����ֵ�Ժ����¼����С
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

    // ������С��
    public Key min(){
        return min(root).key;
    }


    // �Ӹ���㿪ʼһֱ�������ӽ�㣬ֱ�������ӽ��Ϊ��
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
    }

    // ����С�ڵ���ָ����������
    public Key floor(Key key){
        Node x = floor(root ,key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    /*
        ���ܣ�����С�ڵ���ָ�����������Ľ��
        ���裺
        1�����ָ�����Ϊ�գ�����null
        2����Ϊ�գ�ָ�����뵱ǰ���ļ����бȽ�
            ָ���� = ��ǰ���������ص�ǰ���
            ָ���� < ��ǰ�������������ӽ��Ƚϣ��Ҹ�С�ļ����Է��Ϲ���
            ָ���� > ��ǰ�������������ӽ��Ƚϣ��ж��Ƿ񻹴������ӽ��
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

    // ��min��������
    public Key max(){
        return max(root).key;
    }

    // ��min��������
    private Node max(Node x){
        if(x.right == null){
            return x;
        }
        return max(x.right);
    }

    // ��floor��������
    public Key ceiling(Key key){
        Node x = ceiling(root ,key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    // ��floor��������
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
        ����ָ�������ļ�
        kָ�����ǣ����еļ���С�����������������0��ʼ
     */
    public Key select(int k){
        return select(root, k).key;
    }

    /*
         ���ܣ�����ָ�������Ľ��
         ���裺
         �����������ǣ��������Ľ�����+1
         һ�����Ȳ鿴�����x���������������������Ϊt������������Ϊt+1
            ��Ϊ�Ǵ�С�����������ң��Ҵ�0��ʼ���������Կ��Եó����½��ۣ�
            1����� t > k��֤��Ҫ�ҵ�����������������Ե��������м���Ѱ��k��
            2����� t < k��֤��Ҫ�ҵ���������������������������м���Ѱ��k��
               ���ǣ���Ϊ���������κν���������ȸ����������������Ҫ��ȥ��t+1����
               �Ա�����������ȷ���ң��� k = k-(t+1)��
            3����� t = k���Ҵ�0��ʼ���������Ը����x���ǵ�ǰ���������ص�ǰ�����
         �������������������x�� ���ӽ��/���ӽ�� ���������
            ��������һ�Ĳ�����ֱ���ҵ���㣬�����Ҳ�������null
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

    // С��key��������
    public int rank(Key key){
        return rank(key, root);
    }

    // ��select���ƣ��ҵ�ָ�������Ľ�㣬���ش�С������ԭ��鿴select����ע��
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

    // ɾ����С���
    public void deleteMin(){
        root = deleteMin(root);
    }

    /*
         ���ܣ�ɾ����С���
         ���裺
         1�����ϼ�����������ֱ�������յ�������
         2�����ظý���������
         3���ñ�ɾ���Ľ��ĸ���㣬ָ�򷵻ص������ӽ��
         ԭ��һֱ����������������ҵ���С�Ľ�㣻
              ��������С��������ӽڵ㣬��������ӽڵ������С��㣻
              ���û�����ӽڵ㣬ֱ��ɾ����С��㣬ֱ��ָ��null
     */
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // ɾ������㣬��ɾ����С������ƣ��ҵ�����㣬�滻������ڵ㼴��
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

    // �����������ɾ���������������з�������ӵķ�������ϸ���̿�˽�е�ɾ������
    public void delete(Key key){
        root = delete(root, key);
    }

    /*
        ���ܣ�ɾ��һ���ƶ� key �Ľ��
        ԭ��
        1����ָ�򼴽���ɾ���Ľ������ӱ���Ϊt
        2����xָ�����ĺ�̽��min��t.right��
        3����x�������ӣ�ԭ��ָ��һ�����н�㶼����x.key�Ķ����������ָ��deleteMin��t.right����
           Ҳ����ɾ�������н����Ȼ������x.key���Ӷ��������
        4����x�������ӣ���Ϊ�գ���Ϊt.left���������еļ���С�ڱ�ɾ���Ľ������ĺ�̽�㣩

        ��ɾ�����x�������ĺ�̽�������λ�á���Ϊx��һ���ҽ�㣬������ĺ�̽��������������е���С��㡣
        �������滻��Ȼ�ܹ���֤���������ԡ�
     */
    private Node delete(Node x, Key key){
        if(x == null){
            return null;
        }
        // �ҵ�Ҫɾ���Ľ��
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = delete(x.left, key);
        }
        else if(cmp > 0){
            x.right = delete(x.right, key);
        }
        // �ҵ�
        else {
            // ������ɾ���Ľ��������Ϊ�գ�ֱ�ӽ����ӽ���滻ɾ�����
            if(x.right == null){
                return x.left;
            }
            // ������ɾ���Ľ��������Ϊ�գ�ֱ�ӽ����ӽ���滻ɾ�����
            if(x.left == null){
                return x.right;
            }
            // ������Ҷ��У��ҵ����ӽ�����С����滻ɾ����㣬��ɾ��ԭλ�õ���С���
            Node t = x;
            x = min(t.right);// ������С���
            x.right = deleteMin(t.right);// ����ɾ������С���
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

    // ��ϰ3.2.6���������ĸ߶�
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
