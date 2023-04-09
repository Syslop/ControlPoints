package notebook.model;

public class Note {
    private String id = "";
    private String date;
    private String header;
    private String text;

    public Note(String id, String date, String header, String text) {
        this.id = id;
        this.date = date;
        this.header = header;
        this.text = text;
    }

    public Note(String header, String text) {
        this.header = header;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "Note: " + "id = '" + id + "'" + System.lineSeparator() +
                "date: '" + date + "'" + System.lineSeparator() +
                "header: '" + header + "'" + System.lineSeparator() +
                "text: '" + text + "'";
    }
}