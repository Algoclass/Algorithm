package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

public class UIFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfDirPath;
	private JTextField tfFilePath;
	private JButton btnPattern;
	private JButton btnDir;
	private JCheckBox chckbxKmp;
	private JCheckBox chckbxLcss;
	private JCheckBox chckbxRabinKarp;
	private JCheckBox chckbxBoyerMoore;
	private JCheckBox chckbxNaive;
	private JCheckBox chckbxSelectAll;
	private JButton btnStart;
	JFileChooser filechooser;
	String path="";
	String filePath="";
	String dirPath="";
	int Kmpflag=0;
	int Boyerflag=0;
	int Lcssflag=0;
	int Naiveflag=0;
	int Rabinflag=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIFrame frame = new UIFrame();
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
	public UIFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		tfDirPath = new JTextField();
		panel.add(tfDirPath);
		tfDirPath.setColumns(10);

		btnDir = new JButton("Directory");
		panel.add(btnDir);
		btnDir.addActionListener(this);

		tfFilePath = new JTextField();
		panel.add(tfFilePath);
		tfFilePath.setColumns(10);

		btnPattern = new JButton("Pattern");
		panel.add(btnPattern);
		btnPattern.addActionListener(this);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		chckbxLcss = new JCheckBox("LCSS");
		chckbxLcss.setBounds(421, 59, 69, 29);

		chckbxNaive = new JCheckBox("Naive");
		chckbxNaive.setBounds(490, 59, 71, 29);

		chckbxBoyerMoore = new JCheckBox("Boyer Moore");
		chckbxBoyerMoore.setBounds(561, 59, 121, 29);

		chckbxRabinKarp = new JCheckBox("Rabin Karp");
		chckbxRabinKarp.setBounds(682, 59, 109, 29);

		chckbxKmp = new JCheckBox("KMP");
		chckbxKmp.setBounds(791, 59, 63, 29);

		chckbxSelectAll = new JCheckBox("Select All");
		chckbxSelectAll.setBounds(854, 59, 97, 29);
		panel_1.setLayout(null);
		panel_1.add(chckbxLcss);
		panel_1.add(chckbxNaive);
		panel_1.add(chckbxBoyerMoore);
		panel_1.add(chckbxRabinKarp);
		panel_1.add(chckbxKmp);
		panel_1.add(chckbxSelectAll);

		JTextArea tALcss = new JTextArea();
		tALcss.setBounds(490, 125, 200, 50);
		panel_1.add(tALcss);

		JTextArea tANaive = new JTextArea();
		tANaive.setBounds(703, 125, 200, 50);
		panel_1.add(tANaive);

		JTextArea tABoyer = new JTextArea();
		tABoyer.setBounds(386, 214, 200, 50);
		panel_1.add(tABoyer);

		JTextArea tARabin = new JTextArea();
		tARabin.setBounds(592, 214, 200, 50);
		panel_1.add(tARabin);

		JTextArea tAKmp = new JTextArea();
		tAKmp.setBounds(798, 214, 200, 50);
		panel_1.add(tAKmp);

		btnStart = new JButton("Start");
		btnStart.setBounds(625, 88, 115, 29);
		panel_1.add(btnStart);
		btnStart.addActionListener(this);

		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getSource());
		if(e.getSource()==btnDir ||e.getSource()==btnPattern){
			int returnVal = filechooser.showOpenDialog(UIFrame.this);           
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = filechooser.getSelectedFile();
				path = file.getAbsolutePath();
				if(e.getSource()==btnDir){
					tfDirPath.setText(path);	
					dirPath=path;
				}
				else if(e.getSource()==btnPattern){
					tfFilePath.setText(path);
					filePath=path;
				}

			} else {
				System.out.println("Select command cancelled by user."+"\n");
			}
		}
		if(e.getSource()== btnStart){
			dirPath=tfDirPath.getText();
			filePath=tfFilePath.getText();        
			if(chckbxBoyerMoore.isSelected()){
				Boyerflag=1;
				System.out.println("boyer Moore: "+Boyerflag);
			}if(chckbxKmp.isSelected()){
				Kmpflag=1;
				System.out.println("KMP : "+Kmpflag);
			}if(chckbxNaive.isSelected()){
				Naiveflag=1;
				System.out.println("Naive: "+Naiveflag);
			}if(chckbxRabinKarp.isSelected()){
				Rabinflag=1;
				System.out.println("Rabin flag: "+Rabinflag);
			}if(chckbxLcss.isSelected()){
				Lcssflag=1;
				System.out.println("LCSS flag: "+Lcssflag);
			}
			System.out.println("Directory: "+dirPath);
			System.out.println("File: "+filePath);
		}
		tfDirPath.setCaretPosition(tfDirPath.getDocument().getLength());    
	
	}
}
