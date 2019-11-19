package com.hawkeye.elservice.elserviceImpl;

import com.hawkeye.ElInterface.ElCinemaInterface;
import com.hawkeye.mapper.elmapper.ElCinemaMapper;
import com.hawkeye.elpojo.ElCinema;
import com.hawkeye.elservice.ElCinemaService;
import com.hawkeye.util.ESUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElCinemaServiceImpl implements ElCinemaService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElCinemaInterface elCinemaInterface;

    @Autowired
    ElCinemaMapper elCinemaMapper;

    @Override
    public synchronized List<ElCinema> getElCinema(String search) {
        if (!elasticsearchTemplate.indexExists(ElCinema.class)){
            elasticsearchTemplate.createIndex(ElCinema.class);//创建索引
            elasticsearchTemplate.putMapping(ElCinema.class);//创建映射
            elCinemaInterface.saveAll(elCinemaMapper.getElCinema());//添加全部数据
        }

        NativeSearchQueryBuilder queryBuilder = ESUtil.getNSQBuilder();//创建查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("cinemaName", search));

        return elCinemaInterface.search(queryBuilder.build()).toList();//返回数据
    }
}
