package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button add;

    MyDatabaseHelper myDB;
    ArrayList<String> note_id;
    ArrayList<String> note_title;
    ArrayList<String> note_desc;
    ArrayList<byte[]> note_img;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        recyclerView=findViewById(R.id.recyclerView);
        add=findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this,AddActivity.class);
             startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_desc = new ArrayList<>();
        note_img = new ArrayList<byte[]>();


        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, note_id, note_title, note_desc,note_img);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

     void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_desc.add(cursor.getString(2));
              //  note_img.add(cursor.getBlob(3));


            }
        }
    }
}