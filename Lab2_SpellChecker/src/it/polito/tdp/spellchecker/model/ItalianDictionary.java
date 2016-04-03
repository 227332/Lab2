package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.spellchecker.db.ParolaDAO;


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
		ParolaDAO dao=new ParolaDAO();
		dizionario=dao.loadDictionary();
	}
	
	//implementazione con il dizionario, erciò faccio l'override dell'omonimo 
	//metodo della superclasse Dictionary
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> lista=new ArrayList<RichWord>();
		Collections.sort(dizionario);
		for(String el: inputTextList){
			if(contains(el)){
				lista.add(new RichWord(el,true));
			}else{
				lista.add(new RichWord(el,false));
			}
			
		}
		
		return lista;
	}
	
	
	
	public boolean contains(String el){
		ParolaDAO dao= new ParolaDAO();
		return dao.contains(el);
	}
	

}
