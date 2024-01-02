package ps.example.Moviefy2ndasgmt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
//        holder.imageview.setText(movie.getRating().toString());
        holder.cTitle.setText(movie.getTitle());
        holder.cRating.setText(movie.getRating().toString());
        holder.cDescription.setText(movie.getDescription());
//
        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("description", movie.getDescription());
                intent.putExtra("rating", movie.getRating().toString());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView cTitle;
        TextView cRating;
        TextView cDescription;


        androidx.appcompat.widget.LinearLayoutCompat mCard;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            setupViews();
        }

        private void setupViews() { //just making the hooks
            imageview = itemView.findViewById(R.id.imageview);
            cTitle = itemView.findViewById(R.id.cTitle);
            cRating = itemView.findViewById(R.id.cRating);
            cDescription = itemView.findViewById(R.id.cDescription);
            mCard = itemView.findViewById(R.id.mCard);
        }
    }
}
