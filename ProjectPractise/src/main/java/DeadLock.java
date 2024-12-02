public class DeadLock {


    public static void main(String[] args) {
        String lock1="sampath1";
        String lock2="sampath2";
        Thread thrd1 = new Thread() {

            public void run() {
                synchronized (lock1) {
                    System.out.println("Thread1 :Loacked resouce");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }


                synchronized (lock2) {
                    System.out.println("Thread2 :Loacked resouce");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };


}


 }

