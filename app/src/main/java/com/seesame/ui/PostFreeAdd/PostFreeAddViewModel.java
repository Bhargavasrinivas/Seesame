package com.seesame.ui.PostFreeAdd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostFreeAddViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostFreeAddViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}