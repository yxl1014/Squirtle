package yxl.day2.demo3;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyByteBuff {
    private int capacity = 1 << 2;
    private byte[] datas = new byte[capacity];
    private AtomicInteger index = new AtomicInteger(0);
    private int insertindex = 0;
    private Map<Pair<Integer, String>, Pair<Integer, Integer>> typeIndex = new HashMap<>();// 第几个数据，类型，数据起始位置，长度
    private int getIndex = 0;

    public MyByteBuff addInt(int data) {
        System.out.println("int");

        int before = this.index.get();
        System.out.println("添加前index:" + before);

        byte[] b = getByteFromInt(data);
        add(b);

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "Int"), new Pair<>(before, after - before));

        return this;
    }

    public MyByteBuff addChar(char data) {
        System.out.println("char");

        int before = this.index.get();
        System.out.println("添加前index:" + before);

        byte b = (byte) data;
        add(new byte[]{b});

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "Char"), new Pair<>(before, after - before));

        return this;
    }

    public MyByteBuff addDouble(double data) {
        System.out.println("double");

        int before = this.index.get();
        System.out.println("添加前index:" + before);


        String t = String.valueOf(data);
        char[] c = t.toCharArray();
        byte[] b = charBuffToByteBuff(c);
        add(b);

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "Double"), new Pair<>(before, after - before));

        return this;
    }

    public MyByteBuff addByte(byte data) {
        System.out.println("Byte");

        int before = this.index.get();
        System.out.println("添加前index:" + before);

        byte[] b = {data};
        add(b);

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "Byte"), new Pair<>(before, after - before));
        return this;
    }

    public MyByteBuff addBoolean(boolean data) {
        System.out.println("Boolean");

        int before = this.index.get();
        System.out.println("添加前index:" + before);

        String t = String.valueOf(data);
        char[] c = t.toCharArray();
        byte[] b = charBuffToByteBuff(c);
        add(b);

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "Boolean"), new Pair<>(before, after - before));
        return this;
    }

    public MyByteBuff addString(String data) {
        System.out.println("String");
        int before = this.index.get();
        System.out.println("添加前index:" + before);

        char[] c = data.toCharArray();
        byte[] b = charBuffToByteBuff(c);
        add(b);

        int after = this.index.get();
        System.out.println("添加后index:" + this.index.get());

        this.typeIndex.put(new Pair<>(this.insertindex++, "String"), new Pair<>(before, after - before));

        return this;
    }

    public void reset() {
        this.getIndex = 0;
    }

    public int getInt() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return -1;
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "Int");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为int，请重试");
            return -1;
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());

        this.getIndex++;
        return toInt(b);
    }

    public char getChar() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return '~';
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "Char");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为char，请重试");
            return '~';
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());

        this.getIndex++;
        return (char) b[0];
    }

    public double getDouble() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return -1L;
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "Double");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为double，请重试");
            return -1L;
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());
        char[] c = ByteBuffTocharBuff(b);

        this.getIndex++;
        return Double.parseDouble(new String(c));
    }

    public byte getByte() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return -1;
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "Byte");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为byte，请重试");
            return -1;
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());

        this.getIndex++;
        return b[0];
    }

    public Boolean getBoolean() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return null;
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "Boolean");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为Boolean，请重试");
            return null;
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());
        char[] c = ByteBuffTocharBuff(b);

        this.getIndex++;
        return Boolean.getBoolean(new String(c));
    }


    public String getString() {
        if (this.getIndex >= this.insertindex) {
            System.out.println("数据已经查完了");
            return null;
        }
        Pair<Integer, String> k = new Pair<>(this.getIndex, "String");
        if (!this.typeIndex.containsKey(k)) {
            System.out.println("该数据类型不为String，请重试");
            return null;
        }

        Pair<Integer, Integer> v = this.typeIndex.get(k);
        byte[] b = getBytes(v.getKey(), v.getValue());
        char[] c = ByteBuffTocharBuff(b);

        this.getIndex++;
        return new String(c);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.insertindex;
    }

    private void add(byte[] b) {
        int len = b.length;

        while (index.get() + len >= this.capacity) {
            resize();
        }

        int ff = len + index.get();
        for (int i = 0; i < len; i++) {
            this.datas[index.get() + i] = b[i];
        }
        this.index.set(ff);
    }

    private byte[] getByteFromInt(int data) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (data >> 8 * i & 0xFF);
        }
        return b;
    }

    private int toInt(byte[] bytes) {
        int r = 0;
        byte b;
        for (int i = 0; i < bytes.length; i++) {
            b = bytes[i];
            r += (b & 0xFF) << (8 * i);
        }
        return r;
    }

    private void resize() {
        //扩容
        System.out.println("数组空间不足，扩容");
        byte[] olddatas = this.datas;
        System.out.println("扩容前的数组大小为:" + this.capacity);
        this.capacity = this.capacity << 1;
        System.out.println("扩容后的数组大小为:" + this.capacity);

        this.datas = new byte[this.capacity];
        /*for (int i = 0; i < olddatas.length; i++) {
            this.datas[i] = olddatas[i];
        }*/
        System.arraycopy(olddatas, 0, this.datas, 0, olddatas.length);
    }

    private byte[] charBuffToByteBuff(char[] c) {
        byte[] b = new byte[c.length];
        for (int i = 0; i < c.length; i++) {
            b[i] = (byte) c[i];
        }
        return b;
    }

    private char[] ByteBuffTocharBuff(byte[] b) {
        char[] c = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            c[i] = (char) b[i];
        }
        return c;
    }

    private byte[] getBytes(int i, int len) {
        byte[] result = new byte[len];
        System.arraycopy(this.datas, i, result, 0, len);
        return result;
    }
}
