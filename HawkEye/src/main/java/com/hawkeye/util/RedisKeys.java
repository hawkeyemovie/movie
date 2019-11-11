package com.hawkeye.util;

public class RedisKeys {
    /**
     * 热门电影存储在redis里的键
     */
    public static final String POPULAR_KEY = "POPULAR_KEY";

    /**
     * 即将上映电影存储在redis里的键
     */
    public static final String SOONRELEASE = "SOONRELEASE";

    /**
     * 经典电影存储在redis里的键
     */
    public static final String CLASSIC = "CLASSIC";

    /**
     * 最受期待榜存储在redis里的键
     */
    public static  final String MOSTANTICIPATED = "MOSTANTICIPATED";

    /**
     * TOP100榜存储在redis里的键
     */
    public static final String TOP = "TOP";
}
