package com.example.shahzaib.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);

//        image URL: https://lh3.googleusercontent.com/-LXhiWLyOdkg/VeL58UNNDiI/AAAAAAAAAm4/u6jWSeU7fZ4/w663-h373-n-rw/shahzaib%2Bmughal.jpg
    }

//*********** when downloadImg button tapped
    public void downloadImg(View view)
    {
        Log.i("info","Button Tapped");
        DownloadTask task = new DownloadTask();

        try
        {
            Bitmap myImg = task.execute("https://lh3.googleusercontent.com/-LXhiWLyOdkg/VeL58UNNDiI/AAAAAAAAAm4/u6jWSeU7fZ4/w663-h373-n-rw/shahzaib%2Bmughal.jpg").get();
            image.setImageBitmap(myImg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public class DownloadTask extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try
            {
                URL url = new URL(urls[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                Bitmap myImg  = BitmapFactory.decodeStream(in);
                // ab image myImg object main aa gai hy
                return myImg;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

}
