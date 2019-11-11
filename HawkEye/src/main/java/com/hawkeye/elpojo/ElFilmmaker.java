package com.hawkeye.elpojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * el实体类
 * 影人
 */
@Document(indexName = "filmmaker",type = "filmmaker", shards = 1, replicas = 0)
public class ElFilmmaker{

    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String  defaultImg;

    @Field(type = FieldType.Text, analyzer = "pinyin")
    private String filmmakerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getFilmmakerName() {
        return filmmakerName;
    }

    public void setFilmmakerName(String filmmakerName) {
        this.filmmakerName = filmmakerName;
    }

    public ElFilmmaker(Integer id, String defaultImg, String filmmakerName) {
        this.id = id;
        this.defaultImg = defaultImg;
        this.filmmakerName = filmmakerName;
    }

    public ElFilmmaker() {
    }
}
