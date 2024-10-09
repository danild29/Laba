package com.example.laba.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel(String name) {


        mText = new MutableLiveData<>();
        mText.setValue(name);
    }

    public LiveData<String> getText() {
        return mText;
    }
}