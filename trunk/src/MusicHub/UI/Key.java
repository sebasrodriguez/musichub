package MusicHub.UI;

public class Key {

	private char key;
	private int x;
	private int y;
	
	public Key(char key, int x, int y){
		this.key = key;
		this.x = x;
		this.y = y;		
	}
	
	public char getKey(){
		return this.key;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setKey(char key){
		this.key = key;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
}
