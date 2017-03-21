import java.util.*;
import java.math.*;

public class Buddy 
{
	private final String[] atributes = {"Str","Con","Cha","Wis","Dex","Int"};
	private final String[] letters = {"A","B","C","D","E","F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	private Random rand = new Random();
	
	private ArrayList<Genes> stat = new ArrayList();
	private ArrayList<Genes> wants = new ArrayList();
	private ArrayList<Buddy> lovers = new ArrayList();
	
	private String name = "";
	
	
	public Buddy()
	{
		name = letters[rand.nextInt(letters.length)] + letters[rand.nextInt(letters.length)] + letters[rand.nextInt(letters.length)];
		System.out.println("Name: " + name);
		for(int i = 0; i < atributes.length; i++)
		{
			stat.add(new Genes(atributes[i], rand.nextInt(100)/10));
			System.out.print(stat.get(stat.size()-1).toString());
		}
		
			/*int lv1 = rand.nextInt(6);
			wants.add(new Genes(atributes[lv1], stat.get(lv1).getBonus()));
			int lv2 = rand.nextInt(6);
			while(lv2 == lv1)
			{
				lv2 = rand.nextInt(6);
			}
			wants.add(new Genes(atributes[lv2], stat.get(lv2).getBonus()));
			int lv3 = rand.nextInt(6);
			while(lv3 == lv1 || lv3 == lv2)
			{
				lv3 = rand.nextInt(6);
			}
			wants.add(new Genes(atributes[lv3], stat.get(lv3).getBonus()));
			
			System.out.print("Survival: " + this.getTotalStats() + "\nLoves:");
			
			double lv = this.getStats().get(0).getBonus()*2 + this.getStats().get(1).getBonus()*1.5 + this.getStats().get(2).getBonus();
			
			System.out.print(wants.get(wants.size()-1).toString());
			System.out.print(wants.get(wants.size()-2).toString());
			System.out.print(wants.get(wants.size()-3).toString() + "Love:" + lv);
			*/
		System.out.print("\nSurvival: " + this.getSurvival());
		System.out.print(" Attraction : " + this.getAttraction());
		System.out.print(" Stats : " + this.getTotalStats());
		System.out.println("\n===================================");

	}
	public Buddy(String name, int str, int con, int cha, int wis, int dex, int Int)
	{
		this.name = name;
		System.out.println("Name: " + name);
	
		stat.add(new Genes(atributes[0], str));
		System.out.print(stat.get(stat.size()-1).toString());
		stat.add(new Genes(atributes[1], con));
		System.out.print(stat.get(stat.size()-1).toString());
		stat.add(new Genes(atributes[2], cha));
		System.out.print(stat.get(stat.size()-1).toString());
		stat.add(new Genes(atributes[3], wis));
		System.out.print(stat.get(stat.size()-1).toString());
		stat.add(new Genes(atributes[4], dex));
		System.out.print(stat.get(stat.size()-1).toString());
		stat.add(new Genes(atributes[5], Int));
		System.out.print(stat.get(stat.size()-1).toString());
			
		System.out.print("\nSurvival: " + this.getSurvival());
		System.out.print(" Attraction : " + this.getAttraction());
		System.out.print(" Stats : " + this.getTotalStats());
		System.out.println("\n===================================");

	}
	
	public String getName()
	{
		return name;
	}
	
	public Buddy(Buddy p1, Buddy p2)
	{
		String parentName = (p1.getName() +"&"+ p2.getName());
		name = name = letters[rand.nextInt(letters.length)] + letters[rand.nextInt(letters.length)] + letters[rand.nextInt(letters.length)];
		System.out.println(parentName + "'s child's name: " + name);
		for(int i = 0; i < atributes.length; i++)
		{
			double diffrence = ((int) (Math.abs(Math.round(p1.getStats().get(i).getBonus() - p2.getStats().get(i).getBonus())))*100)/100;
			if(rand.nextInt(10) == 1)
			{
			stat.add(new Genes(atributes[i], ((p1.getStats().get(i).getBonus() + p2.getStats().get(i).getBonus())/2) + (rand.nextInt(102)/10)-5));
			System.out.print(stat.get(stat.size()-1).toString());
			}
			else
			{
			stat.add(new Genes(atributes[i],  (rand.nextInt(42)/10)-2 + ((p1.getStats().get(i).getBonus() + p2.getStats().get(i).getBonus())/2) + (((rand.nextInt(2)*2) -1)*(diffrence)*((rand.nextInt(101))/100))/2));
			System.out.print((stat.get(stat.size()-1).toString()));
			}
		}
		/*
		System.out.print("Survival: " + this.getTotalStats() + "\nLoves:");
		double lv = this.getStats().get(0).getBonus()*2 + this.getStats().get(1).getBonus()*1.5 + this.getStats().get(2).getBonus();
		
			wants.addAll(p1.getWants());
			wants.addAll(p2.getWants());
			int r = rand.nextInt(6);
			wants.add(new Genes(atributes[r], this.getStats().get(r).getBonus()));
			wants.add(new Genes(atributes[r], this.getStats().get(r).getBonus()));
			while(wants.size() > 3)
			{
				wants.remove(rand.nextInt(wants.size()));
			}
			
			System.out.print(wants.get(wants.size()-1).toString());
			System.out.print(wants.get(wants.size()-2).toString());
			System.out.print(wants.get(wants.size()-3).toString() + "Love:" + lv);
			*/
		System.out.print("\nSurvival: " + this.getSurvival());
		System.out.print(" Attraction : " + this.getAttraction());
		System.out.print(" Stats : " + this.getTotalStats());
		
		System.out.println("\n===================================");
		
	}
	
	public ArrayList<Genes> getStats()
	{
		return stat;
	}
	
	public double getTotalStats()
	{
		double survival = 0;
		for(int i = 0; i< stat.size(); i++)
		{
			survival += stat.get(i).getBonus();
		}
		return ((double)Math.round(survival*100))/100;
	}
	
	public double getSurvival()
	{
		double[] survivalValues = {1, 0.25, 0, 0.75, 1, 0.5};
		double survival = 0;
		for(int i = 0; i< stat.size(); i++)
		{
			survival += (stat.get(i).getBonus()*survivalValues[i]);
		}
		return ((double)Math.round(survival*100))/100;
	}
	
	public double getAttraction()
	{
		double[] survivalValues = {0.25, 0.5, 1, 0, 0.25, 0.25};
		double survival = 0;
		for(int i = 0; i< stat.size(); i++)
		{
			survival += (stat.get(i).getBonus()*survivalValues[i]);
		}
		return ((double)Math.round(survival*100))/100;
	}
	
	public ArrayList<Genes> getWants()
	{
		return wants;
	}
	
	public boolean lovesBack(Buddy date)
	{
		if(!lovers.contains(date))
		{
			return false;
		}
		if(lovers.size() <= 3)
		{
			return true;
		}
		return (lovers.get(0).equals(date) || lovers.get(1).equals(date) || lovers.get(2).equals(date));
	}
	
	public ArrayList<Buddy> getLovers()
	{
		return lovers;
	}
	
	public void lostLove(Buddy brad)
	{
		lovers.remove(brad);
	}
	
	public void findMate(ArrayList<Buddy> avalable)//Adding love levels and mating
	{
		ArrayList<Buddy> choices = new ArrayList();
		choices.addAll(avalable);
		choices.remove(this);
		while(choices.size() != 0)
		{
			Buddy lover = choices.get(0);
			double loveLevel = 0;
			double highLoveLevel = 0;
			for(int i = 0; i < choices.size(); i++)
			{
				loveLevel += (choices.get(i).getStats().get(0).getBonus())*2;
				loveLevel += (choices.get(i).getStats().get(1).getBonus())*1.5;
				loveLevel += (choices.get(i).getStats().get(2).getBonus())*1;
				if(loveLevel > highLoveLevel)
				{
					lover = choices.get(i);
					highLoveLevel = loveLevel;
				}
				loveLevel = 0;
			}
			lovers.add(lover);
			choices.remove(lover);
		}
		
	}
}
