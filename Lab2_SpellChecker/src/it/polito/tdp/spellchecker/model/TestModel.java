package it.polito.tdp.spellchecker.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItalianDictionary itadiz=new ItalianDictionary();
		EnglishDictionary engdiz=new EnglishDictionary();
		
		itadiz.loadDictionary();
		engdiz.loadDictionary();
		
		List<String> provaita=new LinkedList<String>();
		List<String> provaeng=new LinkedList<String>();
		
		provaita.add("scuola");
		provaita.add("prova");
		provaita.add("malaria");
		provaita.add("squola");
		provaita.add("proma");
		provaita.add("malharia");
		
		provaeng.add("around");
		provaeng.add("monomorphic");
		provaeng.add("nanotech");
		provaeng.add("araand");
		provaeng.add("monomorfic");
		provaeng.add("naniteck");
		
		itadiz.spellCheckText(provaita);
		engdiz.spellCheckText(provaeng);
		
		System.out.println(itadiz.spellCheckText(provaita));
		
		System.out.println(engdiz.spellCheckText(provaeng));
		

	}

}
