package com.techtown.lastapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {
    OnDatabaseCallback callback;
    int position;
    private static final String TAG = "MainActivity";

    BookDatabase database;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final Intent intent = getIntent();
        TextView title_TextView = (TextView) findViewById(R.id.title_TextView);
        final String title = intent.getStringExtra("title");
        if (title != null)
            title_TextView.setText(title);

        TextView nickname_TextView = (TextView) findViewById(R.id.nickname_TextView);
        final String nickname = intent.getStringExtra("nickname");
        if (nickname != null)
            nickname_TextView.setText(nickname);

        TextView contect_TextView = (TextView) findViewById(R.id.contect_TextView);
        final String content = intent.getStringExtra("content");
        if (content != null)
            contect_TextView.setText(content);

        position = intent.getIntExtra("position", 999);
        final double lat = intent.getDoubleExtra("lat",999);
        final double lon = intent.getDoubleExtra("lon",999);

        imageview = (ImageView) findViewById(R.id.imageView1);
        byte[] photo = intent.getByteArrayExtra("photo");
        Bitmap bitmap = BitmapFactory.decodeByteArray( photo, 0, photo.length );
        imageview.setImageBitmap(bitmap);

        ImageButton pointButton = (ImageButton) findViewById(R.id.Point_Button);
        ImageButton locationButton = (ImageButton) findViewById(R.id.locationButton);
        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(title);
                Toast.makeText(getApplicationContext(), nickname+" 500 포인트 적립완료!", Toast.LENGTH_LONG).show();

            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                intent2.putExtra("lat",lat);
                intent2.putExtra("lon",lon);
                intent2.putExtra("title",title);
                intent2.putExtra("nickname",nickname);
                intent2.putExtra("content",content);
                startActivity(intent2);
            }
        });

        database = BookDatabase.getInstance(this);
        boolean isOpen = database.open();
        if (isOpen) {
            Log.d(TAG, "Book database is open.");
        } else {
            Log.d(TAG, "Book database is not open.");
        }
    }

    protected void onDestroy() {
        // close database
        if (database != null) {
            database.close();
            database = null;
        }
        super.onDestroy();
    }

    public void delete(String title) {
        database.deleteRecord(title);
        Toast.makeText(getApplicationContext(), "분실물이 주인을 찾았습니다!", Toast.LENGTH_LONG).show();
        finish();
    }

}