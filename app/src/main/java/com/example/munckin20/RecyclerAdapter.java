package com.example.munckin20;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Person> DataPerson;
    Context context;

    public RecyclerAdapter(Context context, ArrayList<Person> data) {
        this.DataPerson = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textView.setText(DataPerson.get(position).getName());
        holder.level_view.setText(DataPerson.get(position).getLvl());
        holder.str_view.setText(DataPerson.get(position).getStrong());

        if(Objects.equals(DataPerson.get(position).getGender(), "Female")){
            holder.photoSex.setImageResource(R.drawable.female);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                DataPerson.remove(position);
                notifyDataSetChanged();
            }
        });
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView level = dialog.findViewById(R.id.level);
        TextView strenght = dialog.findViewById(R.id.strenght);
        TextView name = dialog.findViewById(R.id.dialog_textName);
        ImageView photoSexDialog = dialog.findViewById(R.id.PhotoSexDialog);

        Person person = DataPerson.get(position);

        name.setText(person.getName());
        if(Objects.equals(person.getGender(), "Female")){
            photoSexDialog.setImageResource(R.drawable.armor_female);
        }
        level.setText(person.getLvl());
        strenght.setText(person.getStrong());

//Добавить уровень
        ImageButton levelup = dialog.findViewById(R.id.level_up);
        levelup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setLvl(String.valueOf(Integer.parseInt(person.getLvl()) + 1));
                person.setStrong(String.valueOf(Integer.parseInt(person.getStrong()) + 1));

                level.setText(person.getLvl());
                strenght.setText(person.getStrong());

                holder.level_view.setText(person.getLvl());
                holder.str_view.setText(person.getStrong());
            }
        });
//Уменьшить уровень
        ImageButton leveldown = dialog.findViewById(R.id.level_down);
        leveldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(person.getLvl()) > 1) {
                    person.setLvl(String.valueOf(Integer.parseInt(person.getLvl()) - 1));
                    person.setStrong(String.valueOf(Integer.parseInt(person.getStrong()) - 1));
                }

                level.setText(person.getLvl());
                strenght.setText(person.getStrong());

                holder.level_view.setText(person.getLvl());
                holder.str_view.setText(person.getStrong());
            }
        });
//Увеличть силу
        ImageButton strengthup = dialog.findViewById(R.id.strenght_up);
        strengthup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setStrong(String.valueOf(Integer.parseInt(person.getStrong()) + 1));

                strenght.setText(person.getStrong());

                holder.level_view.setText(person.getLvl());
                holder.str_view.setText(person.getStrong());
            }
        });
//Уменьшить силу
        ImageButton strengthdown = dialog.findViewById(R.id.strenght_down);
        strengthdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(person.getStrong()) > Integer.parseInt(person.getLvl()))
                    person.setStrong(String.valueOf(Integer.parseInt(person.getStrong()) - 1));

                strenght.setText(person.getStrong());

                holder.level_view.setText(person.getLvl());
                holder.str_view.setText(person.getStrong());
            }
        });
        holder.show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        TextPaint paint = holder.level_view.getPaint();
        Shader textShader = new LinearGradient(0, 0, 0, holder.level_view.getTextSize(),
                new int[]{
                        Color.parseColor("#36ACB6"),
                        Color.parseColor("#1E5BB7"),

                }, null, Shader.TileMode.CLAMP);
        holder.level_view.getPaint().setShader(textShader);

        Shader textShader1 = new LinearGradient(0, 0, 0, holder.str_view.getTextSize(),
                new int[]{
                        Color.parseColor("#C66A80"),
                        Color.parseColor("#A25880"),

                }, null, Shader.TileMode.CLAMP);
        holder.str_view.getPaint().setShader(textShader1);

        Shader textShader2 = new LinearGradient(0, 0, 0, strenght.getTextSize(),
                new int[]{
                        Color.parseColor("#C66A80"),
                        Color.parseColor("#A25880"),

                }, null, Shader.TileMode.CLAMP);
        strenght.getPaint().setShader(textShader2);

        Shader textShader3 = new LinearGradient(0, 0, 0, level.getTextSize(),
                new int[]{
                        Color.parseColor("#36ACB6"),
                        Color.parseColor("#1E5BB7"),

                }, null, Shader.TileMode.CLAMP);
        level.getPaint().setShader(textShader3);

        

    }



    @Override
    public int getItemCount() {
        return DataPerson.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView textView, level_view, str_view;
        ImageButton button;
        View show_dialog;
        CircleImageView photoSex;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoSex = itemView.findViewById(R.id.PhotoSex);
            textView=itemView.findViewById(R.id.textName);
            button=itemView.findViewById(R.id.delete);
            show_dialog = itemView.findViewById(R.id.show_dialog);
            level_view = itemView.findViewById(R.id.level1);
            str_view = itemView.findViewById(R.id.strenght1);

        }
    }

}