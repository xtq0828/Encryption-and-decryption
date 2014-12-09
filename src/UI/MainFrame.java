package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton button_up;
	private JButton button_down;

	private class myListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton judge = (JButton)e.getSource();
			if(judge.equals(button_up)) {
				UpFrame frame = new UpFrame();
				frame.setVisible(true);
				
			}else if(judge.equals(button_down)){
				DownFrame frame = new DownFrame();
				frame.setVisible(true);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setTitle("主界面");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new java.awt.Color(230,255,255));
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_up = new JButton("上传");
		button_up.setBounds(70, 94, 93, 39);
		
		button_down = new JButton("下载");
		button_down.setBounds(258, 94, 93, 39);
		
		contentPane.add(button_up);
		contentPane.add(button_down);
		
		myListener listener = new myListener();
		
		button_up.addActionListener(listener);
		button_down.addActionListener(listener);

	}
}
