package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<JigsawNode> q = new LinkedList<>();
        visited.add(bNode.hashCode());
        q.offer(bNode);

        while(!q.isEmpty()) {
            currentJNode = q.poll();
            int[] canMove = currentJNode.canMove();
            JigsawNode tmp = new JigsawNode(currentJNode);
            for(int i = 0; i < 4; i++) {
                currentJNode = new JigsawNode(tmp);
                if(canMove[i] == 1) {
                    currentJNode.move(i);
                    int hash = currentJNode.hashCode();
                    if(visited.contains(hash)) continue;
                    else visited.add(hash);
                    if(currentJNode.equals(eNode)) {
                        getPath();
                        System.out.println(getSolutionPath());
                        return true;
                    }
                    q.offer(currentJNode);
                }
            }
        }
        return false;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int factor1 = 1;
        int factor2 = 1;
        int factor3 = 2;
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }

        jNode.setEstimatedValue(factor1 * misMatch(jNode) + factor2 * s + factor3 * getDistance(jNode));

    }

    private int getDistance(JigsawNode jNode) {
        int distance = 0;
        int dimension = JigsawNode.getDimension();
        int[] NodeState = jNode.getNodesState();
        int[] finishedNodeState = endJNode.getNodesState();
        for (int i = 1; i <= dimension * dimension; i++) {
            if (NodeState[i] != 0 && NodeState[i] != finishedNodeState[i]) {
                int current_row = (int) (i - 1) / dimension;
                int current_col = (int) (i + 4) % dimension;
                for (int j = 0; j <= dimension * dimension; j++) {
                    if (NodeState[i] == finishedNodeState[j]) {
                        int target_row = (int) (j - 1) / dimension;
                        int target_col = (int) (j + 4) % dimension;
                        distance += (Math.abs(current_row - target_row) + Math
                                .abs(current_col - target_col));
                        break;
                    }
                }
            }
        }
        return distance;
    }

    private int misMatch(JigsawNode jNode) {
        int misMatch = 0;
        int dimension = JigsawNode.getDimension();
        int[] currentJNodeState = jNode.getNodesState();
        int[] targetJNodeState = endJNode.getNodesState();
        for (int i = 1; i <= dimension * dimension; i++) {
            if (currentJNodeState[i] != targetJNodeState[i]) {
                misMatch++;
            }
        }
        return misMatch;
    }
}
