package test2;

import java.awt.*;

/**
 * 五子棋-棋子类
 * */
public class Chess {
    private int x; //棋盘中x的索引值
    private int y; //棋盘中y的索引值
    private Color color; //棋子的颜色

    public static final int DIAMETER=30;//棋子的直径

    //构造方法
    public Chess(int x,int y,Color color) {
        super();
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
