package editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notepad extends JFrame implements ActionListener, WindowListener {
	JTextArea textArea = new JTextArea();
	File fnameContainer;

	public Notepad() {
		Font fontType = new Font("Arial", Font.PLAIN, 15);
		Container con = getContentPane();
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuEdit = new JMenu("Edit");
		JMenu menuHelp = new JMenu("Help");
		con.setLayout(new BorderLayout());
		
		JScrollPane sbrText = new JScrollPane(textArea);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);

		textArea.setFont(fontType);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		con.add(sbrText);

		createMenuItem(menuFile, "New");
		createMenuItem(menuFile, "Open");
		createMenuItem(menuFile, "Save");
		menuFile.addSeparator();
		createMenuItem(menuFile, "Exit");

		createMenuItem(menuEdit, "Cut");
		createMenuItem(menuEdit, "Copy");
		createMenuItem(menuEdit, "Paste");

		createMenuItem(menuHelp, "About Notepad");

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);

		setJMenuBar(menuBar);
		setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
		addWindowListener(this);
		setSize(500, 500);
		setTitle("Untitled.txt - Notepad _ By Manan Garg");
		setVisible(true);
	}

	private void createMenuItem(JMenu menuFile, String string) {
		// TODO Auto-generated method stub
		JMenuItem menuItem = new JMenuItem(string);
		menuItem.addActionListener(this);
		menuFile.add(menuItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser fileSelect = new JFileChooser();

		if (e.getActionCommand().equals("New")) {
			// new
			this.setTitle("Untitled.txt - Notepad");
			textArea.setText("");
			fnameContainer = null;
		} else if (e.getActionCommand().equals("Open")) {
			// open
			int ret = fileSelect.showDialog(null, "Open");

			if (ret == JFileChooser.APPROVE_OPTION) {
				try {
					File fyl = fileSelect.getSelectedFile();
					OpenFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName() + " - Notepad");
					fnameContainer = fyl;
				} catch (IOException ers) {
				}
			}

		} else if (e.getActionCommand().equals("Save")) {
			// save
			if (fnameContainer != null) {
				fileSelect.setCurrentDirectory(fnameContainer);
				fileSelect.setSelectedFile(fnameContainer);
			} else {
				// fileSelect.setCurrentDirectory(new File("."));
				fileSelect.setSelectedFile(new File("Untitled.txt"));
			}

			int ret = fileSelect.showSaveDialog(null);

			if (ret == JFileChooser.APPROVE_OPTION) {
				try {

					File fyl = fileSelect.getSelectedFile();
					SaveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName() + " - Notepad");
					fnameContainer = fyl;

				} catch (Exception ers2) {
				}
			}

		} else if (e.getActionCommand().equals("Exit")) {
			// exit
			Exiting();
		} else if (e.getActionCommand().equals("Copy")) {
			// copy
			textArea.copy();
		} else if (e.getActionCommand().equals("Paste")) {
			// paste
			textArea.paste();
		} else if (e.getActionCommand().equals("About Notepad")) {
			// about
			JOptionPane.showMessageDialog(this, "Created by: Manan Garg _ LGM Intern _ JAVA Developer)",
					"Notepad", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getActionCommand().equals("Cut")) {
			textArea.cut();
		}
	}
	public void OpenFile(String fname) throws IOException {	
		//open file code here
		BufferedReader d=new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
		String l;
		textArea.setText("");
	
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		while((l=d.readLine())!= null) {
			textArea.setText(textArea.getText()+l+"\r\n");
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		d.close();
	}
	public void SaveFile(String fname) throws IOException {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		DataOutputStream o=new DataOutputStream(new FileOutputStream(fname));
		o.writeBytes(textArea.getText());
		o.close();		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		Exiting();
	}
	public void Exiting() {
		System.exit(0);
	}
}
