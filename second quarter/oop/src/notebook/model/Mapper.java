package notebook.model;

public interface Mapper {
    public String mapWithSemicolon(Note user);
    public Note mapSemicolon(String line);
}