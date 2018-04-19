package com.example.a25fli.documentsender;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;


public class DocumentShowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_show);
        Bundle extras = getIntent().getExtras();
        File f = new File(getIntent().getStringExtra("picture"));
//        byte[] byteArray = extras.getByteArray("picture");

//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.my_img);
        Picasso.get().load(f).resize(800,800).centerInside().into(image);
//        image.setImageBitmap(bmp);
//        InputStream theInputStream = Helper.getInstance().getInputStreamer();
//        Drawable d = Drawable.createFromStream(theInputStream, "src");
//        ImageView imageView = findViewById(R.id.my_img);
//        imageView.setImageDrawable(d);
    }
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//
//    }
}
