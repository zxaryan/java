package multithreading;

class MyThread extends Thread{
    @Override  // we are overriding our functionalities if we don't it will still create thread by default run()
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("child thread : "+i);
        }
    }
}

public class ThreadCreation {
    public static void main(String[] args) {               // JVM creates "main thread" initially using main methods
        MyThread t = new MyThread();                       // Main Thread is creating a new thread called "Thread 0"
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread : "+i);
        }
    }
}

//public class ThreadCreation extends Thread{
//    @Override
//    public void run() {
//        super.run();
//        System.out.println("Thread called");
//    }
//
//    public static void main(String[] args) {
//        ThreadCreation t = new ThreadCreation();
//        t.start();
////        t.run();
//    }
//}
