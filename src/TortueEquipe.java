/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joss
 */
class TortueEquipe extends TortueAmelioree{
    protected EquipeTortue equipe;

    public TortueEquipe(EquipeTortue e ) {
        super();
        this.equipe = e;
        equipe.ajouter(this);
    }

    boolean estAlliee(TortueEquipe t)
    {
        return (this.equipe == t.equipe);
          
    }
    
    public void avancer(int dist, TortueBalle tb) {
        //
        if((TortueEquipe)tb.getPossesseur() == this)
            
            
            
        // sinon
        super.avancer(dist);
        
    }
    
}
