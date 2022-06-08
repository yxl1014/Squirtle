package yxl.day1.demo;

import java.util.Random;

public class Test {

    private final Random random = new Random();

    private final int[] a = new int[100];
    private final int[] magic = new int[100];
    private final int[] b = new int[100];
    private final int[] c = new int[100];

    public Test() {
        initMapA();
        initMapB();
        initMapC();
        initMagic();
    }

    private void initMagic() {
        int zero = 0;
        while (zero < 25) {
            int index = random.nextInt(1000) / 10;
            if (magic[index] != 21)
                magic[index] = 21;
            else
                continue;
            zero++;
        }

        zero = 0;
        while (zero < 25) {
            int index = random.nextInt(1000) / 10;
            if (magic[index] != 21 && magic[index] != 22)
                magic[index] = 22;
            else
                continue;
            zero++;
        }

        zero = 0;
        while (zero < 25) {
            int index = random.nextInt(1000) / 10;
            if (magic[index] != 21 && magic[index] != 22 && magic[index] != 23)
                magic[index] = 23;
            else
                continue;
            zero++;
        }

        for (int i = 0; i < 100; i++) {
            if (magic[i] != 21 && magic[i] != 22 && magic[i] != 23)
                magic[i] = 24;
        }
    }

    private void initMapA() {
        int zero = 0;
        while (zero < 30) {
            int index = random.nextInt(1000) / 10;
            if (a[index] != 10)
                a[index] = 10;
            else
                continue;
            zero++;
        }

        for (int i = 0; i < 100; i++) {
            if (a[i] != 10)
                a[i] = 11;
        }
    }

    private void initMapB() {
        int zero = 0;
        while (zero < 30) {
            int index = random.nextInt(1000) / 10;
            if (b[index] != 10)
                b[index] = 10;
            else
                continue;
            zero++;
        }

        for (int i = 0; i < 100; i++) {
            if (b[i] != 10)
                b[i] = 11;
        }
    }

    private void initMapC() {
        int zero = 0;
        while (zero < 40) {
            int index = random.nextInt(1000) / 10;
            if (c[index] != 10)
                c[index] = 10;
            else
                continue;
            zero++;
        }

        for (int i = 0; i < 100; i++) {
            if (c[i] != 10)
                c[i] = 11;
        }
    }

    public int fight1() {
        int index = random.nextInt(100);
        int type = magic(index);
        if (type == 1)
            return 0;

        index = index + random.nextInt(1000);
        switch (magic[index % 100]) {
            case 21:
                return 1;
            case 22:
                return 2;
            case 23:
                return 3;
            case 24:
                return 4;
        }
        return -1;
    }

    public int fight2() {
        int index = random.nextInt(100);
        int type = magic(index);
        if (type == 1) {
            index = random.nextInt(1000);
            if (b[index % 100] == 10)
                return -1;
            else
                return 0;
        }

        index = index + random.nextInt(1000);
        switch (magic[index % 100]) {
            case 21:
                return 1;
            case 22:
                return 2;
            case 23:
                return 3;
            case 24:
                return 4;
        }
        return -404;
    }

    public int fight3() {
        int index = random.nextInt(100);
        int type = magic(index);
        if (type == 1) {
            index = random.nextInt(1000);
            if (b[index % 100] == 10)
                return -1;
            else {
                index = random.nextInt(1000);
                if (c[index % 100] == 10)
                    return -2;
                else
                    return 0;
            }
        }

        index = index + random.nextInt(1000);
        switch (magic[index % 100]) {
            case 21:
                return 1;
            case 22:
                return 2;
            case 23:
                return 3;
            case 24:
                return 4;
        }
        return -1;
    }

    public int magic(int index) {
        if (a[index] == 10) return 0;
        else return 1;
    }

    public void display() {
        int rn = 0;
        for (int i = 0; i < 100; i++) {
            System.out.print(a[i] + " ");
            if (++rn % 10 == 0)
                System.out.println();
        }
    }

    public void displayMagic() {
        System.out.println("==========magic==========");
        int rn = 0;
        for (int i = 0; i < 100; i++) {
            System.out.print(magic[i] + " ");
            if (++rn % 10 == 0)
                System.out.println();
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        int var0 = 0, var1 = 0, var2 = 0, var3 = 0, var4 = 0;
        for (int i = 0; i < 10000; i++) {
            switch (test.fight1()) {
                case 0:
                    var0++;
                    break;
                case 1:
                    var1++;
                    break;
                case 2:
                    var2++;
                    break;
                case 3:
                    var3++;
                    break;
                case 4:
                    var4++;
            }
        }
        System.out.println("-----------fight1------------");
        System.out.println("共攻击：" + (var0 + var1 + var2 + var3 + var4) + "次，" + "物理攻击：" + var0 + "次，" + "魔法攻击：" + (var1 + var2 + var3 + var4) + "次。");
        System.out.println("毒：" + var1 + "次，" + "火：" + var2 + "次，" + "冰：" + var3 + "次，" + "雷：" + var4 + "次。");

        int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0;
        for (int i = 0; i < 10000; i++) {
            switch (test.fight2()) {
                case -1:
                    t2++;
                    break;
                case 0:
                    t1++;
                    break;
                case 1:
                    t3++;
                    break;
                case 2:
                    t4++;
                    break;
                case 3:
                    t5++;
                    break;
                case 4:
                    t6++;
            }
        }
        System.out.println("-----------fight3------------");
        System.out.println("共攻击：" + (t1 + t2 + t3 + t4 + t5 + t6) + "次，" + "物理攻击：" + (t1 + t2) + "次，" + "魔法攻击：" + (t3 + t4 + t5 + t6) + "次。");
        System.out.println("攻击：" + t1 + "次，" + "连击：" + t2 + "次。");
        System.out.println("毒：" + t3 + "次，" + "火：" + t4 + "次，" + "冰：" + t5 + "次，" + "雷：" + t6 + "次。");


        int q = 0, w = 0, e = 0, r = 0, t = 0, y = 0, u = 0;
        for (int i = 0; i < 10000; i++) {
            switch (test.fight3()) {
                case -2:
                    q++;
                    break;
                case -1:
                    w++;
                    break;
                case 0:
                    e++;
                    break;
                case 1:
                    r++;
                    break;
                case 2:
                    t++;
                    break;
                case 3:
                    y++;
                    break;
                case 4:
                    u++;
            }
        }
        System.out.println("-----------fight3------------");
        System.out.println("共攻击：" + (q + w + e + r + t + y + u) + "次，" + "物理攻击：" + (q + w + e) + "次，" + "魔法攻击：" + (r + t + y + u) + "次。");
        System.out.println("攻击：" + (e+q) + "次，" + "连击：" + w + "次。");
        System.out.println("普攻：" + e + "次，" + "分裂：" + q + "次。");
        System.out.println("毒：" + r + "次，" + "火：" + t + "次，" + "冰：" + y + "次，" + "雷：" + u + "次。");
    }
}
