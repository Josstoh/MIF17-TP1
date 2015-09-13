
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
 * @author Joss
 */
public class JeuEquipe extends Observable implements Runnable {

    private final int nbTortues = 5;
    private EquipeTortue equipe1;
    private EquipeTortue equipe2;
    private TortueBalle balle;
    private ArrayList<TortueEquipe> joueuses;

    private Random rGen;

    public JeuEquipe() {
        joueuses = new ArrayList<>();
        rGen = new Random();
        balle = new TortueBalle();

        equipe1 = new EquipeTortue("Joss");
        TortueEquipe t;
        int[] tab = new int[2];
        for (int i = 0; i < nbTortues; ++i) {
            t = new TortueEquipe(equipe1);
            tab = JeuDeBalle.randomCoord();
            t.setPosition(tab[0], tab[1]);
            t.setColor(1);
            joueuses.add(t);
        }

        equipe2 = new EquipeTortue("Physilk");
        for (int i = 0; i < nbTortues; ++i) {
            t = new TortueEquipe(equipe2);
            tab = JeuDeBalle.randomCoord();

            t.setPosition(tab[0], tab[1]);
            t.setColor(8);
            joueuses.add(t);
        }

        //elles s'ajoutent comme copines :)
        for (TortueAmelioree t1 : joueuses) {
            for (TortueAmelioree t2 : joueuses) {
                t1.ajouterCopine(t2);
            }
        }

        balle.setPossesseur(joueuses.get(0));
    }

    public EquipeTortue getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(EquipeTortue equipe1) {
        this.equipe1 = equipe1;
    }

    public EquipeTortue getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(EquipeTortue equipe2) {
        this.equipe2 = equipe2;
    }

    public TortueBalle getBalle() {
        return balle;
    }

    public void setBalle(TortueBalle balle) {
        this.balle = balle;
    }

    public ArrayList<TortueEquipe> getJoueuses() {
        return joueuses;
    }

    public void setJoueuses(ArrayList<TortueEquipe> joueuses) {
        this.joueuses = joueuses;
    }

    public TortueEquipe getTortueAvecBalle() {
        //return tortueAvecBalle;
        return (TortueEquipe) balle.getPossesseur();
    }

    @Override
    public void run() {
        int equipe1NbPasses = 0, equipe2NbPasses = 0;
        EquipeTortue equipeDernierePasse = null;
        int[] tab = new int[2];
        boolean passe = false;

        while (equipe1NbPasses < 10 && equipe2NbPasses < 10) {
            // on fait avancer toutes les tortues
            for (TortueEquipe t : joueuses) {
                tab = JeuDeBalle.randomCoord();
                t.avancerVers(tab[0], tab[1], 3);
            }

            // check passe
            TortueEquipe closer = (TortueEquipe) getTortueAvecBalle().getCopines()[0];
            double dist = getTortueAvecBalle().distanceAvecTortue(closer);
            for (Tortue t : getTortueAvecBalle().getCopines()) {
                if (getTortueAvecBalle().distanceAvecTortue(t) < dist) {
                    closer = (TortueEquipe) t;

                }

            }
            if (dist < 25) {
                if (getTortueAvecBalle().estAlliee(closer)) {
                    if (equipeDernierePasse == null) {
                        equipeDernierePasse = getTortueAvecBalle().equipe;
                        if (equipeDernierePasse == equipe1) {
                            equipe1NbPasses++;
                            System.out.println("Passe de l'équipe 1 !");
                        } else {
                            equipe2NbPasses++;
                            System.out.println("Passe de l'équipe 2 !");
                        }

                    } else {
                        if (getTortueAvecBalle().equipe == equipeDernierePasse) {

                            if (equipeDernierePasse == equipe1) {
                                equipe1NbPasses++;
                                System.out.println("Une Passe en plus pour l'équipe 1 : " + equipe1NbPasses + " !");
                            } else {
                                equipe2NbPasses++;
                                System.out.println("Une Passe en plus pour l'équipe 2 : " + equipe2NbPasses + " !");
                            }

                        }
                        else 
                        {
                            equipeDernierePasse = getTortueAvecBalle().equipe;
                           if (equipeDernierePasse == equipe1) {
                                equipe1NbPasses++;
                                System.out.println("Une Passe en plus pour l'équipe 1 : " + equipe1NbPasses + " !");
                            } else {
                                equipe2NbPasses++;
                                System.out.println("Une Passe en plus pour l'équipe 2 : " + equipe2NbPasses + " !");
                            } 
                        }
                    }
                } else {
                    if (closer.equipe == equipe1) {
                        equipe2NbPasses = 0;
                        System.out.println("Ooh, l'équipe 2 a perdu la balle !");
                    } else {
                        equipe1NbPasses = 0;
                        System.out.println("Ooh, l'équipe 1 a perdu la balle !");
                    }
                }
                balle.setPossesseur(closer);
            }

            
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(JeuEquipe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}