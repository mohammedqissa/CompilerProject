package compilerproject;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by Mohammed Issa & Mohammed Ideas.
 */

public class Token {

    String name ;
    String type;
    int line,id;


    public Token(String name, int line , int id) {
        this.name = name;
        this.line = line;


        if (id != -1) {
            this.id = getId();
        }
        else
            this.id = id;
    }

    public void setType(){
        String type = getType();
        if (type != null){
            this.type=type;
        }
        else
            throw new NullPointerException("Not Recognized Name: "+this.name+" at line: "+(line+1));
    }

    private String getType() {

        String number = "\\d+\\.?\\d*";

        Pattern pattern = Pattern.compile(number);

        boolean is_num = pattern.matcher(this.name).matches();

        if (is_num){
            String intval = "\\d+";
            Pattern pat = Pattern.compile(intval);
            if (pat.matcher(this.name).matches())
                return "int-value";
            else
                return "float-value";
        }
        else {

            String haveEq = "(\\p{ASCII})*=(\\p{ASCII})*";
            Pattern pat = Pattern.compile(haveEq);
            String haveDot = "(\\p{ASCII})*:(\\p{ASCII})*";
            Pattern pat2 = Pattern.compile(haveDot);
            if (pat.matcher(this.name).matches()){

                StringTokenizer tokenizer = new StringTokenizer(this.name,"=:",true);

                if (tokenizer.countTokens()>1){

                    List<String> toks = new ArrayList<>();

                    while (tokenizer.hasMoreTokens()){

                        toks.add(tokenizer.nextToken());
                    }

                    this.name = toks.get(toks.size()-1);


                    for (int i = 0; i < toks.size()-1 ; i++) {

                        Token token = new Token(toks.get(i),this.line,-1);
                        token.setType();
                        Scanner.tokens.add(token);
                    }

                    if (pattern.matcher(this.name).matches()){
                        String intval = "\\d+";
                        Pattern patt = Pattern.compile(intval);
                        if (patt.matcher(this.name).matches())
                            return "int-value";
                        else
                            return "float-value";
                    }
                }


            }

            if (pat2.matcher(this.name).matches()){

                StringTokenizer tokenizer = new StringTokenizer(this.name,":",true);

                if (tokenizer.countTokens()>1){

                    List<String> toks = new ArrayList<>();

                    while (tokenizer.hasMoreTokens()){

                        toks.add(tokenizer.nextToken());
                    }

                    this.name = toks.get(toks.size()-1);


                    for (int i = 0; i < toks.size()-1 ; i++) {

                        Token token = new Token(toks.get(i),this.line,-1);
                        token.setType();
                        Scanner.tokens.add(token);
                    }

                    if (pattern.matcher(this.name).matches()){
                        String intval = "\\d+";
                        Pattern patt = Pattern.compile(intval);
                        if (patt.matcher(this.name).matches())
                            return "int-value";
                        else
                            return "float-value";
                    }
                }


            }

            if (!pattern.matcher(this.name).matches()){

                StringTokenizer tokenizer = new StringTokenizer(this.name,".",true);

                if (tokenizer.countTokens()>1){

                    List<String> toks = new ArrayList<>();

                    while (tokenizer.hasMoreTokens()){

                        toks.add(tokenizer.nextToken());
                    }

                    this.name = toks.get(toks.size()-1);


                    for (int i = 0; i < toks.size()-1 ; i++) {

                        Token token = new Token(toks.get(i),this.line,-1);
                        token.setType();
                        Scanner.tokens.add(token);
                    }


                }
            }

            if (pattern.matcher(this.name).matches()){
                String intval = "\\d+";
                Pattern patt = Pattern.compile(intval);
                if (patt.matcher(this.name).matches())
                    return "int-value";
                else
                    return "float-value";
            }


            if (this.name.equalsIgnoreCase("program")){
                Scanner.flag=Scanner.PROGRAM;
            }else if (this.name.equalsIgnoreCase("const")){
                Scanner.flag=Scanner.CONST;
            }else if (this.name.equalsIgnoreCase("var")){
                Scanner.flag=Scanner.VAR;
            }else if (this.name.equalsIgnoreCase("begin")){
                Scanner.flag=Scanner.BEGIN;
            }


            if (Scanner.ReservedWords.contains(this.name)){
                return this.name;
            }
            else if (Scanner.sympols.contains(this.name)){
                return this.name;
            }
            else if(Character.isAlphabetic(this.name.charAt(0))) {
                switch (Scanner.flag){

                    case 0:{
                        return "program-name";
                    }
                    case 1:{
                        Scanner.consts.add(this.name);
                        return "const-name";
                    }
                    case 2:{
                        Scanner.vars.add(this.name);
                        return "var-name";
                    }
                    case 3:{

                        if (Scanner.consts.contains(this.name))
                            return "const-name";
                        else if (Scanner.vars.contains(this.name))
                            return "var-name";
                        else
                            return null;

                    }


                }
            }


        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String toString(){

        return "Token: "+this.name+" , type: "+this.type+" , id: "+this.id;

    }

    public String getTokenString() {
        return this.type;
    }

    public int getLineNumber() {
        return this.line+1;
    }
}
