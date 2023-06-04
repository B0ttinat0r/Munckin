package com.example.munckin20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class add_player extends AppCompatActivity {

Button gender_btn;
Button add_name;
EditText editText;
MaterialToolbar back_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        back_butt = findViewById(R.id.TopAppBar);
        editText = findViewById(R.id.editTextTextPersonName);
        gender_btn = findViewById(R.id.sex);

        back_butt.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_player.this,MainActivity.class);
                add_player.this.startActivity(i);
            }
        });
    }
    public void ret(View view) {
        if (gender_btn.getText().equals("Male")) gender_btn.setText("Female");
        else gender_btn.setText("Male");
    }
    public void add_person(View v) {
        Intent i = new Intent(add_player.this, MainActivity.class);
        if (!editText.getText().toString().equals("")) {
            i.putExtra("choice", editText.getText().toString());
            i.putExtra("choice1", gender_btn.getText().toString());
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Введите имя", Toast.LENGTH_LONG).show();
        }
    }
}