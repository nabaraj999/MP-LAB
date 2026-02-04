package com.example.exampratice;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StudentInputActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_input);

        tvResult = findViewById(R.id.tvResult);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(v -> showStudentDialog());
    }

    private void showStudentDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_student_details);
        dialog.setCancelable(true);

        EditText etName   = dialog.findViewById(R.id.etName);
        EditText etRoll   = dialog.findViewById(R.id.etRoll);
        EditText etResult = dialog.findViewById(R.id.etResult);
        EditText etGrade  = dialog.findViewById(R.id.etGrade);

        Button btnCancel  = dialog.findViewById(R.id.btnCancel);
        Button btnSave    = dialog.findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            String name   = etName.getText().toString().trim();
            String roll   = etRoll.getText().toString().trim();
            String result = etResult.getText().toString().trim();
            String grade  = etGrade.getText().toString().trim();

            if (name.isEmpty() || roll.isEmpty()) {
                Toast.makeText(this, "Name and Roll are required", Toast.LENGTH_SHORT).show();
                return;
            }

            String info = "Student Information:\n\n" +
                    "Name   :  " + name + "\n" +
                    "Roll   :  " + roll + "\n" +
                    "Result :  " + (result.isEmpty() ? "—" : result) + "\n" +
                    "Grade  :  " + (grade.isEmpty() ? "—" : grade);

            tvResult.setText(info);
            tvResult.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();

        // Optional: make dialog wider
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    (int)(getResources().getDisplayMetrics().widthPixels * 0.90),
                    dialog.getWindow().getAttributes().height
            );
        }
    }
}