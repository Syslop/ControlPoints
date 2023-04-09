package notebook.model;

public class NotesMapper implements Mapper {

    @Override
    public String mapWithSemicolon(Note note) {
        return String.format("%s;%s;%s;%s\n", note.getId(), note.getDate(), note.getHeader(), note.getText());
    }

    @Override
    public Note mapSemicolon(String line) {
        String[] lines = line.split(";");
        return new Note(lines[0], lines[1], lines[2], lines[3]);
    }
}