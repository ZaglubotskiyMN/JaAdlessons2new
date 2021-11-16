package my.threads;

public class SlowMetter {

    public  int measure(int n) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var result = n *10;
        return  result;
    }
}


