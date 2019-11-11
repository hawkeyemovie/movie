package com.hawkeye.ElInterface;

import com.hawkeye.elpojo.ElCinema;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 影院
 */
public interface ElCinemaInterface extends ElasticsearchRepository<ElCinema,Integer> { }
