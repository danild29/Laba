package com.example.laba.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laba.FavoriteBook;

import java.util.List;

@Dao
public interface FavoriteBookDao {
    @Insert
    void insert(FavoriteBook favoriteBook);

    @Query("SELECT * FROM favorite_books WHERE userId = :userId")
    List<FavoriteBook> getFavoriteBooksForUser(int userId);
}