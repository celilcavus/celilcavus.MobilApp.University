package com.example.celilcavusmobilapprandomuser.Interfaces;

import com.example.celilcavusmobilapprandomuser.Entity.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUniversityServices {
    @GET("search")
    Call<List<University>> GetUniversity(@Query("country") String UniversityName);

}
