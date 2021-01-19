/* the boardgame class to decided which situation it falls to for each gameboard iteration.
 * 
 */
public class BoardGame {
	private int board_size;
	private int empty_positions;
	private int max_levels;
	private char[][] gameBoard;
	
	/*initialize the gameboard and let's each of its element being character "g".
	 * and also set up the number of positions must being empty.
	 */
	public BoardGame (int board_size, int empty_positions, int max_levels) {
		this.board_size=board_size;
		this.empty_positions = empty_positions;
		this.max_levels = max_levels;
		gameBoard = new char[board_size][board_size];
		for(int i = 0; i<board_size;i++) {
			for(int j = 0; j<board_size; j++) {
				gameBoard[i][j] = 'g';
			}
		}
	}
	//initialize an hashdictionary and set up its size being 6000.
	public HashDictionary makeDictionary() {
	   return new HashDictionary(6000);
	}
	
	
	// to get the score for certain configuration.
	public int isRepeatedConfig(HashDictionary dict) {
		String a = "";
		for(int i = 0; i<board_size;i++) {
			for(int j = 0; j<board_size; j++) {
				a = a +gameBoard[j][i];
			}
		
		}
		return dict.getScore(a);
	}
	
	//to put an configuraion into an dictionary.
	public void putConfig(HashDictionary dict, int score)  {
		String a = null;
		for(int i = 0; i<board_size;i++) {
			for(int j = 0; j<board_size; j++) {
				a = a +gameBoard[j][i];
			}
	
		}
	    Configuration confi = new Configuration(a, score);
	    
	    try {
			dict.put(confi);
		} catch (DictionaryException e) {
			
			e.printStackTrace();
		}
	}
	
	//set up certain position as certain symbol.
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	//to judge if an position is empty.
	public boolean positionIsEmpty(int row, int col) {
		return (gameBoard[row][col]=='g');
	}
	//to judge if an position is computer.
	public boolean tileOfComputer (int row, int col) {
		return (gameBoard[row][col]=='o');	
	}
	//to judge if an position is  human.
	public boolean tileOfHuman(int row, int col) {
		return (gameBoard[row][col]=='b');
	}
	
	//to judge if an situation is win or not.
	public boolean wins(char symbol) {
		for(int i = 0; i<board_size;i++) {
			   for(int j = 0; j< board_size;j++) {
				   if (gameBoard[i][j] !=  symbol) break;
				   if (j == board_size-1) return true;
			   }
				
			}
		for(int i = 0; i<board_size;i++) {
			   for(int j = 0; j< board_size;j++) {
				   if (gameBoard[j][i] !=  symbol) break;
				   if (j == board_size-1) return true;
			   }
				
			}
		
		for (int i = 0; i<board_size;i++) {
			if(gameBoard[i][i]!=symbol) break;
			if(i == board_size -1) return true;
		}
		
		for (int j = 0; j<board_size;j++) {
			if(gameBoard[j][board_size-1-j]!= symbol) break;
		    if(j == board_size -1) return true;
		}
		return false;
		
		}
	
	/*test if the game is an draw.
	 * 
	 */
	 public boolean isDraw(char symbol, int empty_positions) {
		  if (wins('o')||wins('b')) return false;
		  int emptyNumber =0;
		  for(int i =0; i<board_size;i++) {
			  for(int j =0; j<board_size; j++) {
				  if(positionIsEmpty(i,j)==true) {
					  emptyNumber++;
				  }
			  }
		  }
		  if(empty_positions == 0 && emptyNumber == 0) {
		   return true;
		  }
		  
		  if(emptyNumber == empty_positions) {
		  for(int i = 0; i<board_size;i++) { 
			  for(int j = 0; j<board_size;j++) {
				if (positionIsEmpty(i, j)==true) {
					   if (i!=0) {
						       if(gameBoard[i-1][j]==symbol) return false;
					   }
					   if(i!=board_size-1) {
						       if(gameBoard[i+1][j]==symbol) return false;
					   }
					   if(j!=0) {
						       if(gameBoard[i][j-1]==symbol) return false;
					   }
					   if(j!=board_size-1) {
						       if(gameBoard[i][j+1]==symbol) return false;
					   }
					   if(i!=0&&j!=0) {
						       if(gameBoard[i-1][j-1]==symbol) return false;
					   }
					   if(i!=0&&j!=board_size-1) {
						        if(gameBoard[i-1][j+1]==symbol) return false;
					   }
					   if(i!=board_size-1&&j!=0) {
						        if(gameBoard[i+1][j-1]==symbol) return false;
					   }
					   if(i!=board_size-1&&j!=board_size-1) {
						         if(gameBoard[i+1][j+1]==symbol) return false;
					   }
				}
			  }
		  }
	
		  return true;}
		  
		  return false;
		  
		  
		  
		  
	 }
		 
		
	/*to return all kinds of situations
	 * 3 computer win
	 * 1 human win
	 * 2 draw
	 * 0 can't decided
	 */
	 public int evalBoard(char symbol, int empty_positions) {
		 if(wins('o')) return 3;
		 if(wins('b')) return 0;
		 if(isDraw(symbol,empty_positions)) return 2;
		 else {
			 return 1;
		 }
	 }
	
	
	}
