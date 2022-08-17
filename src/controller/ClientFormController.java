package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFormController {
    public TextArea txtClientArea;
    public TextField txtClientMessage;
    Socket socket=null;

    public void initialize() throws IOException{
        new Thread(() -> {
            try {
                socket =new Socket("localhost",6000);
                InputStreamReader inputStreamReader=new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String record="";
                System.out.println(record);

                while ( !record.equals("Exit")){
                    record= bufferedReader.readLine();
                    txtClientArea.setText("\nServer :"+record.trim()+"\n");
                }

            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();


    }

    public void sendMessageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
        printWriter.println(txtClientMessage.getText());
        txtClientArea.appendText("\tClient :"+txtClientMessage.getText().trim()+"\n");
        printWriter.flush();

    }
}
