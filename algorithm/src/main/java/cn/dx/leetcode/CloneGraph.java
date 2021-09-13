package cn.dx.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表
 * https://leetcode-cn.com/problems/clone-graph/
 * @author gudongxian
 * @version 0.1
 * @date 2020/8/12
 */
public class CloneGraph {

    /**
     * 保存当前已经clone的Node
     */
    private Map<Integer,Node> valSet = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }
        // clone 当前节点
        Node copyNode = new Node(node.val);
        valSet.put(copyNode.val,copyNode);
        // clone 它的邻居节点
        if (!node.neighbors.isEmpty()){
            for (Node neighbor : node.neighbors) {
                // 防止重复不停的复制节点，先判断哈希表中是否存在，存在则直接加入邻居节点中
                if (valSet.containsKey(neighbor.val)){
                    copyNode.neighbors.add(valSet.get(neighbor.val));
                    continue;
                }
                // 递归复制节点
                copyNode.neighbors.add(cloneGraph(neighbor));
            }
        }
        return copyNode;
    }
}
