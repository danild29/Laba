package com.example.laba;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "favorite_books",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Book.class,
                        parentColumns = "id",
                        childColumns = "bookId",
                        onDelete = ForeignKey.CASCADE)
        })
public class FavoriteBook {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int bookId;

    public FavoriteBook(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}