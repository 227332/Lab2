package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	protected List<String> dizionario; //parole contenute nel mio dizionario
	//uso protected così è visibile dalle classi figlie
	
	

	public Dictionary() {
		super();
		dizionario= new ArrayList<String>();
	}

	//definire un metodo senza implementarlo vuol dire questo:
	public void loadDictionary(){
		
	}
	
	/*CASO1_implementazione con il metodo contains() di List:
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> lista=new ArrayList<RichWord>();
		for(String el: inputTextList){
			if(dizionario.contains(el)){
				lista.add(new RichWord(el,true));
			}else{
				lista.add(new RichWord(el,false));
			}
			
		}
		
		return lista;
	}
	*/
	
	//CASO2_implementazione con il metodo dicotomico:
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> lista=new ArrayList<RichWord>();
		Collections.sort(dizionario);
		for(String el: inputTextList){
			if(containsDicotomico(el,0,dizionario.size()-1)){
				lista.add(new RichWord(el,true));
			}else{
				lista.add(new RichWord(el,false));
			}
			
		}
		
		return lista;
	}
	
	//Metodo Dicotomico Iterativo
	/*public boolean containsDicotomico(String el){
		int a=0;
		int b=dizionario.size()-1;
	
		while(a<=b){
			if(el.equals(dizionario.get((a+b)/2)))
				return true;
			if(el.compareTo(dizionario.get((a+b)/2))>0){
				a=1+(a+b)/2;
			}	
			else
				b=(a+b)/2-1;
		}
		return false;
	}*/
	
	//Metodo Dicotomico Ricorsivo
	public boolean containsDicotomico(String el,int a,int b){
		if(a<=b){
			if(el.equals(dizionario.get((a+b)/2)))
				return true;
			if(el.compareTo(dizionario.get((a+b)/2))>0)
				return containsDicotomico(el,1+(a+b)/2,b);
			else
				return containsDicotomico(el,a,(a+b)/2-1);
		}
		return false;
	}
	
}
