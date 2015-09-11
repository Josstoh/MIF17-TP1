
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1203723
 */
public class JeuDeBalle {
    
    private static final int nb_tortue = 10;

    private ArrayList<TortueAmelioree> joueuses;
    private TortueBalle balle;
    //private TortueAmelioree tortueAvecBalle;
    
    public JeuDeBalle() {
        
        TortueAmelioree t;
        
        //création des tortues
        // et on leurs donne des positions aléatoires au passage
        for(int i = 0; i < nb_tortue; ++i) {
            t = new TortueAmelioree();
            
            t.setPosition((int) (Math.random()*100) % 100, (int) (Math.random()*100) % 100);
            joueuses.add(t);
        }
        
        //elles s'ajoutent comme copines :)
        for(TortueAmelioree t1 : joueuses) {
            for(TortueAmelioree t2 : joueuses) {
                t1.ajouterCopine(t2);
            }
        }
        
        //on donne la balle a une tortue
        //tortueAvecBalle = joueuses.get(0);
        balle.setPossesseur(joueuses.get(0));
    }

    public ArrayList<TortueAmelioree> getJoueuses() {
        return joueuses;
    }

    public TortueBalle getBalle() {
        return balle;
    }

    public TortueAmelioree getTortueAvecBalle() {
        //return tortueAvecBalle;
        return balle.getPossesseur();
    }
    
    public int[] randomCoord() {
        int[] tab = new int[2];
        
        tab[0] = ( (int) (Math.random() * 1000) % 300);
        tab[1] = ( (int) (Math.random() * 1000) % 300);
        
        return tab;
    }
    
    public void step() {
        //on fait avancer toutes les joueuses
        int[] tab = new int[2];
        for(TortueAmelioree t : joueuses) {
            tab = randomCoord();
            t.avancerVers(tab[0], tab[1], 10);
        }
        
        
        //on check la distance des joueuses par rapport a celle qui a la balle
        //la balle est passée a une autre joueuse si elle se trouve a moins de 15 pixels
        //de la possesseuse de balle
        TortueAmelioree closer = (TortueAmelioree) getTortueAvecBalle().getCopines()[0];
        /*int dist = -1;
        for(Tortue t : getTortueAvecBalle().getCopines()) {
            
        }*/
        
    }
    
}
