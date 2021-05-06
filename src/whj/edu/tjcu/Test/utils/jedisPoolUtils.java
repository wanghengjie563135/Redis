package whj.edu.tjcu.Test.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 连接池工具类
 */
public class jedisPoolUtils {
    private  static JedisPool jedisPool;

    static {
        //类加载时，读取配置文件
        InputStream is=jedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建properties对象
        // properties文件一般是用来放一些配置文件或其他在以后编程中可能会改变的值，一般存放的都是键值对。从程序中可以直接通过键读取值。
        Properties properties = new Properties();
        //关联文件
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        //初始化jedispool
        jedisPool=new JedisPool(config,properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));

    }

    /**
     * 获取连接方法
     * @return
     */
    public static Jedis getJedis(){
        return  jedisPool.getResource();
    }

}
