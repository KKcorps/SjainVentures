package com.example.kkcorps.sjainventures;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class QEA extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        ImageView imageView = (ImageView) findViewById(R.id.logo);
        TextView textView = (TextView) findViewById(R.id.textView);
        imageView.setImageResource(R.drawable.logo_qea);
        textView.setText(R.string.qea_intro);

        TextView textHeading = (TextView) findViewById(R.id.textHeading);
        textHeading.setText("QEA, is \'By IITians, For (to be) IITians\'");

        Button button_end = (Button) findViewById(R.id.button_end);
        button_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button button_link = (Button) findViewById(R.id.button_link);
        button_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://qea.co.in/"));
                startActivity(intent);
            }
        });
    }


}
