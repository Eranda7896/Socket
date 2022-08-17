package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverFormController {
    public TextArea txtServerArea;
    public TextField txtServerMessage;
    Socket accept=null;

    public void initialize() throws IOException {
        new Thread(() -> {
            try {
                ServerSocket serverSocket =new ServerSocket(6000);
                System.out.println("server started");
                accept = serverSocket.accept();
                System.out.println("client connected");

                InputStreamReader inputStreamReader=new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String record="";
                System.out.println(record);


                while ( !record.equals("Exit")){
                    record= bufferedReader.readLine();
                    txtServerArea.setText("\nClient :"+record.trim()+"\n");
                }
            }catch (Exception e){

                e.printStackTrace();
            }

        }).start();


    }

    public void sendMessageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(accept.getOutputStream());
        printWriter.println(txtServerMessage.getText());
        txtServerArea.appendText("\tServer :"+txtServerMessage.getText().trim()+"\n");
        printWriter.flush();

    }
}
