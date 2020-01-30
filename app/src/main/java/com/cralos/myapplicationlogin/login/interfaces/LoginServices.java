package com.cralos.myapplicationlogin.login.interfaces;

import com.cralos.myapplicationlogin.login.models.LoginRequest;
import com.cralos.myapplicationlogin.login.models.LoginResponse;
import com.cralos.myapplicationlogin.retrofit.EndPoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServices {

    /**
     * @param request
     * @return
     */
    @POST(EndPoints.LOGIN)
    Call<LoginResponse> login(@Body LoginRequest request);

}
