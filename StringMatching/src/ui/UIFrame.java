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

import algorithms.LcssCompute;

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
	
	private JTextArea tALcss;
	private JTextArea tANaive;
	private JTextArea tABoyer;
	private JTextArea tARabin;
	private JTextArea tAKmp;
	
	JFileChooser filechooser;
	String path="";
	static String filePath="";
	static String dirPath="";
	File documentCorpus = null;//new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus");
	File potentialPlagiarisedFile = null;//new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/1.txt");
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
		tfDirPath.setColumns(30);

		btnDir = new JButton("Directory");
		panel.add(btnDir);
		btnDir.addActionListener(this);

		tfFilePath = new JTextField();
		panel.add(tfFilePath);
		tfFilePath.setColumns(30);

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
		chckbxSelectAll.addActionListener(this);
		panel_1.setLayout(null);
		panel_1.add(chckbxLcss);
		panel_1.add(chckbxNaive);
		panel_1.add(chckbxBoyerMoore);
		panel_1.add(chckbxRabinKarp);
		panel_1.add(chckbxKmp);
		panel_1.add(chckbxSelectAll);

		tALcss = new JTextArea();
		tALcss.setBounds(100, 125, 600, 200);
		panel_1.add(tALcss);

		tANaive = new JTextArea();
		tANaive.setBounds(703, 125, 600, 200);
		panel_1.add(tANaive);

		tABoyer = new JTextArea();
		tABoyer.setBounds(100, 400, 350, 300);
		panel_1.add(tABoyer);

		tARabin = new JTextArea();
		tARabin.setBounds(500, 400, 350, 300);
		panel_1.add(tARabin);

		tAKmp = new JTextArea();
		tAKmp.setBounds(1000, 400, 350, 300);
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
				//System.out.println("LCSS flag: "+Lcssflag);
				documentCorpus=new File(dirPath);
				potentialPlagiarisedFile=new File(filePath);
				double percentage=LcssCompute.compute(documentCorpus, potentialPlagiarisedFile);
				tALcss.setText("total percentage match :"+percentage*100);
			}
			System.out.println("Directory: "+dirPath);
			System.out.println("File: "+filePath);
		}
		
		
		if(chckbxSelectAll.isSelected())
		{
			
			chckbxKmp.setSelected(true);
			chckbxBoyerMoore.setSelected(true);
			chckbxLcss.setSelected(true);
			chckbxNaive.setSelected(true);
			chckbxRabinKarp.setSelected(true);
		}
		else if(!chckbxSelectAll.isSelected()){
			chckbxKmp.setSelected(false);
			chckbxBoyerMoore.setSelected(false);
			chckbxLcss.setSelected(false);
			chckbxNaive.setSelected(false);
			chckbxRabinKarp.setSelected(false);
		}
		tfDirPath.setCaretPosition(tfDirPath.getDocument().getLength());    
	
	}
}
