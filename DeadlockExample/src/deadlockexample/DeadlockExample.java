package deadlockexample;

/**
 *
 * @author Nathanael
 */
public class DeadlockExample {

    static final String lock1 = "Pass1";//Password for lock 1
    static final String lock2 = "Pass2";//Password for lock 2

    public static void main(String[] args) {
        Thread t1 = new Thread() {//First Thread
            public void run() {
                synchronized (lock1) {//Needs Password 1 to be sync'd
                    System.out.println("Thread 1 unlocked lock 1");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println("Thread 1 caught error at lock 1");
                    }
                    synchronized (lock2) {//Shouldn't make it this far (Should be fighting t2 for this)
                        System.out.println("Thread 1 unlocked lock 2. Out of deadlock.");
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (lock2) {//Needs password 2 to be sync'd
                    System.out.println("Thread 2 unlocked at lock 1");//First lock but needs Password 2 (which is held by t1)
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println("Thread 2 caught error at lock 1");
                    }
                    synchronized (lock1) {//Shouldn't make it this far
                        System.out.println("Thread 2 unlocked lock 2. Out of deadlock");
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}
