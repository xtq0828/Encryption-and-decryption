package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.barbeque.key.Barbequekey;
import com.load.message.Main;
import com.utils.FileOp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class UpFrame extends JFrame {

	private JPanel contentPane;
	private JButton button_scan;
	private JButton button_k;
	private JButton button_cipher;
	private JButton button_sk;
	private JTextField text_scan;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton button_up;
	private JComboBox comboBox_2;
	private Barbequekey barbequekey;
	private String filename;
	private String require;
	private String require1;
	private String con1;
	private String con2;
	private String con3;
	private JFileChooser chooser;
	private File file;
	private Main file_up;
	private boolean result;
	private JLabel lblNewLabel;

	private class myListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton judge = (JButton) e.getSource();
			if (judge.equals(button_scan)) {
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				if (file == null) {
					text_scan.setText("未选择文件");
				} else {
					text_scan.setText(file.getName().toString());
					filename = text_scan.getText().toString();

					 FileOp.copyFile(file.getAbsolutePath(), "." +
					 File.separator + filename);
				}
			} else if (judge.equals(button_k)) {

				try {
					barbequekey.cd();
					barbequekey.PM_key();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				File oldFile = new File("." + File.separator + "pub_key");
				String rootPath = oldFile.getParent();
				File newFile = new File(rootPath + File.separator + filename
						+ "_pk");

				while (!oldFile.renameTo(newFile)) {

				}
				;
				oldFile.delete();

			} else if (judge.equals(button_cipher)) {

				filename = text_scan.getText().toString();
				if (comboBox.getSelectedItem().toString().equals("VIP")) {
					con1 = "'VIP";
				} else {
					con1 = "'NotVIP";
				}
				if (comboBox_1.getSelectedItem().toString().equals("很活跃")) {
					con2 = " or Active";
				} else {
					con2 = " or NotActive";
				}
				if (comboBox_2.getSelectedItem().toString().equals("粉丝多")) {
					con3 = " or FansMore'";
				} else {
					con3 = " or FansLess'";
				}
				require = con1 + con2 + con3;
				try {
					File pk = new File(filename+"_pk");
					File f = new File(filename);
//					barbequekey.cipher(filename + "_pk", filename, require);
					barbequekey.cipher(pk.getAbsolutePath(), f.getAbsolutePath(), require);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} else if (judge.equals(button_sk)) {

				filename = text_scan.getText().toString();
				if (comboBox.getSelectedItem().toString().equals("VIP")) {
					con1 = "'VIP";
				} else {
					con1 = "'NotVIP";
				}
				if (comboBox_1.getSelectedItem().toString().equals("很活跃")) {
					con2 = " or Active";
				} else {
					con2 = " or NotActive";
				}
				if (comboBox_2.getSelectedItem().toString().equals("粉丝多")) {
					con3 = " or FansMore'";
				} else {
					con3 = " or FansLess'";
				}
				require1 = con1 + con2 + con3;
				try {
					barbequekey.private_key(filename + "_SK", filename + "_pk",
							"master_key", require1, "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (judge.equals(button_up)) {
				file_up = new Main();
				try {
					File f = new File(filename + ".cpabe");
					result = file_up.up(1, f.getAbsolutePath());
					if (result) {
						lblNewLabel.setText("上传成功");
						lblNewLabel.setVisible(true);
					} else {
						lblNewLabel.setText("上传失败");
						lblNewLabel.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public UpFrame() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		getContentPane().setBackground(new java.awt.Color(230, 255, 255));
		setBounds(100, 100, 450, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		text_scan = new JTextField();
		text_scan.setBounds(35, 53, 220, 41);
		contentPane.add(text_scan);
		text_scan.setColumns(10);

		button_scan = new JButton("浏览");
		button_scan.setBounds(297, 52, 93, 41);
		contentPane.add(button_scan);

		ComboBoxModel model1 = new DefaultComboBoxModel(new String[] { "VIP",
				"非VIP" });
		comboBox = new JComboBox();
		comboBox.setBounds(35, 219, 99, 41);
		comboBox.setModel(model1);
		contentPane.add(comboBox);

		ComboBoxModel model2 = new DefaultComboBoxModel(new String[] { "很活跃",
				"不活跃" });
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(161, 219, 99, 41);
		comboBox_1.setModel(model2);
		contentPane.add(comboBox_1);

		ComboBoxModel model3 = new DefaultComboBoxModel(new String[] { "粉丝多",
				"粉丝少" });
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(291, 219, 99, 41);
		comboBox_2.setModel(model3);
		contentPane.add(comboBox_2);

		button_k = new JButton("生成密钥");
		button_k.setBounds(35, 122, 93, 41);
		contentPane.add(button_k);

		button_cipher = new JButton("加密文件");
		button_cipher.setBounds(297, 122, 93, 41);
		contentPane.add(button_cipher);

		button_sk = new JButton("生成私钥");
		button_sk.setBounds(161, 313, 99, 41);
		contentPane.add(button_sk);

		button_up = new JButton("上传");
		button_up.setBounds(162, 401, 98, 41);
		contentPane.add(button_up);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(162, 494, 98, 41);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);

		this.setTitle("上传文件");

		barbequekey = new Barbequekey();
		chooser = new JFileChooser();

		myListener listener = new myListener();
		button_cipher.addActionListener(listener);
		button_k.addActionListener(listener);
		button_scan.addActionListener(listener);
		button_sk.addActionListener(listener);
		button_up.addActionListener(listener);
	}
}
