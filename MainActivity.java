package com.example.importproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    // Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        // Список Категорий
        ArrayList<HashMap<String, Object>> sections = new ArrayList<HashMap<String, Object>>();

// Список параметров конкретной категории
        HashMap<String, Object> section;

// Отправляем запрос в БД
        Cursor cursor = mDb.rawQuery("SELECT * FROM sections", null);
        cursor.moveToFirst();

// Пробегаем по всем категориям
        while (!cursor.isAfterLast()) {
            section = new HashMap<String, Object>();

            // Заполняем категории
            section.put("name",  cursor.getString(1));
            section.put("description",  cursor.getString(2));

            // Закидываем категорию в список
            sections.add(section);

            // Переходим к следующей категории
            cursor.moveToNext();
        }
        cursor.close();

// Какие параметры категории мы будем отображать в соответствующих
// элементах из разметки adapter_item.xml
        String[] from = { "name", "description"};
        int[] to = { R.id.textView, R.id.textView2};

// Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, sections, R.layout.adapter_item, from, to);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, ProductsGraphics.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ProductsVideo.class); //Заполняем Intent
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ProductsMultimedia.class); //Заполняем Intent
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ProductsText.class); //Заполняем Intent
                        break;
                }
                startActivity(intent); //Запускаем активность
            }
        });
    }

}