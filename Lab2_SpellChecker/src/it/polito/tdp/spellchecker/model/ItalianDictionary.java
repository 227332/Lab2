package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ItalianDictionary extends Dictionary {
	//eredita l'attributo List<String> dizionario;
	
	//in realtà potevo anche non creare un costruttore: in tal caso si sarebbe
	//usato automaticamente quello della classe padre
	public ItalianDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void loadDictionary(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("rsc/Italian.txt"));
			String word;
			while ((word = br.readLine()) != null) {
			// Aggiungere word alla struttura dati
				dizionario.add(word);
				
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}

}
