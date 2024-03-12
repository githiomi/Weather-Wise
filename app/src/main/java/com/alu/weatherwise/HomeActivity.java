package com.alu.weatherwise;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomeActivity extends AppCompatActivity {

    ImageView toProfile;
    TextView townName, currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the views
        initViews();

        this.toProfile.setOnClickListener(v -> Toast.makeText(this, "Redirecting to the profile page", Toast.LENGTH_LONG).show());
        this.townName.setText("Nairobi");
        this.currentDate.setText(getDate());

    }

    private void initViews() {
        this.toProfile = findViewById(R.id.IV_toProfile);
        this.townName = findViewById(R.id.TV_townName);
        this.currentDate = findViewById(R.id.TV_currentDate);
    }

    private String getDate() {
        LocalDate localDate = LocalDate.now().plusDays(3);
        int day = localDate.getDayOfMonth();
        String month = localDate.getMonth().toString();
        int year = localDate.getYear();

        return day + " " + month + ", " + year;
    }
}