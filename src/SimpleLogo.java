
// package logo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

				J. Ferber - 1999-2001

				Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/


**************************************************************************/


public class SimpleLogo extends JFrame implements ActionListener,Observer {
	public static final Dimension VGAP = new Dimension(1,5);
	public static final Dimension HGAP = new Dimension(5,1);

	private FeuilleDessin feuille;
	private Tortue courante;
	private JTextField inputValue;
        
        private JeuDeBalle jeu;

	// la procedure principale
	public static void main(String[] args) {
		System.out.println( "Logo demarre!" );
		new SimpleLogo();
	}

	private void quitter() {
		System.exit(0);
	}

	public SimpleLogo() {
		super("un logo tout simple");
                jeu = null;
		logoInit();
	}

	public void logoInit() {
		getContentPane().setLayout(new BorderLayout(10,10));

		// Boutons
		JToolBar toolBar = new JToolBar();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(toolBar);

		getContentPane().add(buttonPanel,"North");

		addButton(toolBar,"Effacer","Nouveau dessin","/icons/New24.gif");

		toolBar.add(Box.createRigidArea(HGAP));
		inputValue=new JTextField("45",5);
		toolBar.add(inputValue);
		addButton(toolBar, "Avancer", "Avancer 50", null);
		addButton(toolBar, "Droite", "Droite 45", null);
		addButton(toolBar, "Gauche", "Gauche 45", null);
		addButton(toolBar, "Lever", "Lever Crayon", null);
		addButton(toolBar, "Baisser", "Baisser Crayon", null);

		String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
								 "vert", "gris clair", "magenta", "orange",
								 "gris", "rose", "jaune"};

		// Create the combo box
		toolBar.add(Box.createRigidArea(HGAP));
		JLabel colorLabel = new JLabel("   Couleur: ");
		toolBar.add(colorLabel);
		JComboBox colorList = new JComboBox(colorStrings);
		toolBar.add(colorList);

		colorList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int n = cb.getSelectedIndex();
				courante.setColor(n);
			}
		});


		// Menus
		JMenuBar menubar=new JMenuBar();
		setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
		menubar.add(menuFile);

		addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
		menubar.add(menuCommandes);
		addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
		addMenuItem(menuCommandes, "Droite", "Droite", -1);
		addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
		addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
		addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
		menubar.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// les boutons du bas
		JPanel p2 = new JPanel(new GridLayout());
		JButton b20 = new JButton("Proc1");
		p2.add(b20);
		b20.addActionListener(this);
		JButton b21 = new JButton("Proc2");
		p2.add(b21);
		b21.addActionListener(this);
		JButton b22 = new JButton("Proc3");
		p2.add(b22);
                b22.addActionListener(this);
                JButton b23 = new JButton("Proc4");
		p2.add(b23);
		b23.addActionListener(this);
                JButton b24 = new JButton("Proc5");
		p2.add(b24);
		b24.addActionListener(this);
                JButton b25 = new JButton("Proc6");
		p2.add(b25);
		b25.addActionListener(this);
                JButton b26 = new JButton("Proc7");
		p2.add(b26);
		b26.addActionListener(this);
                JButton b27 = new JButton("Proc8");
		p2.add(b27);
		b27.addActionListener(this);

		getContentPane().add(p2,"South");

		feuille = new FeuilleDessin(); //500, 400);
		feuille.setBackground(Color.white);
		feuille.setSize(new Dimension(600,400));
		feuille.setPreferredSize(new Dimension(600,400));
			
		getContentPane().add(feuille,"Center");
		
		// Creation de la tortue
		Tortue tortue = new Tortue();
		
		// Deplacement de la tortue au centre de la feuille
		tortue.setPosition(500/2, 400/2); 		
		
		courante = tortue;
		feuille.addTortue(tortue);

		pack();
		setVisible(true);
	}

	public String getInputValue(){
		String s = inputValue.getText();
		return(s);
	}

	/** la gestion des actions des boutons */
	public void actionPerformed(ActionEvent e)
	{
		String c = e.getActionCommand();

		// actions des boutons du haut
		if (c.equals("Avancer")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.avancer(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
			
		}
		else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Lever")) 
			courante.leverCrayon();
		else if (c.equals("Baisser"))
			courante.baisserCrayon();
		// actions des boutons du bas
		else if (c.equals("Proc1"))
			proc1();
		else if (c.equals("Proc2"))
			proc2();
		else if (c.equals("Proc3"))
			proc3();
                else if (c.equals("Proc4"))
			proc4();
                else if (c.equals("Proc5"))
			proc5();
                else if (c.equals("Proc6"))
			proc6();
                else if (c.equals("Proc7"))
			proc7();
                else if (c.equals("Proc8"))
			proc8();
		else if (c.equals("Effacer"))
			effacer();
		else if (c.equals("Quitter"))
			quitter();

		feuille.repaint();
	}

  	/** les procedures Logo qui combine plusieurs commandes..*/
	public void proc1() {
		courante.carre();
	}

	public void proc2() {
		courante.poly(60,8);
	}

	public void proc3() {
		courante.spiral(50,40,6);
	}
        
        public void proc4() {
            courante.maison();
        }
        
        public void proc5() {
            courante.etoile(45, 100);
        }
        
        public void proc6() {
            TortueAmelioree t1 = new TortueAmelioree("Franklin");
            t1.setPosition(500/2 - 200, 400/2 + 200);
            TortueAmelioree t2 = new TortueAmelioree("Tortue Genial");
            t2.setPosition(500/2 - 200 , 400/2 + 150);
            TortueAmelioree t3 = new TortueAmelioree("Donatello");
            t3.setPosition(500/2 -200 , 400/2 + 100);
            TortueAmelioree t4 = new TortueAmelioree();
            t4.setPosition(500/2 - 200, 400/2 + 50);
            
            
            
            t1.ajouterCopine(t2);
            t1.ajouterCopine(t3);
            t2.ajouterCopine(t4);
            t2.ajouterCopine(t3);
            t2.ajouterCopine(t1);
            t4.ajouterCopine(t2);
            t4.ajouterCopine(t3);
            t4.ajouterCopine(t1);
            
            
            feuille.addTortue(t1);
            feuille.addTortue(t2);
            feuille.addTortue(t3);
            feuille.addTortue(t4);
            
           // t1.avancer(40);
            courante = t1;
        }
        
        public void proc7() {
            if(jeu == null) {
                jeu = new JeuDeBalle();
                jeu.addObserver(this);
                for(Tortue t : jeu.getJoueuses()) {
                    feuille.addTortue(t);
                }
                feuille.addTortue(jeu.getBalle());
                return;
            }
            
            new Thread(jeu).start();
            
        }
        
        //pour des tests
        
        public void proc8() {
            TortueAmelioree t1 = new TortueAmelioree("Franklin");
            t1.setPosition(0, 10);
            TortueAmelioree t2 = new TortueAmelioree("Tortue Genial");
            t2.setPosition(600, 400);
            TortueAmelioree t3 = new TortueAmelioree("Donatello");
            t3.setPosition(0, 400);
            TortueAmelioree t4 = new TortueAmelioree();
            t4.setPosition(600, 10);
            
            feuille.addTortue(t1);
            feuille.addTortue(t2);
            feuille.addTortue(t3);
            feuille.addTortue(t4);
            
        }

	// efface tout et reinitialise la feuille
	public void effacer() {
		feuille.reset();
		feuille.repaint();

		// Replace la tortue au centre
		Dimension size = feuille.getSize();
		courante.setPosition(size.width/2, size.height/2);
	}

	//utilitaires pour installer des boutons et des menus
	public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
		JButton b;
		if ((imageName == null) || (imageName.equals(""))) {
			b = (JButton)p.add(new JButton(name));
		}
		else {
			java.net.URL u = this.getClass().getResource(imageName);
			if (u != null) {
				ImageIcon im = new ImageIcon (u);
				b = (JButton) p.add(new JButton(im));
			}
			else
				b = (JButton) p.add(new JButton(name));
			b.setActionCommand(name);
		}

		b.setToolTipText(tooltiptext);
		b.setBorder(BorderFactory.createRaisedBevelBorder());
		b.setMargin(new Insets(0,0,0,0));
		b.addActionListener(this);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		menuItem.addActionListener(this);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
	}

    @Override
    public void update(Observable o, Object arg) {
        feuille.repaint();
        feuille.showTurtles(getGraphics());
    }
}
