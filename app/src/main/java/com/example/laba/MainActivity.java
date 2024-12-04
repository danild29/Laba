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
import androidx.room.Database;

import com.example.laba.Room.AppDatabase;
import com.example.laba.Room.FavoriteBookDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void fillUsers(AppDatabase db)
    {
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");
        User user3 = new User("user3", "pass3");
        User user4 = new User("user4", "pass4");
        User user5 = new User("user5", "pass5");

        db.userDao().insert(user1);
        db.userDao().insert(user2);
        db.userDao().insert(user3);
        db.userDao().insert(user4);
        db.userDao().insert(user5);
    }

    private boolean checkPasswrod(String e, String p)
    {
        AppDatabase db = AppDatabase.getDatabase(context);
        List<User> users = db.userDao().getAllUsers();
        for (User u : users) {
            if (u.getLogin().equals(e) && u.getPassword().equals(p)) {
                AppDatabase.UserId = u.getId();
                return true;
            }
        }
        return false;
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
        AppDatabase db = AppDatabase.getDatabase(context);

//        db.userDao().deleteAllUsers();
//        db.favoriteBookDao().deleteFavoriteBooks();
//        fillUsers(db);
//        List<User> users = db.userDao().getAllUsers();
//        int userId = users.get(0).getId();
//        List<FavoriteBook> favoriteBooks1 = db.favoriteBookDao().getFavoriteBooks();
//        fillFavourites(db, userId);
//        List<FavoriteBook> favoriteBooks2 = db.favoriteBookDao().getFavoriteBooks();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pasword = findViewById(R.id.editTextTextPassword);
                EditText email = findViewById(R.id.editTextTextEmailAddress);

                String p = pasword.getText().toString();
                String e = email.getText().toString();
                Log.e(p, e);

                if (checkPasswrod(e, p))
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

    public  void fillFavourites(AppDatabase db, int userId)
    {
        // Заполнение таблицы избранных книг (например, для первого пользователя)
        FavoriteBook favorite1 = new FavoriteBook(userId, 1); // userId = 1, bookId = 1
        FavoriteBook favorite2 = new FavoriteBook(userId, 2); // userId = 1, bookId = 2

        db.favoriteBookDao().insert(favorite1);
        db.favoriteBookDao().insert(favorite2);
    }
}