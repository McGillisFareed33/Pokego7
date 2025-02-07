package game_project;

import java.util.ArrayList;

public class Firebolt extends Action {

	public Firebolt() {
		this.Name = "Firebolt";
		this.Energy = 15;
		this.MaxEnergy = 15;
		this.Power = 20;
		this.Priority = 1;
		this.TargetsEnemy = 1;
	}
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		battleFunctions.calculateSpecialDamage(caster, opponent, Power);
	}

}
