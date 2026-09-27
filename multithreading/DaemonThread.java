package multithreading;

class MineThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().isDaemon());
    }
}
// exception in threads - illegal thread exception
public class DaemonThread {                   // JVM don't destroy threads but destroys daemon threads when itself destroys or goes shut down
    public static void main(String[] args) {  // we can't create the main thread as daemon thread
        MineThread mt = new MineThread();
        Thread t = new Thread(mt);
        t.setDaemon(true);
        t.start();
    }
}
