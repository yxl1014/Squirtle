package yxl.day1.demo_V2;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomNum<T> {
    private final List<T> datas = new ArrayList<>();
    private double[] weights;

    public RandomNum(List<WeightNode<T>> nodes) {
        Collections.sort(nodes);
        this.calWeights(nodes);
    }

    /**
     * 计算权重，初始化或者重新定义权重时使用
     */
    public void calWeights(List<WeightNode<T>> itemsWithWeight) {
        datas.clear();

        // 计算权重总和
        double weightSum = 0;
        for (WeightNode<T> node : itemsWithWeight) {
            double weight = node.getWeight();
            if (weight <= 0) {
                continue;
            }

            datas.add(node.getData());
            if (Double.isInfinite(weight)) {//若权重无限大
                weight = 10000.0D;
            }
            if (Double.isNaN(weight)) {//若权重无穷小
                weight = 1.0D;
            }
            weightSum += weight;
        }

        // 计算每个节点的实际权重比例
        double[] nodeWeight = new double[datas.size()];
        int index = 0;
        for (WeightNode<T> node : itemsWithWeight) {
            double weight = node.getWeight();
            if (weight <= 0) {
                continue;
            }
            nodeWeight[index++] = weight / weightSum;
        }

        // 计算每个节点的权重范围
        // 权重范围起始位置
        weights = new double[datas.size()];
        double weightRangeStartPoint = 0;
        for (int i = 0; i < index; i++) {
            weights[i] = weightRangeStartPoint + nodeWeight[i];
            weightRangeStartPoint += nodeWeight[i];
        }
    }

    /**
     * 基于权重随机算法选择
     */
    public T choose() {
        double random = ThreadLocalRandom.current().nextDouble();
        int index = Arrays.binarySearch(weights, random);//二分查找
        if (index < 0) {
            index = -index - 1;
        } else {
            return datas.get(index);
        }

        if (index < weights.length && random < weights[index]) {
            return datas.get(index);
        }

        // default
        return datas.get(0);
    }

    public static void main(String[] args) {



        Map<String, AtomicInteger> resultmap = new HashMap<>();

        // test1
        int sampleCount = 10000;
        System.out.println("========test1========");
        WeightNode<String> v1 = new WeightNode<>("普通攻击", 0.7);
        WeightNode<String> v2 = new WeightNode<>("魔法-毒", 0.3 * 0.25);
        WeightNode<String> v3 = new WeightNode<>("魔法-火", 0.3 * 0.25);
        WeightNode<String> v4 = new WeightNode<>("魔法-冰", 0.3 * 0.25);
        WeightNode<String> v5 = new WeightNode<>("魔法-雷", 0.3 * 0.25);

        RandomNum<String> weightRandom = new RandomNum<>(Arrays.asList(v1, v2, v3, v4, v5));

        for (int i = 0; i < sampleCount; i++) {
            resultmap
                    .computeIfAbsent(weightRandom.choose(), (k) -> new AtomicInteger())
                    .incrementAndGet();
        }

        resultmap.forEach((k, v) -> {
            double hit = (double) v.get() / sampleCount;
            System.out.println(k + ", hit:" + hit);
        });

        System.out.println("========test2========");
        WeightNode<String> q1 = new WeightNode<>("普通-攻击", 0.7 * 0.7);
        WeightNode<String> q6 = new WeightNode<>("普通-连击", 0.7 * 0.3);
        WeightNode<String> q2 = new WeightNode<>("魔法-毒", 0.3 * 0.25);
        WeightNode<String> q3 = new WeightNode<>("魔法-火", 0.3 * 0.25);
        WeightNode<String> q4 = new WeightNode<>("魔法-冰", 0.3 * 0.25);
        WeightNode<String> q5 = new WeightNode<>("魔法-雷", 0.3 * 0.25);


        weightRandom = new RandomNum<>(Arrays.asList(q1,q2,q3,q4,q5,q6));

        resultmap = new HashMap<>();

        for (int i = 0; i < sampleCount; i++) {
            resultmap
                    .computeIfAbsent(weightRandom.choose(), (k) -> new AtomicInteger())
                    .incrementAndGet();
        }

        resultmap.forEach((k, v) -> {
            double hit = (double) v.get() / sampleCount;
            System.out.println(k + ", hit:" + hit);
        });


        System.out.println("========test3========");
        WeightNode<String> z1 = new WeightNode<>("普通-攻击-普攻", 0.7 * 0.7*0.6);
        WeightNode<String> z2 = new WeightNode<>("普通-攻击-分裂", 0.7 * 0.7*0.4);
        WeightNode<String> z7 = new WeightNode<>("普通-连击", 0.7 * 0.3);
        WeightNode<String> z3 = new WeightNode<>("魔法-毒", 0.3 * 0.25);
        WeightNode<String> z4 = new WeightNode<>("魔法-火", 0.3 * 0.25);
        WeightNode<String> z5 = new WeightNode<>("魔法-冰", 0.3 * 0.25);
        WeightNode<String> z6 = new WeightNode<>("魔法-雷", 0.3 * 0.25);


        weightRandom = new RandomNum<>(Arrays.asList(z1,z2,z3,z4,z5,z6,z7));

        resultmap = new HashMap<>();

        for (int i = 0; i < sampleCount; i++) {
            resultmap
                    .computeIfAbsent(weightRandom.choose(), (k) -> new AtomicInteger())
                    .incrementAndGet();
        }

        resultmap.forEach((k, v) -> {
            double hit = (double) v.get() / sampleCount;
            System.out.println(k + ", hit:" + hit);
        });
    }
}
