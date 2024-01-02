package ps.example.Moviefy2ndasgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.appsearch.SearchResult;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView imageView3;
    private TextView dTitle;
    private TextView dRating;
    private TextView dDescription;
    private TextView dRelease;

    private Button btnTrailer;

    private static final String YOUTUBE_API_KEY = "AIzaSyCwIZEDLNudboWBfnX8TAvPSAfnY8-yeTM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setupViews();

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String rating = intent.getStringExtra("rating");
        String release = intent.getStringExtra("release");

        dTitle.setText(title);

        dRating.setText(rating);
        dDescription.setText(description);
        dRelease.setText("Release Date: " + release);


        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchOnYouTube(dTitle.getText().toString());
//                searchOnGoogleApi(dTitle.getText().toString());
                searchOnYouTube(dTitle.getText().toString());
//                watchMovieTrailer(getApplicationContext(), dTitle.getText().toString());
            }
        });


    }

    private void setupViews() { //just making the hooks
        imageView3 = findViewById(R.id.imageView3);
        dTitle = findViewById(R.id.dTitle);
        dRating = findViewById(R.id.dRating);
        dDescription = findViewById(R.id.dDescription);

        dRelease = findViewById(R.id.dRelease);

        btnTrailer = findViewById(R.id.btnTrailer);

    }

    private void searchOnYouTube(String query) {
        // Construct the YouTube search URL
        String youtubeSearchUrl = "https://www.youtube.com/results?search_query=" + Uri.encode(query + " trailer");

        // Open the YouTube search results in a web browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeSearchUrl));
        startActivity(intent);
    }

    private void searchOnGoogleApi(String query) {
        String googleApiSearchUrl = "https://www.google.com/search?q=" + Uri.encode(query + " site:youtube.com");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleApiSearchUrl));
        startActivity(intent);
    }


    public static void watchMovieTrailer(Context context, String movieTitle) {
        List<SearchResult> searchResults = searchMovieVideos(movieTitle);
        String api = YOUTUBE_API_KEY;
        if (searchResults != null && !searchResults.isEmpty()) {
//            String videoId = searchResults.get(0).getId().getVideoId();
            String videoId = searchResults.get(0).toString();
            String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            context.startActivity(intent);
        }
    }

    private static List<SearchResult> searchMovieVideos(String movieTitle) {


        return null;
    }
}