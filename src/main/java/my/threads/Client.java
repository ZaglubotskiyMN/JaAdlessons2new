package my.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    public static void main(String[] args) {
        //вывод в предсказуемом порядке
        SlowMetter meter = new SlowMetter();
        int[] meters = {1,2,3,4,5,6,7,8,9};
        List<Measurement> results =new ArrayList<Measurement>();
        ReentrantLock lock = new ReentrantLock();

        Arrays.stream(meters).forEach(n -> {
            Thread thread = new Thread(()-> {
                int result = meter.measure(n);
                // данная строка кода потоко-небезопастна
                //results.add(new Measurement(n,result));
                lock.lock();
                results.add(new Measurement(n,result));
                lock.unlock();

            });
            thread.start();
        });
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        results.stream()
                .sorted((m1,m2)->m1.id.compareTo(m2.id))
                .forEach(m ->{
                    System.out.println(m);
                } );




        results.forEach(m -> {
            // System.out.println(m);
        });
    }

    public static void main3(String[] args) {
        //вывод в предсказуемом порядке
        SlowMetter meter = new SlowMetter();
        int[] meters = {1,2,3,4,5,6,7,8,9};
        List<Measurement> results =new ArrayList<Measurement>();
        ReentrantLock lock = new ReentrantLock();

        Arrays.stream(meters).forEach(n -> {
            Thread thread = new Thread(()-> {
                int result = meter.measure(n);
                // данная строка кода потоко-небезопастна
                //results.add(new Measurement(n,result));
                lock.lock();
                results.add(new Measurement(n,result));
                lock.unlock();

            });
            thread.start();
        });
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        results.stream()
                .sorted((m1,m2)->m1.id.compareTo(m2.id))
                .forEach(m ->{
                    System.out.println(m);
                } );




        results.forEach(m -> {
           // System.out.println(m);
        });
    }

    public static void main2(String[] args) {
        //Минмальны асихронный код
        // на вид все ок, но необходимо решение, проблемы с синхронизацией
        SlowMetter meter = new SlowMetter();
        int[] meters = {1,2,3,4,5,6,7,8,9};
        Arrays.stream(meters).forEach(n -> {
            Thread thread = new Thread(()-> {
                int result = meter.measure(n);
                System.out.println(result);
            });
            thread.start();
        });
    }

    public static void main1(String[] args) {
        //При наличии задержки синхронный код  не удовлетворителен
        SlowMetter meter = new SlowMetter();
        int[] meters = {1,2,3,4,5,6,7,8,9};
        Arrays.stream(meters).forEach(n -> {
            int result = meter.measure(n);
            System.out.println(result);
        });
    }
}

class Measurement {
    public  Integer id;
    public  Integer value;
    public  Measurement(int id, int value) {
        this.id=id;
        this.value=value;
    }
    @Override
    public String toString(){
     return id+ "-" + value;
    }
}
