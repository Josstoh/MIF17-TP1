
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1203723
 */
public class TortueBalle extends Tortue {

    private static final int tailleCercleTortue = 8;
    private TortueAmelioree possesseur;

    public TortueBalle() {
        super();
        possesseur = null;
    }
    
    
    
    @Override
    public void drawTurtle(Graphics graph) {
        if (graph==null)
            return;
        
        if(possesseur != null) {
            x = possesseur.x;
            y = possesseur.y;
        }
        graph.setColor(Color.red);
        graph.fillOval(x-tailleCercleTortue, y+tailleCercleTortue, tailleCercleTortue, tailleCercleTortue);
    }

    public TortueAmelioree getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(TortueAmelioree possesseur) {
        this.possesseur = possesseur;
    }
    
   
    
}
