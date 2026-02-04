package com.example.exampratice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentAPI {

    @GET("users")
    Call<List<StudentModel>> getStudents();

    // New: POST to create a student (jsonplaceholder fakes it)
    @POST("users")
    Call<StudentModel> addStudent(@Body StudentModel student);
}