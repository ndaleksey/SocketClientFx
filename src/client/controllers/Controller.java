package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    private static final int PORT = 4321;
    private DataOutputStream out;

    @FXML
    private Label sendResultLabel;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button startConnectionButton;

    @FXML
    private Button sendMessageButton;

    @FXML
    protected void initialize() {
        startConnectionButton.setOnAction(e->startConnection());
        sendMessageButton.setOnAction(e -> sendMessage());
    }

    private void startConnection() {
        try {
            Socket socket = new Socket("localhost", PORT);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            sendResultLabel.setText("Ошибка подключения к серверу");
        }
    }

    private void sendMessage() {
        try {
            if (out == null) return;
            out.writeUTF(messageTextField.getText());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            sendResultLabel.setText("Ошибка отправки сообщения");
        }
    }
}
