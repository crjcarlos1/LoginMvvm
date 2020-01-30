package com.cralos.myapplicationlogin.login.repository;

import android.content.Context;
import android.os.Handler;

import com.cralos.myapplicationlogin.login.interfaces.ILoginRepository;
import com.cralos.myapplicationlogin.login.interfaces.ILoginViewModel;

public class LoginRepository implements ILoginRepository {

    private ILoginViewModel viewModel;
    private Context context;

    public LoginRepository(ILoginViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public void login(String email, String password) {
        if (email == null || email.length() == 0 || password == null || password.length() == 0) {
            viewModel.showMessage("ingresa email y contraseña");
        } else {
            requestLogin(email, password);
        }
    }

    private void requestLogin(String email, String password) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.successLogin();
            }
        }, 3000);
    }

}
