// Vincent Ly - Eyub Celebioglu

import java.io.Console;
import java.lang.Math;

public class Game {

//Attributs

    private int gagnee = 0;                 //permet de definir l'etat de la partie
    private int nbTours = 0;                //permet de distinguer les 2 joueurs en fonction du nombre de tours
    private int compteurFin = 0;             
    private int choixColonne;               //indique la colonne où le joueur pose son jeton
    private int atterrissageLigne;          //indique la ligne ou atterrit le jeton
    private Console console = System.console();     //permet la saisie de caractères dans la console
    private Grille grille;    
    
//Constructeur

    public Game(Joueur p1, Joueur p2){
        grille = new Grille();
    }

//Lancement de la partie

    public void GameStart(Joueur p1, Joueur p2){
        while (gagnee == 0){
            if (nbTours % 2 == 0){
                do {
                    System.out.println("        " + p1.getName() + ", a votre tour de jouer.\nVeuillez choisir une colonne ou appuyez sur 9 pour sauvegarder et quitter.\n");
                    choixColonne = Integer.valueOf(console.readLine());
                    if (choixColonne == 9){
                        System.out.println("        La partie a ete sauvegardee.");
                        grille.save(grille);
                        System.exit(0);
                    }
                }
                while(choixColonne > 6);    

                while (grille.getIndex()[choixColonne] > 5){
                    System.out.println("        La colonne est déjà remplie, veuillez reposer votre jeton.");
                    choixColonne = Integer.valueOf(console.readLine());
                    /*for (int i = 0; i < 7;i++){
                        if(grille.getIndex()[i] >= 6){
                            compteurFin++;
                        }
                    }
                    if (compteurFin >= 7){
                        System.out.println("LA GRILLE EST REMPLIE DONC EGALITE");
                        System.exit(0);
                    }*/
                }
                atterrissageLigne = grille.poseJeton( grille.getTableau(), choixColonne, p1.getColor() );
                grille.setIndex(choixColonne);
                grille.affichetableau();
                if (grille.getTableau()[atterrissageLigne][choixColonne].check(grille.getTableau(), atterrissageLigne, choixColonne) ){
                    gagnee = 1;
                    System.out.println("\n            Le joueur " + p1.getName() + "(" + p1.getColor() + ")" + " a remporte la partie.\n");
                }
                for (int i = 0; i < 6;i++){
                    for(int j = 0; j < 7; j++){
                        if(grille.getTableau()[i][j].getColor().equals(" ")){               // On regarde si la grille est remplie 
                        compteurFin++;
                        }
                    }
                }
                if (compteurFin == 0){
                    System.out.println("LA GRILLE EST REMPLIE DONC EGALITE");
                    System.exit(0);
                }
            }
            else{
                do {
                    System.out.println("        " + p2.getName() + ", a votre tour de jouer.\nVeuillez choisir une colonne ou appuyez sur 9 pour sauvegarder et quitter.\n");
                    choixColonne = Integer.valueOf(console.readLine());
                    if (choixColonne == 9){
                        System.out.println("        La partie a ete sauvegardee.");
                        grille.save(grille);
                        System.exit(0);
                    }
                }
                while(choixColonne > 6);

                while (grille.getIndex()[choixColonne] > 6){
                    System.out.println("        La colonne est déjà remplie, veuillez reposer votre jeton.");
                    choixColonne = Integer.valueOf(console.readLine());
                }
                atterrissageLigne = grille.poseJeton( grille.getTableau(), choixColonne, p2.getColor() );
                grille.setIndex(choixColonne);
                grille.affichetableau();
                if (grille.getTableau()[atterrissageLigne][choixColonne].check(grille.getTableau(), atterrissageLigne, choixColonne) ){
                    gagnee = 1;
                    System.out.println("\n            Le joueur " + p2.getName() + "(" + p2.getColor() + ")" + " a remporte la partie.\n");
                }
            }
            nbTours++;
   
        }
    }

    public void GameStartAvecBot (Joueur p1, Joueur p2){
        while (gagnee == 0){
            if (nbTours % 2 == 0){
                
                do {
                    System.out.println("        " + p1.getName() + ", a votre tour de jouer.\nVeuillez choisir une colonne ou appuyez sur 9 pour sauvegarder et quitter.\n");
                    choixColonne = Integer.valueOf(console.readLine());
                    if (choixColonne == 9){
                        System.out.println("        La partie a ete sauvegardee.");
                        grille.save(grille);
                        System.exit(0);
                    }
                }
                while(choixColonne > 6);    

                while (grille.getIndex()[choixColonne] > 5){
                    System.out.println("        La colonne est déjà remplie, veuillez reposer votre jeton.");
                    choixColonne = Integer.valueOf(console.readLine());
                }
                atterrissageLigne = grille.poseJeton( grille.getTableau(), choixColonne, p1.getColor() );
                grille.setIndex(choixColonne);
                grille.affichetableau();
                if (grille.getTableau()[atterrissageLigne][choixColonne].check(grille.getTableau(), atterrissageLigne, choixColonne) ){
                    gagnee = 1;
                    System.out.println("\n            Le joueur " + p1.getName() + "(" + p1.getColor() + ")" + " a remporte la partie.\n");
                }
                for (int i = 0; i < 6;i++){
                    for(int j = 0; j < 7; j++){
                        if(grille.getTableau()[i][j].getColor().equals(" ")){
                        compteurFin++;
                        }
                    }
                }
                if (compteurFin == 0){
                    System.out.println("LA GRILLE EST REMPLIE DONC EGALITE");
                    System.exit(0);
                }
            }
            else{

                int random_colonne = (int)(Math.random()*(6-0)) + 0; 
                atterrissageLigne = grille.poseJeton( grille.getTableau(), random_colonne, p2.getColor() );
                grille.setIndex(choixColonne);
                grille.affichetableau();
                if (grille.getTableau()[atterrissageLigne][random_colonne].check(grille.getTableau(), atterrissageLigne, random_colonne) ){
                    gagnee = 1;
                    System.out.println("\n            Le joueur " + p2.getName() + "(" + p2.getColor() + ")" + " a remporte la partie.\n");
                }
            }
            nbTours++;
   
        }
    }

    
    public static void main(String args[]){
        Console consolee = System.console();
        Joueur player_1 = new Joueur();
        Joueur player_2 = new Joueur();
        int a, b;
        Game g = new Game(player_1,player_2);

        do{
            System.out.println("\n0 : Partie PvP avec nom aléatoire et couleur aléatoire\n1 : Partie avec choix de nom et couleur\n2 : Partie contre un bot\n");
            
            a = Integer.valueOf(Integer.valueOf(consolee.readLine() ) );
            
            if (a == 2){
                player_1.setName("Joueur 1");
                player_1.setColor("R");
                player_2.setName("BOT");
                player_2.setColor("J");
                g.grille.affichetableau();
            }
        }
        
        while(a != 1 && a != 0 && a != 2);
        if (a == 1){

            //Saisie des données relatives aux joueurs

            System.out.println("Saisissez le nom du premier joueur.");
            player_1.setName(String.valueOf(consolee.readLine()));

            System.out.println("Saisissez la couleur du premier joueur. J ou R.");
            player_1.setColor(String.valueOf(consolee.readLine()));

            System.out.println("Saisissez le nom du deuxieme joueur.");
            player_2.setName(String.valueOf(consolee.readLine()));

            System.out.println("Saisissez la couleur du deuxieme joueur. J ou R.");
            player_2.setColor(String.valueOf(consolee.readLine()));

            System.out.println("\n " + player_1.getName() + "("+player_1.getColor()+") VS " + player_2.getName() + "("+player_2.getColor()+")\n");

        }
        else if(a == 2){
            do{
                System.out.println("\nVoulez-vous charger une partie precedente ?\n       1 : OUI     0 : NON");
                b = Integer.valueOf(Integer.valueOf(consolee.readLine() ) );
            }
            while(b != 1 && b != 0);
            if ( b == 1 ){
                g.grille.load();
                g.grille.affichetableau();
                b=-1;
                g.GameStartAvecBot(player_1, player_2);   
            }
            else{
    
                g.grille.affichetableau();
                b=-1;
                g.GameStartAvecBot(player_1, player_2);
                System.exit(0);
            }
               
        }
        else{
            player_1.setName("Joueur A");
            player_1.setColor("R");
            player_2.setName("Joueur B");
            player_2.setColor("J");

            System.out.println("\n " + player_1.getName() + "("+player_1.getColor()+") VS " + player_2.getName() + "("+player_2.getColor()+")\n");
        }

//Lancement de la partie
        do{
            System.out.println("\nVoulez-vous charger une partie precedente ?\n       1 : OUI     0 : NON");
            b = Integer.valueOf(Integer.valueOf(consolee.readLine() ) );
        }
        while(b != 1 && b != 0);
        if ( b == 1 ){
            g.grille.load();
            g.grille.affichetableau();
            g.GameStart(player_1, player_2);
        }
        else{

            g.grille.affichetableau();
            g.GameStart(player_1, player_2);
        }
    }
}

