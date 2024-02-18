package ru.geekbrains.less5.chatServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientManager implements Runnable {

    //region Поля

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();
    private List<ClientManager> clientsSend;
    private boolean isPrivate;
    //endregion

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Удаление клиента из коллекции
     */
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                // Чтение данных
                massageFromClient = bufferedReader.readLine();
                if (massageFromClient == null) {
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                // Отправка данных всем слушателям
                broadcastMessage(massageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Проверка на приватность сообщения и формирование списка приватных клиентов
     * @param message сообщение
     * @return
     */
    private String checkPrivateMessage(String message) {
        isPrivate = false;
        clientsSend = new ArrayList<>();
        if (message.startsWith("-p")) {
            String names = message.substring(3);
            if (names.indexOf(" ") > 0) {
                names = names.substring(0, names.indexOf(" "));
                if (!names.equals("")) {
                    String[] namesArr = names.split(",");
                    message = message.substring(message.indexOf(names) + names.length() + 1);
                    for (String s : namesArr) {
                        List<ClientManager> clientsList1 = clients.stream()
                                .filter(x -> x.name.equals(s))
                                .collect(Collectors.toList());
                        if (!clientsList1.isEmpty()) clientsSend.add(clientsList1.get(0));
                        isPrivate = true;
                    }
                }
            }
        }
        if (!isPrivate) clientsSend = clients.stream().filter(x -> !x.name.equals(name)).collect(Collectors.toList());
        return message;
    }

    /**
     * Отправка сообщения всем слушателям
     *
     * @param message сообщение
     */
    public void broadcastMessage(String message) {
        message = checkPrivateMessage(message);
        for (ClientManager client : clientsSend) {
            try {
                if (message != null) {
                    if (message.startsWith("Server"))
                        client.bufferedWriter.write(message);
                    else if (isPrivate)
                        client.bufferedWriter.write(name + ": (private massage) - " + message);
                    else
                        client.bufferedWriter.write(name + ": " + message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}