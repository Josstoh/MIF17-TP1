
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
    
    public JeuDeBalle() {
        //création des tortues
        // et on leurs donne des positions aléatoires au passage
        for(int i = 0; i < nb_tortue; ++i) {
            TortueAmelioree t = new TortueAmelioree();
            
            t.setPosition((int) (Math.random()*100) % 100, (int) (Math.random()*100) % 100);
            joueuses.add(t);
        }
        
        //elles s'ajoutent comme copines :)
        for(TortueAmelioree t1 : joueuses) {
            for(TortueAmelioree t2 : joueuses) {
                t1.ajouterCopine(t2);
            }
        }
        
        
        
    }
    
    
    
}
