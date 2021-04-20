package com.example.study.demo;//package com.example.demo.code.current.test;
//
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.AbstractQueuedSynchronizer;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//public class MyReentrantReadWriteLock implements Lock {
//
//
//    final MyReentrantReadWriteLock.Sync sync;
//
//    public MyReentrantReadWriteLock(boolean fair){
//        if()
//    }
//
//    @Override
//    public void lock() {
//
//    }
//
//    @Override
//    public void lockInterruptibly() throws InterruptedException {
//
//    }
//
//    @Override
//    public boolean tryLock() {
//        return false;
//    }
//
//    @Override
//    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
//        return false;
//    }
//
//    @Override
//    public void unlock() {
//
//    }
//
//    @Override
//    public Condition newCondition() {
//        return null;
//    }
//
//    public String toString(){
//        int c =
//
//    }
//
//    static final class FairSync extends Sync{
//        final boolean writerShouldBlock() {
//            return hasQueuedPredecessors();
//        }
//        final boolean readerShouldBlock() {
//            return hasQueuedPredecessors();
//        }
//    }
//
//    static final class noFairSync extends Sync{
//        final boolean writeShouldBlock(){
//            return false;
//        }
//
//        final boolean readShouldLock(){
//            return apparentlyFirstQueuedIsExclusive();
//        }
//    }
//
//    abstract static class Sync extends AbstractQueuedSynchronizer {
//
//        static final int SHARED_SHIFT = 16;
//        static final int SHARED_UNIT = 65536;
//        static final int MAX_COUNT = 65535;
//        static final int EXCLUSIVE_MASK = 65535;
//
//        static int shareCount(int c){
//            return c >>> 16;
//        }
//
//        static int exclusiveCount(int c){
//            return c&16;
//        }
//
//        static final class HoldCounter{
//            int count=0;
//
//            final long tid = getThreadId(Thread.currentThread());
//        }
//
//    }
//
//    private static final sun.misc.Unsafe UNSAFE;
//    private static final long TID_OFFSET;
//
//    static {
//        try {
//            UNSAFE = sun.misc.Unsafe.getUnsafe();
//            Class<?> tk = Thread.class;
//            TID_OFFSET = UNSAFE.objectFieldOffset
//                    (tk.getDeclaredField("tid"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//    static final long getThreadId(Thread thread) {
//        return UNSAFE.getLongVolatile(thread, TID_OFFSET);
//    }
//
//    public static void main(String[] args) {
////        System.out.println(16<<1);
//        System.out.println(155>>>16);
//    }
//}
