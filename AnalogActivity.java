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

public class AnalogActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    // Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analog);

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
        ArrayList<HashMap<String, Object>> productsAnalog = new ArrayList<HashMap<String, Object>>();

// Список параметров конкретной категории
        HashMap<String, Object> product;

// Отправляем запрос в БД
        Cursor cursor = mDb.rawQuery("SELECT * FROM productsAnalog", null);
        cursor.moveToFirst();

// Пробегаем по всем категориям
        while (!cursor.isAfterLast()) {
            product = new HashMap<String, Object>();

            // Заполняем категории
            product.put("name", cursor.getString(1));
            product.put("description", cursor.getString(2));

            // Закидываем категорию в список
            productsAnalog.add(product);

            // Переходим к следующей категории
            cursor.moveToNext();
        }
        cursor.close();

// Какие параметры категории мы будем отображать в соответствующих
// элементах из разметки adapter_analog.xml
        String[] from = {"name", "description"};
        int[] to = {R.id.analogname, R.id.analogdesc};

// Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, productsAnalog, R.layout.adapter_analog, from, to);
        TextView textView = (TextView) findViewById(R.id.analogname);
        TextView textView2 = (TextView) findViewById(R.id.analogdesc);

        Integer id = getIntent().getIntExtra("id", 0);

        switch (id) {
            case 0:
                String name = (String) productsAnalog.get(0).get("name");
                String description = (String) productsAnalog.get(0).get("description");
                TextView cadTV = findViewById(R.id.analogname);
                cadTV.setText(name);
                TextView cadTV1 = findViewById(R.id.analogdesc);
                cadTV1.setText(description);
            break;

            case 1:
                String name1 = (String) productsAnalog.get(1).get("name");
                String description1 = (String) productsAnalog.get(1).get("description");
                TextView maxTV = findViewById(R.id.analogname);
                maxTV.setText(name1);
                TextView maxTV1 = findViewById(R.id.analogdesc);
                maxTV1.setText(description1);
            break;

            case 2:
                String name2 = (String) productsAnalog.get(2).get("name");
                String description2 = (String) productsAnalog.get(2).get("description");
                TextView brushTV = findViewById(R.id.analogname);
                brushTV.setText(name2);
                TextView brushTV1 = findViewById(R.id.analogdesc);
                brushTV1.setText(description2);
            break;

            case 3:
                String name3 = (String) productsAnalog.get(3).get("name");
                String description3 = (String) productsAnalog.get(3).get("description");
                TextView premiereTV = findViewById(R.id.analogname);
                premiereTV.setText(name3);
                TextView premiereTV1 = findViewById(R.id.analogdesc);
                premiereTV1.setText(description3);
            break;

            case 4:
                String name4 = (String) productsAnalog.get(4).get("name");
                String description4 = (String) productsAnalog.get(4).get("description");
                TextView sonyTV = findViewById(R.id.analogname);
                sonyTV.setText(name4);
                TextView sonyTV1 = findViewById(R.id.analogdesc);
                sonyTV1.setText(description4);
            break;

            case 5:
                String name5 = (String) productsAnalog.get(5).get("name");
                String description5 = (String) productsAnalog.get(5).get("description");
                TextView effectTV = findViewById(R.id.analogname);
                effectTV.setText(name5);
                TextView effectTV1 = findViewById(R.id.analogdesc);
                effectTV1.setText(description5);
            break;

            case 6:
                String name6 = (String) productsAnalog.get(6).get("name");
                String description6 = (String) productsAnalog.get(6).get("description");
                TextView vlcTV = findViewById(R.id.analogname);
                vlcTV.setText(name6);
                TextView vlcTV1 = findViewById(R.id.analogdesc);
                vlcTV1.setText(description6);
                break;

            case 7:
                String name7 = (String) productsAnalog.get(7).get("name");
                String description7 = (String) productsAnalog.get(7).get("description");
                TextView kmpTV = findViewById(R.id.analogname);
                kmpTV.setText(name7);
                TextView kmpTV1 = findViewById(R.id.analogdesc);
                kmpTV1.setText(description7);
                break;

            case 8:
                String name8 = (String) productsAnalog.get(8).get("name");
                String description8 = (String) productsAnalog.get(8).get("description");
                TextView mpcTV = findViewById(R.id.analogname);
                mpcTV.setText(name8);
                TextView mpcTV1 = findViewById(R.id.analogdesc);
                mpcTV1.setText(description8);
                break;

            case 9:
                String name9 = (String) productsAnalog.get(9).get("name");
                String description9 = (String) productsAnalog.get(9).get("description");
                TextView wordTV = findViewById(R.id.analogname);
                wordTV.setText(name9);
                TextView wordTV1 = findViewById(R.id.analogdesc);
                wordTV1.setText(description9);
                break;

            case 10:
                String name10 = (String) productsAnalog.get(10).get("name");
                String description10 = (String) productsAnalog.get(10).get("description");
                TextView sublimeTV = findViewById(R.id.analogname);
                sublimeTV.setText(name10);
                TextView sublimeTV1 = findViewById(R.id.analogdesc);
                sublimeTV1.setText(description10);
                break;

            case 11:
                String name11 = (String) productsAnalog.get(11).get("name");
                String description11 = (String) productsAnalog.get(11).get("description");
                TextView padTV = findViewById(R.id.analogname);
                padTV.setText(name11);
                TextView padTV1 = findViewById(R.id.analogdesc);
                padTV1.setText(description11);
                break;
            default: break;

        }
    }
}