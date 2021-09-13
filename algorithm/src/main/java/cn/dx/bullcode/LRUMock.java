package cn.dx.bullcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 模拟LRU
 * 题目描述
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 *
 *
 * @author gudongxian
 * @version 0.1
 * @date 2020/9/4
 */
public class LRUMock {
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        List<Integer> res = new ArrayList<>();
        LinkedHashMap<Integer,Integer> lru = new LinkedHashMap<Integer,Integer>(k,1,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // 重写移除方法
                return size() > k;
            }
        };
        for (int[] operator : operators) {
            int op = operator[0];
            if (op==1){
                lru.put(operator[1],operator[2]);
            }else if (op==2){
                res.add(lru.getOrDefault(operator[1],-1));
            }
        }
        return res.stream().mapToInt(i->i).toArray();
    }
}
