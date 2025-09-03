package com.example.notes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.media.MediaRecorder;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;


public class MediaActivity extends AppCompatActivity {
    MediaRecorder mediaRecorder;
    Button start,stop;
    boolean recording=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rocrder_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        mediaRecorder = new MediaRecorder();
        start = findViewById(R.id.button7);
        stop = findViewById(R.id.button9);



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startRecording(View view) {
        if(mediaRecorder!=null) {
            try {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                File path = new File(Environment.getExternalStorageDirectory() + File.separator
                        + Environment.DIRECTORY_MUSIC + File.separator + "/MyAudio.3gp");
                mediaRecorder.setOutputFile(path);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                mediaRecorder.prepare();
                mediaRecorder.start();





            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void stopRecording(View view) {
        try {
            if (mediaRecorder != null) {
                if (recording) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                }
            }
            else{
                Log.d("Recorder", "stopRecording: no Recorder availablee");
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

}