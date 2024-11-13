package com.example.laba.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.laba.Book;


public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> author;
    private final MutableLiveData<String> genre;
    private final MutableLiveData<String> publicationDate;
    private final MutableLiveData<String> rating;

    public DashboardViewModel(Book book) {


        mText = new MutableLiveData<>();
        mText.setValue(book.name);

        author = new MutableLiveData<>();
        author.setValue("Автор: " + book.author);

        genre = new MutableLiveData<>();
        genre.setValue("Жанр: " + book.genre);

        publicationDate = new MutableLiveData<>();
        publicationDate.setValue("Дата публикации: " + book.publicationDate);

        rating = new MutableLiveData<>();
        rating.setValue(String.valueOf("Рейтинг: " + book.rating));

    }

    public LiveData<String> getText() { return mText; }
    public LiveData<String> author() { return author; }
    public LiveData<String> genre() { return genre; }
    public LiveData<String> publicationDate() { return publicationDate; }
    public LiveData<String> rating() { return rating; }

}