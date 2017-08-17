package com.example.firebase_musiclist;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 17-Aug-17.
 */

public class MusicUtils {

    /**
     * Scan all songs available in the device
     */
    public static List<Song> getMusicData(Context context) {
        final List<Song> list = new ArrayList<Song>();


        final DatabaseReference reference_song = FirebaseDatabase.getInstance().getReference("song");
        reference_song.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Song song = ds.getValue(Song.class);
                    song.refKey  = (String) ds.getKey();
                    Log.d("RefKey...", song.refKey);
                    list.add(song);
                }

                Log.d("onDataChange$$$$", Integer.toString(list.size()));
            }

            @Override
            public void onCancelled(DatabaseError dberror) {

            }

        });

        return list;
    }



    /**
     * Format Song Duration properly
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }

    private static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
