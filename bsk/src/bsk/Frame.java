package bsk;

public class Frame {

    private int firstThrow;
    private int secondThrow;
 
    public Frame(int i, int j) throws InvalidFrameException{
    	if (i < 0 || j < 0) {
    		throw new InvalidFrameException();
    	}
        this.firstThrow = i;
        this.secondThrow = j;
    }

    public int firstThrow() {
        return this.firstThrow;
    }
    
    public int secondThrow() {
        return this.secondThrow;
    }
    
    public boolean isStrike() {
    	return (this.firstThrow == 10);
    }
    
    public int sum(){
       return this.firstThrow + this.secondThrow;
    }

	public boolean isSpare() {
		return (!this.isStrike() && (this.firstThrow + this.secondThrow)==10);
	}
    
}
