package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList note_id, note_title, note_desc,note_image;





    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_title, ArrayList note_desc, ArrayList note_image){
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_desc = note_desc;
        this.note_image = note_image;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

                                                                                                                                                                                                                        @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {



        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_desc_txt.setText(String.valueOf(note_desc.get(position)));
      //  holder.note_desc_img.setImageBitmap(Memory.getImage(position));







        //Recyclerview onClickListener


    }




    @Override
    public int getItemCount() {
        return note_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_title_txt, note_desc_txt;
        LinearLayout mainLayout;
        ImageView note_desc_img;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_title_txt = itemView.findViewById(R.id.note_title_txt);
            note_desc_txt = itemView.findViewById(R.id.note_desc_txt);
            note_desc_img = itemView.findViewById(R.id.img);

        }

    }

}
