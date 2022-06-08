package yxl.day1.demo_V2;

public class WeightNode<T> implements Comparable<WeightNode<T>> {
    T data;
    double weight;

    public WeightNode() {
    }

    public WeightNode(T data, double weight) {
        this.data = data;
        this.weight = weight;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightNode<T> o) {
        return Double.compare(this.weight, o.weight);
    }
}
