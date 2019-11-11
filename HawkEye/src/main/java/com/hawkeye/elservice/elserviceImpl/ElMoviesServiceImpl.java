package com.hawkeye.elservice.elserviceImpl;

import com.hawkeye.ElInterface.ElMoviesInterface;
import com.hawkeye.mapper.elmapper.ElMoviesMapper;
import com.hawkeye.elpojo.ElMovies;
import com.hawkeye.elservice.ElMoviesService;
import com.hawkeye.util.ESUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElMoviesServiceImpl implements ElMoviesService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElMoviesInterface elMoviesInterface;

    @Autowired
    ElMoviesMapper elMoviesMapper;

    @Override
    public synchronized List<ElMovies> getMovie(String search) {
        if (!elasticsearchTemplate.indexExists(ElMovies.class)){//判断索引是否存在
            elasticsearchTemplate.createIndex(ElMovies.class);//创建索引
            elasticsearchTemplate.putMapping(ElMovies.class);//创建映射
            elMoviesInterface.saveAll(elMoviesMapper.getMovie());//添加所有数据
        }

        NativeSearchQueryBuilder queryBuilder = ESUtil.getNSQBuilder();//创建查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("movieMainTitle", search));

        return elMoviesInterface.search(queryBuilder.build()).toList();//返回数据
    }
}
