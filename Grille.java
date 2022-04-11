// Vincent Ly - Eyub Celebioglu

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Grille{

/* 
    Grille ne pouvait pas seulement être définie comme un tableau[][] de Case(l'objet) car elle devait possèder des méthodes uniques à elle-même, c'est pourquoi la classe Grille existe.
*/


//Attributs

    private Case tableau[][] = new Case[6][7];          //Tableau représentant le terrain de jeu avec les couleurs des jetons
    private int[] index = {0, 0, 0, 0, 0, 0, 0};        //Tableau index indiquant le nombre de jetons présents sur une colonne : 0 au départ

//Getter 

    public Case[][] getTableau(){
        return tableau;
    }
    public int[] getIndex(){
        return index;
    }

//Setter

    public int[] setIndex(int colonne){                 //Incrémente de 1 une case d'index : lorsqu'un joueur pose un jeton
        index[colonne]++;
        return index;
    }

//Constructeur (initialisation du tableau)

public Grille(){
    for (int i = 0; i < 6; i++){
        for (int j = 0; j < 7; j++){     
            tableau[i][j] = new Case(" ");
        }
    }
    stockAll();
}

//Save

    public void save(Grille grille){
        try{
            FileWriter w = new FileWriter("texte.txt");
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 7; j++){     
                    w.write(grille.getTableau()[i][j].getColor());
                }
            }
            w.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

//Load

    public void load(){
        try{
            BufferedReader r = new BufferedReader(new FileReader("texte.txt"));
            String ligne = r.readLine();
            int i = 0;
            int line = 0;
            int colonne = 0;
            for (line = 0; line < 6; line++){
                for (colonne = 0; colonne < 7; colonne++){
                    String a = String.valueOf(ligne.charAt(i));
                    if (a.equals("J") || a.equals("R")){
                        index[colonne]++;
                    }
                    tableau[line][colonne].setColor(a) ;
                    i++;
                }
            }
            r.close();
        }
        catch(Exception e){
            System.out.println(e);
        }      
    }
    

//Stockage des voisins de chaque Case

    public void stockAll(){
        for (int i = 1; i < 5; i++){            //affecte les cases qui ne touchent pas le bord du tableau
            for (int j = 1; j < 6; j++){
                tableau[i][j].StockageVoisin( tableau[i-1][1].getColor(), tableau[i+1][j].getColor(), tableau[i][j-1].getColor(), tableau[i][j+1].getColor()) ;
            }
        }
        for (int j = 1; j < 6; j++){            //affecte les cases qui touchent le haut du tableau
            int i = 0;
            tableau[i][j].StockageVoisin( "N", tableau[i+1][j].getColor(), tableau[i][j-1].getColor(), tableau[i][j+1].getColor()) ;
        }
        for (int j = 1; j < 6; j++){            //affecte les cases qui touchent le bas du tableau
            int i = 5;
            tableau[i][j].StockageVoisin( tableau[i-1][j].getColor(), "N", tableau[i][j-1].getColor(), tableau[i][j+1].getColor() );
        }
        for (int i = 1; i < 5; i++){            //affecte les cases qui touchent la gauche du tableau
            int j = 0;
            tableau[i][j].StockageVoisin(tableau[i-1][j].getColor(), tableau[i+1][j].getColor(), "N", tableau[i][j+1].getColor() );
        }
        for (int i = 1; i < 5; i++){            //affecte les cases qui touchent la droite du tableau
            int j = 6; 
            tableau[i][j].StockageVoisin( tableau[i-1][j].getColor(), tableau[i+1][j].getColor(), tableau[i][j-1].getColor(), "N") ;
        }
        
                        //affecte les cases qui sont dans les 4 coins du tableau
        tableau[0][0].StockageVoisin( "N", tableau[1][0].getColor(), "N", tableau[0][1].getColor() );
        tableau[5][0].StockageVoisin( tableau[4][0].getColor(), "N", "N", tableau[5][1].getColor()) ;
        tableau[0][6].StockageVoisin( "N", tableau[1][6].getColor(), tableau[0][5].getColor(), "N") ;
        tableau[5][6].StockageVoisin( tableau[4][6].getColor(), "N", tableau[5][5].getColor(), "N") ;
    }
    

//Affichage

    public void affichetableau(){
        System.out.println("   0     1     2     3     4     5     6     ");
        for (int i = 0; i < 6; i++){
            System.out.print("+-----+-----+-----+-----+-----+-----+-----+\n");
            for (int j = 0; j < 7; j++){
                System.out.print("|" + "  ");
                tableau[i][j].printColor();
            }
            System.out.println("|  " + i);
        }
        System.out.print("+-----+-----+-----+-----+-----+-----+-----+\n");
    }

        public void afficheIndex(){
            for (int j = 0; j < 7; j++){
                System.out.print("|" + index[j] + "  ");
            }
            System.out.println("\n");
        }


    public int poseJeton(Case tableau[][], int j, String b){       
        int i = 0 ;
        while (tableau[i][j].getColor().equals(" ") && i < 5){                          // tant que la case (pas le classe Case) est vide et que nous ne sommes pas sur la ligne du haut
            if (tableau[i+1][j].getColor().equals(" ") == false){                       // si la case en dessous n'est pas vide 
                tableau[i][j].setColor(b);                                              // alors on colorie la case actuelle
            }
            else{
                i++;                                                                    //sinon on descend 
            }
        }
        if (tableau[i][j].getColor().equals(" ")){                                      
            tableau[i][j].setColor(b);
        }
        stockAll();                                                                     //on restocke tous les voisins de toutes les cases après avoir pose un jeton
        return i;
    }
}