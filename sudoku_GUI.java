import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class sudoku_GUI extends JFrame {
    //初始化数独数组（题目）
    int[][] arrays = new int[][]{
            {0, 0, 0, 6, 0, 2, 0, 0, 0},
            {0, 9, 2, 0, 0, 0, 3, 1, 0},
            {8, 0, 5, 0, 1, 4, 6, 9, 0},
            {1, 3, 0, 4, 0, 0, 7, 0, 0},
            {4, 0, 9, 0, 0, 8, 1, 0, 0},
            {0, 5, 0, 1, 3, 6, 4, 0, 9},
            {0, 0, 0, 0, 0, 1, 5, 8, 7},
            {2, 0, 1, 5, 0, 0, 0, 0, 0},
            {0, 8, 7, 9, 4, 3, 2, 0, 0}
    };
    //初始化答案数组
    int[][] answer = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    //构造
    public sudoku_GUI() {
        initialize();
    }

    //初始化函数
    public void initialize() {
        //Frame
        JFrame frame = new JFrame("数独游戏");
        frame.setBounds(200, 200, 1000, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //Panel
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
//        panel2.setBackground(Color.BLUE);
        panel2.setBounds(500, 0, 500, 500);
        frame.getContentPane().add(panel2);
        panel.setBounds(0, 0, 500, 500);
        frame.getContentPane().add(panel);

        //Label
        JLabel label = new JLabel(" ");
        label.setFont(new Font("楷体", Font.BOLD, 16));
        JLabel label2 = new JLabel(" ");
        label2.setFont(new Font("楷体", Font.BOLD, 16));
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(label);
        panel2.add(label2);

        //Menu
//        Container con =frame.getContentPane();
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("文件");
        JMenuItem newGame = new JMenuItem("新游戏");
        JMenuItem quit = new JMenuItem("退出");
        file.add(newGame);
        file.addSeparator();
        file.add(quit);
        menuBar.add(file);
        frame.setJMenuBar(menuBar);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main(null);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Popup Menu
        JPopupMenu jPopupMenuOne = new JPopupMenu();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem newgameFile = new JMenuItem("新游戏");
        JMenuItem newexit = new JMenuItem("退出");
        fileMenu.add(newgameFile);
        fileMenu.add(newexit);
        jPopupMenuOne.add(fileMenu);
        MouseListener popupListener = new PopupListener(jPopupMenuOne);
        panel2.addMouseListener(popupListener);

        newgameFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main(null);
            }
        });

        newexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //文本框，用来创造数独的9x9方阵
        JTextField[][] board = new JTextField[9][9];


        final boolean[] mark = {true};//输入是否合法
        //初始化表盘
        panel.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new JTextField();
                board[i][j].setFont(new Font("楷体", Font.BOLD, 16));
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                if (arrays[i][j] != 0) {
                    board[i][j].setText(Integer.toString(arrays[i][j]));
                    board[i][j].setFocusable(false);
                    board[i][j].setForeground(Color.BLUE);
                }
//                board[i][j].setForeground(Color.BLUE);
                board[i][j].setVisible(true);
                panel.add(board[i][j]);
                panel.setVisible(true);

            }
        }
        //Button
        JButton button1 = new JButton("提交结果");
        Dimension preferedSize = new Dimension(150, 50);
        button1.setPreferredSize(preferedSize);
        button1.setFont(new Font("楷体", Font.BOLD, 16));
        panel2.add(button1);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //转换出int二维矩阵


                int n = 0;//是否全部格子没输入其他字符
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (board[i][j].getText().length() != 0) {
                            if (board[i][j].getText().equals("1") || board[i][j].getText().equals("2") || board[i][j].getText().equals("3") || board[i][j].getText().equals("4") || board[i][j].getText().equals("5") || board[i][j].getText().equals("6") || board[i][j].getText().equals("7") || board[i][j].getText().equals("8") || board[i][j].getText().equals("9")) {
                                answer[i][j] = Integer.parseInt(board[i][j].getText());
//                                label2.setText(" ");
                            } else {
//                                label2.setText("输入中含有非1-9的数字或字符,\r请仔细检查");
                                n++;
//                                mark[0] = false;
                            }


                        } else
                            answer[i][j] = -1;
                    }
                }

                if (n == 0) {
                    label2.setText(" ");
                    mark[0] = true;
                } else {
                    label2.setText("输入中含有非1-9的数字或字符,\r请仔细检查");
                    label.setText(" ");
                    mark[0] = false;
                }

                if (mark[0]) {
                    if (checkall(answer)) {
                        label.setText("成功完成！");
                    } else {
                        label.setText("失败！");
                    }

                }
            }
        });


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        sudoku_GUI sudoku = new sudoku_GUI();


    }

    //检测行
    public static boolean checkRow(int[][] arrays, int row) {
        for (int j = 0; j < 8; j++) {
            if (arrays[row][j] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (arrays[row][j] == arrays[row][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    //检测列
    public static boolean checkLine(int[][] arrays, int col) {
        for (int j = 0; j < 8; j++) {
            if (arrays[j][col] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (arrays[j][col] == arrays[k][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    //检测九宫格
    public static boolean checkNine(int[][] arrays, int row, int col) {
        // 获得左上角的坐标
        int j = row / 3 * 3;
        int k = col / 3 * 3;
        for (int i = 0; i < 8; i++) {
            if (arrays[j + i / 3][k + i % 3] == 0) {
                continue;
            }
            for (int m = i + 1; m < 9; m++) {
                if (arrays[j + i / 3][k + i % 3] == arrays[j + m / 3][k + m % 3]) {
                    return false;
                }
            }
        }
        return true;
    }

    //检测所有
    private static boolean checkall(int[][] arrays) {
        int row = 0;
        int col = 0;
        int nine = 0;
        for (int i = 0; i < 9; i++) {
            if (checkRow(arrays, i)) {
                row++;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (checkLine(arrays, i)) {
                col++;
            }
        }

        int[] checkNineBoxRow = {0, 0, 0, 3, 3, 3, 6, 6, 6};
        int[] checkNineBoxLine = {0, 3, 6, 0, 3, 6, 0, 3, 6};
        for (int i = 0; i < 9; i++) {
            if (checkNine(arrays, checkNineBoxRow[i], checkNineBoxLine[i])) {
                nine++;
            }
        }
        if (row == 9 && col == 9 && nine == 9)
            return true;
        else
            return false;
    }

    //添加内部类，其扩展了MouseAdapter类，用来处理鼠标事件
    class PopupListener extends MouseAdapter {
        JPopupMenu popupMenu;

        PopupListener(JPopupMenu popupMenu) {
            this.popupMenu = popupMenu;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            showPopupMenu(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopupMenu(e);
        }

        private void showPopupMenu(MouseEvent e) {
            if (e.isPopupTrigger()) {
                //如果当前事件与鼠标事件相关，则弹出菜单
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

}

