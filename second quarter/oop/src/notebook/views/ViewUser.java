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
                String command = prompt("������� �������: ");
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
        String id = prompt("������� id �������: ");
        try {
            Note user = controller.readNote(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseCreate() {

        String header = prompt("������� ��������� �������: ");
        if (header.equals("")) {header = "��� ���������";}
        String text = prompt("������� ����� �������: ");
        if (text.equals("")) {text = "����� ���� ������ ���";}
        controller.saveNote(new Note(header, text));
    }

    private void caseDelete() {
        String id = prompt("������� id �������: ");
        controller.deleteNote(id);
    }

    private void caseUpdate() {
        String id = prompt("������� id �������: ");
        try {
            Note note = controller.readNote(id);
            String field = prompt("����� ���� ��������? " + System.lineSeparator() +
                    "1: ���������" + System.lineSeparator() +
                    "2: �����" + System.lineSeparator());
            String newData = prompt("������� �����: ");
            switch (field){
                case "1":
                    note.setHeader(newData);
                    break;
                case "2":
                    note.setText(newData);
                    break;
                default:
                    throw new Exception("������������ ����");
            }
            controller.updateNote(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}