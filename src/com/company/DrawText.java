package com.company;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawText extends JPanel implements MouseListener {
    int x=0; //鼠标的x坐标
    int y=0; //鼠标的y坐标
    public DrawText() {
        this.setBackground(Color.green);

        // 监听鼠标操作
        this.addMouseListener(this);
    }
    // 画的操作
    @Override
    public void paint(Graphics g) {
        // 调用父类的画笔
        super.paint(g);
        // 设置为红色
        g.setColor(Color.RED);
        g.drawLine(100,100,300,300);

        g.fillOval(x,y,30,30);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    // 鼠标按下的时候的操作
    @Override
    public void mousePressed(MouseEvent e) {
        // 画圆
        // 赋值给坐标x,y
        x=e.getX();
        y=e.getY();
        // 调用画的操作
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}