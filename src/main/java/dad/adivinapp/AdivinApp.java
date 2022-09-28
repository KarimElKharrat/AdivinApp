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
		//TODO quitar numero random de string
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
			intentos++;
			if(numero <= 0 || numero > 100) {
				throw new NumberFormatException();
			} else if(numero == numeroRandom) {
				ifCorrecto();
			} else {
				ifMayorOMenor(numero);
			}
		} catch(NumberFormatException exception) {
			numeroText.setText("");
			ifError();
		}
		numeroText.requestFocus();
	}
	
	private void ifCorrecto() {
		comprobacionAlert = new Alert(AlertType.INFORMATION);
		comprobacionAlert.setTitle("AdivinApp");
		comprobacionAlert.setHeaderText("¡Has ganado!");
		comprobacionAlert.setContentText("Sólo has necesitado " + intentos + " intentos. "
				+ "\nVuelve a jugar y hazlo mejor.");
		
		comprobacionAlert.showAndWait();
		reiniciar();
	}
	
	private void ifMayorOMenor(int numero) {
		comprobacionAlert = new Alert(AlertType.WARNING);
		comprobacionAlert.setTitle("AdivinApp");
		comprobacionAlert.setHeaderText("¡Has fallado!");
		
		if(numeroRandom > numero)
			comprobacionAlert.setContentText("El número a adivinar es mayor que " + numero);
		else
			comprobacionAlert.setContentText("El número a adivinar es menor que " + numero);
		
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
