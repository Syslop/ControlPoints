package base.abstractClasses;

import base.classes.workWithFile.CFileCreator;
import base.classes.workWithFile.CFindTheSameFileName;
import base.interfaces.IWriteToFile;

import java.io.FileWriter;

public abstract class AFileWriter implements IWriteToFile {
    protected FileWriter fileWriter;
    protected CFileCreator fileCreator;
    protected CFindTheSameFileName findTheSameFileName;
}
