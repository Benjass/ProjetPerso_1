package Personnages;

import Enumerations.Action;
import Enumerations.Case;
import Enumerations.Orientations;
import Enumerations.Turns;
import Errors.Error_EnumNonTraitee;
import Terrain.Terrain;

public class Cube extends Personnage {
	private Orientations orientation_1, orientation_2;
	
	//constructeurs
	public Cube(Terrain TERRE) {
		super(TERRE);
		this.orientation_1 = Orientations.Tete;
		this.orientation_2 = Orientations.Nord;
		super.capturePlace(Case.orientation_TN);
	}
	
	//methodes Override
	
	@Override
	public void respawn() {
		super.liberePlace();
		super.respawn();
		this.orientation_1 = Orientations.Tete;
		this.orientation_2 = Orientations.Nord;
		super.capturePlace(Case.orientation_TN);
	}
	
	@Override
	public boolean move(Action deplacement) {
		boolean canMove = false;
		
		try {
			super.liberePlace();
			canMove = super.move(deplacement);
			if(canMove) {	this.changeOrientation(deplacement);	}
			
			switch(this.orientation_1) {
				case Tete:
					switch(this.orientation_2) {
						case Nord:
							super.capturePlace(Case.orientation_TN);
							break;
						case Est:
							super.capturePlace(Case.orientation_TE);
							break;
						case Sud:
							super.capturePlace(Case.orientation_TS);
							break;
						case Ouest:
							super.capturePlace(Case.orientation_TO);
							break;
						default:
					}
					break;
				case Pied:
					switch(this.orientation_2) {
						case Nord:
							super.capturePlace(Case.orientation_PN);
							break;
						case Est:
							super.capturePlace(Case.orientation_PE);
							break;
						case Sud:
							super.capturePlace(Case.orientation_PS);
							break;
						case Ouest:
							super.capturePlace(Case.orientation_PO);
							break;
						default:
					}
					break;
				case Nord:
					switch(this.orientation_2) {
						case Tete:
							super.capturePlace(Case.orientation_NT);
							break;
						case Est:
							super.capturePlace(Case.orientation_NE);
							break;
						case Pied:
							super.capturePlace(Case.orientation_NP);
							break;
						case Ouest:
							super.capturePlace(Case.orientation_NO);
							break;
						default:
					}
					break;
				case Est:
					switch(this.orientation_2) {
						case Tete:
							super.capturePlace(Case.orientation_ET);
							break;
						case Nord:
							super.capturePlace(Case.orientation_EN);
							break;
						case Pied:
							super.capturePlace(Case.orientation_EP);
							break;
						case Sud:
							super.capturePlace(Case.orientation_ES);
							break;
						default:
					}
					break;
				case Sud:
					switch(this.orientation_2) {
						case Tete:
							super.capturePlace(Case.orientation_ST);
							break;
						case Est:
							super.capturePlace(Case.orientation_SE);
							break;
						case Pied:
							super.capturePlace(Case.orientation_SP);
							break;
						case Ouest:
							super.capturePlace(Case.orientation_SO);
							break;
						default:
					}
					break;
				case Ouest:
					switch(this.orientation_2) {
						case Tete:
							super.capturePlace(Case.orientation_OT);
							break;
						case Nord:
							super.capturePlace(Case.orientation_ON);
							break;
						case Pied:
							super.capturePlace(Case.orientation_OP);
							break;
						case Sud:
							super.capturePlace(Case.orientation_OS);
							break;
						default:
					}
					break;
				default:
			}
		
		} catch(Error_EnumNonTraitee e) {
			System.out.println(e.getMessage());
		}
		
		return canMove;
	}

	private void changeOrientation(Action deplacement) throws Error_EnumNonTraitee {
		switch(deplacement) {
			case attend:
				//...
				break;
				
			case monte:
				orientation_1 = turnOne(orientation_1, Turns.devis);
				orientation_2 = turnOne(orientation_2, Turns.devis);
				break;
			case descend:
				orientation_1 = turnOne(orientation_1, Turns.vis);
				orientation_2 = turnOne(orientation_2, Turns.vis);
				break;
				
			case haut:
				orientation_1 = turnOne(orientation_1, Turns.front);
				orientation_2 = turnOne(orientation_2, Turns.front);
				break;
			case droite:
				orientation_1 = turnOne(orientation_1, Turns.horaire);
				orientation_2 = turnOne(orientation_2, Turns.horaire);
				break;
			case bas:
				orientation_1 = turnOne(orientation_1, Turns.back);
				orientation_2 = turnOne(orientation_2, Turns.back);
				break;
			case gauche:
				orientation_1 = turnOne(orientation_1, Turns.anti_horaire);
				orientation_2 = turnOne(orientation_2, Turns.anti_horaire);
				break;
			
			default:
				throw new Error_EnumNonTraitee();
		}
	}	
	
	private Orientations turnOne(Orientations axe, Turns sens) throws Error_EnumNonTraitee {
		Orientations nouvelAxe;
		if(	   		   (axe==Orientations.Tete && (sens==Turns.vis || sens==Turns.devis))
					|| (axe==Orientations.Nord && sens==Turns.back)
					|| (axe==Orientations.Est && sens==Turns.anti_horaire)
					|| (axe==Orientations.Sud && sens==Turns.front)
					|| (axe==Orientations.Ouest && sens==Turns.horaire)						) {	nouvelAxe = Orientations.Tete;	}
		
		else if(   	   (axe==Orientations.Pied && (sens==Turns.vis || sens==Turns.devis))
					|| (axe==Orientations.Nord && sens==Turns.front)
					|| (axe==Orientations.Est && sens==Turns.horaire)
					|| (axe==Orientations.Sud && sens==Turns.back)
					|| (axe==Orientations.Ouest && sens==Turns.anti_horaire)				) {	nouvelAxe = Orientations.Pied;	}
		
		else if(	   (axe==Orientations.Tete && sens==Turns.front)
					|| (axe==Orientations.Pied && sens==Turns.back)
					|| (axe==Orientations.Nord && (sens==Turns.horaire || sens==Turns.anti_horaire))
					|| (axe==Orientations.Est && sens==Turns.devis)
					|| (axe==Orientations.Ouest && sens==Turns.vis)							) {	nouvelAxe = Orientations.Nord;	}

		else if(	   (axe==Orientations.Tete && sens==Turns.horaire)
					|| (axe==Orientations.Pied && sens==Turns.anti_horaire)
					|| (axe==Orientations.Nord && sens==Turns.vis)
					|| (axe==Orientations.Est && (sens==Turns.front || sens==Turns.back))
					|| (axe==Orientations.Sud && sens==Turns.devis) 						) {	nouvelAxe = Orientations.Est;	}

		else if(	   (axe==Orientations.Tete && sens==Turns.back)
					|| (axe==Orientations.Pied && sens==Turns.front)
					|| (axe==Orientations.Est && sens==Turns.vis)
					|| (axe==Orientations.Sud && (sens==Turns.horaire || sens==Turns.anti_horaire))
					|| (axe==Orientations.Ouest && sens==Turns.devis)						) {	nouvelAxe = Orientations.Sud;	}

		else if(	   (axe==Orientations.Tete && sens==Turns.anti_horaire)
					|| (axe==Orientations.Pied && sens==Turns.horaire)
					|| (axe==Orientations.Nord && sens==Turns.devis)
					|| (axe==Orientations.Sud && sens==Turns.vis)
					|| (axe==Orientations.Ouest && (sens==Turns.front || sens==Turns.back))	) {	nouvelAxe = Orientations.Ouest;	}
		else {
			throw new Error_EnumNonTraitee();
		}
		return nouvelAxe;
	}
}
