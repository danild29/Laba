package com.example.laba.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.laba.Book;
import com.example.laba.FavoriteBook;

import java.util.List;

@Dao
public interface FavoriteBookDao {
    @Insert
    void insert(FavoriteBook favoriteBook);

    @Query("SELECT * FROM favorite_books WHERE userId = :userId")
    List<FavoriteBook> getFavoriteBooksForUser(int userId);

    @Query("SELECT b.* FROM favorite_books fb inner join books b on fb.bookId = b.id WHERE userId = :userId")
    List<Book> getBooksForUser(int userId);

    @Query("SELECT * FROM favorite_books")
    List<FavoriteBook> getFavoriteBooks();

    @Query("DELETE FROM favorite_books")
    void deleteFavoriteBooks();

    @Query("SELECT * FROM favorite_books WHERE userId = :userId and bookId = :bookId")
    boolean containsFavoriteBooks(int userId, int bookId);

    @Query("DELETE FROM favorite_books WHERE  userId = :userId and bookId = :bookId")
    void deleteFavoriteBook(int userId, int bookId);
}