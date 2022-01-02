package com.example.a10_26an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.security.identity.ResultData;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String url = "https://www.samsung.com/sec/support/warranty/";
    private String msg;
    private static String msgstr ="a";
    private static String msgstr1 ="b";
    Bundle bundle = new Bundle();
    ArrayList Result = new ArrayList();
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("sibal");
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        msg ="";
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                msgstr += bundle.getString("message");
                textView.setText(bundle.getString("message"));
            }
        };
        Thread thread1 =  new Thread(new Runnable() {

            @Override
            public void run() {
                try {//작동

                    Document doc = null;
                    Document doc1 = null;
                    try {
                        doc = Jsoup.connect(url).get();
                        doc1 = Jsoup.connect(url).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Element elements = doc.select("#tabContent-fillbox1").get(0);
                    Element elements1 = doc1.select("#tabContent-fillbox2").get(0);
                    msg = elements.toString();
                    msgstr += elements.text();
                    msgstr1 += elements1.text();
                    System.out.println("in thread : "+msgstr.length());
                    bundle.putString("message", msg);
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    // DB 호출
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    System.out.println("dead");
                }
            }
        });

        try {
            thread1.start();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(msgstr);
        System.out.println(msgstr1);

    }
}
