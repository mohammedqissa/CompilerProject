package compilerproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class User {

	static private JFrame frame;
	private JTable AllTokens;
	private JTable SymbolTable;
	private JTable error;
	private JTable comments;
	private Scanner scanner;
	static String[] header = { "Token", "ID" };
	static String[] header1 = { "Token", "ID" , "Type"};
	static String[] header2 = { "Token"};
	static String[] header3 = { "ID","Comment"};
	static DefaultTableModel mngr = new DefaultTableModel(header, 0);
	static DefaultTableModel mngr1 = new DefaultTableModel(header1, 0);
	static DefaultTableModel mngr2 = new DefaultTableModel(header2, 0);
	static DefaultTableModel mngr3 = new DefaultTableModel(header3, 0);
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblSymbolTable;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel lblLexicalErrors;
	private JLabel lblComments;
	private JButton exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
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
	public User() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1015, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton read = new JButton("Scan A file");
		read.setIcon(new ImageIcon("D:\\Documents\\School\\Compiler\\CompilerProject\\CompilerProject\\src\\Assets\\scan.png"));
		read.setForeground(Color.DARK_GRAY);
		read.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				File f = j.getSelectedFile();
				scanner = new Scanner(f);

				Parser parser = new Parser();

			}
		});
		read.setBounds(10, 29, 200, 45);
		frame.getContentPane().add(read);

		JLabel lblNewLabel = new JLabel("AllTokens");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 67, 200, 45);
		frame.getContentPane().add(lblNewLabel);


		AllTokens = new JTable(mngr);
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(239, 198, 46));

		for (int i = 0; i < AllTokens.getModel().getColumnCount(); i++) {
			AllTokens.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		AllTokens.setFont(new Font("Calibri", Font.PLAIN, 16));
		AllTokens.setForeground(Color.DARK_GRAY);

		AllTokens.setPreferredScrollableViewportSize(new Dimension(450, 63));

		AllTokens.setFillsViewportHeight(true);
		AllTokens.setBorder(new LineBorder(Color.DARK_GRAY));
		AllTokens.setBounds(265, 132, 194, 235);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 200, 461);
		scrollPane.setViewportView(AllTokens);
		frame.getContentPane().add(scrollPane);
		
		SymbolTable = new JTable(mngr1);
		for (int i = 0; i < SymbolTable.getModel().getColumnCount(); i++) {
			SymbolTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
		SymbolTable.setFont(new Font("Calibri", Font.PLAIN, 16));
		SymbolTable.setForeground(Color.DARK_GRAY);

		SymbolTable.setPreferredScrollableViewportSize(new Dimension(450, 63));

		SymbolTable.setFillsViewportHeight(true);
		SymbolTable.setBorder(new LineBorder(Color.DARK_GRAY));
		SymbolTable.setBounds(265, 132, 194, 235);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(250, 112, 287, 461);
		scrollPane_1.setViewportView(SymbolTable);
		frame.getContentPane().add(scrollPane_1);
		
		lblSymbolTable = new JLabel("Symbol table");
		lblSymbolTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblSymbolTable.setForeground(Color.DARK_GRAY);
		lblSymbolTable.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblSymbolTable.setBounds(250, 67, 287, 45);
		frame.getContentPane().add(lblSymbolTable);
		

		error = new JTable(mngr2);
		DefaultTableCellRenderer headerRenderer1 = new DefaultTableCellRenderer();
		headerRenderer1.setBackground(new Color(239, 100, 46));

		for (int i = 0; i < error.getModel().getColumnCount(); i++) {
			error.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer1);
		}
		error.setFont(new Font("Calibri", Font.PLAIN, 16));
		error.setForeground(Color.DARK_GRAY);

		error.setPreferredScrollableViewportSize(new Dimension(450, 63));

		error.setFillsViewportHeight(true);
		error.setBorder(new LineBorder(Color.DARK_GRAY));
		error.setBounds(265, 132, 194, 235);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(562, 112, 117, 461);
		scrollPane_2.setViewportView(error);
		frame.getContentPane().add(scrollPane_2);
		

		comments = new JTable(mngr3);
		DefaultTableCellRenderer headerRenderer2 = new DefaultTableCellRenderer();
		headerRenderer2.setBackground(new Color(46, 239, 137));
		for (int i = 0; i < comments.getModel().getColumnCount(); i++) {
			comments.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer2);
		}
		comments.setFont(new Font("Calibri", Font.PLAIN, 18));
		comments.setForeground(Color.DARK_GRAY);

		comments.setPreferredScrollableViewportSize(new Dimension(450, 63));

		comments.setFillsViewportHeight(true);
		comments.setBorder(new LineBorder(Color.DARK_GRAY));
		comments.setBounds(265, 132, 194, 235);
		
//		scrollPane_2 = new JScrollPane();
//		scrollPane_2.setBounds(562, 112, 117, 461);
//		scrollPane_2.setViewportView(error);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(699, 112, 273, 461);
		scrollPane_3.setViewportView(comments);
		frame.getContentPane().add(scrollPane_3);
		
		lblLexicalErrors = new JLabel("Lexical Errors");
		lblLexicalErrors.setHorizontalAlignment(SwingConstants.CENTER);
		lblLexicalErrors.setForeground(Color.DARK_GRAY);
		lblLexicalErrors.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblLexicalErrors.setBounds(562, 67, 117, 45);
		frame.getContentPane().add(lblLexicalErrors);
		
		lblComments = new JLabel("Comments");
		lblComments.setHorizontalAlignment(SwingConstants.CENTER);
		lblComments.setForeground(Color.DARK_GRAY);
		lblComments.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblComments.setBounds(699, 67, 183, 45);
		frame.getContentPane().add(lblComments);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit.setIcon(new ImageIcon("D:\\Documents\\School\\Compiler\\CompilerProject\\CompilerProject\\src\\Assets\\Cancel-40.png"));
		exit.setForeground(Color.DARK_GRAY);
		exit.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		exit.setBounds(772, 584, 200, 45);
		frame.getContentPane().add(exit);

	}

}
