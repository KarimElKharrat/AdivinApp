package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label contextoLabel;
	private TextField numeroText;
	private Button comprobarButton;
	private VBox root;
	private Alert comprobacionAlert;
	private int numeroRandom;
	private int intentos;
	
	public void start(Stage primaryStage) throws Exception {
		contextoLabel = new Label("Introduce un número del 1 al 100");
		reiniciar();
		
		numeroText = new TextField();
		
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(this::onComprobarAction);
		
		root = new VBox(5, contextoLabel, numeroText, comprobarButton);
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void onComprobarAction(ActionEvent e) {
		int numero;
		
		try {
			numero = Integer.parseInt(numeroText.getText());
			numeroText.setText("");
			
			if(numero <= 0 || numero > 100) {
				throw new NumberFormatException();
			} else if(numero == numeroRandom) {
				intentos++;
				ifCorrecto(numero);
				reiniciar();
			} else {
				intentos++;
				ifMayorOMenor(numero);
			}
		} catch(NumberFormatException exception) {
			numeroText.setText("");
			ifError();
		}
		numeroText.requestFocus();
	}
	
	private void ifCorrecto(int numero) {
		comprobacionAlert = new Alert(AlertType.INFORMATION);
		comprobacionAlert.setTitle("AdivinApp");
		comprobacionAlert.setHeaderText("¡Has ganado!");
		comprobacionAlert.setContentText("El número era " + numero + ".\nSólo has necesitado " + intentos + " intentos. "
				+ "\nVuelve a jugar y hazlo mejor.");
		
		comprobacionAlert.showAndWait();
	}
	
	private void ifMayorOMenor(int numero) {
		comprobacionAlert = new Alert(AlertType.WARNING);
		comprobacionAlert.setTitle("AdivinApp");
		comprobacionAlert.setHeaderText("¡Has fallado!");
		comprobacionAlert.setContentText("El número a adivinar es " + (numeroRandom > numero ? "mayor":"menor") + " que " + numero);
		
		comprobacionAlert.showAndWait();
	}
	
	private void ifError() {
		comprobacionAlert = new Alert(AlertType.ERROR);
		comprobacionAlert.setTitle("AdivinApp");
		comprobacionAlert.setHeaderText("Error");
		comprobacionAlert.setContentText("El número introducido no es válido");
		
		comprobacionAlert.showAndWait();
	}
	
	private void reiniciar() {
		numeroRandom = (int)(Math.random()*100)+1;
		intentos = 0;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
