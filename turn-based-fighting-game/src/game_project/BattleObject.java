package game_project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BattleObject {
	public static boolean Battle(ArrayList<Character> PlayerSide, ArrayList<Character> OpponentSide)
	{
		int AlliesAlive = PlayerSide.size();
		int OpponentsAlive = OpponentSide.size();
		ArrayList<Character> playerQueue = new ArrayList<>();
		for(int i = 0; i < AlliesAlive; i++ ) {
			playerQueue.add(PlayerSide.get(i));
		}
		ArrayList<Character> opponentQueue = new ArrayList<Character>();
		for(int i = 0; i < OpponentsAlive; i++ ) {
			opponentQueue.add(OpponentSide.get(i));
		}
		Scanner scanner = new Scanner(System.in);
		MoveOrderTree moveTree = new MoveOrderTree();
		
		while((!playerQueue.isEmpty()) && (!opponentQueue.isEmpty()))
		{
			Character CurrentCharacter;
			moveTree.clear();
			for(int i = 0; i < playerQueue.size(); i++)
			{
				CurrentCharacter = playerQueue.get(i);
				boolean actionSelected = false;
				Action selectedAction = null;
				String action;
				for (Action move : CurrentCharacter.Moveset) {
					if(move.Energy > 0)
					{
						actionSelected = false;
						break;
					}
					actionSelected = true;
				}
				if(actionSelected)
				{
					selectedAction = new Suffer();
				}
				while(!actionSelected)
				{
					System.out.println(CurrentCharacter.Name + " is ready! What should " + CurrentCharacter.Name  + " do?");
					action = scanner.nextLine();
					
					switch (action)
					{
						case "help":
							System.out.println("Available Moves:");
							for (Action move : CurrentCharacter.Moveset) {
								if(move.Name != "undefined")
								System.out.println(move.Name);
							}
							break;
						default:
							break;
					}
					for (Action move : CurrentCharacter.Moveset) {
						if(move.Name != "undefined" && action.contains(move.Name))						
						{
							if(move.Energy > 0)
							{
								move.Energy--;
								selectedAction = move;
								actionSelected = true;
							}
							else
							{
								System.out.println("Move has no Energy left!");
							}
						}
					}
					
				}
				Character recipient = null;
				actionSelected = false;
				int target = -2;
				
				while(!actionSelected)
				{
					System.out.println("use move on whom (type id of enemy)?");
					for (int n = 0; n < opponentQueue.size();n++) {
						System.out.println(n + "  :  " + opponentQueue.get(n).Name);	
					}
					target = scanner.nextInt();
					scanner.nextLine();
					if(target >= 0 && target < opponentQueue.size());
					{
						recipient = opponentQueue.get(target);
						actionSelected = true;
					}
				}
				moveTree.insertMoveBySpeed(selectedAction, CurrentCharacter, recipient, false);
				
				
				
			}
			Random random = new Random();
			for (Character battleCharacter : opponentQueue) {
				Character targets = null;
				targets = playerQueue.get(random.nextInt(playerQueue.size()));
				moveTree.insertMoveBySpeed(battleCharacter.Moveset[random.nextInt(4)], battleCharacter ,targets, false);
			}
			
			
			
			
			
			
			boolean inDamageCalc = true;
			int iterator = 1;
			MoveOrderNode CurrNode = null;
			while(inDamageCalc)
			{
				CurrNode = moveTree.getNodeInOrder(iterator);
				iterator++;		
				if(CurrNode != null)
				{
					if(CurrNode.Caster.HPCurrent <= 0)
					{
						System.out.println(CurrNode.Caster.Name + " is already fainted!");
						continue;
					}
					if(CurrNode.targets.HPCurrent <= 0)
					{
						System.out.println("Target Already Dead");
						continue;
					}
					System.out.println(CurrNode.Caster.Name + " used " + CurrNode.actionToPerform.Name + "!");
					CurrNode.actionToPerform.Energy--;
					if(CurrNode.isSide1)
					{
						CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,playerQueue,opponentQueue);
					}
					else
					{
						CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,opponentQueue,playerQueue);
					}
				}
				else
				{
					inDamageCalc = false;
				}
				
				for (int i = 0; i < playerQueue.size(); i++) 
				{
					if(playerQueue.get(i).HPCurrent <= 0)
					{

						AlliesAlive-=1;
						System.out.println(playerQueue.get(i).Name + " is fainted!");
						playerQueue.remove(i);
						i--;
						if(AlliesAlive <= 0) {
							scanner.close();
							return false;
						}

					}
				}
				for (int i = 0; i < opponentQueue.size(); i++) 
				{
					if(opponentQueue.get(i).HPCurrent <= 0)
					{

						OpponentsAlive-=1;
						System.out.println(opponentQueue.get(i).Name + " is fainted!");
						opponentQueue.remove(i);
						i--;
						if(OpponentsAlive <= 0) {
							scanner.close();
							return true;
						}

					}
				}		
			}
			
			
		}
		
		
		scanner.close();
		return false;
		
	}
	
	
	
}
