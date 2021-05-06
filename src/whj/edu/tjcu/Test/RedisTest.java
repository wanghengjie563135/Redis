package whj.edu.tjcu.Test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis的测试类
 */
public class RedisTest {
    @Test
    public void RedisTest_1() {
        //1：建立连接,redismor接口：6379
        Jedis jedis = new Jedis("localhost", 6379);
        //2：操作
        jedis.set("username", "whj");
        //3：释放连接
        jedis.close();
        ;

    }

    @Test
    /**
     * 字符串String操作各种redis数据结构
     */
    public void RedisStringTest_1() {
        //1：建立连接,redismor接口：6379，空参默认值为localhost,6379
        Jedis jedis = new Jedis("localhost", 6379);

        //2：操作
        //储存
        jedis.set("username", "wsn");
        //获取
        String username = jedis.get("username");
        System.out.println(username);
        //可以使用setex()方法存储指定过期时间的key value
        jedis.setex("activecode", 20, "wsn");
        //3：释放连接
        jedis.close();


    }

    /**
     * Hash在jedis中的数据结构
     */
    @Test
    public void RedisHashTest_1() {
        //1：建立连接,redismor接口：6379，空参默认值为localhost,6379
        Jedis jedis = new Jedis();

        //2：操作
        //储存
        jedis.hset("user", "username", "whj");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "femal");
        //获取username的值
        String username = jedis.hget("user", "username");
        System.out.println(username);
        //获取所有hash的值
        Map<String, String> user = jedis.hgetAll("user");
        //keyset遍历集合
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //获取value，用get方法
            String value = user.get(key);
            System.out.println(key + ":" + value);

        }


        //释放连接
        jedis.close();




    }


    /**
     * List在jedis中的数据结构
     */
    @Test
    public void RedisListTest_1() {
        //1：建立连接,redismor接口：6379，空参默认值为localhost,6379
        Jedis jedis = new Jedis();
        //2：操作
        //储存
        jedis.lpush("myList","a","b","c");
        jedis.rpush("myList","d","e","f");
        //获取范围所有取值为0 —— -1
        List<String> myList = jedis.lrange("myList", 0, -1);
        System.out.println(myList);
         //删除列表左右两边的元素
        String myList1 = jedis.lpop("myList");//c
        System.out.println(myList1);
        String myList2 = jedis.rpop("myList");//f
        System.out.println(myList2);
        List<String> myList3 = jedis.lrange("myList", 0, -1);
        System.out.println(myList3);

        //释放连接
        jedis.close();
    }

    /**
     * Set在jedis中的数据结构,不可重复的集合
     */
    @Test
    public void RedisSetTest_1() {
        //1：建立连接,redismor接口：6379，空参默认值为localhost,6379
        Jedis jedis = new Jedis();

        //2：操作
        //储存
        jedis.sadd("mySet","java","python","php","oracle");
        //获取
        Set<String> mySet = jedis.smembers("mySet");
        System.out.println(mySet);


        //释放连接
        jedis.close();

    }

    /**
     *sortedset在jedis中的数据结构,不可重复的有序集合
     */
    @Test
    public void RedisSortedSetTest_1() {
        //1：建立连接,redismor接口：6379，空参默认值为localhost,6379
        Jedis jedis = new Jedis();
        //2：操作
        //储存
       jedis.zadd("mySortedset",15,"鲁班");
       jedis.zadd("mySortedset",8,"嫦娥");
       jedis.zadd("mySortedset",67,"安其拉");
       jedis.zadd("mySortedset",25,"后羿");
       //获取
        Set<String> mySortedset = jedis.zrange("mySortedset", 0, -1);
        System.out.println(mySortedset);
        jedis.zrem("mySortedset","鲁班");
        Set<String> mySortedset1 = jedis.zrange("mySortedset", 0, -1);
        System.out.println(mySortedset1);

        //释放连接
        jedis.close();
    }

    @Test
    /**
     * jedispoll连接池工具类
     */
    public void JedispollTest(){
        //创建jedis连接池
        JedisPool jedisPool = new JedisPool();
        //获取连接
        Jedis jedis = jedisPool.getResource();
        //使用
        String set = jedis.set("username", "wanghengjie");
        System.out.println(set);
        //归还连接，归还到连接池
        jedis.close();


    }



}
