package com.ukar.round;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jia.you
 * @date 2018/12/18
 *
 * 简单轮询和加权轮询算法
 *
 * 考虑到并发情况，可以将此类设计为单例模式
 */
public class WeightRoundRobin {

    /**
     * 服务集合
     */
    private List<Server> servers;

    /**
     * 当前服务索引
     */
    private int currentIndex;

    /**
     * 总服务数
     */
    private int totalServer;

    /**
     * 当前权重
     */
    private int currentWeight;

    /**
     * 最大权重
     */
    private int maxWeight;

    /**
     * 服务器权重最大公约数
     */
    private int gcdWeight;

    public WeightRoundRobin() {
        servers = new ArrayList<>();
        servers.add(new Server("192.168.1.1", 1));
        servers.add(new Server("192.168.1.2", 5));
        servers.add(new Server("192.168.1.3", 10));
        servers.add(new Server("192.168.1.4", 15));
        servers.add(new Server("192.168.1.5", 100));
        servers.add(new Server("192.168.1.6", 5));
        servers.add(new Server("192.168.1.7", 20));
        servers.add(new Server("192.168.1.8", 30));
        totalServer = servers.size();
        currentIndex = totalServer - 1;
        maxWeight = maxWeight();
        gcdWeight = serverGcd();
    }

    /**
     * 返回所有服务器的权重的最大公约数
     *
     * @return
     */
    private int serverGcd() {
        int comDivisor = 0;
        for (int i = 0; i < totalServer - 1; i++) {
            if (comDivisor == 0) {
                comDivisor = gcd(servers.get(i).getWeight(), servers.get(i + 1).getWeight());
            } else {
                comDivisor = gcd(comDivisor, servers.get(i + 1).getWeight());
            }
        }
        return comDivisor;
    }

    /**
     * 获得服务器中的最大权重
     *
     * @return
     */
    private int maxWeight() {
        int max = servers.get(0).getWeight();
        int tmp;
        for (int i = 1; i < totalServer; i++) {
            tmp = servers.get(i).getWeight();
            if (max < tmp) {
                max = tmp;
            }
        }
        return max;
    }

    /**
     * 求两个数的最大公约数 4和6最大公约数是2
     *
     * @param num1
     * @param num2
     * @return
     */
    private int gcd(int num1, int num2) {
        BigInteger i1 = new BigInteger(String.valueOf(num1));
        BigInteger i2 = new BigInteger(String.valueOf(num2));
        return i1.gcd(i2).intValue();
    }

    // 简单轮询
    public Server normalRound() {
        currentIndex = (currentIndex + 1) % totalServer;
        return servers.get(currentIndex);
    }

    /**
     * 遍历全部节点，从最大的权重开始取。
     * 每次权重-公约数递减。
     * 如果当前权重《=0.则从循环从最大权重开始。
     * @return
     */
    public Server weightRound() {
        while (true) {
            currentIndex = (currentIndex + 1) % totalServer;
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if(currentWeight == 0) {
                        return null;
                    }
                }
            }

            if(servers.get(currentIndex).getWeight() >= currentWeight) {
                return servers.get(currentIndex);
            }
        }
    }

    public static void main(String[] args) {
        WeightRoundRobin w = new WeightRoundRobin();
        for(int i = 0 ; i < w.totalServer * 2; i++){
            Server server = w.normalRound();
            System.out.println(server);
        }

    }
}
