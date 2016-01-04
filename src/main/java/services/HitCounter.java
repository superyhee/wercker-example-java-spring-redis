package services;

import redis.clients.jedis.Jedis;

public class HitCounter {
    public static final int REDIS_PORT =6379;
    public static final String REDIS_ADDR_KEY = "REDIS_PORT_" + REDIS_PORT + "_TCP_ADDR";

    private static HitCounter ourInstance = new HitCounter();

    public static HitCounter getInstance() {
        return ourInstance;
    }

    private String key = "counter";
    private Jedis jedis;

    private HitCounter() {
        jedis = new Jedis(System.getenv(REDIS_ADDR_KEY));
        jedis.set(key, "0");
    }

    public Long incr() {
        return jedis.incr(key);
    }
}
