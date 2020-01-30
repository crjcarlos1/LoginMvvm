package com.cralos.myapplicationlogin.login.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.myapplicationlogin.login.interfaces.ILoginRepository;
import com.cralos.myapplicationlogin.login.interfaces.ILoginViewModel;
import com.cralos.myapplicationlogin.login.interfaces.LoginView;
import com.cralos.myapplicationlogin.login.repository.LoginRepository;

public class LoginViewModel extends ViewModel implements ILoginViewModel {

    /*loader*/
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /*Repository*/
    private ILoginRepository repository;

    /*context*/
    private Context context;

    /*loginView*/
    private LoginView view;

    @Override
    public void showMessage(String message) {
        isLoading.setValue(false);
        view.showMessage(message);
    }

    @Override
    public void successLogin() {
        isLoading.setValue(false);
        view.successLogin();
    }

    public void init(LoginView view, Context context) {
        this.view = view;
        this.context = context;
        this.repository = new LoginRepository(this, context);
    }

    public void login(String email, String password) {
        isLoading.setValue(true);
        repository.login(email, password);
    }

}
