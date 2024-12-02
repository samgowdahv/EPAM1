package ProjectPractise.src.main.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class Task implements Runnable
{
    @Override
    public void run() {
        for (int i =0 ; i < 10 ; i++)
        System.out.println(i);


    }
}
class Task2 implements Callable{
    @Override
    public String call() throws Exception {
        return "Callable is completed";
    }
}


public class MuthiThreading {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> task = () -> {
            return "processed";
        };
        Task task1 = new Task();
        executorService.submit(task1);
}
}
