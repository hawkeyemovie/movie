package com.hawkeye.reservice.impl;

import com.alibaba.fastjson.JSON;
import com.hawkeye.mapper.remapper.ClassicMapper;
import com.hawkeye.repojo.Popular;
import com.hawkeye.reservice.ClassicService;
import com.hawkeye.util.RedisKeys;
import com.hawkeye.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassicServiceImpl implements ClassicService {
    @Autowired
    ClassicMapper classicMapper;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 首页的经典电影 从redis种获取八条数据
     * @return
     */
    @Override
    public List<Popular> HomeGetClassic() {
        String strClassic = redisUtil.get(RedisKeys.CLASSIC);
        List<Popular> popularList = null;
        if (strClassic == null){
            popularList = classicMapper.getClassic();
            strClassic = JSON.toJSONString(popularList);
            redisUtil.set(RedisKeys.CLASSIC, strClassic);
        }
        popularList = JSON.parseArray(strClassic,Popular.class);
        return popularList.size() <= 8 ? popularList : popularList.subList(0,8);
    }

    /**
     * 导航栏电影中的经典电影的请求方法
     * @param typeId
     * @param sourceId
     * @param yearId
     * @return
     */
    @Override
    public List<Popular> getClassic(String typeId, String sourceId, String yearId) {
        String strClassic = redisUtil.get(RedisKeys.CLASSIC);
        List<Popular> popularList = null;
        if (strClassic == null){
            popularList = classicMapper.getClassic();
            strClassic = JSON.toJSONString(popularList);
            redisUtil.set(RedisKeys.CLASSIC, strClassic);
        }
        popularList = JSON.parseArray(strClassic,Popular.class);
        if(typeId != null ){
            for (int i = popularList.size()-1; i >= 0; i--) {
                if(popularList.get(i).getTypeId() != Integer.parseInt(typeId) ){
                    popularList.remove(i);
                }
            }
        }
        if(sourceId != null){
            for (int i = popularList.size()-1; i >= 0 ; i--) {
                if(popularList.get(i).getRegionId() != Integer.parseInt(sourceId)){
                    popularList.remove(i);
                }
            }
        }
        if(yearId != null){
            for (int i = popularList.size()-1; i >= 0 ; i--) {
                if(popularList.get(i).getYear() != Integer.parseInt(yearId)){
                    popularList.remove(i);
                }
            }
        }
        //list转map  Collectors.toMap(SoonRelease :: getId,v -> v, (v1,v2) -> v2 )
        //SoonRelease :: getId map的键
        //v -> v 表示选择将原来的对象作为map的value值；
        //(v1,v2) -> v2 如果v1与v2的key值相同，选择v2作为那个key所对应的value值
        Map<Integer,Popular> map = popularList.stream().collect(Collectors.toMap(Popular :: getId, v -> v, (v1, v2) -> v2 ));
        popularList = new ArrayList<>(map.values());

        return popularList;
    }


}
