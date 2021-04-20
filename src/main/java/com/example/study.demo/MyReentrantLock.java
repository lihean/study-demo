package com.example.study.demo;//package com.example.demo.code.current.test;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.AbstractQueuedSynchronizer;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//
//public class MyReentrantLock implements Lock, Serializable {
//
//
//    private final Sync sync;
//
//    public MyReentrantLock(Sync sync){
//        this.sync = sync;
//    }
//
//    public MyReentrantLock(boolean fair){
//        sync = fair?new FairSync():new NoFairSync();
//    }
//
//
//    static final class FairSync extends com.example.demo.code.current.test.MyReentrantLock.Sync {
//        @Override
//        final void lock() {
//            tryAcquire(1);
//        }
//
//        protected final boolean tryAcquire(int acquires){
//
//            final Thread thread = Thread.currentThread();
//            if(!hasQueuedPredecessors()&&compareAndSetState(0,1)){
//
//                setExclusiveOwnerThread(thread);
//            }else if(getExclusiveOwnerThread() == thread){
//
//                int nextState = getState() + acquires;
//
//                if(nextState < 0) throw new Error("Maximum lock count exceeded");
//                setState(nextState);
//            }
//
//            return false;
//        }
//    }
//    /**
//     * Sync object for non-fair locks
//     */
//    static final class NoFairSync extends Sync {
//        private static final long serialVersionUID = 7316153563782823691L;
//
//        @Override
//        final void lock() {
//            if(compareAndSetState(0,1))
//                setExclusiveOwnerThread(Thread.currentThread());
//
//            else {
//                tryAcquire(1);
//            }
//        }
//
//        /**
//         * Performs lock.  Try immediate barge, backing up to normal
//         * acquire on failure.
//         */
//
//
//        protected final boolean tryAcquire(int acquires) {
//            return noFairTryAcquire(acquires);
//        }
//    }
//
//    abstract class Sync extends AbstractQueuedSynchronizer {
//        private static final long serialVersionUID = -517952376203402261L;
//
//        abstract void lock();
//
//        /**
//         * 获取非公平锁
//         *
//         * @param acquire
//         * @return
//         */
//        final boolean noFairTryAcquire(int acquire) {
//            final Thread current = Thread.currentThread();
//
//            int state = getState();
//
//            if (state == 0) {
//                if (compareAndSetState(0, acquire)) {
//                    setExclusiveOwnerThread(current);
//                }
//            } else if (current == getExclusiveOwnerThread()) {
//                int nextState = state + acquire;
//                if (nextState < 0) throw new Error("Maximum lock count exceeded");
//
//                setState(nextState);
//
//                return true;
//            }
//
//            return false;
//        }
//
//        /**
//         * 释放锁
//         *
//         * @param acquire
//         * @return
//         */
//        protected final boolean tryRelease(int acquire) {
//
//            int c = getState() - acquire;
//            if (Thread.currentThread() != getExclusiveOwnerThread()) throw new IllegalMonitorStateException();
//
//            boolean free = false;
//            if (c == 0) {
//
//                free = true;
//                setState(c);
//                setExclusiveOwnerThread(null);
//            }
//
//            return free;
//        }
//
//        /**
//         * 判断当前线程是否持有锁
//         *
//         * @return
//         */
//        protected final boolean isHeldExclusively() {
//            return Thread.currentThread() == getExclusiveOwnerThread();
//        }
//
//        final ConditionObject newCondition() {
//            return new ConditionObject();
//        }
//
//        final Thread getOwner() {
//            return getState() == 0 ? null : getExclusiveOwnerThread();
//        }
//
//        final int getHoldCount() {
//            return isHeldExclusively() ? getState() : 0;
//        }
//
//        final boolean isLock(){
//            return getState() != 0;
//        }
//
//        public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//            in.defaultReadObject();
//
//            setState(0);
//        }
//    }
//
//    @Override
//    public void lock() {
//        sync.lock();
//    }
//
//    @Override
//    public void lockInterruptibly() throws InterruptedException {
//        sync.acquireInterruptibly(1);
//    }
//
//    @Override
//    public boolean tryLock() {
//        return sync.noFairTryAcquire(1);
//    }
//
//    @Override
//    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
//        return sync.tryAcquireNanos(1,unit.toNanos(time));
//    }
//
//    @Override
//    public void unlock() {
//        sync.release(1);
//    }
//
//    @Override
//    public Condition newCondition() {
//        return sync.newCondition();
//    }
//
//    public int getHoldCount(){
//        return sync.getHoldCount();
//    }
//
//    public boolean isHeldExclusively(){
//        return sync.isHeldExclusively();
//    }
//
//    public boolean isLocked(){
//        return sync.isLock();
//    }
//
//    public boolean isFair(){
//        return sync instanceof FairSync;
//    }
//
//    public Thread getOwern(){
//        return sync.getOwner();
//    }
//
//    public final boolean hasQueuedThreads(){
//        return sync.hasQueuedThreads();
//    }
//
//    public final boolean has(Thread thread){
//
//        if(thread == null){
//            throw new NullPointerException();
//        }
//        for(Thread t:sync.getQueuedThreads()){
//            if(t == thread){
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public final int getQueueLength(){
//        return sync.getExclusiveQueuedThreads().size();
//    }
//
//    public Collection<Thread> getQueuedThreads(){
//        return sync.getQueuedThreads();
//    }
//
//    /**
//     * 循环判断链表上的所有线程是否有处于waitStatus（-2）状态的
//     * @param condition
//     * @return
//     */
//    public boolean hasWaiters(Condition condition){
//        if(condition == null) throw new NullPointerException();
//        if(!(condition instanceof AbstractQueuedSynchronizer.ConditionObject)) throw  new IllegalArgumentException("not owner");
//
//        return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }
//
//    public int getWaitQueueLength(Condition condition){
//        if(condition == null) throw new NullPointerException();
//        if(!(condition instanceof AbstractQueuedSynchronizer.ConditionObject)) throw  new IllegalArgumentException("not owner");
//
//        return sync.getWaitQueueLength((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }
//
//    public Collection<Thread> getWaitQueues(Condition condition){
//        if(condition == null) throw new NullPointerException();
//        if(!(condition instanceof AbstractQueuedSynchronizer.ConditionObject)) throw  new IllegalArgumentException("not owner");
//
//        return sync.getWaitingThreads((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }
//
//    @Override
//    public String toString() {
//
//        Thread t = sync.getOwner();
//
//        return super.toString()+((t == null)?"[no lock]":"lock thread is"+ t.getName());
//    }
//}
//
//
