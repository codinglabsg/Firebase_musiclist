package com.example.firebase_musiclist;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {

       String title;
       String refKey;
       String singer;
       String album;
       String composer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();

         title = intent.getStringExtra("title");
         singer = intent.getStringExtra("singer");
         album = intent.getStringExtra("album");
         composer = intent.getStringExtra("composer");
         refKey = intent.getStringExtra("refKey");


        TextView tv_singer = (TextView)findViewById(R.id.title_singer);
        TextView tv_title = (TextView)findViewById(R.id.title);
        TextView tv_album = (TextView)findViewById(R.id.title_album);
        TextView tv_composer = (TextView)findViewById(R.id.title_composer);


        tv_singer.setText(singer);
        tv_title.setText(title);
        tv_album.setText(album);
        tv_composer.setText(composer);

    }

    public void updateInfo(View view){


        EditText et_singer = (EditText)findViewById(R.id.title_singer);
        EditText et_album = (EditText)findViewById(R.id.title_album);
        EditText et_composer = (EditText)findViewById(R.id.title_composer);
        //EditText et_year = (EditText)findViewById(R.id.title_year);

        final DatabaseReference reference_song = FirebaseDatabase.getInstance().getReference("song");
        reference_song.child(refKey).child("singer").setValue(et_singer.getText().toString());
        reference_song.child(refKey).child("album").setValue(et_album.getText().toString());
        reference_song.child(refKey).child("composer").setValue(et_composer.getText().toString());
       // reference_song.notifyAll();


              Toast.makeText(this, "Music update Successfully",5).show();
              Intent back = new Intent (this, MainActivity.class);
              startActivity(back);


    }


}
