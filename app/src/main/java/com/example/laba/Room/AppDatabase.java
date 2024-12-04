package com.example.laba.Room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.laba.Book;
import com.example.laba.FavoriteBook;
import com.example.laba.User;

@Database(entities = {User.class, Book.class, FavoriteBook.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static int UserId;

    public abstract UserDao userDao();
    public abstract BookDao bookDao();
    public abstract FavoriteBookDao favoriteBookDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "library_database")
                            .allowMainThreadQueries() // Не рекомендуется для продакшена
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}