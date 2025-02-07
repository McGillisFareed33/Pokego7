package game_project;

import java.util.ArrayList;

public class Bruise extends Action {

	public Bruise() {
		this.Name = "Bruise";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 20;
		this.Priority = 1;
		this.TargetsEnemy = 1;
	}
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {	
			battleFunctions.calculatePhysicalDamage(caster, opponent, Power);
	}

}
