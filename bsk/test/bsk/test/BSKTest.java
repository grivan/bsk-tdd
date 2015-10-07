package bsk.test;
import static org.junit.Assert.*;

import org.junit.Test;

import bsk.Frame;
import bsk.Game;
import bsk.InvalidFrameException;

public class BSKTest {
    
    @Test
    public void testThrows() throws InvalidFrameException {
        Frame frame = new Frame(2,4);
        assertEquals(2,frame.firstThrow());
        assertEquals(4,frame.secondThrow());
    }
    
    @Test
    public void frameScore() throws InvalidFrameException {
        Frame frame = new Frame(2,6);
        assertEquals(8,frame.sum());
    }
    
    @Test
    public void scoreGame() throws InvalidFrameException {
        Game game = new Game();
        game.addFrame(new Frame(1,5));
        game.addFrame(new Frame(3,6));
        game.addFrame(new Frame(7,2));
        game.addFrame(new Frame(3,6));
        game.addFrame(new Frame(4,4));
        game.addFrame(new Frame(5,3));
        game.addFrame(new Frame(3,3));
        game.addFrame(new Frame(4,5));
        game.addFrame(new Frame(8,1));
        game.addFrame(new Frame(2,6));
        assertEquals(81,game.score());
       }
    
    @Test
    public void identifyStrikeFrame() throws InvalidFrameException {
    	Frame frame = new Frame(10,0);
    	assertTrue(frame.isStrike());
    	frame = new Frame(9,1);
    	assertFalse(frame.isStrike());
    }
    
    @Test
    public void scoreAStrikeFrame() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(1,5));
        game.addFrame(new Frame(3,6));
        game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(9,1));
        
        assertTrue(game.isStrike(3));
        assertEquals(20,game.frameScore(3));
    }
    
    @Test
    public void identifySpareFrame() throws InvalidFrameException {
    	Frame frame = new Frame(1,9);
    	assertTrue(frame.isSpare());
    	frame = new Frame(0,9);
        assertFalse(frame.isSpare());
    }
    
    @Test 
    public void scoreASpareFrame() throws InvalidFrameException {
    	Game game = new Game(); 
    	game.addFrame(new Frame(1,5));
        game.addFrame(new Frame(3,6));
        game.addFrame(new Frame(1,9));
        game.addFrame(new Frame(3,6));
        assertTrue(game.isSpare(3));
        assertEquals(13,game.frameScore(3));
    }
    
    @Test
    public void strikeFollowedBySpare() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(5,5));
        game.addFrame(new Frame(3,6));
        
        assertEquals(20, game.frameScore(1));
        assertEquals(13, game.frameScore(2));
        
    }
    
    @Test
    public void identifyMultipleStrike() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(10,0));
        assertTrue(game.isStrike(1) && game.isStrike(2));
    }
    
    @Test
    public void MultipleStrikeScoreFirstStrike() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(7,2));
        assertEquals(27,game.frameScore(1));
    }
    
    @Test 
    public void MulipleStrikeScoreSecondStrike() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(10,0));
        game.addFrame(new Frame(7,2));
        assertEquals(19,game.frameScore(2));
    }
    
    @Test
    public void multipleStrikesGameScore() throws InvalidFrameException {
    	Game game = new Game();
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(2,6));
    	
    	assertEquals(112,game.score());
    }
    
    @Test
    public void multipleSpares() throws InvalidFrameException {
    	Game game = new Game();

    	game.addFrame(new Frame(8,2));
    	game.addFrame(new Frame(5,5));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(2,6));
    	
    	
    	assertEquals(10,game.frameScore(1));
    	assertEquals(17,game.frameScore(2));
    	
    	//check later
    	assertEquals(93, game.score());
    }
    
    @Test 
    public void isLastSpare() throws InvalidFrameException {
    	Game game = new Game();

    	game.addFrame(new Frame(1,5));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(2,8));
    	
    	assertTrue(game.isSpare(10));
    }
    
    @Test 
    public void LastSpareScore() throws InvalidFrameException {
    	Game game = new Game();

    	game.addFrame(new Frame(1,5));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(2,8));
    	game.addBonusThrow(7);
    	
    	assertEquals(17,game.frameScore(10));
    	assertEquals(90,game.score());
    }
    
    @Test
    public void strikeAsLastFrame() throws InvalidFrameException {
    	Game game = new Game();
    	
    	game.addFrame(new Frame(1,5));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(10,0));
    	game.addBonusThrow(7);
    	game.addBonusThrow(2);
    	
    	assertEquals(19,game.frameScore(10));
    	assertEquals(92,game.score());
    }
    
    @Test
    public void bonusIsStrike() throws InvalidFrameException {
    	Game game = new Game();

    	game.addFrame(new Frame(1,5));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(3,6));
    	game.addFrame(new Frame(4,4));
    	game.addFrame(new Frame(5,3));
    	game.addFrame(new Frame(3,3));
    	game.addFrame(new Frame(4,5));
    	game.addFrame(new Frame(8,1));
    	game.addFrame(new Frame(2,8));
    	game.addBonusThrow(10);
    	
    	assertEquals(20,game.frameScore(10));
    	assertEquals(93,game.score());
    }
    
    @Test
    public void BestScore() throws InvalidFrameException {
       Game game = new Game();
    	
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(10,0));
    	game.addBonusThrow(10);
    	game.addBonusThrow(10);
    	
    	assertEquals(30,game.frameScore(1));
    	assertEquals(30,game.frameScore(2));
    	assertEquals(30,game.frameScore(3));
    	assertEquals(30,game.frameScore(4));
    	assertEquals(30,game.frameScore(5));
    	assertEquals(30,game.frameScore(6));
    	assertEquals(30,game.frameScore(7));
    	assertEquals(30,game.frameScore(8));
    	assertEquals(30,game.frameScore(9));
    	assertEquals(30,game.frameScore(10));
    	
    	assertEquals(300,game.score());
    }
    
    @Test
    public void realGame() throws InvalidFrameException {
    	
    	Game game = new Game();
    	
    	game.addFrame(new Frame(6,3));
    	game.addFrame(new Frame(7,1));
    	game.addFrame(new Frame(8,2));
    	game.addFrame(new Frame(7,2));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(6,2));
    	game.addFrame(new Frame(7,3));
    	game.addFrame(new Frame(10,0));
    	game.addFrame(new Frame(8,0));
    	game.addFrame(new Frame(7,3));
    	game.addBonusThrow(10);
    	
    	assertEquals(9,game.frameScore(1));
    	assertEquals(8,game.frameScore(2));
    	assertEquals(17,game.frameScore(3));
    	assertEquals(9,game.frameScore(4));
    	assertEquals(18,game.frameScore(5));
    	assertEquals(8,game.frameScore(6));
    	assertEquals(20,game.frameScore(7));
    	assertEquals(18,game.frameScore(8));
    	assertEquals(8,game.frameScore(9));
    	assertEquals(20,game.frameScore(10));
    	
    	assertEquals(135,game.score());
    	
    }
    
    @Test
    public void throwCanNotBeNegative() {
    	try {
    		Frame frame = new Frame(1,-1);
    		frame = new Frame(-1,1);
    		fail();
    	}
    	catch (InvalidFrameException e) {
    		
    	};
    	
    }
}