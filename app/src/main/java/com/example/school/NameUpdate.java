package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NameUpdate extends Activity {
    public void setView(View v){
        LayoutInflater l = getLayoutInflater();
        View view = l.inflate(R.layout.teacher_page,null);
        TextView tn = (TextView) view.findViewById(R.id.textViewTn);
        String value = getIntent().getStringExtra("Name");
        tn.setText(value);

    }

}
