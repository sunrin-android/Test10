package com.example.test10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // data 준비
        ArrayList<Custom> datas = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM tb_calllog";
        Cursor cursor =  db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            Custom customData = new Custom();
            customData.id = cursor.getInt(0);
            customData.name = cursor.getString(1);
            customData.photo = cursor.getString(2);
            customData.date = cursor.getString(3);
            customData.phone = cursor.getString(4);
            datas.add(customData);
        }
        db.close();

        CustomAdapter adapter = new CustomAdapter(this, R.layout.item, datas);

        listView.setAdapter(adapter);
    }
}
