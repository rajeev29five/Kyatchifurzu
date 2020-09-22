package com.anime.kyatchifurzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter  mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mRecyclerLayoutManager;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        Field fields[] = R.raw.class.getFields();
        final List<AudioFileEntity> audioFile = fetchAudioData(fields);

        mRecyclerViewAdapter = new AudioListAdapter(audioFile/*, new ClickListener() {

            @Override
            public void onPositionClickListener(View view, int position) {
                if(view.getId() == R.id.button_play) {
                    stopPlaying(view);
                    mMediaPlayer = MediaPlayer.create(getApplicationContext(), audioFile.get(position).getResourceId());
                    mMediaPlayer.start();
                } else if(view.getId() == R.id.button_options) {
                    Uri uri = Uri.parse("android.resource://com.anime.kyatchifurzu/" + audioFile.get(position).getResourceId());
                    RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, uri);
                }
            }

            @Override
            public void onLongClickListener(int position) {

            }
        }*/);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    private void stopPlaying(View view) {

        if(mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private List<AudioFileEntity> fetchAudioData(Field[] fields) {

        List<AudioFileEntity> audioFile = new ArrayList<>();
        for (int i = 0 ; i<fields.length; i++)
        {
            AudioFileEntity _audioFile = new AudioFileEntity();
            _audioFile.setName(AudioDetailsEnum.valueOf(fields[i].getName().toUpperCase()).getAudioDetails());
            _audioFile.setResourceId(getResources().getIdentifier(fields[i].getName(), "raw", getPackageName()));
            audioFile.add(_audioFile);
        }
        return audioFile;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivity(intent);
            }
        }
    }
}
