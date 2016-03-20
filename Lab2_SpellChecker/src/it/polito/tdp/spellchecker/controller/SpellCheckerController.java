package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class SpellCheckerController {
	private List<RichWord> elenco; //elenco parole etichettate come corrette/non corrette
	private String language; //selected language from the ComboBox
	private Dictionary itamodel; //modello a cui è collegato il controller
	private Dictionary engmodel; //modello a cui è collegato il controller
	


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguage;

    @FXML
    private TextArea txtTranslate;

    @FXML
    private Button btnSpellCheck;

    /*con TextArea:
    @FXML
    private TextArea txtResult;
    */
    //con TextFlow:
    @FXML
    private TextFlow txtResult;

    @FXML
    private Label lblError;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblTime;
    
    
	/*ATT: NON SERVE il costruttore:
	 * public SpellCheckerController() {
		super();
		elenco = new ArrayList<RichWord>();
	}
	perchè elenco sarà un puntatore ad una lista che viene già creata in spellCheckText()!!!
    */
    
    @FXML
    void doClearText(ActionEvent event) {
    	txtTranslate.setText("");
    	lblError.setText("");
    	lblTime.setText("");
    	boxLanguage.setValue(null);//così non compare nessuna lingua selezionata
    	/*con TextArea:
    	 * txtResult.setText("");
    	 */
    	//con TextFlow:
    	txtResult.getChildren().clear();
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {    	
    	if(boxLanguage.getValue()==null){
    			lblError.setText("Select a Language!");
    			return;//esco cioè da tale metodo
    	} 
    	language=boxLanguage.getValue();
    	
    	//ATT:il check si fa con equals() non con==, non dimenticarlo!!!
    	if(txtTranslate.getText().equals("")) {
    		lblError.setText("Insert a Text to translate!");
    		return;//RICORDA DI METTERE IL RETURN! Esco cioè da tale metodo
    	}
    	
    	String[] list=txtTranslate.getText().toLowerCase().split("[,.;:]|\\s");//si usano così i regex,ma qual è la regola generale???
    	List<String> text=new ArrayList<String>();
    	for(String el:list){//non c'è un metodo che crea direttamente una List da un Vector??
    		text.add(el);
    	}   	
    	long t1,t2;
    	if(language.equals("Italian")){
    		itamodel.loadDictionary();
    		t1=System.nanoTime();
    		elenco=itamodel.spellCheckText(text);
    		t2=System.nanoTime();
    	}else{
    		engmodel.loadDictionary();
    		t1=System.nanoTime();
    		elenco=engmodel.spellCheckText(text);
    		t2=System.nanoTime();
    	}
    	
    	lblTime.setText("Spell check completed in "+(t2-t1)/1e9+" seconds");
    	
    	/*con la TextArea:
    	boolean errori=false;
    	String risultato=""; //risultato da stampare nella seconda area di testo
    	for(RichWord el:elenco){
			if(!el.isCorretta()){
				errori=true;
				risultato=risultato+el.getParola()+" ";
			}
		}
		if(errori){
			lblError.setText("Your Text contains errors!");
			txtResult.setText(risultato);
			
		}else{
			lblError.setText("No Errors");
    		

		}    */
    	
    	/*TextFlow è come un contenitore di una List<Text>, pertanto da esso
    	 * posso farmi restituire la List<Text> tramite il metodo getChildren().
    	 * Ogni elemento di tale lista è un oggetto di tipo Text, ossia è un Nodo
    	 * in cui non solo posso inserire un testo mediante setText(..), ma posso 
    	 * anche stabilire il Font(per esempio Arial 14) e il Fill (cioè il colore)
    	 * che deve avere tale testo!
    	 * Per aggiungere un testo al mio TextFlow, pertanto, non posso farlo in 
    	 * modo diretto ma lo si fa aggiungendo un nuovo nodo Text alla List<Text>
    	 * mediante l'istruzione, fatta sull'oggetto TextFlow, getChildren().add(..);
    	 *  
    	 */
    	
    	
    	//con TextFlow:
    	boolean errori=false;
    	
    	for(RichWord el:elenco){
			if(!el.isCorretta()){
				errori=true;
				Text t=new Text();
				t.setText(el.getParola()+" ");
				t.setFill(javafx.scene.paint.Color.RED);
				txtResult.getChildren().add(t); //getChildren restituisce una List<Text>
			}else{
				Text t=new Text();
				t.setText(el.getParola()+" ");
				t.setFill(javafx.scene.paint.Color.BLACK);
				txtResult.getChildren().add(t); 
			}
		}
		if(errori){
			lblError.setText("Your Text contains errors!");
			lblError.setTextFill(javafx.scene.paint.Color.RED );
			
		}else{
			lblError.setText("No Errors");
			lblError.setTextFill(Paint.valueOf("#0094ff"));
			/*RICORDA!!! Per il colore si fa così:
			lblError.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
			se si vuole usare un attributo di Color che è static...
			...e, invece:
			lblError.setTextFill(Paint.valueOf("#0094ff"));
			se si vuole usare un attributo di Color non static, con "#0094ff"
			che è il codice String di quel colore (da Scene Builder si vede
			qual è il codice String associato al colore che tu selezioni)
			*/		
			
		}
	}
     

    
    public void setModel(Dictionary m1,Dictionary m2){
    	itamodel=m1;
    	engmodel=m2;
    }
    

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTranslate != null : "fx:id=\"txtTranslate\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        //inizializzo la lista della ComboBox
        boxLanguage.getItems().addAll("English","Italian");
    }
    
}
