
import java.util.AbstractSet;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joss
 */
public class EquipeTortue {
    private String nomJoueur;
    private HashSet<TortueEquipe> tortues;

    EquipeTortue(String nomJoueur) {
        this.nomJoueur = nomJoueur;
        tortues = new HashSet<TortueEquipe>();
    }
    
    
    void ajouter(TortueEquipe t)
    {
        tortues.add(t);
    }
    
    void supprimer(TortueEquipe t)
    {
        if(tortues.contains(t))
            tortues.remove(t);
    }
}
