package com.example.lukak.samgre;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostDetailsActivity extends AppCompatActivity {
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //get button
        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.btnPostDetailsBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //initialize
        gson = new Gson();

        //load
        loadData();
    }

    private void loadData() {
        //get elements
        TextView txtDate = (TextView) findViewById(R.id.txtPostDetailsDate);
        TextView txtCause = (TextView) findViewById(R.id.txtPostDetailsCause);
        TextView txtDescription = (TextView) findViewById(R.id.txtPostDetailsDescription);

        //get data
        Intent intent = getIntent();
        Post post = (Post)gson.fromJson(intent.getStringExtra("post"), Post.class);

        //create needed object
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        //set text
        txtDate.setText("Čas objave: " + df.format(post.getDate()));
        txtCause.setText("Vzrok: " + post.getCause());
        txtDescription.setText("Opis: " + post.getDescription());
    }
}
