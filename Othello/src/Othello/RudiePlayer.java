package Othello;

import java.util.List;

import DataStructure.MinimaxAlphaBeta;
import DataStructure.Node;

public class RudiePlayer extends OthelloPlayer{

	private int depth;
	private MinimaxAlphaBeta mx;
	
	public RudiePlayer(int depth)
	{
		this.depth = depth;
		this.mx = new MinimaxAlphaBeta();
	}

	@Override
	public OthelloMove getMove(OthelloState state) {

		List<OthelloMove> moves = state.generateMoves();    
		
		if (!moves.isEmpty()) 
		{
			Node root = new Node(state);
			
			//time tracker for how long it takes to decide a move
			long startTime = System.currentTimeMillis();
			//get list of potential moves
			for(OthelloMove choice : moves)
			{
				OthelloState s = root.getCurrentState().clone();
				s.applyMoveCloning(choice);
				root.addChild(new Node(s, root));
			}
			

			int bestVal = 0;
			int bestPosition = 0;
			
			if(state.nextPlayerToMove == 0)
			{
				bestVal = Integer.MIN_VALUE;
			}
			else
			{
				bestVal = Integer.MAX_VALUE;
			}
			
			for(int i = 0; i < root.getChildren().size(); i++)
			{
				//get the best value depends on the player
				//since we are evaluating based on score. 2nd player want the score to be in the negatives
				//if the player who is executing this is 0, they want positive values so they get the best of max
				if(state.nextPlayerToMove == 0)
				{
					int val = mx.getBestMove(root.getChildren().get(i), depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if( val > bestVal)
					{
						bestPosition = i;
						bestVal = val;
					}
				}
				//if the player who is executing this is 1, they are the 2nd player. they want the values to be in the negatives/min
				else
				{
					//setting it to false will make it return the minimal
					int val = mx.getBestMove(root.getChildren().get(i), depth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if( val < bestVal)
					{
						bestPosition = i;
						bestVal = val;
					}
				}

			}
			long endTime = System.currentTimeMillis();
			System.out.println("Rudie Player execution time: " + (endTime - startTime) + " miliseconds");
			return moves.get(bestPosition);
		}
		
		return null;
	}
	
	
}
