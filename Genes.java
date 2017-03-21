import java.util.*;
import java.math.*;

public class Genes 
{
	double bonus;
	String name;
	public Genes(String name, double bonus)
	{
		this.bonus = bonus;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBonus()
	{
		return bonus;
	}
	public String toString()
	{
		return "[" + name + ": " + Math.round((bonus*10))/10 + "]";
	}
}
