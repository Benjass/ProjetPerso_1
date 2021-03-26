package Personnages;

import Enumerations.Action;
import Enumerations.Case;
import Errors.Error_EnumNonTraitee;
import Terrain.Terrain;

public class Basic extends Personnage {
	
	//constructeurs
	public Basic(Terrain TERRE) {
		super(TERRE);
		super.capturePlace(Case.personnage);
	}
	
	//methodes Override
	
	@Override
	public void respawn() {
		super.liberePlace();
		super.respawn();
		super.capturePlace(Case.personnage);
	}
	
	public boolean move(Action deplacement) {
		
		boolean canMove = false;
		try {
			
			super.liberePlace();
			canMove = super.move(deplacement);
			super.capturePlace(Case.personnage);
		
		} catch(Error_EnumNonTraitee e) {
			System.out.println(e.getMessage());
		}
		
		return canMove;
	}
}
