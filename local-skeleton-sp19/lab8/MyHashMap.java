import java.math.BigInteger;
import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {
    private int sizeNum;
    public Set<K> setForKeys;
    private int initialSize;
    private int curBuSize;
    private double loadFactor;
//    private int buckNum;
    private ArrayList<Entry<K,V>> bucket;

    public MyHashMap(){
        this.initialSize = 16;
        this.loadFactor = 0.75;
        bucket = new ArrayList<Entry<K,V>>(initialSize);
        bucket.addAll (Collections.nCopies (initialSize, null));
        sizeNum = 0;
        setForKeys = new HashSet<K>();
        curBuSize = initialSize;
    }
    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        bucket = new ArrayList<Entry<K,V>>(initialSize);
        bucket.addAll (Collections.nCopies (initialSize, null));
        sizeNum = 0;
        setForKeys = new HashSet<K>();
        curBuSize = initialSize;
    }
    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        bucket = new ArrayList<Entry<K,V>>(initialSize);
        bucket.addAll (Collections.nCopies (initialSize, null));
        sizeNum = 0;
        setForKeys = new HashSet<K>();
        curBuSize = initialSize;
    }


    @Override
    public void clear() {
        setForKeys = new HashSet<K>();
        sizeNum = 0;
        bucket = null;
//        bucket.clear();


//        bucket.addAll (Collections.nCopies (initialSize, null));
//        bucket = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (setForKeys == null) {
            return false;
        } else {
            return setForKeys.contains(key);
        }

    }
    @Override
    public void put(K key, V value) {


        int h = hash (key);
        Entry<K,V> e = find (key, bucket.get (h));
        if (e == null) {
            bucket.set (h, new Entry<K,V> (key, value, bucket.get(h)));
            sizeNum += 1;
            setForKeys.add(key);
            //TODO bucket.size() return number of buckets or number of buckets who has key
            if (sizeNum > bucket.size () * loadFactor) grow ();
        }else {
             e.setValue(value);
        }



    }

    private void grow () {
        int curBuSizeForNew;
        curBuSizeForNew = primeAbove(curBuSize *2);
        MyHashMap<K,V> newMap
                = new MyHashMap (curBuSizeForNew, loadFactor);
        newMap.putAll (this);
        copyFrom (newMap);

    }
    private void putAll(MyHashMap<K,V> S){
        setForKeys = S.keySet();
        int h ;
        V value;
        for(K i: setForKeys){
            h = hash (i);
            value = S.get(i);
            bucket.set (h, new Entry<K,V> (i, value, bucket.get(h)));
            sizeNum += 1;
        }

    }
    private void copyFrom (MyHashMap<K,V> S) {
        sizeNum = S.sizeNum;
        bucket = S.bucket;
        loadFactor = S.loadFactor;
        curBuSize = S.curBuSize;
    }

    private int primeAbove (int N) {
        BigInteger result;
        String str = Integer.toString(N);
        BigInteger a = new BigInteger(str);
        result = a.nextProbablePrime();
        return result.intValue();
    }


    private int hash(Object key) {
        return  (key == null) ? 0
                : (0x7fffffff & key.hashCode ()) % curBucketSize() ;
    }
    private int curBucketSize(){
        return curBuSize;
    }
    private static class Entry<K,V>{
        K keyNaive;
        V valueNaive;
        Entry<K, V> next;
        public Entry(K key, V value, Entry<K,V> next){
            this.keyNaive = key;
            this.valueNaive = value;
            this.next = next;
        }
        public V setValue (V x) {
            V old = valueNaive;
            valueNaive = x;
            return old;
        }

    }
    private Entry<K,V> find (Object key, Entry<K,V> bin) {
        for (Entry<K,V> e = bin; e != null; e = e.next)
            if (key == null && e.keyNaive == null || key.equals (e.keyNaive))
                return e;
        return null;
    }



    @Override
    public V get(Object key) {
        if (bucket == null) {
            return null;
        }
        int curhashTem = hash(key);
        Entry<K,V> e = find(key, bucket.get(curhashTem));
        return (e == null) ? null : e.valueNaive;
    }


    @Override
    public int size() {
        return sizeNum;
    }



    @Override
    public Set<K> keySet() {

        return setForKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }



    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}

