
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1203723
 */
public class TortueAmelioree extends Tortue {
    private static int nb_tortue_cree = 0;
    
    private String nom;
    
    private HashSet<Tortue> copines;
    
    TortueAmelioree() {
        super();
        copines = new HashSet<>();
        nom = "Tortue" + nb_tortue_cree;
        nb_tortue_cree++;
       
    }
    TortueAmelioree(String nom) {
        super();
        copines = new HashSet<>();
        this.nom = nom;
        nb_tortue_cree++;
    }
    
    public void afficherNom() {
        System.out.println("je m'appelle " + nom);
    }
    
    public void ajouterCopine(Tortue t) {
        if(copines.contains(t) || this == t) return;
        copines.add(t);
    }
    
    public void supprimerCopine(Tortue t) {
        copines.remove(t);
    }
    
    public double distanceAvecTortue(Tortue t) {
        return sqrt( pow(x - t.x, 2) + pow(y - t.y, 2));
    }
    
    public HashSet<Tortue> copinesProche() {
        HashSet<Tortue> copinesProche = new HashSet<Tortue>();
        for(Tortue t : copines) {
            if(distanceAvecTortue(t) <= 15) copinesProche.add(t);
        }
        return copinesProche;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        for(Tortue t : copines) {
            if(distanceAvecTortue(t) <= 15) {
                if(t instanceof TortueAmelioree) {
                    System.out.println("Bonjour " + ((TortueAmelioree) t).getNom() + " peux tu avancer stp ?!");
                    t.avancer(31);
                }
            }
        }
        
    }
   
    
}
