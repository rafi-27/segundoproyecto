package proyectodos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class proyectoDos implements Initializable {
    @FXML
    AnchorPane izquierdo;
    @FXML
    AnchorPane derecho;

    @FXML
    Pane panelIzquierdo;
    @FXML
    Pane panelDerecho;

    @FXML
    HBox todosLosBotonesPrimeraParte;
    @FXML
    HBox todosLosBotonesSegundaParte;
    @FXML
    HBox hboxSuperior;
    @FXML
    HBox hboxBajBox;
    @FXML
    ImageView imagen;

    Label palabras = new Label();

    String secreta = "THIAR";
    private int fallos = 1;
    private final int MAX_FALLOS = 7;
    private ArrayList<Character> letrasPulsadas = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ponerBotones();
        ponerTitulo();
        // String formaSectreto="";
        // for (int i = 0; i < secreta.length(); i++) {
        // formaSectreto+="_ ";
        // }
        // palabras.setFont( new Font("Ubuntu",20));
        // palabras.setText(formaSectreto.trim());
        // hboxBajBox.getChildren().add(palabras);

        imagen.setImage(new Image(getClass().getResourceAsStream("img/Ahorcado1.png")));
    }

    @FXML
    private void ponerTitulo() {
        Label texto = new Label("Etiqueta juego del ahorcado");
        texto.setFont(new Font("Ubuntu", 24));
        hboxSuperior.getChildren().add(texto);
        // texto.setVisible(false);
    }

    @FXML
    private void ponerBotones() {
        String[] vocabulario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "Y", "Z" };
        TeclaPulsada gestor = new TeclaPulsada();
        for (int i = 0; i < vocabulario.length; i++) {
            if (i <= 13) {
                Button botoncito = new Button(vocabulario[i]);
                botoncito.setOnAction(gestor);
                todosLosBotonesPrimeraParte.getChildren().add(botoncito);
            } else {
                Button botoncito = new Button(vocabulario[i]);
                botoncito.setOnAction(gestor);
                todosLosBotonesSegundaParte.getChildren().add(botoncito);
            }
        }
    }

    class TeclaPulsada implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent tecla) {
            // TODO Auto-generated method stub
            Button boton = (Button) tecla.getSource();
            boton.setDisable(true);
            comprobarLetra(boton.getText());
        }
    }

    public void comprobarLetra(String c) {
        boolean acertada = true;
        String formatoSecreto = "";

        letrasPulsadas.add(c.charAt(0));
        if (secreta.contains(c)) {
            for (int i = 0; i < secreta.length(); i++) {
                if (letrasPulsadas.contains(secreta.charAt(i))) {
                    formatoSecreto = formatoSecreto + secreta.charAt(i) + " ";
                } else {
                    acertada = false;
                    formatoSecreto += "_ ";
                }
            }
            palabras.setFont(new Font("Ubuntu", 20));
            palabras.setText(formatoSecreto.trim());
            hboxBajBox.getChildren().add(palabras);
        } else {
            fallos++;
            acertada = false;
            imagen.setImage(new Image(getClass().getResourceAsStream("img/Ahorcado" + fallos + ".png")));
        }
        // acertada
        // !formatoSecreto.contains("_")
        if (!formatoSecreto.contains("_")) {
            acertada();
        }

        if (fallos == MAX_FALLOS) {
            fallada();
        }

    }

    public void acertada() {
        Alert alertaGanadora = new Alert(AlertType.CONFIRMATION);
        alertaGanadora.setTitle("Has ganado.");
        alertaGanadora.setHeaderText("Muy bien hecho.");
        alertaGanadora.setContentText("Has adivinado la palabra secreta.");
        alertaGanadora.showAndWait();
    }

    public void fallada() {
        Alert alertaPerdedora = new Alert(AlertType.ERROR);
        alertaPerdedora.setTitle("Has perdido.");
        alertaPerdedora.setHeaderText("No has adivinado la palabra.");
        alertaPerdedora.setContentText("No has adivinado la palabra secreta.");
        alertaPerdedora.showAndWait();
    }

}
