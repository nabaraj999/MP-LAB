package com.example.exampratice;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentApiDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private StudentAdapter studentAdapter;
    private List<StudentModel> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_api_demo);

        recyclerView = findViewById(R.id.studentRecycler);
        progressBar = findViewById(R.id.progressBar);
        tvEmpty = findViewById(R.id.tvEmpty);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        loadStudents();
    }

    private void loadStudents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")  // ← working test URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI api = retrofit.create(StudentAPI.class);

        // For JSONPlaceholder use "users" instead of "students"
        Call<List<StudentModel>> call = api.getStudents();  // change @GET("users") in interface if using this

        progressBar.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    studentList.clear();
                    studentList.addAll(response.body());
                    studentAdapter.notifyDataSetChanged();

                    if (studentList.isEmpty()) {
                        tvEmpty.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    // Debug: show how many loaded
                    Toast.makeText(StudentApiDemoActivity.this,
                            "Loaded " + studentList.size() + " students", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(StudentApiDemoActivity.this,
                            "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(StudentApiDemoActivity.this,
                        "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                tvEmpty.setVisibility(View.VISIBLE);
            }
        });
    }
}