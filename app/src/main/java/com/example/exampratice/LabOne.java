package com.example.exampratice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LabOne extends AppCompatActivity {

    private TextView txtResult;
    private double num1 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_one);   // ← must match layout file name

        txtResult = findViewById(R.id.txtResult);
    }

    public void numberClick(View view) {
        Button b = (Button) view;
        String current = txtResult.getText().toString();

        if (current.equals("0")) {
            txtResult.setText(b.getText().toString());
        } else {
            txtResult.append(b.getText().toString());
        }
    }

    public void operatorClick(View view) {
        Button b = (Button) view;
        String current = txtResult.getText().toString();

        if (!current.isEmpty()) {
            num1 = Double.parseDouble(current);
            operator = b.getText().toString();
            txtResult.setText("0");
        }
    }

    public void equalClick(View view) {
        String current = txtResult.getText().toString();
        if (!current.isEmpty() && !operator.isEmpty()) {
            double num2 = Double.parseDouble(current);
            double result = 0;

            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        txtResult.setText("Error");
                        return;
                    }
                    break;
            }

            txtResult.setText(String.valueOf(result));
            operator = "";
        }
    }

    public void clearClick(View view) {
        txtResult.setText("0");
        num1 = 0;
        operator = "";
    }
}