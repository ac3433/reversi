package DataStructure;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import Othello.*;

public class MonteCarloTreeSearch {

	
	private LinkedList<Node> queue;
	private List<OthelloMove> moves;

	public OthelloMove getMove(OthelloState state, int iterations)
	{
		queue = new LinkedList<Node>();
		Node root = new Node(state);
		moves = state.generateMoves();
		
		//this is used to queue up remaining nodes in the root to check to visit
		//to be used in tree policy of finding remaining children
		for(OthelloMove choice : moves)
		{
			OthelloState s = state.clone();
			s.applyMove(choice);
			queue.add(new Node(s, root));
		}
						
		for(int i = 0; i < iterations; i++)
		{
			Node node = treePolicy(root);
			if(node != null)
			{
				Node node2 = defaultPolicy(node);
				int nodeScore = score(node2);
				backup(node, nodeScore);
			}
		}
				
		return moves.get(getBestChild(root));
	}
	
	public int getBestChild(Node root)
	{
		return null;
	}
	

	public Node treePolicy(Node root)
	{
		//if any remaining children not in the tree then generate and return it.
		if(!queue.isEmpty())
		{
			Node node = queue.poll();
			root.addChild(node);
			return node;
		}
		
		if(root.getCurrentState().gameOver())
		{
			return root;
		}

		//since the position of the moves is the same as the children. only need to get it
		return root.getChildren().get(getBestChild(root));
		
		return null;
	}
	
	public Node defaultPolicy(Node node)
	{
		
		return null;
	}
	
	public int score(Node node)
	{
		
		return 0;
	}
	
	public void backup(Node node, int nodeScore)
	{
		
	}

}
