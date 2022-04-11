// Vincent Ly - Eyub Celebioglu

public class Joueur {

//Attributs

    private String name;
    private String color;

//Constructeur

    public Joueur(String a, String b){
        name = a;
        color = b;
    }

    public Joueur(){
        name = "";
        color = "";
    }

//Getter
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }

//Setter
    public void setName(String a){
        name = a;
    }
    public void setColor(String a){
        color = a;
    }


}
