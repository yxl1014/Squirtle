package yxl.day1.demo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class WRandom<K, V extends Number> {
    private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();

    public WRandom(List<Pair<K, V>> list) {
        for (Pair<K, V> pair : list) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();// 统一转为double
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());// 权重累加
        }
    }

    public K random() {
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    public static void main(String[] args) {

        List<Pair<?, ?>> list = new ArrayList<Pair<?, ?>>();
        list.add(new Pair<String, Double>("普通攻击", 0.7 * 0.7 * 0.6));
        list.add(new Pair<String, Double>("分裂攻击", 0.7 * 0.7 * 0.4));
        list.add(new Pair<String, Double>("连击", 0.7 * 0.3));
        list.add(new Pair<String, Double>("魔法攻击毒", 0.3 * 0.20));
        list.add(new Pair<String, Double>("魔法攻击雷", 0.3 * 0.20));
        list.add(new Pair<String, Double>("魔法攻击冰", 0.3 * 0.20));
        list.add(new Pair<String, Double>("魔法攻击火", 0.3 * 0.20));
        list.add(new Pair<String, Double>("魔法攻击风", 0.3 * 0.20));
        int a, c, d, mp, mm, mi, mf, mw;
        a = c = d = mp = mm = mi = mf = mw = 0;
        WRandom weight = new WRandom(list);
        for (int i = 0; i < 10000; i++) {
            String w = (String) weight.random();
            switch (w) {
                case "普通攻击":
                    a++;
                    break;
                case "分裂攻击":
                    c++;
                    break;
                case "连击":
                    d++;
                    break;
                case "魔法攻击毒":
                    mp++;
                    break;
                case "魔法攻击雷":
                    mm++;
                    break;
                case "魔法攻击冰":
                    mi++;
                    break;
                case "魔法攻击火":
                    mf++;
                    break;
                case "魔法攻击风":
                    mw++;
                    break;
            }
        }
        System.out.println("普通攻击" + a);
        System.out.println("分裂攻击" + c);
        System.out.println("连击" + d);
        System.out.println("魔法攻击毒" + mp);
        System.out.println("魔法攻击雷" + mm);
        System.out.println("魔法攻击冰" + mi);
        System.out.println("魔法攻击火" + mf);
        System.out.println("魔法攻击风" + mw);
    }
}
