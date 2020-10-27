package com.example.databaselocalsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;


import com.example.databaselocalsqlite.R;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.txtview);

        SinhVien qlsv = new SinhVien(this);
        qlsv.taosv("Tran Van", "Dung", "K19T1");
        qlsv.taosv("Tran Van", "Hung", "K19T2");
        qlsv.taosv("Tran Van", "Hai", "K19T1");
        qlsv.taosv("Nguyen Van", "Toan", "K19T1");

        Cursor con_tro = qlsv.getAllSv();
        String chuoi="";
        con_tro.moveToFirst();
        do {
            chuoi+=con_tro.getString(0) + "\t\t\t";
            chuoi+=con_tro.getString(1) + "\t\t\t";
            chuoi+=con_tro.getString(2) + "\t\t\t";
            chuoi+=con_tro.getString(3) + "\n";
        } while(con_tro.moveToNext());

        tv.setText(chuoi);
    }
}