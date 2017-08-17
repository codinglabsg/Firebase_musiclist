package com.example.firebase_musiclist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<Song> list = new ArrayList<>();
    private MusicAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * Initialize view
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.main_listview);

        //Scan Music array to the list
        list = MusicUtils.getMusicData(this);

        Log.d("initView$$$$", Integer.toString(list.size()));

        adapter = new MusicAdapter(this, list);
        adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intentUpdate = new Intent(MainActivity.this,Update.class);

                intentUpdate.putExtra("title", list.get(position).title);
                intentUpdate.putExtra("singer", list.get(position).singer);
                intentUpdate.putExtra("album", list.get(position).album);
                intentUpdate.putExtra("composer", list.get(position).composer);
                intentUpdate.putExtra("refKey", list.get(position).refKey);


                startActivity(intentUpdate);
            }


        });



    }





}
