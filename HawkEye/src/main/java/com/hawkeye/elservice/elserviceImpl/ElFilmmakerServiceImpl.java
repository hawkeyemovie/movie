package com.hawkeye.elservice.elserviceImpl;

import com.hawkeye.ElInterface.ElFilmmakerInterface;
import com.hawkeye.elpojo.ElFilmmaker;
import com.hawkeye.elservice.ElFilmmakerService;
import com.hawkeye.mapper.elmapper.ElFilmmakerMapper;
import com.hawkeye.util.ESUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElFilmmakerServiceImpl implements ElFilmmakerService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElFilmmakerInterface elFilmmakerInterface;

    @Autowired
    ElFilmmakerMapper elFilmmakerMapper;

    @Override
    public synchronized List<ElFilmmaker> getFilmmakers(String search) {
        if (!elasticsearchTemplate.indexExists(ElFilmmaker.class)){//判断索引是否存在
            elasticsearchTemplate.createIndex(ElFilmmaker.class);//创建索引
            elasticsearchTemplate.putMapping(ElFilmmaker.class);//创建映射
            elFilmmakerInterface.saveAll(elFilmmakerMapper.getFilmmakers());//添加全部数据
        }

        NativeSearchQueryBuilder queryBuilder = ESUtil.getNSQBuilder();//创建查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("filmmakerName", search));

        return elFilmmakerInterface.search(queryBuilder.build()).toList();//返回数据
    }
}
