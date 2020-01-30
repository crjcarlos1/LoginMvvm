package com.cralos.myapplicationlogin.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cralos.myapplicationlogin.R;
import com.cralos.myapplicationlogin.databinding.FragmentLoginBinding;
import com.cralos.myapplicationlogin.home.FragmentHome;
import com.cralos.myapplicationlogin.loader.Loader;
import com.cralos.myapplicationlogin.login.interfaces.LoginView;
import com.cralos.myapplicationlogin.login.interfaces.OnClickLogin;
import com.cralos.myapplicationlogin.login.viewmodel.LoginViewModel;

public class FragmentLogin extends Fragment implements OnClickLogin, LoginView {
    public static final String TAG = FragmentLogin.class.getSimpleName();

    /*dataBinding*/
    private FragmentLoginBinding binding;

    /*viewModel*/
    private LoginViewModel loginViewModel;

    /*loader*/
    private Loader loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setOnClickLoginListener(this);
        initFragmentLogin();
        return binding.getRoot();
    }

    /***
     *  dataBinding se invoca desde el onClick del botton en el xml
     * @param email
     * @param password
     */
    @Override
    public void login(String email, String password) {
        loginViewModel.login(email, password);
    }

    @Override
    public void successLogin() {
        loginViewModel.getIsLoading().removeObservers(getActivity());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentHome.TAG);
        transaction.replace(R.id.containerFragments, new FragmentHome(), FragmentHome.TAG).commit();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void initFragmentLogin() {
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        loginViewModel.init(this, getContext());
        loaderObserver();
    }

    private void loaderObserver() {
        loginViewModel.getIsLoading().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showLoader();
                } else {
                    hideLoader();
                }
            }
        });
    }

    private void showLoader() {
        loader = new Loader();
        loader.show(getActivity().getSupportFragmentManager(), Loader.TAG);
    }

    private void hideLoader() {
        if (loader != null) {
            loader.dismiss();
            loader = null;
        }
    }


}
