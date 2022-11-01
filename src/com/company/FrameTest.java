package com.company;

import javax.swing.*;

public class FrameTest {
    public static void main(String[] args) {
        JFrame myFrame=new JFrame();
        // 设置JFrame大小
        myFrame.setBounds(200,200,300,400);
        // 把内容放进画板
        myFrame.add(new DrawText());
        // 点击X的时候关闭
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置为可见
        myFrame.setVisible(true);
    }
}
