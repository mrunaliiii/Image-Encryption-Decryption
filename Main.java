package MAIN;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Main{
	public static void main(String args[]){
		new FileEncryptUI().start();
	}	
}

class FileEncryptUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3603647891754269950L;
	//declare form UI controls
	private JTextField txtEncFile,		//encrypt source file
					   txtEncKey,
					   txtDecFile,		//decrypt source file
					   txtDecKey;		//decrypt source key
	private JButton btnEncBrw,			//browse encrypt source file
					btnDecBrw,			//browse decrypt source file			
					btnEncRun,			//run encryption
					btnDecRun,			//run decryption
					btnDone1,
					btnDone2,
					btnExit,
					btnExit1;
	private JTabbedPane tab;
	private JPanel pnlEnc,				//main encryption panel
				   pnlDec,				//main decryption panel
				   pnlEncRow1,
				   pnlEncRow2,
				   pnlDecRow1,
				   pnlDecRow2,
				   pnlAbt;
	private AESFileEncryption e_aes;
	private AESFileDecryption d_aes;
	private String strAbout[] = {"Press Done before Encypting or decrypting the file"};


	public FileEncryptUI(){

		e_aes = new AESFileEncryption();
		d_aes = new AESFileDecryption();
		// encryption panel		
		txtEncFile = new JTextField("",30);
		txtEncKey = new JTextField("",30);
		
		btnDone1 = new JButton("Done");
		btnDone1.setPreferredSize(new Dimension(80,20));
		btnDone1.addActionListener(this);
		btnDone1.setMnemonic(KeyEvent.VK_X);
		
		btnDone2 = new JButton("Done");
		btnDone2.setPreferredSize(new Dimension(80,20));
		btnDone2.addActionListener(this);
		btnDone2.setMnemonic(KeyEvent.VK_X);

		btnExit = new JButton("Exit");
		btnExit.setPreferredSize(new Dimension(80,20));
		btnExit.addActionListener(this);
		btnExit.setMnemonic(KeyEvent.VK_X);
		
		btnExit1 = new JButton("Exit");
		btnExit1.setPreferredSize(new Dimension(80,20));
		btnExit1.addActionListener(this);
		btnExit1.setMnemonic(KeyEvent.VK_X);

		btnEncBrw = new JButton("Browse");
		btnEncBrw.addActionListener(this);

		btnEncRun = new JButton("Encrypt");
		btnEncRun.setPreferredSize(new Dimension(80,20));
		btnEncRun.addActionListener(this);
		btnEncRun.setMnemonic(KeyEvent.VK_E);

		pnlEncRow1 = new JPanel(new BorderLayout());
		pnlEncRow1.setPreferredSize(new Dimension(300,20));
		pnlEncRow1.setBackground(new Color(0,0,0,0));
		pnlEncRow1.add(new JLabel("File: "),"West");
		pnlEncRow1.add(txtEncFile,"Center");
		pnlEncRow1.add(btnEncBrw,"East");
		
		pnlEncRow2 = new JPanel(new BorderLayout());
		pnlEncRow2.setPreferredSize(new Dimension(300,20));
		pnlEncRow2.setBackground(new Color(0,0,0,0));
		pnlEncRow2.add(new JLabel("Key: "),"West");
		pnlEncRow2.add(txtEncKey,"Center");
		pnlEncRow2.add(btnDone1,"East");

		pnlEnc = new JPanel(new FlowLayout());		
		pnlEnc.setBackground(new Color(0,0,0,0));
		pnlEnc.add(new JLabel("Select a file to be encrypted"));
		pnlEnc.add(pnlEncRow1);
		pnlEnc.add(pnlEncRow2);
		pnlEnc.add(btnEncRun);
		pnlEnc.add(btnExit);

		// decryption panel
		txtDecFile = new JTextField("",30);
		txtDecKey = new JTextField("",30);

		btnDecBrw = new JButton("Browse");
		btnDecBrw.addActionListener(this);

		btnDecRun = new JButton("Decrypt");
		btnDecRun.setPreferredSize(new Dimension(80,20));
		btnDecRun.addActionListener(this);
		btnDecRun.setMnemonic(KeyEvent.VK_D);

		pnlDecRow1 = new JPanel(new BorderLayout());
		pnlDecRow1.setPreferredSize(new Dimension(300,20));
		pnlDecRow1.setBackground(new Color(0,0,0,0));
		pnlDecRow1.add(new JLabel("File: "),"West");
		pnlDecRow1.add(txtDecFile,"Center");
		pnlDecRow1.add(btnDecBrw,"East");

		pnlDecRow2 = new JPanel(new BorderLayout());
		pnlDecRow2.setPreferredSize(new Dimension(300,20));
		pnlDecRow2.setBackground(new Color(0,0,0,0));
		pnlDecRow2.add(new JLabel("Key: "),"West");
		pnlDecRow2.add(txtDecKey,"Center");
		pnlDecRow2.add(btnDone2,"East");

		pnlDec = new JPanel(new FlowLayout());		
		pnlDec.setBackground(new Color(0,0,0,0));
		pnlDec.add(new JLabel("Select file to decrypt and decryption key"));
		pnlDec.add(pnlDecRow1);
		pnlDec.add(pnlDecRow2);
		pnlDec.add(btnDecRun);
		pnlDec.add(btnExit1);

		//about panel
		pnlAbt = new JPanel(new FlowLayout());
		pnlAbt.setBackground(new Color(0,0,0,0));
		for (int i=0; i<strAbout.length; i++)
			pnlAbt.add(new JLabel(strAbout[i]));

		//main tab
		tab = new JTabbedPane();
		tab.setPreferredSize(new Dimension(310,150));
		tab.add("Encrypt",pnlEnc);
		tab.add("Decrypt",pnlDec);
		tab.add("About",pnlAbt);

		//main frame
		setSize(400,200);
		setLocation(100,100);
		getContentPane().add(tab,"Center");

		setTitle("DES File Encryption/Decryption");		
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setResizable(false);		
	}

	public void start(){
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		JButton btn = (JButton)e.getSource();
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		JFileChooser k = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		if (btn == btnEncBrw){
			 int r = j.showSaveDialog(null);
			 
	            if (r == JFileChooser.APPROVE_OPTION)
	 
	            {
	                txtEncFile.setText(j.getSelectedFile().getAbsolutePath());
	            }
	            else
	                txtEncFile.setText("the user cancelled the operation");
		}

		if (btn == btnDecBrw){
			
            int r = k.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                txtDecFile.setText(k.getSelectedFile().getAbsolutePath());
            }
            else
                txtDecFile.setText("the user cancelled the operation");
		}
		if (btn == btnDone1){
			
			if (txtEncKey.getText() == null){
				JOptionPane.showMessageDialog(null,				
					"Key file not found or cannot be accessed.",
					"Error",JOptionPane.ERROR_MESSAGE);
					return;
			}
			
		}

		if (btn == btnDone2){
			
			if (txtDecKey.getText() == null){
				JOptionPane.showMessageDialog(null,				
					"Key file not found or cannot be accessed.",
					"Error",JOptionPane.ERROR_MESSAGE);
					return;
			}
		}

		if (btn == btnEncRun){
			
			String txtKey = txtEncKey.getText();
			int key = Integer.parseInt(txtKey);
			System.out.println(key);
			String path = txtEncFile.getText();
			System.out.println(path);
			
			try {
				e_aes.encrypt(key, path);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	
		if (btn == btnDecRun){
			String txtKey = txtDecKey.getText();
			int key = Integer.parseInt(txtKey);
			System.out.println(key);
			String path = txtDecFile.getText();
			System.out.println(path);
			
			try {
				d_aes.decrypt(key, path);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		


		if (btn == btnExit){
			System.exit(0);
		}
		if (btn == btnExit1){
					System.exit(0);
					}
				
		}
	

	}
