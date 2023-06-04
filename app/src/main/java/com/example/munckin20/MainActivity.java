package com.example.munckin20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Person> DataList;
    String Sex;
    String Name;
    MaterialToolbar mbar;
    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.players);

        loadData();

        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.VERTICAL, false));
        RecyclerAdapter adapter = new RecyclerAdapter(this,DataList);
        recyclerView.setAdapter(adapter);


        Name = getIntent().getStringExtra("choice");
        Sex = getIntent().getStringExtra("choice1");

        if (Name != null) {
            Person person = new Person(Name,Sex,"1","1");
            DataList.add(person);
        }

        mbar = findViewById(R.id.mbar);
        mbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Удачной игры");
                dialog.setMessage("«Манчкин» — это лихая пародия на классику ролевых игр. Здесь нет места пафосным речам, натужному героизму и отыгрышу персонажа. С нами вы сможете зачистить подземелья и добраться до победного уровня, не отвлекаясь на всю эту ролевую шелуху!");
                dialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.create();
                dialog.show();
                return false;
            }
        });






    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(DataList);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        DataList = gson.fromJson(json, type);
        if (DataList == null) {
            DataList = new ArrayList<>();
        }
    }

    public void add_player_1(View view) {
        Intent i = new Intent(MainActivity.this,add_player.class);
        MainActivity.this.startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}