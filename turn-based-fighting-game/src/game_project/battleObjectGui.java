package game_project;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class battleObjectGui {
	
	
	guitestClass guiClass;
	int BattleState;
	int BattleIterator = 0;
	int currentCharacter;
	int side1Attacked;
	int side2Attacked;
	Action actionToPerform;
	int actionTargetsEnemy = 0;
	ArrayList<Character> side1;
	ArrayList<Character> side2;
	static ArrayList<actionResult> messages;
	int AlliesAlive;
	int OpponentsAlive;
	turnState state = turnState.moveSelect;
	MoveOrderTree moveTree;
	
	int turncounter = 0;
	public battleObjectGui (guitestClass guiClass)
	{
		this.guiClass = guiClass;
		messages = new ArrayList<>();
	}
	
	public battleObjectGui (ArrayList<Character> side1,ArrayList<Character> side2)
	{
		this.guiClass = null;
		messages = new ArrayList<>();
		actionToPerform = null;
		this.BattleState = 0;
		this.side1 = side1;
		this.side2 = side2;
		AlliesAlive = this.side1.size();
		OpponentsAlive = this.side2.size();
		moveTree = new MoveOrderTree();
		currentCharacter = 0;
		side1Attacked = 0;
		side2Attacked = 0;
		displayText("What will " + side1.get(currentCharacter).Name + " do?");
	}
	
	
	public void startBattle(ArrayList<Character> side1,ArrayList<Character> side2)
	{
		messages.clear();
		actionToPerform = null;
		this.BattleState = 0;
		this.side1 = side1;
		this.side2 = side2;
		AlliesAlive = this.side1.size();
		OpponentsAlive = this.side2.size();
		moveTree = new MoveOrderTree();
		currentCharacter = 0;
		side1Attacked = 0;
		side2Attacked = 0;
		turncounter = 0;
		displayText("What will " + side1.get(currentCharacter).Name + " do?");
	}
	public static void addMessage(actionResult msg)
	{
		messages.add(msg);
	}
	
	public void getNewText()
	{
		if(!messages.isEmpty())
		{
			System.out.println(messages.get(0).text);
			if(guiClass != null)
			{
			guiClass.infoLabel.setText(messages.get(0).text);
				for (HashMap.Entry<Character, Integer> entry : messages.get(0).changes.entrySet()) {
					Character key = entry.getKey();
					for (characterStatus status : guiClass.statuses) {
						if(status.displayedChar.equals(key))
						{
							status.adjustStatus();
							break;
						}
					}
				}
			}
			//TODO: Set the main label to messages.get(0). 
			messages.remove(0);
		}

	}
	
	public void displayText(String Msg)
	{
		if(guiClass != null)
		{
		guiClass.infoLabel.setText(Msg);
		}
		System.out.println(Msg); //TODO: set the main label to Msg
	}
	
	public void proceedBattle()
	{
		BattleState = 2;
		currentCharacter = 0;
		state = CalculateMove();
		switch(state)
		{
		case turnInProgress:
			getNewText();
			break;
		case victorious:
			addMessage(new actionResult("You Won"));
			getNewText();
			BattleState = -1;
			break;
		case defeated:
			addMessage(new actionResult("You Lost"));
			getNewText();
			BattleState = -1;
			break;
		case moveSelect:
			getNewText();
			BattleState = 0;
			currentCharacter = 0;
			moveTree.clear();
			displayText("What will " + side1.get(currentCharacter).Name + " do?");
			break;
		}
	}
	
	public void cancelMove()
	{
		if(messages.isEmpty())
		{
			switch(BattleState)
			{
			case 0:
				if(currentCharacter > 0)
				{
					moveTree.DeleteNode(side1.get(currentCharacter-1));
					currentCharacter --;
					displayText("What will " + side1.get(currentCharacter).Name + " do?");
				}
				break;
			case 1:
				BattleState = 0;
				displayText("What will " + side1.get(currentCharacter).Name + " do?");
				break;
			default:
				proceedBattle();
				break;
			}
		}
		else
		{
			System.out.println("skipped");
			getNewText();
		}
	}
	
	
	public void buttonPressed(int buttonNo)
	{
		if(messages.isEmpty())
		{
		switch(BattleState)
		{
		case 0:
			if(side1.get(currentCharacter).Moveset[buttonNo].Energy > 0)
			{
				actionToPerform = side1.get(currentCharacter).Moveset[buttonNo];
				actionTargetsEnemy = actionToPerform.TargetsEnemy;
				BattleState = 1;
				displayText("to whom?");
			}
			else
			{
				for(Action action : side1.get(currentCharacter).Moveset)
				{
					if(action.Energy > 0)
					{
						displayText("Move out of energy," +  side1.get(currentCharacter).Name + " needs to pick another move!");
						return;
					}
				}
				actionToPerform = new Suffer();
				moveTree.insertMoveBySpeed(actionToPerform, side1.get(currentCharacter), side2.get(0), true);
				currentCharacter++;
				if(currentCharacter >= AlliesAlive)
					{
						BattleState = 2;
						CalculateOpponentMoves();
						StartBattlePhase();
						proceedBattle();
					}
				else
					{
						BattleState = 0;
						displayText("What will " + side1.get(currentCharacter).Name + " do?");
					}
			}
			break;
		case 1:
			if(buttonNo < side2.size())
			{
			moveTree.insertMoveBySpeed(actionToPerform, side1.get(currentCharacter), side2.get(buttonNo), true);
			currentCharacter++;
			if(	currentCharacter >= AlliesAlive)
				{
					CalculateOpponentMoves();
					StartBattlePhase();
					proceedBattle();
				}
			else
				{
					BattleState = 0;
					displayText("What will " + side1.get(currentCharacter).Name + " do?");
				}
			}
			break;
		case -1:
			System.out.println("skipped");
			getNewText();
			break;
		default:
			proceedBattle();
			break;
		}
		}
		else
		{
			System.out.println("skipped");
			getNewText();
		}
	}
	public void CalculateOpponentMoves()
	{
		turncounter++;
		Random rand = new Random();
		for(Character enemy : side2)
		{
			enemy.resetMoveEnergy();
			switch(enemy.ID)
			{
			case -1:
				Character highestHP = null;
				int HPsum = 0;
				int MaxHPsum = 0;
				for(Character target : side1)
				{
					HPsum += target.HPCurrent;
					MaxHPsum += target.HP;
					if(highestHP == null)
					{
						highestHP = target;
						continue;
					}
					if(target.HPCurrent > highestHP.HPCurrent)
					{
						highestHP = target;
					}
				}
				if(enemy.HPCurrent < enemy.HP / 6)
				{
					moveTree.insertMoveBySpeed(enemy.Moveset[0],enemy, highestHP, false);
					continue;
				}
				if(HPsum <= MaxHPsum  /3)
				{
					moveTree.insertMoveBySpeed(enemy.Moveset[1],enemy, highestHP, false);
					continue;
				}
				moveTree.insertMoveBySpeed(enemy.Moveset[3],enemy, highestHP, false);

				break;
			case -2:
				moveTree.insertMoveBySpeed(enemy.Moveset[rand.nextInt(4)],enemy ,side1.get(rand.nextInt(side1.size())), false);
				break;
			}
			
			
		}
	}
	public void StartBattlePhase()
	{
		for(Character character : side1)
		{
			character.ReflectCounter += character.ReflectCounter > 0 ? -1 : 0;
		}
		for(Character character : side2)
		{
			character.ReflectCounter += character.ReflectCounter > 0 ? -1 : 0;
		}
	}
	
	
	public turnState CalculateMove()
	{
		MoveOrderNode CurrNode = moveTree.getNodeInOrder(BattleIterator);
		BattleIterator++;
		if(CurrNode != null)
		{
			if(CurrNode.Caster.HPCurrent <= 0)
			{
				addMessage(new actionResult(CurrNode.Caster.Name + " is already fainted!"));
			}
			else if(CurrNode.targets.HPCurrent <= 0 && CurrNode.actionToPerform.TargetsEnemy == 1)
			{
				addMessage(new actionResult("opponentAlreadyDead"));
			}
			else
			{
				addMessage(new actionResult(CurrNode.Caster.Name + " used " + CurrNode.actionToPerform.Name + "!"));
				CurrNode.actionToPerform.Energy--;
				if(CurrNode.isSide1)
				{
					CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,side1,side2);
				}
				else
				{
					CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,side2,side1);
				}
			}
		}
		else
		{
			BattleIterator = 0;
			moveTree.clear();
			return turnState.moveSelect;
		}
		
		for (int i = 0; i < side1.size(); i++) 
		{
			if(side1.get(i).HPCurrent <= 0)
			{

				AlliesAlive-=1;
				addMessage(new actionResult(side1.get(i).Name + " is fainted!"));
				side1.remove(i);
				i--;
				if(AlliesAlive <= 0) {
					return turnState.defeated;
				}

			}
		}
		for (int i = 0; i < side2.size(); i++) 
		{
			if(side2.get(i).HPCurrent <= 0)
			{

				OpponentsAlive-=1;
				addMessage(new actionResult(side2.get(i).Name + " is fainted!"));
				side2.remove(i);
				i--;
				if(OpponentsAlive <= 0) {
					return turnState.victorious;
				}
			}
		}
		return turnState.turnInProgress;		
	}
	public boolean CalculateTurn()
	{
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
					addMessage(new actionResult(CurrNode.Caster.Name + " is already fainted!"));
					continue;
				}
				if(CurrNode.targets.HPCurrent <= 0)
				{
					addMessage(new actionResult("opponentAlreadyDead"));
					continue;
				}
				System.out.println(CurrNode.Caster.Name + " used " + CurrNode.actionToPerform.Name + "!");
				CurrNode.actionToPerform.Energy--;
				if(CurrNode.isSide1)
				{
					CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,side1,side2);
				}
				else
				{
					CurrNode.actionToPerform.Execute(CurrNode.Caster, CurrNode.targets,side2,side1);
				}
			}
			else
			{
				inDamageCalc = false;
			}
			
			for (int i = 0; i < side1.size(); i++) 
			{
				if(side1.get(i).HPCurrent <= 0)
				{

					AlliesAlive-=1;
					addMessage(new actionResult(side1.get(i).Name + " is fainted!"));
					side1.remove(i);
					i--;
					if(AlliesAlive <= 0) {
						return false;
					}

				}
			}
			for (int i = 0; i < side2.size(); i++) 
			{
				if(side2.get(i).HPCurrent <= 0)
				{

					OpponentsAlive-=1;
					addMessage(new actionResult(side2.get(i).Name + " is fainted!"));
					side2.remove(i);
					i--;
					if(OpponentsAlive <= 0) {
						return false;
					}
				}
			}		
		}
		BattleState = 0;
		moveTree.clear();
		return true;
	}
}
