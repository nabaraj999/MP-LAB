package com.example.exampratice;  // ← change to your real package

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class StudentCrudActivity extends AppCompatActivity {

    private EditText etName, etRollNo, etId;
    private Button btnAdd, btnView, btnUpdate, btnDelete;
    private ListView listViewStudents;

    private StudentDbHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_crud);   // ← match your layout file name

        etName    = findViewById(R.id.etName);
        etRollNo  = findViewById(R.id.etRollNo);
        etId      = findViewById(R.id.etId);

        btnAdd    = findViewById(R.id.btnAdd);
        btnView   = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        listViewStudents = findViewById(R.id.listViewStudents);

        dbHelper = new StudentDbHelper(this);

        loadStudents();  // show list when screen opens

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String roll = etRollNo.getText().toString().trim();

            if (name.isEmpty() || roll.isEmpty()) {
                Toast.makeText(this, "Fill name and roll number", Toast.LENGTH_SHORT).show();
                return;
            }

            long result = dbHelper.addStudent(name, roll);
            if (result > 0) {
                Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etRollNo.setText("");
                loadStudents();
            } else {
                Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(v -> loadStudents());

        btnUpdate.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            if (idStr.isEmpty()) {
                Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (Exception e) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString().trim();
            String roll = etRollNo.getText().toString().trim();

            if (name.isEmpty() || roll.isEmpty()) {
                Toast.makeText(this, "Fill name and roll", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.updateStudent(id, name, roll)) {
                Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
                loadStudents();
            } else {
                Toast.makeText(this, "ID not found", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            if (idStr.isEmpty()) {
                Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (Exception e) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.deleteStudent(id)) {
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                etId.setText("");
                loadStudents();
            } else {
                Toast.makeText(this, "ID not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadStudents() {
        List<String> students = dbHelper.getAllStudents();

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
            listViewStudents.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(students);
            adapter.notifyDataSetChanged();
        }

        if (students.isEmpty()) {
            Toast.makeText(this, "No students yet", Toast.LENGTH_SHORT).show();
        }
    }
}