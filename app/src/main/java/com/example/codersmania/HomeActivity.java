package com.example.codersmania;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the LinearLayout container
        LinearLayout categoriesContainer = findViewById(R.id.categoriesContainer);

        // Loop through all enum values in the Category class
        for (Category category : Category.values()) {
            // Create a new TextView for each category
            TextView textView = new TextView(this);
            textView.setText(category.getDisplayName());
            textView.setTextSize(18); // Set text size
            textView.setPadding(16, 16, 16, 16);

            // Add the TextView to the LinearLayout container
            categoriesContainer.addView(textView);
        }
    }
}
