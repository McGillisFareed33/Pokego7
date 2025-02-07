package game_project;


public class battleFunctions {
	
	public static void Heal(Character character,int amount)
	{
		if(character.HP >= character.HPCurrent + amount)
		{
			character.HPCurrent += amount;
		}
		else
		{
		character.HPCurrent = character.HP;
		}

		actionResult result = new actionResult(character.Name + " got healed!");
		result.changes.put(character, amount);
		battleObjectGui.addMessage(result);
	}
	public static void DealDamage(Character character,int amount)
	{
		if( character.HPCurrent - amount > 0)
		{
			character.HPCurrent -= amount;
		}
		else
		{
		character.HPCurrent = 0;
		}
		actionResult result = new actionResult(character.Name + " Took " + amount + " damage!");
		result.changes.put(character, -amount);
		battleObjectGui.addMessage(result);
	}
	
	public static void resetDebuffs(Character target)
	{
		for (int i = 0; i < target.StatMods.length; i++) {
			target.StatMods[i] = target.StatMods[i] < 0 ? 0 : target.StatMods[i] ;
		}
		actionResult result = new actionResult(target.Name + " has been cleansed from debuffs");
		result.changes.put(target, 0);
		battleObjectGui.addMessage(result);
	}
	
	public static void ReduceStat(Character character,Stats stat,int amount)
	{
		actionResult result = new actionResult();
		
		
		switch(stat)
		{
		case ATK:
			if(amount > 0)
			{
				result.text = " Attack of " + character.Name + " has been increased";
			}
			else
			{
				result.text = (" Attack of " + character.Name + " has been decreased");
			}
			character.StatMods[0] += amount;
			if(	Math.abs(character.StatMods[0]) > 5)
			{
				character.StatMods[0] = (int) (5 * Math.signum(character.StatMods[0]));
				result.text = ("ATK can't go further!");

			}
			break;
		case DEF:
			if(amount > 0)
			{
				result.text = (" Defense of " + character.Name + " has been increased");
			}
			else
			{
				result.text = (" Defense of " + character.Name + " has been decreased");
			}
			character.StatMods[1] += amount;
			if(	Math.abs(character.StatMods[1]) > 5)
			{
				character.StatMods[1] = (int) (5 * Math.signum(character.StatMods[1]));
				result.text = ("DEF can't go further!");

			}
			break;
		case SPATK:
			if(amount > 0)
			{
				result.text = (" Special attack of " + character.Name + " has been increased");
			}
			else
			{
				result.text = (" Special attack of " + character.Name + " has been decreased");
			}
			character.StatMods[2] += amount;
			if(	Math.abs(character.StatMods[2]) > 5)
			{
				character.StatMods[2] = (int) (5 * Math.signum(character.StatMods[2]));
				result.text = ("SPATK can't go further!");
			}
			break;
		case SPDEF:
			if(amount > 0)
			{
				result.text = (" Special defense of " + character.Name + " has been increased");
			}
			else
			{
				result.text = (" Special defense of " + character.Name + " has been decreased");
			}
			character.StatMods[3] += amount;
			if(	Math.abs(character.StatMods[3]) > 5)
			{
				character.StatMods[3] = (int) (5 * Math.signum(character.StatMods[3]));
				result.text = ("SPDEF can't go further!");
			}
			break;
		case SPD:
			if(amount > 0)
			{
				result.text = (" Speed of " + character.Name + " has been increased");
			}
			else
			{
				result.text = (" Speed of " + character.Name + " has been decreased");
			}
			character.StatMods[4] += amount;
			if(	Math.abs(character.StatMods[4]) > 5)
			{
				character.StatMods[4] = (int) (5 * Math.signum(character.StatMods[4]));
				result.text = ("SPD can't go further!");

			}
			break;
		}
		result.changes.put(character, 0);
		battleObjectGui.addMessage(result);
	}
	public static void calculatePhysicalDamage(Character caster, Character opponent, int power)
	{
		if(opponent.ReflectCounter > 0)
		{
			battleObjectGui.addMessage(new actionResult("but " + opponent.Name + " is protected!"));
			return;
		}
		int damage = (int)  Math.ceil((caster.ATK + caster.StatMods[0]*5)  * power / (1 + opponent.DEF + opponent.StatMods[1] * 5 + 1));
		if(damage > 0)
		{
			DealDamage(opponent, damage);
		}
	}
	
	public static void calculateSpecialDamage(Character caster, Character opponent, int power)
	{
		if(opponent.ReflectCounter > 0)
		{
			battleObjectGui.addMessage(new actionResult("but " + opponent.Name + " is protected!"));
			return;
		}
		int damage = (int) Math.ceil((caster.SPATK + caster.StatMods[2]*5) * power / (opponent.SPDEF + opponent.StatMods[3] * 5 + 1));
		if(damage > 0)
		{
			DealDamage(opponent, damage);
		}
	}
	
	
	
	
	
}
