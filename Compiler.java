import java.util.*;
import java.math.*;
//feelGuilty(10/10);
//run.DoWork();
public class Compiler 
{
	private static Random rand = new Random();
	
	private static ArrayList<Double> Averages = new ArrayList();
	private static ArrayList<Integer> Generation = new ArrayList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Buddy> genePool = new ArrayList();
		ArrayList<Buddy> children = new ArrayList();
		
		String[] atributes = {"Str","Con","Cha","Wis","Dex","Int"};
		
		int startingPop = 10;
		int maxPop = 250;
		
		for(int i = 0; i < startingPop; i++)//starting 10
		{
			genePool.add(new Buddy());
		}
		//genePool.add(new Buddy("Tristan", 20,20,20,20,20,20));
		
	//for(int q = 0; q < 7; q++)
	{
		
		for(int t = 0; t < 300; t++)
		{
			int population = genePool.size();
			double totalSurvival = 0;
			double averageSurviving = 0;
			double averagelAttraction = 0;
			System.out.println("********************************************************" + t);
			System.out.print("Average:");
			for(int a = 0; a < atributes.length; a++)
			{
				double average = 0;
				for(int i = 0; i < genePool.size(); i++)//starting 10
				{
					average += genePool.get(i).getStats().get(a).getBonus();
				}
				totalSurvival += average;
				average = average/genePool.size();
				System.out.print("[" + atributes[a] + ": " + Math.round((100*average))/100 + "]");
			}
			for(int i = 0; i < genePool.size(); i++)
			{
				averageSurviving += genePool.get(i).getSurvival();
				averagelAttraction += genePool.get(i).getAttraction();
			}
			totalSurvival = totalSurvival/genePool.size();
			totalSurvival = Math.round((100*totalSurvival))/100;
			Averages.add(totalSurvival);
			Generation.add(t);
			System.out.print("\n(Population:" + genePool.size() + ")");
			System.out.print("(Surviving Average" + ": " + Math.round((100*(averageSurviving/genePool.size())))/100  + ")");
			System.out.print("(Attraction Average" + ": " + Math.round((100*(averagelAttraction/genePool.size())))/100  + ")");
			System.out.print("{Stat Average" + ": " + totalSurvival  + "}");
			System.out.println("\n********************************************************" + t);
			
			ArrayList<Buddy> orderedList = new ArrayList();//Orders by survival
			while(genePool.size() != 0)
			{
				Buddy bonusLarge = genePool.get(0);
				for(int i = 0; i < genePool.size(); i++)
				{
					if(bonusLarge.getSurvival() < genePool.get(i).getSurvival())
						{
						bonusLarge = genePool.get(i);
						}
				}
				genePool.remove(bonusLarge);
				orderedList.add(bonusLarge);
			}
			genePool.addAll(orderedList);
			orderedList.removeAll(orderedList);
			System.out.print("Deaths: ");
			int numOfChildren = 0;
			for(int i = 0; i < genePool.size(); i++)
			{
				double oddsOfLiving = i;
				oddsOfLiving = oddsOfLiving/genePool.size();
				if(rand.nextInt(100) <= oddsOfLiving*100)
				{
				System.out.print("(" + genePool.get(i).getName() + ", " + genePool.get(i).getSurvival() + ")");
				genePool.remove(i);
				numOfChildren++;
				}
			}
			System.out.print(" (" + numOfChildren + ")");
			//ArrayList<Buddy> orderedList = new ArrayList();//Orders by survival
			System.out.print("\nParents: ");
			while(genePool.size() != 0)
			{
				Buddy bonusLarge = genePool.get(0);
				for(int i = 0; i < genePool.size(); i++)
				{
					if(bonusLarge.getAttraction() < genePool.get(i).getAttraction())
						{
						bonusLarge = genePool.get(i);
						}
				}
				genePool.remove(bonusLarge);
				orderedList.add(bonusLarge);
			}
			genePool.addAll(orderedList);
			orderedList.removeAll(orderedList);
			
			numOfChildren = 0;
			ArrayList<Buddy> matting = new ArrayList();
			for(int i = 0; i < genePool.size(); i++)
			{
				double oddsOfMate = i;
				oddsOfMate = oddsOfMate/genePool.size();
				if(rand.nextInt(100) > oddsOfMate*100)
				{
				matting.add(genePool.get(i));
				numOfChildren++;
				System.out.print("(" + genePool.get(i).getName() + ", " + genePool.get(i).getAttraction() + ")");
				//numOfChildren++;
				}
			}
			System.out.print("(" + numOfChildren + ")");
			System.out.print("\n");
			
			while(matting.size() >= 2)
			{
				Buddy goGetter = matting.get(rand.nextInt(matting.size()));
				matting.remove(goGetter);
				Buddy goGotter = matting.get(rand.nextInt(matting.size()));
				matting.remove(goGotter);
				genePool.add(new Buddy(goGetter, goGotter));
				
				if(rand.nextInt(10) <= 8 && genePool.size() < startingPop)
				genePool.add(new Buddy(goGotter, goGetter));
				else if(rand.nextInt(11) <= 7)
				genePool.add(new Buddy(goGotter, goGetter));
			}
			
			if(maxPop < genePool.size())
			{
			while(genePool.size() != 0 )
			{
				Buddy bonusLarge = genePool.get(0);
				for(int i = 0; i < genePool.size(); i++)
				{
					if(bonusLarge.getStats().get(1).getBonus() < genePool.get(i).getStats().get(1).getBonus())
						{
						bonusLarge = genePool.get(i);
						}
				}
				genePool.remove(bonusLarge);
				orderedList.add(bonusLarge);
			}
			genePool.addAll(orderedList);
			orderedList.removeAll(orderedList);
			
			System.out.print("Overpopulation Deaths: ");
			numOfChildren = 0;
			while(genePool.size() > maxPop)
			{
				int i = rand.nextInt(genePool.size());
				System.out.print("(" + genePool.get(i).getName() + ", " + genePool.get(i).getSurvival() + ")");
				genePool.remove(i);
				numOfChildren++;
				
			}
			System.out.print(" (" + numOfChildren + ")");
			System.out.print("\n");
			}
			/*ArrayList<Buddy> group = new ArrayList();
			Buddy toGroup;
			for(int i = 0; i < 3; i++)//starting 10
			{
				toGroup = genePool.get(rand.nextInt(genePool.size()));
				genePool.remove(toGroup);
				group.add(toGroup);
				//System.out.print(toGroup.getName());
			}
			
			if(group.get(0).getTotalStats() <= group.get(1).getTotalStats() && group.get(0).getTotalStats() <= group.get(2).getTotalStats())
			{
				group.add(new Buddy(group.get(1), group.get(2)));
				group.remove(0);
			}
			else if(group.get(1).getTotalStats() <= group.get(0).getTotalStats() && group.get(1).getTotalStats() <= group.get(2).getTotalStats())
			{
				group.add(new Buddy(group.get(0), group.get(2)));
				group.remove(1);
			}
			else
			{
				group.add(new Buddy(group.get(0), group.get(1)));
				group.remove(2);
			}
			genePool.addAll(group);
			*/
			//Pick 3 then find best two and have em' mate
			/*
			Buddy dater = genePool.get(i);
			for(int a = 0; a < dater.getLovers().size() && i < genePool.size(); a++)
			{
				//System.out.println("1test" + genePool.size());
				if(dater.getLovers().get(a).lovesBack(genePool.get(i)) && genePool.contains(dater))
				{
					Buddy Spouse = dater.getLovers().get(a);
					//System.out.println(children.size());
					if((dater.getTotalStats() + Spouse.getTotalStats())/2 > totalSurvival)
					{
					children.add(new Buddy(dater, Spouse));
					children.add(new Buddy(dater, Spouse));
					children.add(new Buddy(Spouse, dater));
					children.add(new Buddy(Spouse, dater));
					//System.out.println(children.size());
					}
					else
					{
					//System.out.println(children.size());
					//children.add(new Buddy(Spouse, dater));
					}
					genePool.remove(dater);
					genePool.remove(Spouse);
					for(int d = 0; d < genePool.size(); d++)
					{
						genePool.get(d).lostLove(dater);
						genePool.get(d).lostLove(Spouse);
					}
					i=0;
				}*/
			
		}
		System.out.print("All Averages: ");
		for(int i = 0; i < Averages.size()-1; i++)
		{
			System.out.print(" " + Averages.get(i) + "|");
			
		}
		System.out.print(" " + Averages.get(Averages.size()-1));
		System.out.print("\nGenerations:: ");
		for(int i = 0; i < Generation.size()-1; i++)
		{	
			String sizeHelper = ".";
			while(Averages.get(i).toString().length() - Generation.get(i).toString().length() > sizeHelper.length())
			{
				sizeHelper += "0";
			}
			if(Averages.get(i).toString().length() == Generation.get(i).toString().length())
			{
				sizeHelper = "";
			}
			System.out.print(" " + Generation.get(i) + sizeHelper + "|");
		}
		System.out.print(" " + Generation.get(Generation.size()-1));
		//genePool.removeAll(genePool);
		//genePool.addAll(children);
		//children.removeAll(children);
		}
	
	}
}
