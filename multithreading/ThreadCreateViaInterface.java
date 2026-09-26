package multithreading;

class NewThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("child thread : "+i);
        }
    }
}

public class ThreadCreateViaInterface {
    public static void main(String[] args) {
        NewThread nt = new NewThread();
        Thread t = new Thread(nt);  // This one steps added in runnable interface way because we need start method
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread : "+i);
        }
    }
}
