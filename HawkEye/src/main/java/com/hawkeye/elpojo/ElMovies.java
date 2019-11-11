package com.hawkeye.elpojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * el实体类
 * 影视
 */
@Document(indexName = "movies",type = "movies", shards = 1, replicas = 0)
public class ElMovies {

    @Field(type = FieldType.Text)
    private String  defaultImg;
    @Id
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "pinyin")
    private String movieMainTitle;

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieMainTitle() {
        return movieMainTitle;
    }

    public void setMovieMainTitle(String movieMainTitle) {
        this.movieMainTitle = movieMainTitle;
    }

    public ElMovies(String defaultImg, Integer id, String movieMainTitle) {
        this.defaultImg = defaultImg;
        this.id = id;
        this.movieMainTitle = movieMainTitle;
    }

    public ElMovies() {
    }
}
