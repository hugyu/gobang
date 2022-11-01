package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiveFrame extends JFrame {
//    private static final int WIDTH=300;
//    private static final int HEIGHT=400;

    private JPanel toolBar; //工具栏有三个按钮
    private JButton startButton,backButton,exitButton; //对应菜单栏的三个按钮
    private DrawPanel drawPanel; //棋盘的面板

    private JMenuBar menuBar;//菜单栏
    private JMenu sysMenu; //菜单
    private JMenuItem startMenuItem,backMenuItem,exitMenuItem; //开始，悔棋，退出
    // 定义监听的对象
    private MyListener listener;
    // 构造方法
    public FiveFrame() {}

    // 初始化方法
    public void init() {

        listener=new MyListener();
        // 设置标题
        this.setTitle("单机版五子棋游戏");
        // new对象
        toolBar=new JPanel();
        startButton=new JButton("开始");
        startButton.addActionListener(listener);
        backButton=new JButton("悔棋");
        backButton.addActionListener(listener);
        exitButton=new JButton("退出");
        exitButton.addActionListener(listener);

        drawPanel=new DrawPanel();
        menuBar=new JMenuBar();
        sysMenu=new JMenu("系统");
        startMenuItem=new JMenuItem("开始");
        startMenuItem.addActionListener(listener);
        backMenuItem=new JMenuItem("悔棋");
        backMenuItem.addActionListener(listener);
        exitMenuItem=new JMenuItem("退出");
        exitMenuItem.addActionListener(listener);

        this.setJMenuBar(menuBar); //设置窗口的菜单栏
        menuBar.add(sysMenu); //加系统的菜单
        // 加系统菜单的菜单项
        sysMenu.add(startMenuItem);
        sysMenu.add(backMenuItem);
        sysMenu.add(exitMenuItem);

        //工具栏中加按钮
        toolBar.add(startButton);
        toolBar.add(backButton);
        toolBar.add(exitButton);

        // 设置布局方式
        this.setLayout(new BorderLayout());
        // 添加并设置位置
        this.add(toolBar,BorderLayout.NORTH);
        this.add(drawPanel,BorderLayout.CENTER);

        // 设置窗口关闭的方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口不能改变大小
        this.setResizable(false);

        // 设置窗口的大小
//        this.setSize(WIDTH,HEIGHT);
        // 设置窗口的位置居中
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2-200,(Toolkit.getDefaultToolkit().getScreenSize().height)/2-350);
        pack(); //里面的东西撑开
        //设置是否可见
        this.setVisible(true);

    }

    private class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // 重新开始
            if(e.getSource()==startButton||e.getSource()==startMenuItem) {
                drawPanel.restartGame();
            }
            // 悔棋
            if(e.getSource()==backButton||e.getSource()==backMenuItem) {
                drawPanel.goback();
            }
            // 退出
            if(e.getSource()==exitButton||e.getSource()==exitMenuItem) {
                System.exit(0);
            }
        }
    }

}
