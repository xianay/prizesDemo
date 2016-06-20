/*
 * GobangJFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.test.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author __USER__
 */
public class ServerJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private final static int UP = 1 << 0;
	private final static int DOWN = 1 << 1;
	private final static int LEFT = 1 << 2;
	private final static int RIGHT = 1 << 3;
	private final static int LEFT_UP = 1 << 4;
	private final static int RIGHT_DOWN = 1 << 5;
	private final static int RIGHT_UP = 1 << 6;
	private final static int LEFT_DOWN = 1 << 7;
	
	private int[] pos = null;  //鼠标点击 放下一个棋子的坐标
	
	private enum Chess // 棋子
	{
		NULL, // 空
		WHITE, // 白
		BLACK// 黑
	}

	final static int FIVE = 5;
	int count_white = 1;
	int count_black = 1;
	int ranks = 15;
	Chess[][] chesses;

	private JTextField jTextField_ranks;
	private JLabel jLabel_ranks;

	private JPanel jPanel_chat;
	private JTextArea ta_chat;
	private JScrollPane sp_ta_chat;

	private JPanel p_tf_chat;
	private JButton b_tf_chat;
	private JTextField tf_chat;

	private InputStream inChat = null;
	private OutputStream outChat = null;

	private InputStream inPlay = null;
	private OutputStream outPlay = null;
	
	private boolean isPort1Ready;  //1103端口是否连接
	private boolean isPort2Ready;  //1104端口是否连接
	
	private Thread playT = new Thread(){
		public void run() {
			connected_play();
			receive();
		};
	};
	
	private void connected_play()
	{
		while (inPlay == null || outPlay == null) {
			try {
				ServerSocket ss = new ServerSocket(1104);
				Socket s = ss.accept();
				while (inPlay == null || outPlay == null) {
					inPlay = s.getInputStream();
					outPlay = s.getOutputStream();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
				System.out.println(s.getInetAddress().getHostAddress()
						+ " [1104端口] 连接到本机服务器。");
				isPort2Ready = true;
			} catch (IOException e) {
			}
		}
	}
	
	/***
	 * 接受信息
	 */
	private void receive()
	{
		while (true) {
			DataInputStream dis = new DataInputStream(inPlay);
			try {
				if (dis.available() > 0) {
					int row = dis.readInt(); //接收客户端 当前棋子的 x[棋盘坐标]
					int col = dis.readInt(); //接收客户端 当前棋子的 y[棋盘坐标]
					canPlace(row, col, Chess.WHITE);  //放置一颗白色棋子
				}
				Thread.sleep(100);
			} catch (IOException e) {
			} catch (InterruptedException e) {
			}
		}
	}
	
	/**
	 * 发送信息
	 */
	private void send()
	{
		DataOutputStream dos = new DataOutputStream(outPlay);
		try {
			dos.writeInt(pos[0]);  //发送当前放下棋子的 x[棋盘坐标]
			dos.writeInt(pos[1]);  //发送当前放下棋子的 y[棋盘坐标]
			dos.flush();
		} catch (IOException e) {
		}
	}
	
	/***
	 * 聊天线程
	 */
	private Thread chatT = new Thread() {
		public void run() {
			connected_chat();
			receiveMessage();
		};
	};

	private void connected_chat() {
		try {
			ServerSocket ss = new ServerSocket(1103);
			Socket s = ss.accept();
			while (inChat == null || outChat == null) {
				inChat = s.getInputStream();
				outChat = s.getOutputStream();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			System.out.println(s.getInetAddress().getHostAddress()
					+ " [1103端口] 连接到本机服务器。");
			isPort1Ready = true;
		} catch (IOException e) {
		}
	}

	/**
	 * 接受聊天消息
	 */
	private void receiveMessage() {
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(inChat));
			try {
				if (br.ready()) {
					String ip = InetAddress.getLocalHost().getHostAddress() + '：';
					String str = ip + br.readLine();
					if (str != null) {
						ta_chat.append(str + '\n');
					}
				}
				Thread.sleep(100);
			} catch (IOException e) {
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 发送聊天消息
	 */
	private void sendMessage() {
		String s = tf_chat.getText() + '\n';
		ta_chat.append("我：" + s);
		PrintWriter pw = new PrintWriter(outChat);
		pw.write(s);
		pw.flush();
		tf_chat.setText("");
	}

	/**
	 * 初始化聊天窗口
	 */
	void initChat() {
		ta_chat = new JTextArea(10, 20);
		sp_ta_chat = new JScrollPane(ta_chat);
		ta_chat.setEditable(false);

		tf_chat = new JTextField();
		tf_chat.addKeyListener(new KeyAdapter() {
			// 回车键 发送消息
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case 10:
					if(isPort1Ready && isPort2Ready)
					sendMessage();
					break;
				}
			}
		});

		b_tf_chat = new JButton("发送");
		b_tf_chat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("发送")) {
					if(isPort1Ready && isPort2Ready)
					sendMessage();
				}
			}
		});
		p_tf_chat = new JPanel(new GridLayout(2, 2));
		p_tf_chat.add(tf_chat);
		p_tf_chat.add(b_tf_chat);

		jPanel_chat = new JPanel(new BorderLayout());
		jPanel_chat.setBorder(BorderFactory.createTitledBorder("聊天"));
		jPanel_chat.add(sp_ta_chat, BorderLayout.CENTER);
		jPanel_chat.add(p_tf_chat, BorderLayout.SOUTH);
		this.add(jPanel_chat, BorderLayout.EAST);
	}

	/** Creates new form GobangJFrame */
	public ServerJFrame() {
		initComponents();
		myInit();
		chatT.start();
		playT.start();
	}

	/**
	 * 是否胜利
	 */
	void isVictory() {
		if (count_black == FIVE) {
			//JOptionPane.showMessageDialog(null, "黑棋胜！");
			reset();
		}
		if (count_white == FIVE) {
			//JOptionPane.showMessageDialog(null, "白棋胜！");
			reset();
		}
		count_white = 1;
		count_black = 1;
	}

	/**
	 * 打印
	 */
	void printChesses() {
		for (int i = 0; i < chesses.length; i++) {
			System.out.print("-" + "-");
		}
		System.out.println();
		for (int i = 0; i < chesses.length; i++) {
			for (int j = 0; j < chesses[i].length; j++) {
				int x = chesses[i][j].ordinal();
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 重置
	 */
	void reset() {
		try {
			ranks = Integer.parseInt(jTextField_ranks.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "行列数格式错误!");
			return;
		}
		chesses = new Chess[ranks][ranks];
		for (int i = 0; i < chesses.length; i++) {
			for (int j = 0; j < chesses[i].length; j++) {
				chesses[i][j] = Chess.NULL;
			}
		}
		repaint();
	}

	/**
	 * 判断哪个颜色的棋子连接了
	 * 
	 * @param c
	 */
	private final void checkChessColor(Chess c) {
		switch (c) {
		case WHITE:
			count_white++;
			break;
		case BLACK:
			count_black++;
			break;
		}
	}

	/**
	 * 判断上下方向
	 * 
	 * @param i
	 * @param j
	 * @param dir
	 */
	private final void check_UP_DOWN(int i, int j, int dir) {
		Chess c = chesses[i][j];
		// 向上判断
		if ((dir & UP) != 0) {
			if (i - 1 >= 0) {
				if (chesses[i - 1][j] != Chess.NULL && chesses[i - 1][j] == c) {
					checkChessColor(c);
					check_UP_DOWN(i - 1, j, UP);
				}
				dir ^= UP;
			}
		}
		// 向下判断
		if ((dir & DOWN) != 0) {
			if (i + 1 <= ranks - 1) {
				if (chesses[i + 1][j] != Chess.NULL && chesses[i + 1][j] == c) {
					checkChessColor(c);
					check_UP_DOWN(i + 1, j, DOWN);
				}
				dir ^= DOWN;
			}
		}
	}

	/**
	 * 判断左右方向
	 * 
	 * @param i
	 * @param j
	 * @param dir
	 */
	private final void check_LEFT_RIGHT(int i, int j, int dir) {
		Chess c = chesses[i][j];
		// 向左判断
		if ((dir & LEFT) != 0) {
			if (j - 1 >= 0) {
				if (chesses[i][j - 1] != Chess.NULL && chesses[i][j - 1] == c) {
					checkChessColor(c);
					check_LEFT_RIGHT(i, j - 1, LEFT);
				}
				dir ^= LEFT;
			}
		}
		// 向右判断
		if ((dir & RIGHT) != 0) {
			if (j + 1 <= ranks - 1) {
				if (chesses[i][j + 1] != Chess.NULL && chesses[i][j + 1] == c) {
					checkChessColor(c);
					check_LEFT_RIGHT(i, j + 1, RIGHT);
				}
				dir ^= RIGHT;
			}
		}
	}

	/**
	 * 判断左上到右下方向
	 * 
	 * @param i
	 * @param j
	 * @param dir
	 */
	private final void check_LEFT_UP_RIGHT_DOWN(int i, int j, int dir) {
		Chess c = chesses[i][j];
		// 向左上判断
		if ((dir & LEFT_UP) != 0) {
			if (i - 1 >= 0 && j - 1 >= 0) {
				if (chesses[i - 1][j - 1] != Chess.NULL
						&& chesses[i - 1][j - 1] == c) {
					checkChessColor(c);
					check_LEFT_UP_RIGHT_DOWN(i - 1, j - 1, LEFT_UP);
				}
				dir ^= LEFT_UP;
			}
		}
		// 向右下判断
		if ((dir & RIGHT_DOWN) != 0) {
			if (i + 1 <= ranks - 1 && j + 1 <= ranks - 1) {
				if (chesses[i + 1][j + 1] != Chess.NULL
						&& chesses[i + 1][j + 1] == c) {
					checkChessColor(c);
					check_LEFT_UP_RIGHT_DOWN(i + 1, j + 1, RIGHT_DOWN);
				}
				dir ^= RIGHT_DOWN;
			}
		}
	}

	/**
	 * 判断右上到左下
	 * 
	 * @param i
	 * @param j
	 * @param dir
	 */
	private final void check_RIGHT_UP_LEFT_DOWN(int i, int j, int dir) {
		Chess c = chesses[i][j];
		// 向右上判断
		if ((dir & RIGHT_UP) != 0) {
			if (i - 1 >= 0 && j + 1 <= ranks - 1) {
				if (chesses[i - 1][j + 1] != Chess.NULL
						&& chesses[i - 1][j + 1] == c) {
					checkChessColor(c);
					check_RIGHT_UP_LEFT_DOWN(i - 1, j + 1, RIGHT_UP);
				}
				dir ^= RIGHT_UP;
			}
		}
		// 向左下判断
		if ((dir & LEFT_DOWN) != 0) {
			if (i + 1 <= ranks - 1 && j - 1 >= 0) {
				if (chesses[i + 1][j - 1] != Chess.NULL
						&& chesses[i + 1][j - 1] == c) {
					checkChessColor(c);
					check_RIGHT_UP_LEFT_DOWN(i + 1, j - 1, LEFT_DOWN);
				}
				dir ^= LEFT_DOWN;
			}
		}
	}

	/**
	 * 判断
	 * 
	 * @param row
	 * @param column
	 * @param dir
	 */
	void check(int i, int j, int dir) {
		check_UP_DOWN(i, j, dir);
		isVictory();
		check_LEFT_RIGHT(i, j, dir);
		isVictory();
		check_LEFT_UP_RIGHT_DOWN(i, j, dir);
		isVictory();
		check_RIGHT_UP_LEFT_DOWN(i, j, dir);
		isVictory();
	}

	/**
	 * 放置一个棋子
	 * 
	 * @param p
	 * @param c
	 * @return 是否放置成功
	 */
	boolean addChess(Point p, Chess c) {
		double val = Math.min(jPanel_Gobang.getWidth(),
				jPanel_Gobang.getHeight()) * 0.5 * 0.9;
		int x = (int) (jPanel_Gobang.getWidth() / 2 - val);
		int y = (int) (jPanel_Gobang.getHeight() / 2 - val);
		int w = (int) (val * 2);
		int h = (int) (val * 2);
		double val0 = val * 2 * 0.9 / (ranks - 1);
		int x0 = (int) (x + w / 2 - val * 2 * 0.9 / 2);
		int y0 = (int) (y + h / 2 - val * 2 * 0.9 / 2);
		int dx = p.x - x0;
		int dy = p.y - y0;
		if (p.x < x0 - val0 / 2 || p.y < y0 - val0 / 2
				|| p.x >= x0 + val0 * (ranks - 1) + val0 / 3
				|| p.y >= y0 + val0 * (ranks - 1) + val0 / 3) {
			return false;
		}
		int i = (int) (dx + val0 / 2) / (int) val0;
		int j = (int) (dy + val0 / 2) / (int) val0;
		i = i == ranks ? ranks - 1 : i;
		j = j == ranks ? ranks - 1 : j;
		return canPlace(j, i, c);
	}
	
	/**
	 * 是否可以放置棋子
	 * @param row
	 * @param col
	 * @param c
	 * @return
	 */
	private boolean canPlace(int row, int col, Chess c)
	{
		if (chesses[row][col] == Chess.NULL) {
			chesses[row][col] = c;
			//将放下的 一个棋子的[棋盘坐标]保存
			pos = new int[]{row,col};
			// 成功放置棋子 重绘画面
			repaint();
			count_black = 1;
			count_white = 1;
			int dir = UP | DOWN | LEFT | RIGHT | LEFT_UP | RIGHT_DOWN
					| RIGHT_UP | LEFT_DOWN;
			check(row, col, dir);
			return true;
		}
		return false;
	}

	/**
	 * 绘制棋子
	 * 
	 * @param g
	 */
	void drawChess(Graphics g) {
		for (int i = 0; i < chesses.length; i++) {
			for (int j = 0; j < chesses[i].length; j++) {
				Chess c = chesses[i][j];
				if (c != Chess.NULL) {
					switch (c) {
					case WHITE:
						g.setColor(Color.WHITE);
						break;
					case BLACK:
						g.setColor(Color.BLACK);
						break;
					}
					double val = Math.min(jPanel_Gobang.getWidth(),
							jPanel_Gobang.getHeight()) * 0.5 * 0.9;
					int x = (int) (jPanel_Gobang.getWidth() / 2 - val);
					int y = (int) (jPanel_Gobang.getHeight() / 2 - val);
					int w = (int) (val * 2);
					int h = (int) (val * 2);
					double val0 = val * 2 * 0.9 / (ranks - 1);
					int x0 = (int) (x + w / 2 - val * 2 * 0.9 / 2);
					int y0 = (int) (y + h / 2 - val * 2 * 0.9 / 2);
					int x1 = (int) (x0 + (int) (j * val0) - val0 / 2.4);
					int y1 = (int) (y0 + (int) (i * val0) - val0 / 2.4);
					int w1 = (int) (val0 * 0.90);
					int h1 = w1;
					g.fillOval(x1, y1, w1, h1);
				}
			}
		}

	}

	/**
	 * 绘制棋盘
	 * 
	 * @param g
	 * @param ranks
	 * @param broder
	 */
	void drawChessboard(Graphics g, int ranks, int broder) {
		// 绘制棋盘底座
		g.setColor(Color.LIGHT_GRAY);
		double val = Math.min(jPanel_Gobang.getWidth(),
				jPanel_Gobang.getHeight()) * 0.5 * 0.9;
		int x = (int) (jPanel_Gobang.getWidth() / 2 - val);
		int y = (int) (jPanel_Gobang.getHeight() / 2 - val);
		int w = (int) (val * 2);
		int h = (int) (val * 2);
		g.fillRect(x, y, w, h);
		// 绘制底座边框
		g.setColor(Color.DARK_GRAY);
		for (int i = 1; i <= broder; i++) {
			g.drawRect(x - i, y - i, w + i * 2, h + i * 2);
		}
		// 绘制棋盘网格
		g.setColor(Color.BLUE);
		double val0 = val * 2 * 0.9 / (ranks - 1);
		int x0 = (int) (x + w / 2 - val * 2 * 0.9 / 2);
		int y0 = (int) (y + h / 2 - val * 2 * 0.9 / 2);
		for (int i = 0; i < ranks; i++) {
			int x1 = x0;
			int y1 = (int) (y0 + i * val0);
			int x2 = (int) (x0 + val0 * (ranks - 1));
			int y2 = (int) (y0 + i * val0);
			// 横线
			g.drawLine(x1, y1, x2, y2);
			int x3 = (int) (x0 + i * val0);
			int y3 = y0;
			int x4 = (int) (x0 + i * val0);
			int y4 = (int) (y0 + val0 * (ranks - 1));
			// 竖线
			g.drawLine(x3, y3, x4, y4);
		}
	}

	void myInit() {
		jPanel_Gobang = new javax.swing.JPanel() {

			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				drawChessboard(g, ranks, 2);
				drawChess(g);
			}
		};
		jPanel_Button = new javax.swing.JPanel();
		jButton_reset = new javax.swing.JButton();
		jButton_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		// this.setResizable(false);
		// this.setMinimumSize(new Dimension(400, 400));
		// jPanel_Gobang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jPanel_Gobang, java.awt.BorderLayout.CENTER);
		this.add(jPanel_Button, java.awt.BorderLayout.SOUTH);
		jButton_reset.setText("重置");
		jPanel_Button.add(jButton_reset);
		jLabel_ranks = new JLabel("行列数:");
		jTextField_ranks = new JTextField(10);
		jTextField_ranks.setText(Integer.toString(ranks));
		jPanel_Button.add(jLabel_ranks);
		jPanel_Button.add(jTextField_ranks);
		jPanel_Gobang.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(isPort1Ready && isPort2Ready)
				if (addChess(e.getPoint(), Chess.BLACK)) {
					if(pos != null)
					{
						send();
						pos = null;
					}
				}

//				if (addChess(e.getPoint(), chess ? Chess.BLACK : Chess.WHITE)) {
//					// printChesses();
//					chess = !chess;
//				}
			}
		});
		initChat();
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		reset();
		this.setTitle("Server");
		jButton_reset.setEnabled(false);
		jTextField_ranks.setEditable(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

	}// </editor-fold>
		// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerJFrame().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton_reset;
	private javax.swing.JPanel jPanel_Button;
	private javax.swing.JPanel jPanel_Gobang;
	// End of variables declaration//GEN-END:variables

	static {
		try {
//			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		} catch (UnsupportedLookAndFeelException e) {

		}
	}

}