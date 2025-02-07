package game_project;

import java.util.ArrayList;

public class DarkPulse extends Action {

	public DarkPulse() {
		this.Name = "Dark Pulse";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 10;
		this.Priority = 1;
		this.TargetsEnemy = 2;
	}
	
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		for (Character character : opponents) {
			battleFunctions.calculateSpecialDamage(caster, character, Power);
		}

	}
	
	
}
