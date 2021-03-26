package Terrain;

import java.util.ArrayList;
import java.util.List;

import Enumerations.Case;
import Errors.Error_EnumNonTraitee;

public class Terrain {
	
	//attributs constants
	private final int HAUTEUR = 5;
	private final int PROFONDEUR = 10;
	private final int LONGUEUR = 15;
	
	//attributs variant
	private Case[][][] plateau = new Case[HAUTEUR][PROFONDEUR][LONGUEUR];
	
	//constructeurs
	public Terrain() {
		initTerrain();
	}
	
	//getter
	public int getMinX() {
		return 0;
	}
	public int getMinY() {
		return 0;
	}
	public int getMinZ() {
		return 0;
	}
	public int getMaxX() {
		return LONGUEUR;
	}
	public int getMaxY() {
		return PROFONDEUR;
	}
	public int getMaxZ() {
		return HAUTEUR;
	}
	
	//methodes
	private void initTerrain() {
		for(int z = 0; z != HAUTEUR; z++) {
			for(int y = 0; y != PROFONDEUR; y++) {
				for(int x = 0; x != LONGUEUR; x++) {
					plateau[z][y][x] = Case.vide;
				}
			}
		}
	}
	
	public void setCase(int x, int y, int z, Case newCase) {
		plateau[z][y][x] = newCase;
	}
	
	//visualisation
	private List<char[][]> vues3D(){
		List<char[][]> vues = new ArrayList<char[][]>();
		
		vues.add(vueAerienne());
		vues.add(vueFace());
		vues.add(vueProfil());
		
		return vues;
	}
	
	private char[][] vueAerienne() {
		char[][] vueAerienne = new char[PROFONDEUR+2][LONGUEUR+2];
		for(int y = 0; y != vueAerienne.length; y++) {
			for(int x = 0; x != vueAerienne[y].length; x++) {
				try {
					if(	   (y==0 					&& x==0)
						|| (y==vueAerienne.length-1 && x==0)
						|| (y==0 					&& x==vueAerienne[y].length-1)
						|| (y==vueAerienne.length-1 && x==vueAerienne[y].length-1)	) {
						vueAerienne[y][x] = '.';
						
					} else if(y==0 || y==vueAerienne.length-1) {
						vueAerienne[y][x] = '-';
					} else if(x==0 || x==vueAerienne[y].length-1) {
						vueAerienne[y][x] = '|';
					} else {
						vueAerienne[y][x] = charCorrespondantVueAerienne(Case.vide);
					}
				} catch(Error_EnumNonTraitee e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		for(int z = 0; z != HAUTEUR; z++) {
			for(int y = 0; y != PROFONDEUR; y++) {
				for(int x = 0; x != LONGUEUR; x++) {
					if(plateau[z][y][x] != Case.vide) {
						try {
							vueAerienne[y+1][x+1] = charCorrespondantVueAerienne(plateau[z][y][x]);
						}catch(Error_EnumNonTraitee e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
		
		return vueAerienne;
	}

	private char[][] vueFace() {
		char[][] vueFace = new char[HAUTEUR+2][LONGUEUR+2];
		for(int z = 0; z != vueFace.length; z++) {
			for(int x = 0; x != vueFace[z].length; x++) {
				try {
					if(	       (z==0 				&& x==0)
							|| (z==vueFace.length-1 && x==0)
							|| (z==0 				&& x==vueFace[z].length-1)
							|| (z==vueFace.length-1 && x==vueFace[z].length-1)	) {
							vueFace[z][x] = '.';
							
						} else if(z==0 || z==vueFace.length-1) {
							vueFace[z][x] = '-';
						} else if(x==0 || x==vueFace[z].length-1) {
							vueFace[z][x] = '|';
						} else {
							vueFace[z][x] = charCorrespondantVueFace(Case.vide);
						}
				} catch(Error_EnumNonTraitee e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		for(int y = PROFONDEUR-1; y != -1; y--) {
			for(int z = 0; z != HAUTEUR; z++) {
				for(int x = 0; x != LONGUEUR; x++) {
					if(plateau[z][y][x] != Case.vide) {
						try {
							vueFace[z+1][x+1] = charCorrespondantVueFace(plateau[z][y][x]);
						}catch(Error_EnumNonTraitee e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
		
		return vueFace;
	}
	
	private char[][] vueProfil() {
		char[][] vueProfil = new char[PROFONDEUR+2][HAUTEUR+2];
		for(int y = 0; y != vueProfil.length; y++) {
			for(int z = 0; z != vueProfil[y].length; z++) {
				try {
					if(	   	   (y==0 					&& z==0)
							|| (y==vueProfil.length-1	&& z==0)
							|| (y==0 					&& z==vueProfil[y].length-1)
							|| (y==vueProfil.length-1	&& z==vueProfil[y].length-1)	) {
							vueProfil[y][z] = '.';
							
						} else if(y==0 || y==vueProfil.length-1) {
							vueProfil[y][z] = '-';
						} else if(z==0 || z==vueProfil[y].length-1) {
							vueProfil[y][z] = '|';
						} else {
							vueProfil[y][z] = charCorrespondantVueProfil(Case.vide);
						}
				} catch(Error_EnumNonTraitee e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		for(int x = LONGUEUR-1; x != -1; x--) {
			for(int y = 0; y != PROFONDEUR; y++) {
				for(int z = 0; z != HAUTEUR; z++) {
					if(plateau[z][y][x] != Case.vide) {
						try {
							vueProfil[y+1][z+1] = charCorrespondantVueProfil(plateau[z][y][x]);
						}catch(Error_EnumNonTraitee e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
		
		return vueProfil;
	}
	
	private char charCorrespondantVueAerienne(Case casePlateau) throws Error_EnumNonTraitee{
		char charCorrespondant;
		
		switch(casePlateau) {
		case vide:
			charCorrespondant = ' ';
			break;
		case orientation_TN:
			charCorrespondant = '1';
			break;
		case orientation_TE:
			charCorrespondant = '1';
			break;
		case orientation_TS:
			charCorrespondant = '1';
			break;
		case orientation_TO:
			charCorrespondant = '1';
			break;
		case orientation_PN:
			charCorrespondant = '6';
			break;
		case orientation_PE:
			charCorrespondant = '6';
			break;
		case orientation_PS:
			charCorrespondant = '6';
			break;
		case orientation_PO:
			charCorrespondant = '6';
			break;
		case orientation_NT:
			charCorrespondant = '2';
			break;
		case orientation_NE:
			charCorrespondant = '3';
			break;
		case orientation_NP:
			charCorrespondant = '5';
			break;
		case orientation_NO:
			charCorrespondant = '4';
			break;
		case orientation_ET:
			charCorrespondant = '2';
			break;
		case orientation_EN:
			charCorrespondant = '4';
			break;
		case orientation_EP:
			charCorrespondant = '5';
			break;
		case orientation_ES:
			charCorrespondant = '3';
			break;
		case orientation_ST:
			charCorrespondant = '2';
			break;
		case orientation_SE:
			charCorrespondant = '4';
			break;
		case orientation_SP:
			charCorrespondant = '5';
			break;
		case orientation_SO:
			charCorrespondant = '3';
			break;
		case orientation_OT:
			charCorrespondant = '2';
			break;
		case orientation_OS:
			charCorrespondant = '4';
			break;
		case orientation_OP:
			charCorrespondant = '5';
			break;
		case orientation_ON:
			charCorrespondant = '3';
			break;
		case personnage:
			charCorrespondant = 'X';
			break;
		default:
			throw new Error_EnumNonTraitee();
		}
		
		return charCorrespondant;
	}
	
	private char charCorrespondantVueFace(Case casePlateau) throws Error_EnumNonTraitee{
		char charCorrespondant;
		
		switch(casePlateau) {
		case vide:
			charCorrespondant = ' ';
			break;
		case orientation_TN:
			charCorrespondant = '5';
			break;
		case orientation_TE:
			charCorrespondant = '3';
			break;
		case orientation_TS:
			charCorrespondant = '2';
			break;
		case orientation_TO:
			charCorrespondant = '4';
			break;
		case orientation_PN:
			charCorrespondant = '5';
			break;
		case orientation_PE:
			charCorrespondant = '4';
			break;
		case orientation_PS:
			charCorrespondant = '2';
			break;
		case orientation_PO:
			charCorrespondant = '3';
			break;
		case orientation_NT:
			charCorrespondant = '6';
			break;
		case orientation_NE:
			charCorrespondant = '6';
			break;
		case orientation_NP:
			charCorrespondant = '6';
			break;
		case orientation_NO:
			charCorrespondant = '6';
			break;
		case orientation_ET:
			charCorrespondant = '4';
			break;
		case orientation_EN:
			charCorrespondant = '5';
			break;
		case orientation_EP:
			charCorrespondant = '3';
			break;
		case orientation_ES:
			charCorrespondant = '2';
			break;
		case orientation_ST:
			charCorrespondant = '1';
			break;
		case orientation_SE:
			charCorrespondant = '1';
			break;
		case orientation_SP:
			charCorrespondant = '1';
			break;
		case orientation_SO:
			charCorrespondant = '1';
			break;
		case orientation_OT:
			charCorrespondant = '3';
			break;
		case orientation_OS:
			charCorrespondant = '2';
			break;
		case orientation_OP:
			charCorrespondant = '4';
			break;
		case orientation_ON:
			charCorrespondant = '5';
			break;
		case personnage:
			charCorrespondant = 'X';
			break;
		default:
			throw new Error_EnumNonTraitee();
		}
		
		return charCorrespondant;
	}
	
	private char charCorrespondantVueProfil(Case casePlateau) throws Error_EnumNonTraitee{
		char charCorrespondant;
		
		switch(casePlateau) {
		case vide:
			charCorrespondant = ' ';
			break;
		case orientation_TN:
			charCorrespondant = '4';
			break;
		case orientation_TE:
			charCorrespondant = '5';
			break;
		case orientation_TS:
			charCorrespondant = '3';
			break;
		case orientation_TO:
			charCorrespondant = '2';
			break;
		case orientation_PN:
			charCorrespondant = '3';
			break;
		case orientation_PE:
			charCorrespondant = '5';
			break;
		case orientation_PS:
			charCorrespondant = '4';
			break;
		case orientation_PO:
			charCorrespondant = '2';
			break;
		case orientation_NT:
			charCorrespondant = '3';
			break;
		case orientation_NE:
			charCorrespondant = '5';
			break;
		case orientation_NP:
			charCorrespondant = '4';
			break;
		case orientation_NO:
			charCorrespondant = '2';
			break;
		case orientation_ET:
			charCorrespondant = '6';
			break;
		case orientation_EN:
			charCorrespondant = '6';
			break;
		case orientation_EP:
			charCorrespondant = '6';
			break;
		case orientation_ES:
			charCorrespondant = '6';
			break;
		case orientation_ST:
			charCorrespondant = '4';
			break;
		case orientation_SE:
			charCorrespondant = '5';
			break;
		case orientation_SP:
			charCorrespondant = '3';
			break;
		case orientation_SO:
			charCorrespondant = '2';
			break;
		case orientation_OT:
			charCorrespondant = '1';
			break;
		case orientation_OS:
			charCorrespondant = '1';
			break;
		case orientation_OP:
			charCorrespondant = '1';
			break;
		case orientation_ON:
			charCorrespondant = '1';
			break;
		case personnage:
			charCorrespondant = 'X';
			break;
		default:
			throw new Error_EnumNonTraitee();
		}
		
		return charCorrespondant;
	}

	public void printPlateau() {
		List<char[][]> vues = vues3D();
		char[][] vueCentre = vues.get(0);
		char[][] vueBas = vues.get(1);
		char[][] vueGauche = vues.get(2);
		
		String print = "";
		
		for(int i = 0; i != PROFONDEUR+2; i++) {
			for(int iGauche = 0; iGauche != vueGauche[i].length; iGauche++) {
				print += vueGauche[i][iGauche];
			}
			
			print += ' ';
			
			for(int iCentre = 0; iCentre != vueCentre[i].length; iCentre++) {
				print += vueCentre[i][iCentre];
			}
			print += '\n';
		}
		
		print += '\n';
		
		for(int i = (HAUTEUR+2)-1; i != -1; i--) {
			for(int iVide = 0; iVide != HAUTEUR+2; iVide++) {
				print += ' ';
			}
			
			print += ' ';
			
			for(int iBas = 0; iBas != vueBas[i].length; iBas++) {
				print += vueBas[i][iBas];
			}
			print += '\n';
		}
		
		System.out.println(print);
	}
}
