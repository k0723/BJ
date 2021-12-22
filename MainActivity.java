package com.example.a10_26an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String url = "https://www.samsung.com/sec/support/warranty/";
    String msg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        final Bundle bundle = new Bundle();
        
        new Thread(){

            @Override

            public void run() {

                Document doc = null;
                try {

                    doc = Jsoup.connect(url).get();
                    Element elements = doc.select("div.content.visual-content").get(0);
                    System.out.println(elements);
                    msg = elements.text();

                    System.out.println(msg);
                    bundle.putString("message",msg);
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    Handler handler = new Handler() {

        @Override
        public  void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            textView.setText(bundle.getString("message"));
        }
    };
}