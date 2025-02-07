package game_project;

import java.util.ArrayList; 

public class Rally extends Action {
	
	public Rally() {
		this.Name = "Rally";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 20;
		this.Priority = 1;
		this.TargetsEnemy = 0;
	}
	
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
				for (Character character : allies) {
					battleFunctions.ReduceStat(character, Stats.ATK, 1);
					battleFunctions.ReduceStat(character, Stats.DEF, 1);

		
		}
	}

}
