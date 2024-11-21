package com.example.laba.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laba.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Query("SELECT * FROM books")
    List<Book> getAllBooks();
}