//package
package Personnages;

//import
import Enumerations.Action;
import Enumerations.Case;
import Errors.Error_EnumNonTraitee;
import Terrain.Terrain;


//class
public abstract class Personnage {
	
	//attributs
	private Terrain TERRE;
	//(0,0,0) en bas au fond a gauche
	private int positionX;
	private int positionY;
	private int positionZ;
	
	//constructeurs
	protected Personnage(Terrain terrain) {
		this.TERRE = terrain;
		spawn();
	}
	
	//getter-setter
	private int getPositionX() {
		return positionX;
	}
	private void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	private int getPositionY() {
		return positionY;
	}
	private void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	private int getPositionZ() {
		return positionZ;
	}
	private void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}
	
	//methodes
	protected void capturePlace(Case repere) {
		TERRE.setCase(positionX, positionY, positionZ, repere);
	}
	
	protected void liberePlace() {
		TERRE.setCase(positionX, positionY, positionZ, Case.vide);
	}
	
	private void spawn() {
		this.setPositionX(0);
		this.setPositionY(0);
		this.setPositionZ(0);
	}
	
	public void respawn() {
		liberePlace();
		spawn();
	}
	
	public boolean move(Action direction) throws Error_EnumNonTraitee {
		
		boolean canMoveOn;
		switch(direction) {
		
			case attend:
				canMoveOn = false;
				break;
				
			case haut:
				if(getPositionY() != TERRE.getMinY()) {
					setPositionY(getPositionY()-1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
			case droite:
				if(getPositionX() != TERRE.getMaxX()) {
					setPositionX(getPositionX()+1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
			case bas:
				if(getPositionY() != TERRE.getMaxY()) {
					setPositionY(getPositionY()+1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
			case gauche:
				if(getPositionX() != TERRE.getMinX()) {
					setPositionX(getPositionX()-1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
				
			case monte:
				if(getPositionZ() != TERRE.getMaxZ()) {
					setPositionZ(getPositionZ()+1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
			case descend:
				if(getPositionZ() != TERRE.getMinZ()) {
					setPositionZ(getPositionZ()-1);
					canMoveOn = true;
				} else {
					canMoveOn = move(Action.attend);
				}
				break;
				
			default:
				throw new Error_EnumNonTraitee();
		}
		
		return canMoveOn;
	}
}