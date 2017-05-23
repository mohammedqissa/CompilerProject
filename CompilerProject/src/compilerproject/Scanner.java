package compilerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


/**
 *
 * Created by Mohammed Issa & Mohammed Ideas
 */

public class Scanner {


    static ArrayList<String> consts = new ArrayList<>();
    static ArrayList<String> vars = new ArrayList<>();
    static List<String> input ;
    static int flag =-1;

    static int PROGRAM=0,CONST=1,VAR=2,BEGIN=3;

    static ArrayList<String> ReservedWords = new ArrayList<>();
    static ArrayList<String> sympols = new ArrayList<>();
    // all tokens before cut
    static ArrayList<Token> tokens = new ArrayList<>();
    // all tokens after cut
    static Token[] tokensar;
    File file;


    public Scanner(File file) {
        this.file = file;

        fillReservedWords();
        fillSympols();
        readLines();

        // this is to read the tokens that holds more than one digit. e.g. .. , := , >= ... etc.
        tokensar = new Token[tokens.size()];
        for (int i = 0; i < tokensar.length; i++) {
            tokensar[i] = tokens.get(i);
        }
        int cut =0;

        for (int i = 0; i < tokensar.length-cut-1; i++) {
            if (tokensar[i].type.equals("."))
                if (tokensar[i+1].type.equals(".")){
                    tokensar[i].type="..";
                    cut++;
                    for (int j = i+1; j < tokensar.length-1; j++) {
                        tokensar[j]=tokensar[j+1];
                    }
                }

            if (tokensar[i].type.equals(":"))
                if (tokensar[i+1].type.equals("=")){
                    tokensar[i].type=":=";
                    cut++;
                    for (int j = i+1; j < tokensar.length-1; j++) {
                        tokensar[j]=tokensar[j+1];
                    }
                }

            if (tokensar[i].type.equals(">"))
                if (tokensar[i+1].type.equals("=")){
                    tokensar[i].type=">=";
                    cut++;
                    for (int j = i+1; j < tokensar.length-1; j++) {
                        tokensar[j]=tokensar[j+1];
                    }
                }
            if (tokensar[i].type.equals("<"))
                if (tokensar[i+1].type.equals("=")){
                    tokensar[i].type="<=";
                    cut++;
                    for (int j = i+1; j < tokensar.length-1; j++) {
                        tokensar[j]=tokensar[j+1];
                    }
                }

            if (tokensar[i].type.equals("<"))
                if (tokensar[i+1].type.equals(">")){
                    tokensar[i].type="<>";
                    cut++;
                    for (int j = i+1; j < tokensar.length-1; j++) {
                        tokensar[j]=tokensar[j+1];
                    }
                }

        }

        tokens = new ArrayList<>();

        for (int i = 0; i < tokensar.length-cut+1; i++) {
            tokens.add(tokensar[i]);
        }



    }



    public static void main(String[] args){

		String number = "\\d*\\.?\\d*";


		String comment = "\\(\\*(\\p{ASCII})*\\*\\)|\\{(\\p{ASCII})*}";

		String q = "(\\p{ASCII})*\\(\\)(\\p{ASCII})*";

		String haveEq = "(\\p{ASCII})*=(\\p{ASCII})*";

		Pattern pattern = Pattern.compile(haveEq);


        StringTokenizer tokenizer = new StringTokenizer("6=1.3","=:",true);
		System.out.println(pattern.matcher("var:4").matches() );
		System.out.println(new StringTokenizer("y:=1.3","=:",true).countTokens());
		System.out.println(tokenizer.countTokens());
        System.out.println(new StringTokenizer("end.",".",true).countTokens());


    }


    public void readLines() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            input = new ArrayList<>();
            java.util.Scanner sc = new java.util.Scanner(fileReader);
            while(sc.hasNext()){
            	input.add(sc.nextLine()+"\n");
            }
			sc.close();


            String special ="['<','>',';',',','(',')','+','-','*','/',' ','[',']']";

            for (int i = 0; i < input.size(); i++) {

                String tokensLine = input.get(i);


                if (tokensLine.compareTo("") != 0) {
                    StringTokenizer stringTokenizer = new StringTokenizer(tokensLine, special, true);

                    while (stringTokenizer.hasMoreTokens()) {

                        String a = stringTokenizer.nextToken().trim();


                        if (a.length() > 0) {
                            Token token = new Token(a,i,-1);
                            token.setType();
                            tokens.add(token);
                        }

                    }
                }
            }



        } catch (FileNotFoundException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void fillReservedWords() {

        ReservedWords.add("program");
        ReservedWords.add("begin");
        ReservedWords.add("end");
        ReservedWords.add("const");
        ReservedWords.add("var");
        ReservedWords.add("integer");
        ReservedWords.add("real");
        ReservedWords.add("char");
        ReservedWords.add("array");
        ReservedWords.add("mod");
        ReservedWords.add("div");
        ReservedWords.add("read");
        ReservedWords.add("readln");
        ReservedWords.add("write");
        ReservedWords.add("writeln");
        ReservedWords.add("if");
        ReservedWords.add("then");
        ReservedWords.add("else");
        ReservedWords.add("while");
        ReservedWords.add("do");
        ReservedWords.add("repeat");
        ReservedWords.add("until");


    }

    private void fillSympols(){

	    sympols.add(".");
        sympols.add(";");
        sympols.add("=");
        sympols.add(":");
        sympols.add(",");
        sympols.add("..");
        sympols.add(":=");
        sympols.add("(");
        sympols.add(")");
        sympols.add("+");
        sympols.add("-");
        sympols.add("*");
        sympols.add("/");
        sympols.add("<>");
        sympols.add("<");
        sympols.add("<=");
        sympols.add(">");
        sympols.add(">=");
        sympols.add("[");
        sympols.add("]");

    }


}
