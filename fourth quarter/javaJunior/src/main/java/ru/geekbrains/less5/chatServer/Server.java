package ru.geekbrains.less5.chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    //region Поля

    /**
     * Серверный сокет
     */
    private final ServerSocket serverSocket;
    private List<ClientManager> clientManagers = new ArrayList<>();
    //endregion


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент!");
                clientManagers.add(new ClientManager(socket));
                Thread thread = new Thread(clientManagers.get(clientManagers.size() - 1));
                thread.start();
            }
        } catch (IOException e) {
            closeSocket();
        }
    }

    private void closeSocket() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}