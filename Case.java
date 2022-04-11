// Vincent Ly - Eyub Celebioglu

import java.util.EnumMap ;

public class Case{

    protected enum SENS{         
        voisinhaut,         
        voisinbas,                  
        voisingauche,
        voisindroite,  
    }
//Attributs
    private String color;
    private EnumMap<SENS, String> stockage = new EnumMap <SENS,String> (SENS.class);
    private int compteur = 0;

//Constructeur
    public Case(String a){
        color = a;
    }

//Setter
    public void setColor(String color){
        this.color = color;
    }

//Getter
    public String getColor(){
        return color;
    }
    public EnumMap<SENS, String> getStockage(){
        return stockage;
    }

//Affichage
    public void printColor(){
        System.out.print(this.color + "  ");
    }

//Stockage des voisins
    public void StockageVoisin(String a, String b, String c, String d){
        stockage.put(SENS.voisinhaut, a);
        stockage.put(SENS.voisinbas, b);
        stockage.put(SENS.voisingauche, c);
        stockage.put(SENS.voisindroite, d);
    }

//Vérification de la présence d'un voisin dans les 8 directions
    public int checkDroite(Case tab[][], int i, int j, int compteur){
        if (tab[i][j].getColor().equals("J") == false && tab[i][j].getColor().equals("R") == false){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i][j].getStockage().get(SENS.voisindroite).equals(tab[i][j].getColor()) ){
            compteur++;
            return checkDroite(tab, i, j+1, compteur);
        }
        return compteur;
    }


    public int checkGauche(Case tab[][], int i, int j, int compteur){
        if (tab[i][j].getColor().equals("J") == false && tab[i][j].getColor().equals("R") == false ){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i][j].getStockage().get(SENS.voisingauche).equals(tab[i][j].getColor() )){
            compteur++;
            return checkGauche(tab, i, j-1, compteur);
        }
        return compteur;
    }


    public int checkHaut(Case tab[][], int i, int j, int compteur){
        if (tab[i][j].getColor().equals("J") == false && tab[i][j].getColor().equals("R") == false){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i][j].getStockage().get(SENS.voisinhaut).equals(tab[i][j].getColor() )){
            compteur++;
            return checkHaut(tab, i-1, j, compteur);
        }
        return compteur;
    }


    public int checkBas(Case tab[][], int i, int j, int compteur){
        if (tab[i][j].getColor().equals("J") == false && tab[i][j].getColor().equals("R") == false){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i][j].getStockage().get(SENS.voisinbas).equals(tab[i][j].getColor() )){
            compteur++;
            return checkBas(tab, i+1, j, compteur);
        }
        return compteur;
    }

    public int checkDiag1(Case tab[][], int i, int j, int compteur){
        if (i == 0 || j == 6){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i-1][j+1].getColor().equals(tab[i][j].getColor() ) && tab[i][j].getColor().equals(" ") == false){
            compteur++;
            return checkDiag1(tab, i-1, j+1, compteur);
        }
        return compteur;
    }

    public int checkDiag2(Case tab[][], int i, int j, int compteur){
        if (i == 5 || j == 6){
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i+1][j+1].getColor().equals(tab[i][j].getColor() ) && tab[i][j].getColor().equals(" ") == false){
            compteur++;
            return checkDiag2(tab, i+1, j+1, compteur);
        }
        return compteur;
    }

    public int checkDiag3(Case tab[][], int i, int j, int compteur){
        if (i == 5 || j == 0) {
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i+1][j-1].getColor().equals(tab[i][j].getColor() ) && tab[i][j].getColor().equals(" ") == false){
            compteur++;
            return checkDiag3(tab, i+1, j-1, compteur);
        }
        return compteur;
    }

    public int checkDiag4(Case tab[][], int i, int j, int compteur){
        if (i == 0 || j == 0) {
            return compteur;
        }
        if (compteur == 3){
            return compteur;
        }
        else if( tab[i-1][j-1].getColor().equals(tab[i][j].getColor() ) && tab[i][j].getColor().equals(" ") == false){
            compteur++;
            return checkDiag4(tab, i-1, j-1, compteur);
        }
        return compteur;
    }

//Vérification d'un alignement de 4 jetons sur les 8 directions
    public boolean check(Case tab[][], int i, int j){
        if ( (checkBas(tab, i, j, compteur) + checkHaut(tab, i, j, compteur) ) == 3){
            return true;
        }
        if ( (checkGauche(tab, i, j, compteur) + checkDroite(tab, i, j, compteur) ) == 3){
            return true;
        }
        if ( (checkDiag1(tab, i, j, compteur) + checkDiag3(tab, i, j, compteur) ) == 3){
            return true;
        }
        if ( (checkDiag2(tab, i, j, compteur) + checkDiag4(tab, i, j, compteur) ) == 3){
            return true;
        }
        compteur = 0;
        return false;
    }

}