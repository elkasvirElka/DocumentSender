package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DocumentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document);
        Button edit_document = findViewById(R.id.edit_document);

        edit_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentActivity.this, DocumentEditActivity.class);
                startActivity(intent);
            }
        });
    }
}
