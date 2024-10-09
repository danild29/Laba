package com.example.laba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean checkPasswrod(String p)
    {
        return true;
//        String[] passwords = {"123", "qwe", "zxc", "1234567", "gh12"};
//        for (String str : passwords) {
//            if (str.equals(p)) {
//                return true;
//            }
//        }
//        return false;
    }


    private boolean checkEmail(String p)
    {
        return true;
//        String[] passwords = {"123@gmail.com", "qwe@gmail.com", "zxc@gmail.com", "1234567@gmail.com", "123"};
//        for (String str : passwords) {
//            if (str.equals(p)) {
//                return true;
//            }
//        }
//        return false;
    }

    MainActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pasword = findViewById(R.id.editTextTextPassword);
                EditText email = findViewById(R.id.editTextTextEmailAddress);

                String p = pasword.getText().toString();
                String e = email.getText().toString();
                Log.e(p, e);

                if (checkPasswrod(p) && checkEmail(e))
                {
                    Intent intent = new Intent(context, Navig.class);
                    startActivity(intent);
                }
                else
                {
                    pasword.setTextColor(Color.RED);
                    email.setTextColor(Color.RED);
                }
        }});
    }
}