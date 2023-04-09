package notebook.controllers;

import notebook.model.Repository;
import notebook.model.Note;


import java.util.List;

public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) {
        repository.createNote(note);
    }

    public Note readNote(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }
        throw new Exception("Заметка не найдена");
    }

    public List<Note> listNotes(){
        List<Note> notes = repository.getAllNotes();
        return notes;
    }

    public void deleteNote(String noteId){
        repository.deleteNote(noteId);
    }

    public void updateNote(Note note){
        repository.updateNote(note);
    }
}