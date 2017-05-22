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
		try {
			table = read_table();
		} catch (IOException e) {
			e.printStackTrace();
		}

		table[0][8] = ",";


		terminals = table[0];

		nonterminals = new String[table.length];

		for (int i = 0; i < nonterminals.length ; i++) {
			nonterminals[i] = table[i][0];
		}

//		for(int i =0;i<34;i++)
//			for(int j=0;j<40;j++)
//				table[i][j] = product[0];
//		//filing parsing table
//
//		//prog-decl
//		table[1][2]=product[1];
//		table[1][7]=product[1];
//		table[1][3]=product[1];
//
//		//lib-decl
//		table[2][2]=product[2];
//		table[2][7]=product[3];
//
//		//heading
//		table[4][7]=product[4];
//		//block
//		table[5][8]=product[5];
//		//declarations
//		table[6][8]=product[6];
//		table[6][10]=product[6];
//		table[6][15]=product[6];
//		table[6][16]=product[6];
//		table[6][17]=product[6];
//		//const-decl
//		table[7][8]=product[8];
//		table[7][10]=product[7];
//		table[7][15]=product[8];
//		table[7][16]=product[8];
//		table[7][17]=product[8];
//		//var-decl
//		table[8][8]=product[10];
//		table[8][15]=product[9];
//		table[8][16]=product[9];
//		table[8][17]=product[9];
//		//name-list
//		table[9][13]=product[11];
//		//more-names
//		table[10][6]=product[13];
//		table[10][11]=product[13];
//		table[10][13]=product[13];
//		table[10][14]=product[12];
//		table[10][30]=product[13];
//		table[10][31]=product[13];
//		table[10][32]=product[13];
//		table[10][30]=product[13];
//		//data-type
//		table[11][15]=product[14];
//		table[11][16]=product[15];
//		table[11][17]=product[16];
//		//stmt-list
//		table[12][6]=product[17];
//		table[12][8]=product[17];
//		table[12][9]=product[18];
//		table[12][13]=product[17];
//		table[12][32]=product[17];
//		table[12][31]=product[17];
//		table[12][2]=product[17];
//		table[12][12]=product[17];
//		table[12][13]=product[17];
//		table[12][12]=product[18];
//		table[12][16]=product[17];
//		table[12][15]=product[17];
//		//statement
//		table[13][6]=product[26];
//		table[13][8]=product[25];
//		table[13][11]=product[26];
//		table[13][13]=product[19];
//		table[13][30]=product[26];
//		table[13][31]=product[26];
//		table[13][32]=product[20];
//		table[13][23]=product[20];
//		table[13][21]=product[21];
//		table[13][22]=product[26];
//		table[13][23]=product[26];
//		table[13][12]=product[22];
//		table[13][32]=product[24];
//		table[13][31]=product[23];
//		table[13][23]=product[23];
//		//ass-stmt
//		table[14][13]=product[27];
//		//ass-oper
//		table[15][12]=product[28];
//		table[15][18]=product[29];
//		table[15][19]=product[30];
//		table[15][20]=product[31];
//		table[15][21]=product[32];
//		table[15][22]=product[33];
//		//exp
//		table[16][11]=product[34];
//		table[16][13]=product[34];
//		table[16][23]=product[34];
//		table[16][30]=product[34];
//		table[16][31]=product[34];
//		//exp-prime
//		table[17][6]=product[36];
//		table[17][11]=product[36];
//		table[17][13]=product[36];
//		table[17][24]=product[36];
//		table[17][25]=product[35];
//		table[17][26]=product[35];
//		table[17][30]=product[36];
//		table[17][31]=product[36];
//		table[17][23]=product[36];
//		table[17][21]=product[36];
//		//term
//		table[18][11]=product[37];
//		table[18][13]=product[37];
//		table[18][23]=product[37];
//		table[18][30]=product[37];
//		table[18][31]=product[37];
//		//term-prime
//		table[19][6]=product[39];
//		table[19][11]=product[39];
//		table[19][13]=product[39];
//		table[19][24]=product[39];
//		table[19][25]=product[39];
//		table[19][26]=product[39];
//		table[19][27]=product[38];
//		table[19][28]=product[38];
//		table[19][29]=product[38];
//		table[19][30]=product[39];
//		table[19][31]=product[39];
//		table[19][12]=product[39];
//		table[19][12]=product[39];
//		//factor
//		table[20][11]=product[42];
//		table[20][13]=product[41];
//		table[20][23]=product[40];
//		table[20][30]=product[43];
//		table[20][31]=product[43];
//		//add-sign
//        table[21][25]=product[44];
//        table[21][26]=product[45];
//        //mul-sign
//		table[22][27]=product[46];
//		table[22][28]=product[47];
//		table[22][29]=product[48];
//		//value
//		table[23][30]=product[49];
//        table[23][31]=product[50];
//        //inout-stmt
//        table[24][32]=product[51];
//		table[24][11]=product[52];
//		//if-stmt
//		table[25][11]=product[53];
//		//else-part
//		table[26][11]=product[55];
//		table[26][11]=product[54];
//		//while-stmt
//        table[27][11]=product[56];
//        //repeat-stmt
//        table[28][11]=product[57];
//        //inc-stmt
//		table[29][11]=product[58];
//		table[29][11]=product[58];
//		//inc-oper
//		table[30][11]=product[59];
//		table[30][11]=product[60];
//		//bool-exp
//        table[31][11]=product[61];
//        table[31][13]=product[61];
//		table[31][30]=product[61];
//		table[31][31]=product[61];
//		//name-value
//		table[32][11]=product[63];
//		table[32][13]=product[62];
//        table[32][30]=product[64];
//        table[32][31]=product[64];
//        //relation-oper
//		table[33][3]=product[67];
//		table[33][5]=product[61];
//		table[33][11]=product[65];
//		table[33][11]=product[66];
//        table[33][11]=product[62];
//        table[33][11]=product[23];
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

		CSVReader csvReader = new CSVReader(new FileReader(new File("/Users/mohammedissa/Desktop/CompilerProject/CompilerProject/table.csv")));
		List<String[]> list = csvReader.readAll();

		// Convert to 2D array
		String[][] dataArr = new String[list.size()][];
		dataArr = list.toArray(dataArr);

		return dataArr;

	}
}
