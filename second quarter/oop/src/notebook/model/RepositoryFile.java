package notebook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositoryFile implements Repository {
    private Mapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation, Mapper mapper) {
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(mapper.mapSemicolon(line));
        }
        return notes;
    }

    @Override
    public void createNote(Note note) {

        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }

        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);

        Date dateTime = new Date();
        note.setDate(dateTime.toString());

        notes.add(note);
        saveRepository(notes);
    }

    private void saveRepository(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        for (Note item : notes) {
            lines.add(mapper.mapWithSemicolon(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void deleteNote(String noteId) {
        List<Note> notes = getAllNotes();
        Note foundNote = null;

        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                foundNote = note;
            }
        }

        if (foundNote != null) {
            notes.remove(foundNote);
            System.out.println("Заметка удалена");
        } else {
            System.out.println("Некорректный id");
        }

        saveRepository(notes);
    }

    @Override
    public void updateNote(Note note) {
        List<Note> notes = getAllNotes();

        int index = 0;
        for (Note item : notes) {
            if (item.getId().equals(note.getId())) {
                notes.set(index, note);
                break;
            }
            index++;
        }
        saveRepository(notes);
    }
}