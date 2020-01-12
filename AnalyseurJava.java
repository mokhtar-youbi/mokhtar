
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImageFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.io.*;

public class AnalyseurJava {

	private JFrame frmAnalyseur;
	JTextArea textArea;
	static ArrayList<String> mots = new ArrayList<String>();
	static ArrayList<String> lignes = new ArrayList<String>();
	static ArrayList<String> lex = new ArrayList<String>();
	static String[] mot;
	static JFileChooser file_chooser = new JFileChooser("C:\\Users\\jkl\\Desktop\\Documents");
	static FileNameExtensionFilter fich = new FileNameExtensionFilter("Fichiers text", "snl");	
	
/////////////////////////	
	public static void charger() throws FileNotFoundException {
		file_chooser.addChoosableFileFilter(fich);
		if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file = file_chooser.getSelectedFile();
			Scanner sc_lignes = new Scanner(file);
			Scanner sc_mots = new Scanner(file);
			mots.clear();
			lignes.clear();
				while(sc_lignes.hasNextLine()){
					lignes.add(sc_lignes.nextLine());
				}
				while(sc_mots.hasNext()){
					mots.add(sc_mots.next());
					}
				
			sc_mots.close();
			sc_lignes.close();
			}	
	}

///////////////////////////////	

	public boolean isNum(String chaine, int i) {
		char[] nombre = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int j = 0;
		while (j < nombre.length) {
			if (chaine.charAt(i) == nombre[j]) {
				return true;
			}
			j++;
		}

		return false;
	}

	public String num(String chaine) {
		int i = 0;
		int a = 0;
		boolean point_unique = true;
		while (i < chaine.length()) {
			if (isNum(chaine, i)) a++;
			else if(point_unique & chaine.charAt(a)=='.') {
				a++;
				point_unique = false;
			}
			i++;
		}
		
		if (a == chaine.length() && !chaine.contains(",")) return "Nombre entier";
		else if (a == chaine.length() && !point_unique) return "Nombre reel";
		return null;

	}
	
/*********************************************************************************************************************/	
	
	public boolean isChar(String chaine, int i) {
		char[] alphabet = { 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 
				'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
				'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
				'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q',
				'R', 'r', 'S', 's', 'T',
				't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 
				'x', 'Y', 'y', 'Z', 'z',',','\"','%'};
		int k = 0;
		while (k < alphabet.length) {
			if (chaine.charAt(i) == alphabet[k]) {
				return true;
			}
			k++;
		}
		return false;

	}

	public String id(String chaine) {
		boolean verifier_Premier = false;
		boolean tiret_unique = true;
		int a = 0;
		int i = 0;
		if (isChar(chaine, 0)) {
			a++;
			verifier_Premier = true;
		}
		if (verifier_Premier == true && chaine.length() == 1)
			return "identificateur";
		
		else if (chaine.length() > 1) {
			i = 1;
			while (i < chaine.length()) {
				
				if (isChar(chaine, i))
					{a++;
					tiret_unique=true;
					}
				else if (isNum(chaine, i))
					{a++;
					tiret_unique=true;
					}
				else if (chaine.charAt(i) == '_' && tiret_unique) {
					tiret_unique=false;
					a++;
				}
				i++;
			}
			if (a == chaine.length())
				return "Identificateur";
		}
		return null;
	}
	
/////////////////////////////////////////////////////////////////////////////////////	
	
	public String val(String chaine) {
		String[] mot_reserve = { "Snl_Star",
				                  "Snl_Int",
				                  ",",
				                  "i j Aft_5 f_f_5",
				                  "%.",
				                  "Set",
				                  "Snl_Real",
				                  "If",
				                  "%",
				                  "<", 
                                  "Else",
				                  "Start",
				                  "Get", 
				                  "Finish",
				                  "Snl_Put",
				                  "ceci_est_un_message",
				                    "\"" ,
				                   "%..",
				                   "Snl_Close" };

		String[] Affichage = {"Mot réservé début de programme",
				              " Mot reserve pour déclaration d'un entier",
				              " Mot réservé virgule ",
				               "identificateur",
				               "Mot réservé pour fin d'une instruction", 
				               "Mot réservé pour affectation d'une valeur",
				               " Mot réservé pour déclaration d'un Real",
				           	   "Mot réservé pour une condition", 
				             	"Mot réservé pour fin d'une condition", 
				             	"opérateur de comparaison",
				             	"Mot réservé pour condition SINON", 
				             	"Mot réservé pour début d'un bloc  ",
				             	"Mot réservé pour affectation d'une variable",
				             	"Mot réservé pour fin d'un bloc  ",
				             	"Mot réservé pour affichage un message",
				             	"message",
				             	"message",
				             	"Mot réservé pour un commentaire",
		                        "Mot reserve Fin du programme" };
				 
				
				
				
		int i = 0;
		while (i < mot_reserve.length) {
			if (chaine.equals(mot_reserve[i])) {
				return Affichage[i];
			}
			i++;
		}
		return null;
	}
	
/////////////////////////////////////////////////	

	
	public void lexicale(List<String> liste) {
		int i = 0;
		
		while (i < mots.size()) {
			if (val(mots.get(i)) != null) {
				lex.add(val(mots.get(i)));
			}
			else if (id(mots.get(i)) != null) {
				lex.add(id(mots.get(i)));
			} 
			else if (num(mots.get(i)) != null) {
				lex.add(num(mots.get(i)));
			} 
			else lex.add("Erreur lexical");

			i++;
		}
	
	}
				
///////////////////////////////////////////////////////	
	public String sym(String chaine){
		
 		if(chaine.equals("Snl_Star")) {
			return "public class sym {\n"
					+ "    public static void main(String[] args){";
			
		}
		
	    else if(chaine.contains(" ")) {
				mot = chaine.split(" ");
			
				if(mot[0].equals("Snl_Int")){ 
		 
					if(!mot[mot.length-1].equals("%."))
					return "erreur sémentique";
				String temp = "int ";
					for(int i = 1 ;i<mot.length-1;i++) {
								if(id(mot[i]) != null){
														
							temp+= mot[i];
							if(mot[mot.length-1].equals(","))
							{
	
						           temp+=" , ";
							}
						}
					}
					
				temp += " ;";
				
				return temp;
		}
			//////////////////
if(mot[0].equals("Snl_Real")){ 
								
				if(!mot[mot.length-1].equals("%."))
					return "erreur sémentique";
				String temp = "float ";
					for(int i = 1 ;i<mot.length-1;i++) {
								if(id(mot[i]) != null){
														
							temp+= mot[i];
							if(mot[mot.length-1].equals(","))
							{
	
						           temp+=" , ";
							}
						}
					}
					
				temp += " ;";
				
				return temp;
		}
/////////////////////////////////////////
if(mot[0].equals("Set")){ 
		
	if(!mot[mot.length-1].equals("%."))
		return "erreur sémentique";
	
	String temp = " ";
		for(int i = 1 ;i<2;i++) {
			
			
			if(id(mot[i]) != null)
				
					temp+= mot[i];
				 temp+=" = ";
				
			i=i+1;
			
			 if(num(mot[i]) != null)
			if(num(mot[i]) == "Nombre reel")
				temp+= mot[i];
			
					
			else if(num(mot[i]) == "Nombre entier") 
						
						temp+= mot[i];
					
		}
		
	temp += " ;";
	
	return temp;
		

}
//////////////////////////////////////
if(mot[0].equals("Get")){ 
	
	if(!mot[mot.length-1].equals("%."))
		return "erreur sémentique";
	
	String temp = " ";
		for(int i = 1 ;i<2;i++) {
			
			
			if(id(mot[i]) != null)
				
					temp+= mot[i];
				 temp+=" = ";
				
			i=i+2;
			
			 if(id(mot[i]) != null)
			
				temp+= mot[i];
	
					
		}
		
	temp += " ;";
	
	return temp;
}

//////////////////////////////////////////
if(mot[0].equals("If")){ 
	
	if(mot[1].equals("%")) {
	
	
	if(!mot[mot.length-1].equals("%."))
		return "erreur sémentique";
	
	String temp = "if ";
	temp+="( ";
		for(int i = 2 ;i<3;i++) {
			
			
			if(id(mot[i]) != null) {
				
						
				temp+= mot[i];
					 temp+="<";
					
					}
			
			temp+= mot[4];
		}
		
		
	temp += " ) ";
	
	return temp;
}
}

/////////////////////////////////////////
if(mot[0].equals("Snl_Put")){ 
	
	
	
	
	if(!mot[mot.length-1].equals("%."))
		return "erreur sémentique";
	
	
	if(id(mot[1]) != null){
		String temp = "system.out.println( ";
		for(int i = 1 ;i<mot.length;i++) {
			
			
			if(id(mot[i]) != null){
				
				
				
					temp+= mot[i];
				temp+="  ";
				
			}
		}
		
	temp += ");";
	
	return temp;
		
	}
	 
	
}

///////////////////////////////////////
if(mot[0].equals("%..")){ 
	String temp = "// ";
		for(int i = 1 ;i<mot.length;i++) {
			
			
			if(id(mot[i]) != null)
			
				
				
					temp+= mot[i];
				temp+=" ";
				
			
		}
		
	return temp;
}
////////////////////////////////////////////
		}
		else if(chaine.equals("Snl_Close")) return "}"
		+ " }";
else if(chaine.equals("Else")) return "else";else if(chaine.equals("Start")) return "{";
else if(chaine.equals("Finish")) return "}";
		return "erreur sémentique";
	}
	
////////////////////////////
	public String syntax(String chaine){
		if(chaine.equals("Snl_Close")) return "Fin du programme";
		 else if(chaine.equals("Snl_Star")) return "Début du programme";
		else if(chaine.equals("Else")) return "SINON";
		else if(chaine.equals("Start")) return "Début d'un bloc";
		else if(chaine.equals("Finish")) return "Fin d'un bloc";
		else if(chaine.startsWith("%..")) return "un commentaire";
		else if(chaine.startsWith("Snl_Put \" ") && chaine.endsWith(" \" %.")) return "Affichage d'un message à l'ecran";
		else if(chaine.contains(" ")) {
			mot = chaine.split(" ") ;
			int i=0, k=1;
			
				if(mot[i].equals("Snl_Int")){ 
					i++;
					
						String temp =" ";
						if(id(mot[i]) != null){
							temp += mot[i]+" , ";
							i++;
							while(mot[i].equals(",")){
								
								i++;
								k++;
								if(id(mot[i]) != null) 
									temp += mot[i]+" , ";
									i++;
							}
							if(mot[i].equals("%.")) return "Déclaration de " +temp+ " variables entiers";
						}
					
					
				}
				else if(mot[i].equals("Get")){
					String temp =" ";
					String tem =" ";
					i++;
					if(id(mot[i]) != null){
						temp += mot[i]+" ";
						i++;
					if(mot[i].equals("from")){
						i++;
						
						 if(id(mot[i]) !=null){
							 tem += mot[i]+" ";
							i++;
							if(mot[i].equals("%.")) return "affectation valeur de "+tem+" dans variable "+temp;
						}
						
					}
				}
				
				}
				else if(mot[i].equals("Snl_Real")){
					i++;
					
				
					String temp =" ";
					
						if(id(mot[i]) != null){
							temp += mot[i]+" , ";
							i++;
							while(mot[i].equals(",")){
								i++;
								k++;
								if(id(mot[i]) != null)
									temp += mot[i]+" , ";
									i++;
							}
							if(mot[i].equals("%.")) return "Déclaration de "+temp+" variables reels";
						}
					
					
				}	
					
				
				else if(mot[i].equals("If")){
					i++;
					if(mot[i].equals("%")){
						i++;
					if(id(mot[i]) != null ){
						i++;
						if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==")){
						i++;
						if(id(mot[i]) != null ){
							i++;
						if(mot[i].equals("%.")){
							return "condition";
							 }}}}
				}
				}
				else if(mot[i].equals("Set")){
					String temp =" ";
					String tem =" ";
					i++;
					if(id(mot[i]) != null){
						temp += mot[i]+" , ";
						i++;
						if(num(mot[i]) == "Nombre entier") {
							tem += mot[i]+" , ";
							i++;
							if(mot[i].equals("%.")) return "affectation d un valeur entier"+tem +" dans variable"+temp ;
							
						
						else if(num(mot[i]) == "Nombre reel") 
							tem += mot[i]+" , ";
							i++;
							if(mot[i].equals("%.")) return "affectation d un valeur reel"+tem +" dans variable "+temp;
						
						}
					
				}
					
				}
				else if(mot[i].equals("Snl_Put")){
					i++;
					
						
						if(id(mot[i]) != null){
							i++;
							if(mot[i].equals("%.")) return "affichage de la valeur  "+mot[i-1];
						}
						

				}
				 
				}
		return "erreur de syntaxe";
	}
	

/****************************************/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyseurJava window = new AnalyseurJava();
					window.frmAnalyseur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

 
	public  AnalyseurJava (){
		
		frmAnalyseur = new JFrame();
		frmAnalyseur.setBounds(100, 120, 450, 350);
		frmAnalyseur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frmAnalyseur.setResizable(false);
	frmAnalyseur.setTitle("Analyseur");
	frmAnalyseur.getContentPane().setBackground(Color.BLUE);
	frmAnalyseur.getContentPane().setLayout(null);
	frmAnalyseur.setLocationRelativeTo(null);
	frmAnalyseur.setSize(800,600);
	Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(Color.WHITE);
	panel_1.setBounds(0, 74, 764, 493);
	frmAnalyseur.getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(0, 0, 745, 461);
	panel_1.add(scrollPane);
	
	textArea = new JTextArea();
	textArea.setEditable(false);
	textArea.setBounds(10, 190, 692, 340);
	scrollPane.setColumnHeaderView(textArea);
	textArea.setForeground(Color.BLACK);
	textArea.setBackground(Color.WHITE);
	textArea.setFont(new Font("Perpetua", Font.BOLD, 16));
	
	JButton btnAsmantique = new JButton("smantique");
	btnAsmantique.setBounds(518, 577, 236, 59);
	frmAnalyseur.getContentPane().add(btnAsmantique);
	btnAsmantique.setCursor(cursor);
	btnAsmantique.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 textArea.setText("");
			
			int i = 0;
			while (i < lignes.size()) {
				textArea.setText(textArea.getText() +""+ sym(lignes.get(i))+" \n" );
				try 
				{
					
					FileWriter f =new FileWriter("sym.java");
					BufferedWriter a = new BufferedWriter(f);
					
					a.write(textArea.getText()+"\n"); 
				a.close();	
				}catch (IOException er) {;}
				
				
				i++;
				
			}
			
			
			
			}
	});
	btnAsmantique.setForeground(Color.WHITE);
	btnAsmantique.setBackground(new Color(0, 128, 128));
	btnAsmantique.setFont(new Font("Roboto", Font.BOLD, 17));
	
	JButton btnAsyntaxique = new JButton("Syntaxique");
	btnAsyntaxique.setForeground(Color.WHITE);
	btnAsyntaxique.setBounds(265, 577, 236, 59);
	frmAnalyseur.getContentPane().add(btnAsyntaxique);
	btnAsyntaxique.setCursor(cursor);
	
	btnAsyntaxique.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			textArea.setText("");
			
			int i = 0;
			while (i < lignes.size()) {
				textArea.setText(textArea.getText()+lignes.get(i) + "      ///// " +syntax(lignes.get(i))+" ////// \n" );
				i++;}
		}
	});
	//});
	//btnAsyntaxique.setForeground(Color.BLACK);
	btnAsyntaxique.setBackground(new Color(0, 128, 128));
	btnAsyntaxique.setFont(new Font("Roboto", Font.BOLD, 17));
	
	JButton btnAlexicale = new JButton("Lexicale");
	btnAlexicale.setBounds(10, 577, 236, 59);
	frmAnalyseur.getContentPane().add(btnAlexicale);
	btnAlexicale.setCursor(cursor);
	btnAlexicale.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

textArea.setText("");
			lexicale(mots);
			int i = 0;
			while (i < mots.size()) {
				textArea.setText(textArea.getText()+mots.get(i) + "  :  " + lex.get(i)+"\n");
				i++;}
		}
	});
	btnAlexicale.setForeground(Color.WHITE);
	btnAlexicale.setBackground(new Color(0, 128, 128));
	btnAlexicale.setFont(new Font("Roboto", Font.BOLD, 17));
	
			JButton btnNewButton = new JButton("Charger");
			btnNewButton.setBounds(265, 10, 236, 59);
			frmAnalyseur.getContentPane().add(btnNewButton);
			btnNewButton.setCursor(cursor);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
 					try {
						  textArea.setText("");
						charger();
						
						
						int i = 0;
						while (i < lignes.size()) {
							textArea.setText(textArea.getText()+lignes.get(i)+"\n");
							i++;}	
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
				  
				}  
			});
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(new Color(0, 128, 128));
			btnNewButton.setFont(new Font("Roboto", Font.BOLD, 17));
	frmAnalyseur.setBounds(100, 100, 778, 687);
	frmAnalyseur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}