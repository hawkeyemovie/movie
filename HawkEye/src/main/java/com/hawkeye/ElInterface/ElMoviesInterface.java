package com.hawkeye.ElInterface;


import com.hawkeye.elpojo.ElMovies;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 影视剧
 */
public interface ElMoviesInterface extends ElasticsearchRepository<ElMovies,Integer> { }
