package game_project;

import java.util.ArrayList;

public abstract class Action implements Cloneable{
	String Name;
	int Energy;
	int MaxEnergy;
	int Power;
	int Priority;
	int TargetsEnemy = 1; // 1 = targets enemy , 0 = targets allies, -1 = targets caster; 2 = targets every enemy;
	public void RestoreAction()
	{
		Energy = MaxEnergy;
	}
	public abstract void Execute(Character caster,Character opponent , ArrayList<Character> allies, ArrayList<Character> opponents);
}
