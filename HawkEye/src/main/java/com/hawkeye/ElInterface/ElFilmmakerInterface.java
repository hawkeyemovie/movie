package com.hawkeye.ElInterface;

import com.hawkeye.elpojo.ElFilmmaker;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 影人
 */
public interface ElFilmmakerInterface extends ElasticsearchRepository<ElFilmmaker,Integer> { }
