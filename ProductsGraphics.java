package com.example.importproduct;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsGraphics extends AppCompatActivity {

    // Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_graphics);

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
        ArrayList<HashMap<String, Object>> productsGraphics = new ArrayList<HashMap<String, Object>>();

// Список параметров конкретной категории
        HashMap<String, Object> product;

// Отправляем запрос в БД
        final Cursor[] cursor = {mDb.rawQuery("SELECT * FROM productsGraphics;", null)};
        cursor[0].moveToFirst();

// Пробегаем по всем категориям
        while (!cursor[0].isAfterLast()) {
            product = new HashMap<String, Object>();

            // Заполняем категории
            product.put("name",  cursor[0].getString(1));
            product.put("description",  cursor[0].getString(2));

            // Закидываем категорию в список
            productsGraphics.add(product);

            // Переходим к следующей категории
            cursor[0].moveToNext();
        }
        cursor[0].close();

// Какие параметры категории мы будем отображать в соответствующих
// элементах из разметки adapter_item.xml
        String[] from = { "name", "description"};
        int[] to = { R.id.textView, R.id.textView2};

// Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, productsGraphics, R.layout.adapter_item, from, to);
        ListView listView = findViewById(R.id.lv_graph);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long idgraph = id;
                int i = (int) idgraph;

                Intent intent = new Intent(ProductsGraphics.this, AnalogActivity.class);
                intent.putExtra("id", i);

                startActivity(intent);
//                switch (i) {
//                    case 0:
//                        Intent toCAD = new Intent(ProductsGraphics.this, AnalogActivity.class);
//                        toCAD.putExtra("id", idgraph);
//
//                        startActivity(toCAD);
//                        break;
//                    case 1:
//                        Intent to3Ds = new Intent(ProductsGraphics.this, AnalogActivity.class);
//                        to3Ds.putExtra("id", idgraph);
//
//                        startActivity(to3Ds);
//                        break;
//                    case 2:
//                        Intent toZBrush = new Intent(ProductsGraphics.this, AnalogActivity.class);
//                        toZBrush.putExtra("id", idgraph);
//
//                        startActivity(toZBrush);
//                        break;
//                }
            }
        });
    }
}