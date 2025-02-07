package game_project;

import java.util.ArrayList;

public class Dash extends Action {

	public Dash() {		
		this.Name = "Dash";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 5;
		this.Priority = 2;
		this.TargetsEnemy = 1;
	}

	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		battleFunctions.calculatePhysicalDamage(caster, opponent, Power);
	}
}
