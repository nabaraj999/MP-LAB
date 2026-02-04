package com.example.exampratice;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContextDemoActivity extends AppCompatActivity {

    private TextView tvLongPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_demo);

        tvLongPress = findViewById(R.id.tvLongPress);

        // Important: Register the view for context menu
        registerForContextMenu(tvLongPress);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Optional: title shown at top of menu
        menu.setHeaderTitle("Choose Action");

        // Inflate from resource (recommended)
        getMenuInflater().inflate(R.menu.context_menu, menu);

        // Alternative: add items programmatically
        // menu.add(0, 101, 0, "Copy");
        // menu.add(0, 102, 0, "Paste");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_copy) {
            Toast.makeText(this, "Copy selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_paste) {
            Toast.makeText(this, "Paste selected", Toast.LENGTH_LONG).show();
        } else if (id == R.id.action_delete) {
            Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);  // or return true
    }
}