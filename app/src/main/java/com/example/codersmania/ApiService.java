package com.example.codersmania;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/register/user")
    Call<UserModel> registerUser(@Body UserModel user);

    // Updated: Adding login endpoint
    @POST("/login/user")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
