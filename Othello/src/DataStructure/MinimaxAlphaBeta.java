package DataStructure;

import Othello.OthelloMove;
import Othello.OthelloState;

public class MinimaxAlphaBeta {

	public int getBestMove(Node node, int depth, boolean isMax, int alpha, int beta)
	{
		//if there is no more moves
		if(node.getCurrentState().generateMoves().isEmpty() || node.getCurrentState().gameOver())
		{
			return node.getCurrentState().score();
		}
		if(depth == 0)
		{
			return node.getCurrentState().score();
		}
		
		depth--;
		
		//produce choices and put them as children
		for(OthelloMove choice : node.getCurrentState().generateMoves())
		{
			OthelloState state = node.getCurrentState().clone();
			state = state.applyMoveCloning(choice);
			node.addChild(new Node(state, node));
		}
		
		//max move checker
		if(isMax)
		{
			int best = Integer.MIN_VALUE;
			
			for(Node child : node.getChildren())
			{
				best = Math.max(best, getBestMove(child, depth, !isMax, alpha, beta));
				alpha = Math.max(alpha, best);
				
				//prune
				if(beta <= alpha)
					break;
			}
			
			return best;
		}
		//min move checker
		else
		{
			int best = Integer.MAX_VALUE;
			
			for(Node child : node.getChildren())
			{
				best = Math.min(best, getBestMove(child, depth, !isMax, alpha, beta));
				beta = Math.min(best, beta);
				
				//prune here
				if(beta <= alpha)
					break;
			}
			return best;
		}
		
	}
}
