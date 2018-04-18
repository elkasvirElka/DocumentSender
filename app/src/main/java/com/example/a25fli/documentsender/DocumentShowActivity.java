package com.example.a25fli.documentsender;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;


public class DocumentShowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_show);

        InputStream theInputStream = Helper.getInstance().getInputStreamer();
        Drawable d = Drawable.createFromStream(theInputStream, "src");
        ImageView imageView = findViewById(R.id.my_img);
        imageView.setImageDrawable(d);
    }
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//
//    }
}
