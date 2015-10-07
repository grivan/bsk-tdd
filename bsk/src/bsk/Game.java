package bsk;

import java.util.ArrayList;

public class Game {
    private ArrayList<Frame> frames;
    private int bonusThrows[] = new int[2];
    private int bonusThrow = 0;
    private int gameSize = 10;
    
    public Game() {
        this.frames = new ArrayList<Frame>(gameSize);
    }
    
    public void addFrame(Frame frame){
       this.frames.add(frame);
    }
    
    public int score() {
      int gameScore = 0;
      for(int i=0;i<10;i++){
        gameScore += this.frameScore(i+1);
      }
      return gameScore;
    }

	public boolean isStrike(int i) {
		return this.frames.get(i-1).isStrike();
	}
	
	public int bonusThrowsScores() {
		int total = 0;
		for(int i=0;i<2;i++) {
			total+=bonusThrows[i];
		}
		return total;
	}

	public int frameScore(int i) {
		
		if( i < this.gameSize
				&& this.frames.get(i).isStrike() 
				&& this.frames.get(i-1).isStrike()) {
			if (i+1 == 10) {
				return(this.frames.get(i-1).sum() +
						this.frames.get(i).sum()) +
						this.bonusThrows[0];
			} else {
				return(this.frames.get(i-1).sum() +
						this.frames.get(i).sum() +
						this.frames.get(i+1).firstThrow());	
			}

		}
		else if (this.frames.get(i-1).isStrike()) {
			if (i < this.gameSize) {
				return (this.frames.get(i-1).sum() +
						this.frames.get(i).sum());
						}
			else {
				return this.frames.get(i-1).sum()+this.bonusThrowsScores();
			}
		}
		else if(i < this.gameSize
				&& this.frames.get(i-1).isSpare()
				&& this.frames.get(i).isSpare()) {
			return this.frames.get(i-1).sum();
		}
		else if(this.frames.get(i-1).isSpare()) {
			if (i < this.gameSize) {
				return (this.frames.get(i-1).sum() +
				this.frames.get(i).firstThrow());
			} else {
				return this.frames.get(i-1).sum()+this.bonusThrowsScores();
			}
		}
		else {
			return this.frames.get(i-1).sum();
		}
	}

	public boolean isSpare(int i) {
		
		return this.frames.get(i-1).isSpare();
	}
	
	public void addBonusThrow(int bonusThrow) {
		this.bonusThrows[this.bonusThrow++] = bonusThrow;
	}
}