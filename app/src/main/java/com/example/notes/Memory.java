package com.example.notes;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Memory {
    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;
    private String title;
    private String desc;
    private String image;

    public static final int COL_ID = 0;
    public static final int COL_TITLE = 1;
    public static final int COL_DESCRIPTION = 2;
    public static final int COL_IMAGE = 3;

    public Memory(Cursor cursor) {
        this.title = cursor.getString(COL_TITLE);
        this.desc = cursor.getString(COL_DESCRIPTION);
        this.image = cursor.getString(COL_IMAGE);
    }

    public Memory(String title, String desc, Bitmap image) {
        this.title = title;
        this.desc = desc;
        this.image = bitmapToString(resizeBitmap(image));
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    private static String bitmapToString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }
}
