
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1203723
 */
public class JeuDeBalle  extends Observable implements Runnable{
    
    private static final int nb_tortue = 10;

    private ArrayList<TortueAmelioree> joueuses;
    private TortueBalle balle;
    
    private Random rGen;
    //private TortueAmelioree tortueAvecBalle;
    
    public JeuDeBalle() {
        rGen = new Random();
        joueuses = new ArrayList<>();
        balle = new TortueBalle();
        TortueAmelioree t;
        
        //création des tortues
        // et on leurs donne des positions aléatoires au passage
        int[] tab = new int[2];
        for(int i = 0; i < nb_tortue; ++i) {
            t = new TortueAmelioree();
            tab = randomCoord();
            
            t.setPosition(tab[0], tab[1]);
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
    
    //genere des coordonées aléatoires entre x:0-600 y:10-400
    public final int[] randomCoord() {
        int[] tab = new int[2];
        
        tab[0] = ( (int) (rGen.nextDouble() * 10000) % 600);
        tab[1] = 10 + ( (int) (rGen.nextDouble() * 10000) % 390);
        
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
        double dist = getTortueAvecBalle().distanceAvecTortue(closer);
        for(Tortue t : getTortueAvecBalle().getCopines()) {
            if(getTortueAvecBalle().distanceAvecTortue(t) < dist) 
                closer = (TortueAmelioree) t;
            
        }
        
        //si la distance est sufisamment petite la balle est passée
        if(dist < 25)
            balle.setPossesseur(closer);
        
            
        
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(JeuDeBalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            step();
            setChanged();
            notifyObservers();
        }
    }
    
}
