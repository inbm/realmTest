package com.inbm.inbmstarter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    public ListLiveData<User> users = new ListLiveData<>();

    public MyViewModel(@NonNull Application application) {
        super(application);
    }
}
