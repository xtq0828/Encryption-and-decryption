package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.barbeque.key.Barbequekey;
import com.load.message.Main;
import com.utils.FileOp;

import javax.swing.JLabel;


public class DownFrame extends JFrame {

	private JPanel contentPane;
	private JButton button_down;
	private JButton button_pk;
	private JButton button_sk;
	private JTextField text_file;
	private JButton button_gain;
	private JTextField text_pk;
	private JTextField text_sk;
	private JButton button_open;
	private Barbequekey barbequekey;
	private String filename;
	private String P_key;
	private String private_key;
	private JFileChooser chooser;
	private File file;
	private Main file_down;
	private boolean result;
	JLabel lblNewLabel;
	
	private String basePath = "/home/barbeque/down/";

	private class myListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton judge = (JButton)e.getSource();
			if(judge.equals(button_down)) {
				file_down = new Main();
				filename = text_file.getText().toString();
				if (!filename.endsWith(".cpabe")) {
					filename += ".cpabe";
				}
				try {
					result = file_down.up(2, filename);
					if(result) {
						lblNewLabel.setText("下载成功");
						lblNewLabel.setVisible(true);
					}else {
						lblNewLabel.setText("下载失败");
						lblNewLabel.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else if(judge.equals(button_pk)){
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				if(file == null) {
					text_pk.setText("未选择公钥");
				}else {
					text_pk.setText(file.getName().toString());
					FileOp.copyFile(file.getAbsolutePath(), basePath + file.getName());
				}
			}else if(judge.equals(button_sk)) {
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				if(file == null) {
					text_sk.setText("未选择私钥");
				}else {
					text_sk.setText(file.getName().toString());
					FileOp.copyFile(file.getAbsolutePath(), basePath + file.getName());
				}
			}else if(judge.equals(button_gain)) {
				P_key = text_pk.getText().toString();
				private_key = text_sk.getText().toString();
				try {
					barbequekey.decipher(basePath + P_key, basePath + private_key, basePath + filename);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}else if(judge.equals(button_open)) {
				try {
					filename = filename.replace(".cpabe", "");
					barbequekey.show(basePath + filename);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}			
		}
	}
	public DownFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		getContentPane().setBackground(new java.awt.Color(230,255,255));
		setBounds(100, 100, 450, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text_file = new JTextField();
		text_file.setBounds(35, 53, 220, 41);
		contentPane.add(text_file);
		text_file.setColumns(10);
		
		button_down = new JButton("下载");
		button_down.setBounds(297, 52, 93, 41);
		contentPane.add(button_down);
		
		text_pk = new JTextField();
		text_pk.setBounds(35, 122, 220, 41);
		contentPane.add(text_pk);
		text_pk.setColumns(10);
		
		button_pk= new JButton("选择公钥");
		button_pk.setBounds(297, 122, 93, 41);
		contentPane.add(button_pk);
		
		text_sk = new JTextField();
		text_sk.setBounds(35, 194, 220, 41);
		contentPane.add(text_sk);
		text_sk.setColumns(10);
		
		button_sk = new JButton("选择私钥");
		button_sk.setBounds(297, 194, 93, 41);
		contentPane.add(button_sk);
		
		button_gain = new JButton("解密文件");
		button_gain.setBounds(157, 314, 98, 41);
		contentPane.add(button_gain);
		
		button_open = new JButton("打开文件");
		button_open.setBounds(157, 385, 98, 41);
		contentPane.add(button_open);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(157, 457, 98, 41);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		this.setTitle("下载文件");
		
		barbequekey = new Barbequekey();
		chooser = new JFileChooser();
		
		myListener listener = new myListener();
		button_down.addActionListener(listener);
		button_gain.addActionListener(listener);
		button_open.addActionListener(listener);
		button_pk.addActionListener(listener);
		button_sk.addActionListener(listener);
	}
}
