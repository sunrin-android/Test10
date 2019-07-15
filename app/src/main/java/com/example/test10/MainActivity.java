package com.example.test10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 권한 체크
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED) {
            // 권한 요청
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    10);
        }

        // 데이터베이스 생성, 오픈
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select name, photo, date, phone from tb_calllog";
        // tb_calllog 내용 조회
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<CallLogVO> datas = new ArrayList<>();

        while(cursor.moveToNext()) {
            CallLogVO call = new CallLogVO();
            call.name = cursor.getString(0);
            call.photo = cursor.getString(1);
            call.date = cursor.getString(2);
            call.phone = cursor.getString(3);
            datas.add(call);
        }
        db.close();

        CallLogAdapter adapter = new CallLogAdapter(this, R.layout.main_list_item, datas);
        ListView listView = findViewById(R.id.main_list);
        listView.setAdapter(adapter);
    }
}
