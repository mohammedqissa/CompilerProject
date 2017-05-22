package compilerproject;

public class Prod_Array {

    private String From;
	private String To;
	public Prod_Array(String From,String To)
	{
		this.From = From;
		this.To = To;
	}
	public String getDest()
	{
		return To;
	}
	public String getSource()
	{
		return From;
	}
}
