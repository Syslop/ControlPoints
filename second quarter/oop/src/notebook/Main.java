package notebook;

import notebook.controllers.Controller;
import notebook.model.*;
import notebook.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("notes.txt");
        Repository repository = new RepositoryFile(fileOperation, new NotesMapper());
        Controller controller = new Controller(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}