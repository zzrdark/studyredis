package buluoFilter;

import redis.clients.jedis.Client;

/**
 * @author zzr
 */
public class BLFilterDemo {
    /**
     * > docker pull redislabs/rebloom  # 拉取镜像
     * > docker run -p6379:6379 redislabs/rebloom  # 运行容器
     * > redis-cli  # 连接容器中的 redis 服务...
     *
     *
     * 127.0.0.1:6379> bf.add codehole user1
     * (integer) 1
     * 127.0.0.1:6379> bf.add codehole user2
     * (integer) 1
     * 127.0.0.1:6379> bf.add codehole user3
     * (integer) 1
     * 127.0.0.1:6379> bf.exists codehole user1
     * (integer) 1
     * 127.0.0.1:6379> bf.exists codehole user2
     * (integer) 1
     * 127.0.0.1:6379> bf.exists codehole user3
     * (integer) 1
     * 127.0.0.1:6379> bf.exists codehole user4
     * (integer) 0
     * 127.0.0.1:6379> bf.madd codehole user4 user5 user6
     * 1) (integer) 1
     * 2) (integer) 1
     * 3) (integer) 1
     * 127.0.0.1:6379> bf.mexists codehole user4 user5 user6 user7
     * 1) (integer) 1
     * 2) (integer) 1
     * 3) (integer) 1
     * 4) (integer) 0...
     *
     *
     */

    public static void main(String[] args) {
        Client client = new Client();

        client.del("codehole");
        for (int i = 0; i < 100000; i++) {
            client.add("codehole", "user" + i);
            boolean ret = client.exists("codehole", "user" + i);
            if (!ret) {
                System.out.println(i);
                break;
            }
        }

        client.close();
    }

}
