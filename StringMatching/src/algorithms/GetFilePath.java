package algorithms;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;
 
// Anant: create 1 more file choser like this and set the value to DIRECTORIES_ONLY (see the first comment below)
// Jadoo: integrate the classes in file reader
// Mattoo: your algos are giving output of indexes only. in order to perform analytics i need matched patterns
public class GetFilePath extends JPanel
                             implements ActionListener {
  
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    String path="";
 
    public GetFilePath() {
        super(new BorderLayout());
 
        log = new JTextArea(20,80);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
 
        fc = new JFileChooser();
 
      
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        openButton = new JButton("Select Doc Corpus");
        openButton.addActionListener(this);
 

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        //buttonPanel.add(saveButton);
 
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) {
 

 
            int returnVal = fc.showOpenDialog(GetFilePath.this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path=file.getAbsolutePath();
                log.append("Selected: " +path+ newline);
            } else {
                log.append("Select command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());    
    }
 

 
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Select files");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        frame.add(new GetFilePath());
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}