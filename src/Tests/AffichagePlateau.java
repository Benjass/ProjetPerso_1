package Tests;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Enumerations.Action;
import Personnages.Cube;
import Personnages.Personnage;
import Terrain.Terrain;

public final class AffichagePlateau {
	
	private static BufferedReader buffer;
	private static char commande;
	
	public static void main(String[] args) {
		Terrain jeu = new Terrain();
		
		Personnage monster_1 = new Cube(jeu);
		jeu.printPlateau();
		
		//game loop
		while(true) {
			try {
				buffer = new BufferedReader(new InputStreamReader(System.in));
				commande = (char)(buffer.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(commande == ' ') {
				monster_1.respawn();
			} else {
				Action action = actionOf(commande);
				monster_1.move(action);
			}
			jeu.printPlateau();
			
		}
	}

	private static Action actionOf(char commande) {
		
		Action action;
		
		switch(commande) {
		case 'z':
			action = Action.haut;
			break;
		case 'd':
			action = Action.droite;
			break;
		case 's':
			action = Action.bas;
			break;
		case 'q':
			action = Action.gauche;
			break;
			
		case 'p':
			action = Action.monte;
			break;
		case 'l':
			action = Action.descend;
			break;
			
		default:
			System.out.println("saisie non traitee, action=attend");
			action = Action.attend;
		}
		
		return action;
	}

}

