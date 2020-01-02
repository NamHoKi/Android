package com.techtown.lastapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

//    DatabaseHelper dbHelper = DatabaseHelper.getInstance(getApplicationContext());
//    SQLiteDatabase db = dbHelper.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent userintent = getIntent();

        final  ArrayList<User>  users = (ArrayList<User>)userintent.getSerializableExtra("users");

        final EditText idText = (EditText) findViewById(R.id.username);
        final EditText passwordText = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.btnLogin);
        Button btnLinkToRegisterScreen = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnLinkToRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }

        });

        final String id = idText.getText().toString();
        final String pw = passwordText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String id = idText.getText().toString();
                String pw = passwordText.getText().toString();


                boolean fail=true;
                for(int i = 0; i<users.size(); i++){
                    if(id.equals(users.get(i).id) && pw.equals(users.get(i).pw)){
                        fail=false;
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        mainIntent.putExtra("id",users.get(i).id);
                        // startActivity(mainIntent);
                        startActivityForResult(mainIntent,202);
                    }
                }
                if(fail)
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();

            }

        });

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean success = executeRawQueryParam(id, pw);
//                if(success){
//                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
//                    LoginActivity.this.startActivity(mainIntent);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

    }

//    private boolean executeRawQueryParam(String id, String pw) {
//        Cursor c1 = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
//        if(c1 != null) {
//            int recordCount = c1.getCount();
//            for (int i = 0; i < recordCount; i++) {
//                c1.moveToNext();
//                String DBid = c1.getString(0);
//                String DBpw = c1.getString(1);
//
//                if(DBid.equals(id) && DBpw.equals(pw)) {
//                    c1.close();
//                    return true;
//                }
//            }
//        }
//        c1.close();
//        return false;
//    }


}

