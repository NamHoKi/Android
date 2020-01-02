package com.techtown.lastapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class Fragment1 extends Fragment {
    EditText editText, editText2, editText3;
    ImageButton imgloadButton, saveButton;
    OnDatabaseCallback callback;

    ImageView photoview;
    Bitmap bitmap;  // 디비에 넣을거

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        final double lat = getArguments().getDouble("lat");
        final double lon = getArguments().getDouble("lon");

        editText = (EditText) rootView.findViewById(R.id.editText);
        editText2 = (EditText) rootView.findViewById(R.id.editText2);
        editText3 = (EditText) rootView.findViewById(R.id.editText3);
        photoview = (ImageView) rootView.findViewById(R.id.imageView1);

        bitmap = ((MainActivity2)getActivity()).getBitmap();
        photoview.setImageBitmap(bitmap);

//        imgloadButton=(ImageButton) rootView.findViewById(R.id.imgloadButton);
        saveButton=(ImageButton) rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = 6;
                String name = editText.getText().toString();
                String author = editText2.getText().toString();
                String contents = editText3.getText().toString();
                byte[] photoimage = bitmapToByteArray(bitmap);
                callback.insert(number, name, author, contents, lat, lon, photoimage);
                Toast.makeText(getContext(), "게시글을 올렸습니다.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

//        imgloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainintent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
//                getActivity().startActivity(mainintent); // requestCode
//
//            }
//        });

        return rootView;
    }
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
