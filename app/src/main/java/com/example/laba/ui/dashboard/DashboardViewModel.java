package com.example.laba.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.laba.Book;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel(Book book) {


        mText = new MutableLiveData<>();
        mText.setValue(book.name);
        mText.setValue(book.author);
        mText.setValue(book.genre);
        mText.setValue(book.publicationDate);
        mText.setValue(String.valueOf(book.rating));

    }

    public LiveData<String> getText() {
        return mText;
    }
}