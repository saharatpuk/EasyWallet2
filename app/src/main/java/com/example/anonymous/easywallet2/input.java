package com.example.anonymous.easywallet2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.anonymous.easywallet2.db.MoneyHelper;

public class input extends AppCompatActivity {
    MoneyHelper lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ImageView imageView = findViewById(R.id.imageView3);


    }

    public void click(View view){

        EditText eUser = findViewById(R.id.edit_your_user);
        EditText ePass = findViewById(R.id.edit_your_pass);
        String sUser = eUser.getText().toString();
        String sPass = ePass.getText().toString();
        lg = new MoneyHelper(this);

        ContentValues cv = new ContentValues();
        cv.put(lg.COL_TITLE,sUser);
        cv.put(lg.COL_NUMBER,sPass);
        cv.put(lg.COL_PICTURE,"002.jpg");
        MoneyHelper lgHelper = new MoneyHelper(this);
        SQLiteDatabase db = lgHelper.getWritableDatabase();
        long result = db.insert(lg.TABLE_NAME,null,cv);

        Intent intent = new Intent(input.this,MainActivity.class);
        startActivity(intent);
    }
}
