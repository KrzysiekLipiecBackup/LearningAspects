package com.example.learningaspects.ui.practice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PracticeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PracticeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is practice fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}