package com.techtown.lastapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
//    public static final int RESPONSE_CODE_OK=200;
    public static final int REQUEST_CODE_FOUND=202;
//    static final int REQUEST_TAKE_PHOTO = 1;
//    String mCurrentPhotoPath;

    static boolean menu_lost = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent recivedIntent=getIntent();
        String username=recivedIntent.getStringExtra("username");
        String password=recivedIntent.getStringExtra("password");

//        Toast.makeText(this, "username : " + username +
//                ", password : " + password, Toast.LENGTH_LONG).show();

        ImageButton logoutButton=(ImageButton)findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra("mesage","로그아웃 완료!");

                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        ImageButton lostButton=(ImageButton)findViewById(R.id.lostButton);
        lostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
//                intent.putExtra("titleMsg","Lost Something!");
//                intent.putExtra("LoFa", (int)1);
                menu_lost = true;
                startActivityForResult(intent,REQUEST_CODE_FOUND);
            }
        });

        ImageButton foundButton=(ImageButton)findViewById(R.id.foundButton);
        foundButton.setOnClickListener(new View.OnClickListener() { //찾을때 누르는 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Get_gps.class);
                startActivityForResult(intent,REQUEST_CODE_FOUND);
            }
        });
    }
}
