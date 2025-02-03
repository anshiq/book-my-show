package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.MovieElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

@Repository
//@EnableElasticsearchRepositories
public interface MovieElasticRepository extends ElasticsearchRepository<MovieElastic,String> {
}
