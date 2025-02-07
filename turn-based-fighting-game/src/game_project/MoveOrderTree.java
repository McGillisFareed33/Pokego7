package game_project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class MoveOrderTree {
	MoveOrderNode root;
	public MoveOrderTree()
	{
		root = null;
	}
	public void clear() {
		root = null;
	}
	public void insertMoveBySpeed(Action action, Character caster, Character targets, boolean isSide1)
	{
		MoveOrderNode node = root;
		while(node != null)
		{
			if(node.actionToPerform.Priority < action.Priority)
			{
				if(node.children[0] == null)
				{
					node.children[0] = new MoveOrderNode(action, caster, targets, isSide1);
					node.children[0].parent = node;
					return;
				}
				node = node.children[0];
			}
			else if(node.actionToPerform.Priority == action.Priority && node.Caster.SPD <= caster.SPD)
			{
				if(node.children[0] == null)
				{
					node.children[0] = new MoveOrderNode(action, caster, targets, isSide1);
					node.children[0].parent = node;
					return;
				}
				node = node.children[0];
			}
			else
			{
				if(node.children[1] == null)
				{
					node.children[1] = new MoveOrderNode(action, caster, targets , isSide1);
					node.children[1].parent = node;
					return;
				}
				node = node.children[1];
			}
		}
		root = new MoveOrderNode(action, caster, targets , isSide1);
	}
	
	public MoveOrderNode getNodeInOrder(int order)
	{
		MoveOrderNode node = root;
		MoveOrderStack nodes = new MoveOrderStack();
		while((!nodes.isEmpty())|| node != null) 
		{
			if(node!= null)
			{
				nodes.push(node);
				node = node.children[0];
			}
			else
			{
			node = nodes.pop();
			order--;
			if(order < 0)
			{
			return node;	
			}

			node = node.children[1];
			}
		}
		return null;
	}
	
	public void DeleteNode(int inOrderNo)
	{
		MoveOrderNode node = root;
		MoveOrderStack nodes = new MoveOrderStack();
		while((!nodes.isEmpty())|| node != null) 
		{
			if(node!= null)
			{
				nodes.push(node);
				node = node.children[0];
			}
			else
			{
			node = nodes.pop();
			inOrderNo--;
			if(inOrderNo < 0)
			{
				RemoveSpecificNode(node);
			return;	
			}
			node = node.children[1];
			}
		}
		return;
	}
	
	public void DeleteNode(Character character)
	{
		MoveOrderNode node = root;
		MoveOrderStack nodes = new MoveOrderStack();
		while((!nodes.isEmpty())|| node != null) 
		{
			if(node!= null)
			{
				nodes.push(node);
				node = node.children[0];
			}
			else
			{
			node = nodes.pop();
			if(node.Caster.equals(character))
			{
				RemoveSpecificNode(node);
			return;	
			}
			node = node.children[1];
			}
		}
		return;
	}
	
	
	
	
	public void RemoveSpecificNode(MoveOrderNode node)
	{
		boolean c0 = node.children[0] == null;
		boolean c1 = node.children[1] == null;
		if(node == root)
		{

			if(c0 && !c1)
			{
				root = node.children[1];
				node.children[1].parent = null;
			}
			else if(c1 && !c0)
			{
				root = node.children[0];
				node.children[0].parent = null;
			}
			else if(c0 && c1)
			{
				root = null;
			}
			else
			{
				MoveOrderNode newnodeplace = node.children[0];
				while(newnodeplace.children[1] != null)
				{
					newnodeplace = newnodeplace.children[1];
				}
				newnodeplace.children[1] = node.children[1];
				node.children[1].parent = newnodeplace;
			}
		}
		else if((!c0) && !c1)
		{
			node.children[0].parent = node.parent;
			if(node.parent.children[0].equals(node))
			{
				node.parent.children[0] = node.children[0];
			}
			else
			{
				node.parent.children[1] = node.children[0];
			}
			MoveOrderNode newnodeplace = node.children[0];
			while(newnodeplace.children[1] != null)
			{
				newnodeplace = newnodeplace.children[1];
			}
			newnodeplace.children[1] = node.children[1];
			node.children[1].parent = newnodeplace;
		}
		else if(c0 && !c1)
		{
			node.children[1].parent = node.parent;
			if(node.parent.children[0].equals(node))
			{
				node.parent.children[0] = node.children[1];
			}
			else
			{
				node.parent.children[1] = node.children[1];
			}
		}
		else if(c1 && !c0)
		{
			node.children[0].parent = node.parent;
			if(node.parent.children[0].equals(node))
			{
				node.parent.children[0] = node.children[0];
			}
			else
			{
				node.parent.children[1] = node.children[0];
			}
		}
		else
		{
			if(!(node.parent.children[0] == null))
			{
				if(node.parent.children[0].equals(node))
				{
				node.parent.children[0] = null;
				}
			}
			else
			{
				node.parent.children[1] = null;
			}
		}

		
	}
	public void printInOrder()
	{
		MoveOrderNode node = getNodeInOrder(0);
		for(int i = 1; node != null ; i++)
		{
			System.out.println((i-1) + node.Caster.Name);
			node = getNodeInOrder(i);
		}
	}
	
}



