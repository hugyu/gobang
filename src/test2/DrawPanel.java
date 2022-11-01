package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawPanel extends JPanel implements MouseListener {
    public static final int MARGIN=30; //棋盘的边距
    public static final int GRID_SPAN=35;//网格间距

    public static final int ROWS=15; //横线15条
    public static final int COLS=15; //竖线15条


    private int x_index,y_index; //x与y的索引值
    private boolean isBalck=true; // 棋子的颜色

    private Chess[] chessList=new Chess[(ROWS+1)*(COLS+1)]; //棋子的列表
    private int chessCount=0; //棋子的数量

    private boolean gameOver=false; //游戏是否结束

    // 构造方法
    public DrawPanel() {
        super();
        // 设置背景颜色
        this.setBackground(Color.pink);
        // 增加鼠标监听事件
        this.addMouseListener(this);


    }

    // 重写绘画方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 画棋盘-横线
        for(int i=0;i<=ROWS;i++) {
            g.drawLine(MARGIN,MARGIN+i*GRID_SPAN,MARGIN+COLS*GRID_SPAN,MARGIN+i*GRID_SPAN);
        }
        // 画棋盘-竖线
        for(int i =0;i<=COLS;i++) {
            g.drawLine(MARGIN+i*GRID_SPAN,MARGIN,MARGIN+i*GRID_SPAN,MARGIN+ROWS*GRID_SPAN);
        }
        // 画棋子
        for (int i=0;i<chessCount;i++) {
            int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;
            int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;
            // 设置颜色
            g.setColor(chessList[i].getColor());
            g.fillOval(xPos-Chess.DIAMETER/2,yPos-Chess.DIAMETER/2,Chess.DIAMETER,Chess.DIAMETER);
            // 最后一颗棋子上画红色框
            if(i==chessCount-1) {
                g.setColor(Color.RED);
                g.drawRect(xPos-Chess.DIAMETER/2,yPos-Chess.DIAMETER/2,Chess.DIAMETER,Chess.DIAMETER);
            }
        }
    }
    // 自动调用
    @Override
    public Dimension getPreferredSize() {
        // 获取完美的宽高
        return new Dimension(MARGIN*2+GRID_SPAN*ROWS,MARGIN*2+GRID_SPAN*COLS);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    //鼠标按下的时候
    @Override
    public void mousePressed(MouseEvent e) {
        x_index=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
        y_index=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
        // 先判断可不可以下在某个位置
        // 1.游戏结束不能下棋子
        if(gameOver) {
            return;
        }
        // 2.落在棋盘外面不能下
        if(x_index<0 || x_index>COLS ||y_index<0||y_index>ROWS) {
            return;
        }
        //3.位置有棋子了
        if(findChess(x_index,y_index)) {
            return;
        }
        // new 棋子
        Chess ch=new Chess(x_index,y_index,isBalck?Color.black:Color.white);
        // 添加棋子
        chessList[chessCount++]=ch;
        this.repaint();
        if(isWin()) {
            String msg=String.format("恭喜你，%s赢了",isBalck?"黑棋":"白棋");
            // 弹出对话框
            JOptionPane.showMessageDialog(this,msg);
            gameOver=true;
        }
        isBalck=!isBalck;
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
    // 位置上是否有棋子的方法
    private boolean findChess(int x,int y) {
        for (Chess c:chessList) {
            if(c!=null && c.getX()==x && c.getY()==y) {
                return true;
            }
        }
        return false;
    }

    // 得到棋盘上的棋子
    private  Chess getChess(int x,int y,Color color) {
        for(Chess c:chessList) {
            if(c!=null&&c.getX()==x&&c.getY()==y&&c.getColor()==color) {
                return c;
            }
        }
        return null;
    }
    //赢棋的话，需要4个方向
    private boolean isWin() {
        return search1() || search2() || search3() || search4();
    }
    // 斜向-西南-东北
    private boolean search1() {
        int continueCount=1; //连续棋子的个数
        // 斜向上寻找
        for(int x=x_index+1,y=y_index-1;x<=COLS&&y>=0;x++,y--) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        //斜向下寻找
        for(int x=x_index-1,y=y_index+1;y<=ROWS&&x>=0;x--,y++) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        // 五子连珠
        if(continueCount>=5) {
            return true;
        }
        return false;
    }

    // 斜向-西北-东南
    private boolean search2() {
        int continueCount=1; //连续棋子的个数
        // 斜向上寻找
        for(int x=x_index-1,y=y_index-1;x>=0&&y>=0;x--,y--) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        //斜向下寻找
        for(int x=x_index+1,y=y_index+1;y<=ROWS&&x<=COLS;x++,y++) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        // 五子连珠
        if(continueCount>=5) {
            return true;
        }
        return false;
    }
    // 横着
    private boolean search3() {
        int continueCount=1; //连续棋子的个数
        // 向右寻找
        for(int x=x_index+1,y=y_index;x<=COLS;x++) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        //向左寻找
        for(int x=x_index-1,y=y_index;x>=0;x--) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        // 五子连珠
        if(continueCount>=5) {
            return true;
        }
        return false;
    }
    //竖着
    private boolean search4() {
        int continueCount=1; //连续棋子的个数
        // 向上寻找
        for(int x=x_index,y=y_index-1;y>=0;y--) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        //向下寻找
        for(int x=x_index,y=y_index+1;y<=ROWS;y++) {
            Color c=isBalck?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            } else {
                break;
            }
        }
        // 五子连珠
        if(continueCount>=5) {
            return true;
        }
        return false;
    }

    // 重新开始游戏的方法
    public void restartGame() {
        //清除棋子
        for(int i=0;i<chessList.length;i++) {
            chessList[i]=null;
        }
        //恢复游戏相关的变量
        isBalck=true;
        gameOver=false;
        chessCount=0;
        //重画棋盘
        this.repaint();
    }
    // 悔棋的方法
    public void goback() {
        // 棋盘里面没有棋
        if(chessCount==0) {
            return;
        }
        //从列表中置空
        chessList[chessCount-1]=null;
        //总数减一
        chessCount--;
        if(chessCount>0) {
            x_index=chessList[chessCount-1].getX();
            y_index=chessList[chessCount-1].getY();
        }
        //颜色变回去
        isBalck=!isBalck;
        this.repaint();
    }


}
