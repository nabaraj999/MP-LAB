package com.example.exampratice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PopupDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_demo);

        Button btnShowPopup = findViewById(R.id.btnShowPopup);

        btnShowPopup.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_edit) {
                    Toast.makeText(this, "Edit clicked", Toast.LENGTH_LONG).show();
                } else if (id == R.id.action_delete) {
                    Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.action_share) {
                    Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popup.show();
        });
    }
}