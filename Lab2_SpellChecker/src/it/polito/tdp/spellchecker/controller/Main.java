package it.polito.tdp.spellchecker.controller;
	
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SpellChecker.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			
			//creo la VIEW
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//creo il MODEL (NOTA: in questo esercizio ne ho due!!)
			Dictionary itamodel=new ItalianDictionary();
			Dictionary engmodel=new EnglishDictionary();

			//ricevo il CONTROLLER
			SpellCheckerController controller=loader.getController();
			
			//collego il CONTROLLER con il MODEL
			controller.setModel(itamodel,engmodel);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
