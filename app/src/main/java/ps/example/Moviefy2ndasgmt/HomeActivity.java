package ps.example.Moviefy2ndasgmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String API_KEY = "7550d3e4cb2f49c8572a7d0a86e66067"; //i contacted TMDb and they gave me this key
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    ////////////////////////////////////////////////////////////////////////////////////////////
//    private static final String OMDB_API_KEY = "";
//    private static final String OMDB_API_BASE_URL = "http://www.omdbapi.com/";
    private RequestQueue requestQueue;

    private Button btnLogout;

    private List<Movie> movieList;
    private RecyclerView movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupViews();


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupViews() { //just making the hooks
        movies = findViewById(R.id.movies);
        btnLogout = findViewById(R.id.btnLogout);
//        edtLogPassword = findViewById(R.id.edtLogPassword);
//        rmmbrme = findViewById(R.id.rmmbrme);
        movies.setHasFixedSize(true);
        movies.setLayoutManager(new LinearLayoutManager(this));

        movieList=new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
//        requestQueue=volly.getRequestQueue();

        fetchMovies();
    }

    private void fetchMovies() {


        String url=TMDB_BASE_URL + "?api_key=" + API_KEY;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
                        try {
                            JSONArray moviesArray = response.getJSONArray("results");

                            // Create Movie objects for each movie in the array
                            for (int i = 0; i < moviesArray.length(); i++) {
                                JSONObject movieJson = moviesArray.getJSONObject(i);

                                String title = movieJson.getString("title");
                                String poster = movieJson.getString("poster_path");
                                String description = movieJson.getString("overview");
                                Double rating = movieJson.getDouble("vote_average");
                                String release = movieJson.getString("release_date");

                                // Create a Movie object
                                Movie movie = new Movie(title, poster, description, rating,release);

                                movieList.add(movie);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MovieAdapter adapter=new MovieAdapter(HomeActivity.this,movieList);
                        movies.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley_error", "Error: " + error.toString());
                    }


                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);


    }

}