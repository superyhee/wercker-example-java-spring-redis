package services;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HitCounter {
    private static final String DETAULT_REDIS_HOST = "redis";
    private static HitCounter ourInstance = new HitCounter();

    public static HitCounter getInstance() {
        return ourInstance;
    }

    private String key = "counter";
    private JedisPool pool;

    private HitCounter() {

        String hostname = DETAULT_REDIS_HOST;
        String getConfFrom = System.getenv("GET_CONF_FROM");
        if ("env".equals(getConfFrom)) {
            hostname = System.getenv("REDIS_HOST");
        }

        System.out.println("Connecting to redis at host '" + hostname + "'");

        InetAddress address;
        try {
            address = InetAddress.getByName(hostname);
            System.out.println("IP : " + address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        pool = new JedisPool(new JedisPoolConfig(), hostname);
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, "0");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long incr() {
        Long returnValue = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            returnValue = jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return returnValue;
    }
}