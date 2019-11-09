import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editor {

	private static JFrame frame;
	private static JTextField txtName;
	private static JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editor window = new editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setText("Articel-Name");
		txtName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnObjects = new JMenu("Objects");
		mnObjects.setBackground(SystemColor.scrollbar);
		menuBar.add(mnObjects);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Bilder");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NameOfTheFile = Input("Name of the Picture");
			    String temp = "<br> <img src=\"../BLOG/img/" + NameOfTheFile + "\">";
			    editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmNewMenuItem.setBackground(SystemColor.window);
		mnObjects.add(mntmNewMenuItem);
		
		JMenuItem mntmSource = new JMenuItem("Bibliographie");
		mntmSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SourceInhalt = Input("SourceText");
			    String temp = "<Bibliographie>" + SourceInhalt + "</Bibliographie>";
			    editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmSource.setBackground(SystemColor.window);
		mnObjects.add(mntmSource);
		
		JMenuItem mntmVideo = new JMenuItem("Video");
		mntmVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String VideoTitel = Input("Video Name");
			    String temp = "<source src=\"./ExtraMedia/Video/" + VideoTitel + "\" type=\"video/mp4\">";
			    editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmVideo.setBackground(SystemColor.window);
		mnObjects.add(mntmVideo);
		
		JMenuItem mntmAbsatz = new JMenuItem("Absatz");
		mntmAbsatz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = "<br>"  + "<br>";
				editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmAbsatz.setBackground(SystemColor.window);
		mnObjects.add(mntmAbsatz);
		
		JMenuItem mntmLink = new JMenuItem("Link");
		mntmLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String link = Input("link");
			    String name = Input("Name of the Link");
			    String temp = "<a href=\"" + link + "\">" + name + "</a>";
			    editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmLink.setBackground(SystemColor.window);
		mnObjects.add(mntmLink);
		
		JMenuItem mntmLink_1 = new JMenuItem("Headline");
		mntmLink_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String HeadlineInhalt = Input("Headline");
			    String temp = "<h3>" + HeadlineInhalt + "</h3>";
			    editorPane.setText(editorPane.getText() + "\n" + temp);
			}
		});
		mntmLink_1.setBackground(SystemColor.window);
		mnObjects.add(mntmLink_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.setBackground(SystemColor.textHighlight);
		menuBar.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setBackground(SystemColor.desktop);
		menuBar.add(mntmSave);
	}
	
	private static void open() {
		JFileChooser j = new JFileChooser();
		j.showOpenDialog(frame);
		
	}
	
	private static void save() {
		System.out.println("Save");
		File dir = new File("BLOG");
		dir.mkdirs();
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String Date =  dateFormat.format(date);
		
		String Name = txtName.getText() + "---" + Date;
		
		File file = new File (dir, Name);

		 try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file + ".html", true)); 
			PrintWriter print1 = new PrintWriter(out);
			
			print1.write(editorPane.getText());
			print1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// --------------------------------
		// Adding the file to the JSON-Info
		File dir2 = new File("JSON");
		dir2.mkdirs();
		File file2 = new File (dir2, "blog.txt");

		 try {
			BufferedWriter out2 = new BufferedWriter(new FileWriter(file2, true)); 
		    PrintWriter print = new PrintWriter(out2);
		    
		    print.write("\n" + Name);
		    print.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getFileName() {
		return null;
	}
	
	private static String Input(String Name) {
		JFrame frame = new JFrame("Input");

	    // prompt the user to enter their name
	    String input = JOptionPane.showInputDialog(frame, Name);

	    if (input != null && input != "") {
	    	return input;
	    }
		return "";
	}
}
