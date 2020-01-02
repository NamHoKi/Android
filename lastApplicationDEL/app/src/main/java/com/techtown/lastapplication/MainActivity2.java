package com.techtown.lastapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity implements OnDatabaseCallback {
    private static final String TAG = "MainActivity";

    //Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;

    BookDatabase database;
    double lat = 0, lon = 0;
    static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
//        int flag1 = (int) intent.getIntExtra("LoFa",1);
//        int flag2 = (int) intent.getIntExtra("LoFa",2);

        lat = (double) intent.getDoubleExtra("lat", 999);
        lon = (double) intent.getDoubleExtra("lon", 999);

        byte[] photo = getIntent().getByteArrayExtra("photo");
        if (photo != null)
            bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);

        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lon", lon);
        fragment1.setArguments(bundle);

        TabLayout tabs = findViewById(R.id.tabs);

        boolean flag = false;

//        if(flag1==2) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
//            tabs.addTab(tabs.newTab().setText("입력"));
//            tabs.addTab(tabs.newTab().setText("조회"));
//        }
//        else if(flag2==1){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
//            tabs.addTab(tabs.newTab().setText("조회"));
//            tabs.addTab(tabs.newTab().setText("입력"));
//            flag = true;
//        }

        if(!MenuActivity.menu_lost) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
            tabs.addTab(tabs.newTab().setText("입력"));
            tabs.addTab(tabs.newTab().setText("조회"));
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
            tabs.addTab(tabs.newTab().setText("조회"));
            tabs.addTab(tabs.newTab().setText("입력"));
            flag = true;
        }

        final boolean finalFlag = flag;
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
//                if (finalFlag) {
//                    if (position == 0) {
//                        selected = fragment2;
//                    } else if (position == 1) {
//                        selected = fragment1;
//                    }
//                } else if (position == 0) {
//                    selected = fragment1;
//                } else if (position == 1) {
//                    selected = fragment2;
//                }

                if (finalFlag) {
                    if (position == 0) {
                        selected = fragment2;
                    } else if (position == 1) {
                        selected = fragment1;
                    }
                    MenuActivity.menu_lost = false;
                }
                else {
                    if (position == 0) {
                        selected = fragment1;
                    } else if (position == 1) {
                        selected = fragment2;
                    }
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // open database
        if (database != null) {
            database.close();
            database = null;
        }

        database = BookDatabase.getInstance(this);
        boolean isOpen = database.open();
        if (isOpen) {
            Log.d(TAG, "Book database is open.");
        } else {
            Log.d(TAG, "Book database is not open.");
        }

    }

    static Bitmap getBitmap() {
        return bitmap;
    }

    protected void onDestroy() {
        // close database
        if (database != null) {
            database.close();
            database = null;
        }
        super.onDestroy();
    }

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    public void insert(int number, String name, String author, String contents, double lat, double lon, byte[] photo) {
        database.insertRecord(number, name, author, contents, getTime(), lat, lon, photo);
        Toast.makeText(getApplicationContext(), "게시물을 올렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void delete(String title) {
        database.deleteRecord(title);
        Toast.makeText(getApplicationContext(), "분실물이 주인을 찾았습니다!", Toast.LENGTH_LONG).show();
    }

    @Override
    public ArrayList<BookInfo> selectAll() {
        ArrayList<BookInfo> result = database.selectAll();
        Toast.makeText(getApplicationContext(), "분실 정보를 조회했습니다.", Toast.LENGTH_LONG).show();

        return result;
    }
}
