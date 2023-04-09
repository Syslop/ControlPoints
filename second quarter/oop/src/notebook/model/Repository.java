package notebook.model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    void createNote(Note note);
    void deleteNote(String noteId);
    void updateNote(Note note);
}