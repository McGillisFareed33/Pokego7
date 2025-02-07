package game_project;

import java.util.ArrayList;

public class Awareness extends Action {

	public Awareness() {
		this.Name = "Awareness";
		this.Energy = 5;
		this.MaxEnergy = 5;
		this.Power = 20;
		this.Priority = 2;
		this.TargetsEnemy = -1;
	}

	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		
		battleFunctions.ReduceStat(caster, Stats.DEF, 2);

	}

}
