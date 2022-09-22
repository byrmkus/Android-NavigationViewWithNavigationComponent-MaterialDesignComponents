package com.example.navigationcomponentkullanimi.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> country = new MutableLiveData<>("Serbia");
    public LiveData<String> countryLiveData =  country;

    public void saveCountry(String newCountry) {
        country.postValue(newCountry);
    }

    public LiveData<String> getCountryLiveData(){
        return countryLiveData;
    }
}
