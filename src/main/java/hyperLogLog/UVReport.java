package hyperLogLog;

import redis.clients.jedis.Jedis;

/**
 * @author zzr
 */
public class UVReport {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 100000; i++) {
            jedis.pfadd("codehole1", "user" + i);
        }
        long total = jedis.pfcount("codehole1");
        System.out.printf("%d %d\n", 100000, total);
        jedis.close();
    }

}
