package com.alu.weatherwise;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.weatherwise.databinding.ActivityHomeBinding;

import java.time.LocalDate;

public class HomeActivity extends AppCompatActivity {

    ImageView toProfile;
    TextView townName, currentDate, overview, details;
    FrameLayout overviewDetailsContainer;

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

        ActivityHomeBinding homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());

        // Initialize the views
        initViews(homeBinding);

        this.toProfile.setOnClickListener(v -> Toast.makeText(this, "Redirecting to the profile page", Toast.LENGTH_LONG).show());
        this.townName.setText("Nairobi");
        this.currentDate.setText(getDate());
        this.details.setOnClickListener(v -> switchToDetails());
        this.overview.setOnClickListener(v -> switchToOverview());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();

    }

    private void initViews(ActivityHomeBinding binding) {
        this.toProfile = findViewById(R.id.IV_toProfile);
        this.townName = findViewById(R.id.TV_townName);
        this.currentDate = findViewById(R.id.TV_currentDate);
        this.overview = findViewById(R.id.TV_overview);
        this.details = findViewById(R.id.TV_details);
        this.overviewDetailsContainer = binding.FLOverviewDetails;
    }

    private String getDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth() + " " + localDate.getMonth() + ", " + localDate.getYear();
    }

    /**
     * This method switches to the Overview Fragment
     */
    private void switchToOverview() {
        this.details.setBackground(getDrawable(R.drawable.active_tab_background));
        this.overview.setBackgroundColor(getColor(R.color.transparent));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new DetailsFragment())
                .commit();
    }

    /**
     * This method switches to the Details Fragment
     */
    private void switchToDetails() {
        this.overview.setBackground(getDrawable(R.drawable.active_tab_background));
        this.details.setBackgroundColor(getColor(R.color.transparent));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();
    }
}