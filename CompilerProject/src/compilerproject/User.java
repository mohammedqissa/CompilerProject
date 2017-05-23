package compilerproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
	static protected JTextArea error;
	private Scanner scanner;
	static String[] header = { "Token", "ID", "Type" };
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
	private JLabel lblLexicalErrors;
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

				int count = 1 ;
				for ( int i = 0 ; i < Scanner.consts.size() ; i++){
					Vector<String> v = new Vector<>();

					v.add(Scanner.consts.get(i));
					v.add(count+"");
					count++;
					for ( int k = 0 ; k <  Scanner.tokens.size() - 1 ; k++){
						if( Scanner.consts.get(i).equalsIgnoreCase(Scanner.tokens.get(k).name)){
							
							v.add(Scanner.tokens.get(k).type);
						}
					}
					mngr1.addRow(v);
				}
				
				for ( int i = 0 ; i < Scanner.vars.size() ; i++){
					Vector<String> v = new Vector<>();

					v.add(Scanner.vars.get(i));
					v.add(count+"");
					count++;
					for ( int k = 0 ; k <  Scanner.tokens.size() - 1 ; k++){
						if( Scanner.vars.get(i).equalsIgnoreCase(Scanner.tokens.get(k).name)){
							v.add(Scanner.tokens.get(k).type);
						}
					}
					mngr1.addRow(v);
				}
				
				count = 1;
				for ( int i = 0 ; i < Scanner.tokens.size() ; i++){
					Vector<String> v = new Vector<>();

					v.add(Scanner.tokens.get(i).name);
					v.add(count+"");
					count++;
					
					String spec = "['<','>',';',',','(',')','+','-','*','/',' ','[',']',':=','..','.','=']";
					
					String type = Scanner.tokens.get(i).type;
					 Pattern pat = Pattern.compile(spec);
					if ( pat.matcher(type).matches()){
						type = "Special Symbole";
					}
					
					v.add(type);
					mngr.addRow(v);
				}
				
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
		scrollPane.setBounds(10, 112, 277, 461);
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
		scrollPane_1.setBounds(297, 112, 255, 461);
		scrollPane_1.setViewportView(SymbolTable);
		frame.getContentPane().add(scrollPane_1);
		
		lblSymbolTable = new JLabel("Symbol table");
		lblSymbolTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblSymbolTable.setForeground(Color.DARK_GRAY);
		lblSymbolTable.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblSymbolTable.setBounds(250, 67, 287, 45);
		frame.getContentPane().add(lblSymbolTable);
		

		error = new JTextArea();
		error.setEnabled(false);
//		DefaultTableCellRenderer headerRenderer1 = new DefaultTableCellRenderer();
//		headerRenderer1.setBackground(new Color(239, 100, 46));

//		for (int i = 0; i < error.getModel().getColumnCount(); i++) {
//			error.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer1);
//		}
		error.setFont(new Font("Calibri", Font.PLAIN, 16));
		error.setForeground(Color.DARK_GRAY);

//		error.setPreferredScrollableViewportSize(new Dimension(450, 63));

//		error.setFillsViewportHeight(true);
		error.setBorder(new LineBorder(new Color(239, 100, 46)));
		error.setBounds(265, 132, 194, 235);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(562, 112, 400, 461);
		scrollPane_2.setViewportView(error);
		frame.getContentPane().add(scrollPane_2);
		


		
//		scrollPane_3 = new JScrollPane();
//		scrollPane_3.setBounds(699, 112, 273, 461);
//		scrollPane_3.setViewportView(comments);
//		frame.getContentPane().add(scrollPane_3);
		
		lblLexicalErrors = new JLabel(" Errors");
		lblLexicalErrors.setHorizontalAlignment(SwingConstants.CENTER);
		lblLexicalErrors.setForeground(Color.DARK_GRAY);
		lblLexicalErrors.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblLexicalErrors.setBounds(562, 67, 117, 45);
		frame.getContentPane().add(lblLexicalErrors);
		
//		lblComments = new JLabel("Comments");
//		lblComments.setHorizontalAlignment(SwingConstants.CENTER);
//		lblComments.setForeground(Color.DARK_GRAY);
//		lblComments.setFont(new Font("Calibri Light", Font.PLAIN, 18));
//		lblComments.setBounds(699, 67, 183, 45);
//		frame.getContentPane().add(lblComments);
		
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
		
		JButton parse = new JButton("Parse");
		parse.setForeground(Color.DARK_GRAY);
		parse.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		parse.setBounds(250, 29, 200, 45);
		frame.getContentPane().add(parse);
		parse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Parser parser = new Parser();
			}
		});

	}
}
