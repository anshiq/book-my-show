package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.entity.Movie;
import com.bookMyShow.bookmyshow.entity.MovieElastic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bookMyShow.bookmyshow.Utilities.makeMovie;

@Service
@RequiredArgsConstructor
public class MovieElasticsearching {

    private static final String MOVIE_INDEX = "movie";

    @Autowired(required = false)
    private ElasticsearchOperations elasticsearchOperations;

    public List<Movie> findByText(String query) {
        if (elasticsearchOperations == null) {
            return new ArrayList<>();
        }
            Query searchQuery = new StringQuery(
                    "{\"bool\": {" +
                            "\"should\": [" +
                            "{\"wildcard\": {\"name\": {\"value\": \"*"+ query +"*\"}}}," +
                            "{\"wildcard\": {\"movieType\": {\"value\": \"*"+ query +"*\"}}}," +
                            "{\"wildcard\": {\"persona.name\": {\"value\": \"*"+ query +"*\"}}}" +
                            "]," +
                            "\"minimum_should_match\": 0" +
                            "}}");

            SearchHits<MovieElastic> movies = elasticsearchOperations.search(searchQuery, MovieElastic.class,
                    IndexCoordinates.of(MOVIE_INDEX));

            return movies.stream()
                    .map(a -> makeMovie(a.getContent()))
                    .toList();


    }

    public List<Movie> findByKey(String query) {
        if (elasticsearchOperations == null) {
            return new ArrayList<>();
        }
            Query searchQuery = new StringQuery(
                    "{\n" +
                            "\"bool\": {\n" +
                            "\"should\": [\n" +
                            "  {\"match\": {\"name\": {\"query\": \"" + query + "\", \"operator\": \"and\"}}},\n" +
                            "  {\"match\": {\"movieType\": {\"query\": \"" + query + "\", \"operator\": \"and\"}}},\n" +
                            "  {\"match\": {\"persona.name\": {\"query\": \"" + query + "\", \"operator\": \"and\"}}}\n" +
                            "],\n" +
                            "\"minimum_should_match\": 0\n" +
                            "}}");

            SearchHits<MovieElastic> movies = elasticsearchOperations.search(searchQuery, MovieElastic.class,
                    IndexCoordinates.of(MOVIE_INDEX));

            return movies.stream()
                    .map(a -> makeMovie(a.getContent()))
                    .toList();
        }




}
