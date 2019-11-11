package com.hawkeye.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hawkeye.pojo.Movie;
import com.hawkeye.repojo.Popular;
import com.hawkeye.repojo.SoonRelease;
import com.hawkeye.reservice.BillboardService;
import com.hawkeye.reservice.ClassicService;
import com.hawkeye.reservice.SoonReleaseService;
import com.hawkeye.reservice.impl.PopularServiceImpl;
import com.hawkeye.service.impl.MovieRegionServiceImpl;
import com.hawkeye.service.impl.MovieServiceImpl;
import com.hawkeye.service.impl.MovieTypeServiceImpl;
import com.hawkeye.util.RedisKeys;
import com.hawkeye.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 展示控制器
 */
@Controller
@RequestMapping("/exhibition")
public class ExhibitionController {

    /**
     * 类型
     */
    @Autowired
    MovieTypeServiceImpl movieTypeService;
    /**
     * redis
     */
    @Autowired
    RedisUtil redisUtil;

    /**
     * 区域
     */
    @Autowired
    MovieRegionServiceImpl movieRegionService;

    /**
     *电影
     */
    @Autowired
    MovieServiceImpl movieService;

    /**
     *热门电影
     */
    @Autowired
    PopularServiceImpl popularService;

    /**
     * 即将上映电影
     */
    @Autowired
    SoonReleaseService soonReleaseService;

    /**
     * 经典电影
     */
    @Autowired
    ClassicService classicService;

    /**
     * 榜单
     */
    @Autowired
    BillboardService billboardService;

    /**
     * 得到所有电影类型
     * @return
     */
    @RequestMapping("/movietype")
    @ResponseBody
    public String movietype(){
        return JSON.toJSONString(movieTypeService.getMovieTypes());
    }

    /**
     * 得到所有区域
     * @return
     */
    @RequestMapping("/movieregion")
    @ResponseBody
    public String movieregion(){
        return JSON.toJSONString(movieRegionService.getMovieRegions());
    }

    /**
     * 得到所有电影
     * @return
     */
    @RequestMapping("/movie")
    @ResponseBody
    public List<Movie> movie(Map<String,Object> map,@RequestParam(value = "page",defaultValue = "1")Integer page, Integer typrId, Integer regionId, String release){
        PageHelper.startPage(page,5);
        PageInfo<Movie> pageInfo = new PageInfo<>(movieService.getMovies(typrId,regionId,release));
        return pageInfo.getList();
    }

    /**
     * 首页显示热门数据的请求方法
     * @return
     */
    @RequestMapping("/index/popular")
    @ResponseBody
    public List<Popular> homePagePopular(){
        //获取热门数据
        String strPopular = redisUtil.get(RedisKeys.POPULAR_KEY);
        List<Popular> popularList = null;
        //如果为空就去数据库拿然后写入redis
        if(strPopular == null){
            popularList = popularService.getPopular();
            redisUtil.set(RedisKeys.POPULAR_KEY,JSON.toJSONString(popularList));
            //返回集合中的前8条数据
            return popularList.subList(0,8);
        }
        //字符串转换成热门对象集合
        popularList = JSON.parseArray(strPopular,Popular.class);
        //返回集合中的前8条数据
        return popularList.size() <= 8 ? popularList : popularList.subList(0,8);
    }

    /**
     * 首页的即将上映电影的请求方法
     * @return
     */
    @RequestMapping("/index/soonrelease")
    @ResponseBody
    public List<SoonRelease> homePageSoonRelease(){
        //获取redis的数据
        String strSoonRelease = redisUtil.get(RedisKeys.SOONRELEASE);
        List<SoonRelease> soonReleasesList = null;
        //判断redis有没有数据
        if(strSoonRelease == null){
            //redis没有数据去数据库拿
            soonReleasesList = soonReleaseService.getSoonRelease();
            //存入redis
            redisUtil.set(RedisKeys.SOONRELEASE,JSON.toJSONString(soonReleasesList));
            strSoonRelease = redisUtil.get(RedisKeys.SOONRELEASE);
        }
        //将JSON格式的字符串转成集合
        soonReleasesList = JSON.parseArray(strSoonRelease,SoonRelease.class);
        //返回集合前8条数据
        return soonReleasesList.size() <= 8 ? soonReleasesList : soonReleasesList.subList(0,8);
    }

    /**
     * 电影的正在热映的请求方法
     * @return
     */
    @RequestMapping("/shouingup")
    @ResponseBody
    public List<Popular> isShowingUp(String typeId,String sourceId,String yearId){
        //从redis获取热门数据
        String strPopular = redisUtil.get(RedisKeys.POPULAR_KEY);
        List<Popular> popularList = null;
        //如果为空就去数据库拿然后写入redis
        if(strPopular == null){
            //去数据库拿
            popularList = popularService.getPopular();
            //写入redis
            redisUtil.set(RedisKeys.POPULAR_KEY,JSON.toJSONString(popularList));
            strPopular = redisUtil.get(RedisKeys.POPULAR_KEY);
        }
        //将字符串转换成集合
        popularList = JSON.parseArray(strPopular,Popular.class);
        if(typeId != null){
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
        Map<Integer,Popular> map = popularList.stream().collect(Collectors.toMap(Popular :: getId,v -> v, (v1,v2) -> v2 ));
        popularList = new ArrayList<>(map.values());
        return popularList;
    }

    /**
     * 即将上映电影的请求方法
     * @param typeId  类型Id
     * @param sourceId 区域Id
     * @param yearId 年代Id
     * @return
     */
    @RequestMapping("/soonrelease")
    @ResponseBody
    public List<SoonRelease> soonRelease(String typeId,String sourceId,String yearId){
        //获取redis的数据
        String strSoonRelease = redisUtil.get(RedisKeys.SOONRELEASE);
        List<SoonRelease> soonReleasesList = null;
        //判断redis有没有数据
        if(strSoonRelease == null){
            //redis没有数据去数据库拿
            soonReleasesList = soonReleaseService.getSoonRelease();
            //存入redis
            redisUtil.set(RedisKeys.SOONRELEASE,JSON.toJSONString(soonReleasesList));
            strSoonRelease = redisUtil.get(RedisKeys.SOONRELEASE);
        }
        //将JSON格式的字符串转成集合
        soonReleasesList = JSON.parseArray(strSoonRelease,SoonRelease.class);
        if(typeId != null ){
            for (int i = soonReleasesList.size()-1; i >= 0; i--) {
                if(soonReleasesList.get(i).getTypeId() != Integer.parseInt(typeId) ){
                    soonReleasesList.remove(i);
                }
            }
        }
        if(sourceId != null){
            for (int i = soonReleasesList.size()-1; i >= 0 ; i--) {
                if(soonReleasesList.get(i).getRegionId() != Integer.parseInt(sourceId)){
                    soonReleasesList.remove(i);
                }
            }
        }
        if(yearId != null){
            for (int i = soonReleasesList.size()-1; i >= 0 ; i--) {
                if(soonReleasesList.get(i).getYear() != Integer.parseInt(yearId)){
                    soonReleasesList.remove(i);
                }
            }
        }
        //list转map  Collectors.toMap(SoonRelease :: getId,v -> v, (v1,v2) -> v2 )
        //SoonRelease :: getId map的键
        //v -> v 表示选择将原来的对象作为map的value值；
        //(v1,v2) -> v2 如果v1与v2的key值相同，选择v2作为那个key所对应的value值
        Map<Integer, SoonRelease> map = soonReleasesList.stream().collect(Collectors.toMap(SoonRelease :: getId, v -> v, (v1, v2) -> v2 ));
        soonReleasesList = new ArrayList<>(map.values());
        return soonReleasesList;
    }


    /**
     * 首页经典电影的请求方法
     * @return
     */
    @RequestMapping("/index/classic")
    @ResponseBody
    public List<Popular> homePageClassic(){
        return classicService.HomeGetClassic();
    }

    /**
     * 导航栏的电影中的经典电影的请求方法
     * @param typeId
     * @param sourceId
     * @param yearId
     * @return
     */
    @RequestMapping("classic")
    @ResponseBody
    public List<Popular> classic(String typeId,String sourceId,String yearId){
        return classicService.getClassic(typeId,sourceId,yearId);
    }

    /**
     * 最受期待的请求方法
     * @return
     */
    @RequestMapping("/mostanticipated")
    @ResponseBody
    public List<Movie> mostAnticipated(){
        List<Movie> list = billboardService.getMostAnticipated();
        return list;
    }

    /**
     * TOP100榜的请求方法
     * @param displayNum
     * @return
     */
    @RequestMapping("/topbill")
    @ResponseBody
    public List<Movie> topBill(String displayNum) {
        return displayNum == null ? billboardService.getTOPBill(0) : billboardService.getTOPBill(Integer.parseInt(displayNum));
    }
}
