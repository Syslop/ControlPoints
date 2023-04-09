package notebook.views;

import notebook.controllers.Controller;
import notebook.model.Note;


import java.util.List;

import static notebook.views.UtilsView.prompt;

public class ViewUser {

    private Controller controller;

    public ViewUser(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());

                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        caseCreate();
                        break;
                    case READ:
                        caseRead();
                        break;
                    case LIST:
                        caseList();
                        break;
                    case DELETE:
                        caseDelete();
                        break;
                    case UPDATE:
                        caseUpdate();
                        break;
                }
            } catch (Exception e) {
                System.out.printf("Error: %s" + System.lineSeparator(), e.getMessage());
            }
        }
    }

    private void caseList() {
        List<Note> notes = controller.listNotes();
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    private void caseRead() {
        String id = prompt("Введите id заметки: ");
        try {
            Note user = controller.readNote(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseCreate() {

        String header = prompt("Введите заголовок заметки: ");
        if (header.equals("")) {header = "Без заголовка";}
        String text = prompt("Введите текст заметки: ");
        if (text.equals("")) {text = "Здесь пока ничего нет";}
        controller.saveNote(new Note(header, text));
    }

    private void caseDelete() {
        String id = prompt("Введите id заметки: ");
        controller.deleteNote(id);
    }

    private void caseUpdate() {
        String id = prompt("Введите id заметки: ");
        try {
            Note note = controller.readNote(id);
            String field = prompt("Какое поле изменить? " + System.lineSeparator() +
                    "1: заголовок" + System.lineSeparator() +
                    "2: текст" + System.lineSeparator());
            String newData = prompt("Введите текст: ");
            switch (field){
                case "1":
                    note.setHeader(newData);
                    break;
                case "2":
                    note.setText(newData);
                    break;
                default:
                    throw new Exception("Некорректный ввод");
            }
            controller.updateNote(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}