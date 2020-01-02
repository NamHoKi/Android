package com.techtown.lastapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

//import statm.example.management.DatabaseHelper.TABLE_NAME;ic co

public class RegisterActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();

//    DatabaseHelper dbHelper = DatabaseHelper.getInstance(getApplicationContext());
//    SQLiteDatabase db = dbHelper.getWritableDatabase();

    EditText idText,passwordText, nameText, phoneText,backText;
    Button registerButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idText = (EditText) findViewById(R.id.input_id);
        passwordText = (EditText) findViewById(R.id.input_password);
        nameText = (EditText) findViewById(R.id.input_name);
        phoneText = (EditText) findViewById(R.id.input_mobile);
        registerButton = (Button) findViewById(R.id.btn_signup);
//        backText=(EditText)findViewById(R.id.link_login);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pw = passwordText.getText().toString();
                String name = nameText.getText().toString();
                String phone = phoneText.getText().toString();

                users.add(new User(id, pw, name, phone));
                Intent userintent = new Intent(getApplicationContext(), LoginActivity.class);
//                for(int i=0;i<users.size();i++)
                userintent.putExtra("users", users);
                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                startActivity(userintent);
            }

        });


    }


//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String id = idText.getText().toString();
//                String pw = passwordText.getText().toString();
//                String name = nameText.getText().toString();
//                String age = ageText.getText().toString();
//
//                insert(id, pw, name, age);
//                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
//
//                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
//                startActivity(mainIntent);
//            }
//        });


//    public void insert(String id, String pw, String name, String age){
//        try {
//            db.execSQL("insert into " + dbHelper.TABLE_NAME + "(id, pw, name , age) values (" + id +", " + pw + ", " + name + ", " + age + ");" );
//        } catch(Exception ex) {
//            Toast.makeText(getApplicationContext(), "insert 실패", Toast.LENGTH_LONG).show();
//        }
//    }



}