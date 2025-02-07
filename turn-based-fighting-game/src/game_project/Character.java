package game_project;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Character implements Comparable<Character> {
	String Name;
	ImageIcon characterIcon;
	ImageIcon characterDeathIcon;
	int ID;
	int LVL;
	int HP;
	int HPCurrent;
	int ATK;
	int DEF;
	int SPATK;
	int SPDEF;
	int SPD;
	int ReflectCounter = 0;
	int[] StatMods;
	Action[] Moveset;
	public Character(String Name, int LVL , CharacterTemplate template,ImageIcon characterIcon,ImageIcon characterDeathIcon) {
		super();
		this.characterIcon = characterIcon;
		this.characterDeathIcon = characterDeathIcon;
		this.Name = Name;
		this.LVL = LVL;
		this.ID = template.ID;
		this.HP = template.HP + LVL + (int) Math.floor((2 * template.HP + LVL) / 10);
		this.HPCurrent = this.HP;
		this.ATK = template.ATK + LVL + (int) Math.floor((2 * template.ATK + LVL) / 10);
		this.DEF = template.DEF + LVL + (int) Math.floor((2 * template.DEF + LVL) / 10);
		this.SPATK = template.SPATK + LVL + (int) Math.floor((2 * template.SPATK + LVL) / 10);
		this.SPDEF = template.SPDEF + LVL + (int) Math.floor((2 * template.SPDEF + LVL) / 10);
		this.SPD = template.SPD + LVL + (int) Math.floor((2 * template.SPD + LVL) / 10);
		this.StatMods = new int[5];
		resetStatMods();
		Moveset = new Action[4];
	}
	@Override
	public int compareTo(Character o) {
		return this.SPD - o.SPD;
	}
	
	public void resetStatMods()
	{
		for (int i = 0; i < StatMods.length; i++) {
			this.StatMods[i] = 0;
		}
	}
	
	public void resetMoveEnergy()
	{
		for(Action act : Moveset)
		{
			act.Energy = act.MaxEnergy;
		}
	}

	
	public void setupMoves(Action[] moveset)
	{
		for(int i = 0; i < 4; i++)
		{
			try {
				Moveset[i] = moveset[i].getClass().getDeclaredConstructor().newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
