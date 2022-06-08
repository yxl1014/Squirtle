package yxl.day1.demo1;

public class FindWay2 {

    private int[][] map;
    private int h;
    private int w;
    private int[][] lastPath;
    private int[][] result;
    private int[] start;
    private int[] end;

    public FindWay2(int[][] map) {
        this.map = map;
        this.h = this.map.length;
        this.w = this.map[0].length;

        //初始化最短距离为数组全部元素的数量
        this.result = new int[h][w];
        this.lastPath = new int[h][w];
        this.lastPath[0][0] = h * w;
        this.start = Local(5);
        this.end = Local(8);
    }

    //复制当前的最短路径
    private void refreshPath(int[][] map, int[][] lastPath, int nowDepth) {
        //先清空原有路径
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                lastPath[i][j] = 0;
            }
        }

        //记录新的最短距离
        lastPath[0][0] = nowDepth;

        //再复制新的最短路径
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    lastPath[i][j] = map[i][j];
                }
            }
        }
    }

    //判断map[i][j]是否在数组内，防止数组访问越界
    public boolean inArr(int[][] map, int i, int j) {
        return (i >= 0 && i < map.length && j >= 0 && j < map[i].length); //map[i]用到了逻辑与的短路效应，只有i未越界，后面的判断才会继续
    }

    //寻找从当前位置map[i][j]到达终点的最短路径和最短距离
    //nowDepth表示递归深度（路的长度）
    public void findMinWay(int[][] map, int i, int j, int[][] lastPath, int nowDepth) {

        //比较nowDepth和lastPath[0][0]的大小，如果目前走的距离已经比最短路径长，则直接返回。剪枝，剪掉了当前路径这种选择
        //但是，不将当前位置置为2，而是不做处理
        if (nowDepth >= lastPath[0][0]) {
            return;
        } else {//继续尝试
            if (/*map[end[0]][end[1]] == 8 && */i == end[0] && j == end[1]) {//已经找到出口，并且这条路比之前的最短路径都短，则记录路径，并更新最短距离
                lastPath[0][0] = nowDepth;
                map[end[0]][end[1]] = 2;
                refreshPath(map, lastPath, nowDepth);
                map[end[0]][end[1]] = 0;//先令终点为2，记录路径。然后置0，表示未走可走，这里也是回退一步的意思。
                return;
            } else {//还在找路
                if (map[i][j] == 0 || map[i][j] == 5) {//当前路不是障碍物，可以尝试走
                    //先假定当前位置可以走通
                    map[i][j] = 2;
                    //再寻找下一个位置，递归到目标
                    if (inArr(map, i + 1, j))//进行边界判断
                        findMinWay(map, i + 1, j, lastPath, nowDepth + 1);
                    if (inArr(map, i, j + 1))
                        findMinWay(map, i, j + 1, lastPath, nowDepth + 1);
                    if (inArr(map, i - 1, j))
                        findMinWay(map, i - 1, j, lastPath, nowDepth + 1);
                    if (inArr(map, i, j - 1))
                        findMinWay(map, i, j - 1, lastPath, nowDepth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    //得到str的坐标
    private int[] Local(int s) {
        int[] local = new int[2];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == s) {
                    local[0] = i;
                    local[1] = j;
                    return local;
                }
            }
        }
        return local;
    }

    public void start() {
        findMinWay(map, start[0], start[1], lastPath, 0);
        lastPath[start[0]][start[1]] = 5;
        lastPath[end[0]][end[1]] = 8;
    }

    public void printMaze() {
        System.out.println("打印迷宫如下============");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMinPath() {
        System.out.println("最短路径如下============");
        for (int i = 0; i < lastPath.length; i++) {
            for (int j = 0; j < lastPath[i].length; j++) {
                System.out.print(lastPath[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("最短距离为" + lastPath[0][0]);
    }

    public void printResult() {
        for (int i = 0; i < lastPath.length; i++) {
            for (int j = 0; j < lastPath[i].length; j++) {
                if (map[i][j] != 0)
                    result[i][j] = map[i][j];
                if (lastPath[i][j] != 0)
                    result[i][j] = lastPath[i][j];
            }
        }
        System.out.println("-------result-------");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FindWay2 t1 = new FindWay2(new int[][]
                {{5, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 8},
                        {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}});

        //打印走前迷宫
        t1.printMaze();

        t1.start();

        //打印最短路径
        t1.printMinPath();

        t1.printResult();
    }
}




