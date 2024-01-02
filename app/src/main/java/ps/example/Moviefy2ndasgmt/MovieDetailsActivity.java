package ps.example.Moviefy2ndasgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView imageView3;
    private TextView dTitle;
    private TextView dRating;
    private TextView dDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setupViews();

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String rating = intent.getStringExtra("rating");



        dTitle.setText(title);

        dDescription.setText(description);
        dRating.setText(rating);
    }

    private void setupViews() { //just making the hooks
        imageView3 = findViewById(R.id.imageView3);
        dTitle = findViewById(R.id.dTitle);
        dRating = findViewById(R.id.dRating);
        dDescription = findViewById(R.id.dDescription);

    }
}