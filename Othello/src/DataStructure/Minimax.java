package DataStructure;

import Othello.OthelloMove;
import Othello.OthelloState;

public class Minimax {

	public int getBestMove(Node node, int depth, boolean isMax)
	{
		//if there is no more moves
		if(node.getCurrentState().generateMoves().isEmpty())
			return 0;
		
		if(depth == 0)
		{
			return node.getCurrentState().score();
		}
		
		depth--;
		
		
		for(OthelloMove choice : node.getCurrentState().generateMoves())
		{
			
		}
		
		//max move checker
		if(isMax)
		{
			int best = 0;
			
			for(Node child : node.getChildren())
			{
				best = Math.max(best, getBestMove(child, depth, !isMax));
			}
			
			return best;
		}
		//min move checker
		else
		{
			int best = 0;
			
			for(Node child : node.getChildren())
			{
				best = Math.min(best, getBestMove(child, depth, isMax));
			}
			return best;
		}
		
	}
}
