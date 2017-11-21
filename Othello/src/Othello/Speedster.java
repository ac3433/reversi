package Othello;

import java.util.List;

import DataStructure.MinimaxAlphaBetaTimer;
import DataStructure.Node;

public class Speedster extends OthelloPlayer {

	private int depth;
	private int timeStopper;
	private MinimaxAlphaBetaTimer mx;
	
	//time it should take to find the solution
	public Speedster(int timeStopper)
	{
		this.depth = 1;
		this.timeStopper = timeStopper;
		this.mx = new MinimaxAlphaBetaTimer();
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
			
			timer:
			while(true)
			{
				for(int i = 0; i < root.getChildren().size(); i++)
				{
					//get the best value depends on the player
					//since we are evaluating based on score. 2nd player want the score to be in the negatives
					//if the player who is executing this is 0, they want positive values so they get the best of max
					if(state.nextPlayerToMove == 0)
					{
						int val = mx.getBestMove(root.getChildren().get(i), depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE, startTime, timeStopper);
						//sole purpose to stop the loop
						if(val == Integer.MIN_VALUE)
							break timer;
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
						int val = mx.getBestMove(root.getChildren().get(i), depth, false, Integer.MAX_VALUE, Integer.MIN_VALUE, startTime, timeStopper);
						//sole purpose to stop the loop once it reach the time needed
						if(val == Integer.MIN_VALUE)
							break timer;
						if( val < bestVal)
						{
							bestPosition = i;
							bestVal = val;
						}
					}

				}
				
				depth++;
			}

			return moves.get(bestPosition);
		}
		
		return null;
	}
}
