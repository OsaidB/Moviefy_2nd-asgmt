package ps.example.Moviefy2ndasgmt;
public class Movie {
    private String title;
    private String poster;
    private String description;
    private Double rating;

    public Movie(String title, String poster, String description, Double rating) {

        this.title = title;
        this.poster = poster;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
