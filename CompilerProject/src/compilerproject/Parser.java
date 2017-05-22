package compilerproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Mohammed Issa & Mohammed Ideas.
 */
public class Parser {

	Stack<String> Stk;
	ArrayList<Token> input;
	Productions Prod;
	Token [] tokens;
	int cnt = 0;
	public static String stack;

	public Parser()
	{




		Prod = new Productions();
		Stk = new Stack<String>();
		input = Scanner.tokens;
		Iterator<Token> Input = input.iterator();
		tokens = new Token[input.size()];
		for(int i =0;i<tokens.length;i++){
			tokens[i]=Input.next();
			System.out.println(tokens[i].name+"  "+tokens[i].type);
		}
		Stk.push("prog-decl");



		while(!Stk.isEmpty() && cnt < tokens.length)
		{
			stack+=Stk.peek().trim()+"\n";

			if (Prod.getIndexOfTerminal(Stk.peek().trim()) >= 0){

				if (Stk.peek().equals(tokens[cnt].type)){
					cnt++;
					Stk.pop();
				}
				else
					try {

						throw new NullPointerException("mismatch Error at line: " + (tokens[cnt].getLineNumber()) + " Expected: " + Stk.peek() + " not " + tokens[cnt].getTokenString().trim());
					}
					catch (NullPointerException e){
						System.err.print(e.fillInStackTrace());
						System.exit(1);
					}

			}

			else {

				System.out.println(Prod.getIndexOfNonterminal(Stk.peek().trim()) + "	"+Stk.peek().trim());
				System.out.println(Prod.getIndexOfTerminal(tokens[cnt].type) + "	"+tokens[cnt].type);


				String prod = Prod.getProductionValue(Prod.getIndexOfNonterminal(Stk.peek().trim()),Prod.getIndexOfTerminal(tokens[cnt].type));

				System.out.println(prod);
				if (prod.equals("error"))

					try{
							throw new NullPointerException("parsing Error at line: "+(tokens[cnt].getLineNumber()) +" Expected: "+Stk.peek()+" not "+tokens[cnt].getTokenString().trim());

					}
					catch (NullPointerException e){
						System.err.print(e.fillInStackTrace());
						System.exit(1);
					}


				PushS(prod);


			}


		}


		System.out.println("======= No Errors ========");
		System.exit(0);

	}
	private void PushS(String productionValue) {
		// TODO Auto-generated method stub
		Stk.pop();

		if (productionValue.equalsIgnoreCase("lambda"))
			return;

		String []array = productionValue.split(" ");
		if (array.length>0)
			for(int i = array.length-1;i>=0;i--)
			{
				Stk.push(array[i].trim());
			}
	}
}