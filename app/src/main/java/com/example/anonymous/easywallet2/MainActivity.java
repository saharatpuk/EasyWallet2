package com.example.anonymous.easywallet2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.easywallet2.adapter.MoneyItemLists;
import com.example.anonymous.easywallet2.db.MoneyHelper;
import com.example.anonymous.easywallet2.model.MoneyItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MoneyHelper moneyHelper;
    private SQLiteDatabase mDb;
    private ArrayList<MoneyItem> mTaleItemList = new ArrayList<>();
    private MoneyItemLists mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moneyHelper = new MoneyHelper(this);
        mDb = moneyHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new MoneyItemLists(
                this,
                R.layout.item,
                mTaleItemList
        );
        TextView textView = findViewById(R.id.textView1);
        int s = sum()-out();
        textView.setText(Integer.toString(s));
        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(mAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                MoneyItem a = mTaleItemList.get(position);

                String[] items = new String[]{"์NO", "YES"};
                dialog.setMessage("ยืนยันการลบรายการ"+a.title+a.number);
                dialog.setPositiveButton("yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        MoneyItem item = mTaleItemList.get(position);
                        int phoneId = item.id;

                        mDb.delete(
                                MoneyHelper.TABLE_NAME,
                                MoneyHelper.COL_ID + "=?",
                                new String[]{String.valueOf(phoneId)}
                        );
                        loadDataFromDb();
                        mAdapter.notifyDataSetChanged();

                    }
                });
                dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                /*dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {

                        } else if (i == 1) { // ลบข้อมูล

                        }
                    }
                });*/
                AlertDialog alertDialog = dialog.create();

                alertDialog.show();
                return true;
            }
        });



    } // ปิดเมธอด onCreate

    public void input(View view) {
        Intent intent = new Intent(MainActivity.this,input.class);
        startActivity(intent);

    }
    public void output(View view) {
        Intent intent = new Intent(MainActivity.this,output.class);
        startActivity(intent);
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                MoneyHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mTaleItemList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MoneyHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(MoneyHelper.COL_TITLE));
            String story = cursor.getString(cursor.getColumnIndex(MoneyHelper.COL_NUMBER));
            String picture = cursor.getString(cursor.getColumnIndex(MoneyHelper.COL_PICTURE));

            MoneyItem item = new MoneyItem(id, title, story, picture);
            mTaleItemList.add(item);
        }
    }
    public int sum(){
        int a=0;
        for(MoneyItem m : mTaleItemList){
            if(m.picture=="002.jpg"){
                a=a+(Integer.parseInt(m.number));
            }
        }
        return a;
    }

    public int out(){
        int a=0;
        for(MoneyItem m : mTaleItemList){
            if(m.picture=="001.jpg"){
                a=a+(Integer.parseInt(m.number));
            }
        }
        return a;
    }
}
