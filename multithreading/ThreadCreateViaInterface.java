package multithreading;

class NewThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Old thread name: "+Thread.currentThread().getName());
        Thread.currentThread().setName("Main_Thread_By_Main_Thread");
        System.out.println("New new name: "+Thread.currentThread().getName());
        for (int i = 0; i < 20; i++) {
            System.out.println("child thread : "+i);
        }
    }
}

public class ThreadCreateViaInterface {
    public static void main(String[] args) {
        NewThread nt = new NewThread();
        Thread t = new Thread(nt);  // This one steps added in runnable interface way because we need start method
        t.start();
        System.out.println("Old thread name: "+Thread.currentThread().getName());
        Thread.currentThread().setName("Main_Thread_By_JVM");
        System.out.println("New new name: "+Thread.currentThread().getName());
        System.out.println(10/0);
    }
}
