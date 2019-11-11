package com.hawkeye.elpojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * el实体类
 * 影院
 */
@Document(indexName = "cinema",type = "cinema", shards = 1, replicas = 0)
public class ElCinema {
    /**
     * 影院id
     */
    @Id
    private Integer id;

    /**
     * 影院名称
     */
    @Field(type = FieldType.Text, analyzer = "pinyin")
    private String cinemaName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public ElCinema(Integer id, String cinemaName) {
        this.id = id;
        this.cinemaName = cinemaName;
    }

    public ElCinema() {
    }
}
