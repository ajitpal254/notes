package com.example.notes;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class AddActivity extends AppCompatActivity {

    EditText title_input, desc_input, img_input;
    Button add_button,add_location,record_button;

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    private ImageView selectedImageView;
    private EditText titleEditText;
    Bitmap imageToStore;
    TextView lati;
    TextView logi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        desc_input = findViewById(R.id.desc_input);
        add_button = findViewById(R.id.add);
        add_location = findViewById(R.id.setlocation);
        lati = findViewById(R.id.txtlat);
        logi = findViewById(R.id.txtlongi);
        record_button = findViewById(R.id.record);
        Double reclatia,sdreclongi;

        this.selectedImageView = (ImageView) findViewById(R.id.imgview);
       // this.titleEditText = (EditText) findViewById(R.id.new_memory_title);
        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openRecorder();
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                Bitmap image = null;
                if (selectedImageView.getDrawable() != null && selectedImageView.getDrawable() instanceof BitmapDrawable) {
                    image = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();
                }

                myDB.addNote(new Memory(title_input.getText().toString().trim(),
                        desc_input.getText().toString().trim()
                        , image));
            }
        });

        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,MapsActivity.class));
            }
        });

    }
//        new MemoryDbHelper(this).addMemory(new Memory(titleEditText.getText().toString(), image));
        public void openRecorder(){
        Intent record = new Intent(AddActivity.this,MediaActivity.class);
        startActivity(record);
        }
    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }

    public void cancel(View view)
    {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                selectedImageView.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedImageView.setImageBitmap(imageBitmap);
            
        }
    }
}
