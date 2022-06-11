package me.Stun.Startup;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class Console extends JFrame {

	public static JTextArea TextArea;
	public static JScrollPane ScrollPane;
	public static Container cp;

	public int frameWidth;
	public int frameHeight;
	public int x;
	public int y;

	public static Console windowInstance;

	public Console() {

		super();
		windowInstance = this;

		frameWidth = 1280;
		frameHeight = 720;

		setSize(frameWidth, frameHeight);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - getSize().width) / 2;
		y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("StunShell");
		setResizable(false);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.setBackground(Color.BLACK);

		Font font = new Font("Lucida Console", Font.PLAIN, 20);
		TextArea = new JTextArea();
		TextArea.setFont(font);

		TextArea.setBounds(1, 1, 1920, 1050);
		TextArea.setBackground(Color.BLACK);
		TextArea.setForeground(Color.WHITE);

		DefaultCaret caret = (DefaultCaret) TextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		ScrollPane = new JScrollPane(TextArea);
		ScrollPane.setBounds(1, 1, 1920, 1050);
		ScrollPane.setBackground(Color.BLACK);
		cp.add(ScrollPane);

		// setVisible(true);

	}

}
