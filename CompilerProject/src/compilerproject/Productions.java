package compilerproject;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Productions {

    Prod_Array[] product = new Prod_Array[69];
	String[][] table;
	String[] terminals ,nonterminals;

	// the grammar
	public Productions(){

		product[ 0 ] = new Prod_Array("error" , "error");
		product[ 1 ] = new Prod_Array("prog-decl" , "heading declarations block .");
		product[ 2 ] = new Prod_Array("heading" , "program program-name ;");
		product[ 3 ] = new Prod_Array("block" , "begin stmt-list end");
		product[ 4 ] = new Prod_Array("declarations" , "const-decl var-decl");
		product[ 5 ] = new Prod_Array("const-decl" , "const const-list");
		product[ 6 ] = new Prod_Array("const-decl" , "lambda");
		product[ 7 ] = new Prod_Array("const-list" , "const-name = value ; const-list");
		product[ 8 ] = new Prod_Array("const-list" , "lambda");
		product[ 9 ] = new Prod_Array("var-decl" , "var var-list");
		product[ 10 ] = new Prod_Array("var-decl" , "lambda");
		product[ 11 ] = new Prod_Array("var-list" , "var-item ; var-list");
		product[ 12 ] = new Prod_Array("var-list" , "lambda");
		product[ 13 ] = new Prod_Array("var-item" , "name-list : data-type");
		product[ 14 ] = new Prod_Array("name-list" , "var-name more-names");
		product[ 15 ] = new Prod_Array("more-names" , ", name-list");
		product[ 16 ] = new Prod_Array("more-names" , "lambda");
		product[ 17 ] = new Prod_Array("data-type" , "integer");
		product[ 18 ] = new Prod_Array("data-type" , "real");
		product[ 19 ] = new Prod_Array("data-type" , "char");
		product[ 20 ] = new Prod_Array("data-type" , "array [ range ]");
		product[ 21 ] = new Prod_Array("range" , "int-value .. int-value");
		product[ 22 ] = new Prod_Array("stmt-list" , "statement ; stmt-list");
		product[ 23 ] = new Prod_Array("stmt-list" , "lambda");
		product[ 24 ] = new Prod_Array("statement" , "ass-stmt");
		product[ 25 ] = new Prod_Array("statement" , "read-stmt");
		product[ 26 ] = new Prod_Array("statement" , "write-stmt");
		product[ 27 ] = new Prod_Array("statement" , "if-stmt");
		product[ 28 ] = new Prod_Array("statement" , "while-stmt");
		product[ 29 ] = new Prod_Array("statement" , "repeat-stmt");
		product[ 30 ] = new Prod_Array("statement" , "block");
		product[ 31 ] = new Prod_Array("ass-stmt" , "var-name := exp");
		product[ 32 ] = new Prod_Array("exp" , "term exp-prime");
		product[ 33 ] = new Prod_Array("exp-prime" , "add-sign term exp-prime");
		product[ 34 ] = new Prod_Array("exp-prime" , "lambda");
		product[ 35 ] = new Prod_Array("term" , "factor term-prime");
		product[ 36 ] = new Prod_Array("term-prime" , "mul-sign factor term-prime");
		product[ 37 ] = new Prod_Array("term-prime" , "lambda");
		product[ 38 ] = new Prod_Array("factor" , "( exp )");
		product[ 39 ] = new Prod_Array("factor" , "var-name");
		product[ 40 ] = new Prod_Array("factor" , "const-name");
		product[ 41 ] = new Prod_Array("factor" , "value");
		product[ 42 ] = new Prod_Array("add-sign" , "+");
		product[ 43 ] = new Prod_Array("add-sign" , "-");
		product[ 44 ] = new Prod_Array("mul-sign" , "*");
		product[ 45 ] = new Prod_Array("mul-sign" , "/");
		product[ 46 ] = new Prod_Array("mul-sign" , "mod");
		product[ 47 ] = new Prod_Array("mul-sign" , "div");
		product[ 48 ] = new Prod_Array("value" , "float-value");
		product[ 49 ] = new Prod_Array("value" , "int-value");
		product[ 50 ] = new Prod_Array("read-stmt" , "read ( name-list )");
		product[ 51 ] = new Prod_Array("read-stmt" , "readln ( name-list )");
		product[ 52 ] = new Prod_Array("write-stmt" , "write ( name-list )");
		product[ 53 ] = new Prod_Array("write-stmt" , "writeln ( name-list )");
		product[ 54 ] = new Prod_Array("if-stmt" , "if condition then statement else-part");
		product[ 55 ] = new Prod_Array("else-part" , "else statement");
		product[ 56 ] = new Prod_Array("else-part" , "lambda");
		product[ 57 ] = new Prod_Array("while-stmt" , "while condition do statement");
		product[ 58 ] = new Prod_Array("repeat-stmt" , "repeat stmt-list until condition");
		product[ 59 ] = new Prod_Array("condition" , "name-value relational-oper name-value");
		product[ 60 ] = new Prod_Array("name-value" , "var-name");
		product[ 61 ] = new Prod_Array("name-value" , "const-name");
		product[ 62 ] = new Prod_Array("name-value" , "value");
		product[ 63 ] = new Prod_Array("relational-oper" , "=");
		product[ 64 ] = new Prod_Array("relational-oper" , "<>");
		product[ 65 ] = new Prod_Array("relational-oper" , "<");
		product[ 66 ] = new Prod_Array("relational-oper" , "<=");
		product[ 67 ] = new Prod_Array("relational-oper" , ">");
		product[ 68 ] = new Prod_Array("relational-oper" , ">=");
		
		// reads the table from scv.
		try {
			table = read_table();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// due to problem in CSV reader.
		table[0][8] = ",";

		terminals = table[0];

		nonterminals = new String[table.length];

		for (int i = 0; i < nonterminals.length ; i++) {
			nonterminals[i] = table[i][0];
		}

	}


	public String getProductionValue(int nonTermeinal,int Termeinal){
		String prod = table[nonTermeinal][Termeinal];
		if (prod.equals("error"))
			return "error";
		return product[Integer.parseInt(prod)].getDest();
	}

	public int getIndexOfNonterminal(String non){

		for (int i = 0; i <nonterminals.length ; i++) {
			if (nonterminals[i].equalsIgnoreCase(non))
				return i;
		}
		return -1;

	}

	public int getIndexOfTerminal(String non){

		for (int i = 0; i < terminals.length; i++) {
			if (terminals[i].equalsIgnoreCase(non))
				return i;
		}
		return -1;

	}


	public String[][] read_table() throws IOException {

		CSVReader csvReader = new CSVReader(new FileReader(new File("CompilerProject/table.csv")));
		List<String[]> list = csvReader.readAll();

		// Convert to 2D array
		String[][] dataArr = new String[list.size()][];
		dataArr = list.toArray(dataArr);

		return dataArr;

	}
}
