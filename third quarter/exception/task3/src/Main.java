import base.classes.Presenter;
import base.classes.checkInputData.*;
import base.classes.parseData.CDataParseProcessor;
import base.classes.ui.CGetData;
import base.classes.workWithFile.CFileCreator;
import base.classes.workWithFile.CFileWriter;
import base.classes.workWithFile.CFindTheSameFileName;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Путь к папке с файлами
        String infoPathFolder = "src/data/";
        Presenter presenter = new Presenter(new CGetData(),
                new CDataParseProcessor(),
                new CCheckDataProcessor(new CCheckQuantity(),
                        new CCheckFullName(),
                        new CCheckBirthday(),
                        new CCheckSex()),
                new CFileWriter(new CFileCreator(),
                        new CFindTheSameFileName(),
                        infoPathFolder));
        presenter.run();
    }
}