package com.hawkeye.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public class ESUtil {

    @Autowired
    private static ElasticsearchTemplate elasticsearchTemplate;

    private ESUtil(){}

    private static NativeSearchQueryBuilder searchQuery;

    public static NativeSearchQueryBuilder getNSQBuilder(){
        if(searchQuery == null){
            synchronized(ESUtil.class){
                if(searchQuery == null){
                    searchQuery = new NativeSearchQueryBuilder();
                }
            }
        }
        return searchQuery;
    }

    public static ElasticsearchTemplate getESTemplate(){
        return elasticsearchTemplate;
    }
}
