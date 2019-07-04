package vn.fstack.designpattern.objectpool;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;

public abstract class ObjectPool<T> {

    private int MAX_POOL = 2; // max number of the object in the pool
    private long expirationTime;
    private Hashtable<T, Long> locked, unlocked;

    public ObjectPool() {
        expirationTime = 30000; // 30 seconds
        locked = new Hashtable<T, Long>();
        unlocked = new Hashtable<T, Long>();
    }

    protected abstract T create();
    protected abstract boolean validate(T o);
    protected abstract void remove(T o);

    public synchronized T getObject() {
        long now = System.currentTimeMillis();
        T t;
        if (unlocked.size() > 0) {
            Enumeration<T> e = unlocked.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlocked.get(t)) > expirationTime) {
                    System.out.println("object has expired");
                    unlocked.remove(t);
                    remove(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlocked.remove(t);
                        locked.put(t, now);
                        System.out.println("object reuse");
                        return (t);
                    } else {
                        System.out.println("object failed validation");
                        unlocked.remove(t);
                        remove(t);
                        t = null;
                    }
                }
            }
        }
        
        if (locked.size() + unlocked.size() >= MAX_POOL) {
            System.out.println("the created object has exceeded the limit");
            t = null;
        } else {
            System.out.println("create a new one");
            t = create();
            locked.put(t, now);
        }
        
        return (t);
    }

    public synchronized void release(T t) {
        locked.remove(t);
        unlocked.put(t, System.currentTimeMillis());
    }
    
    public synchronized void removeAll() {
        for (Entry<T, Long> entry : locked.entrySet()) {
            remove(entry.getKey());
        }
        for (Entry<T, Long> entry : unlocked.entrySet()) {
            remove(entry.getKey());
        }
    }
}