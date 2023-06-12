package base.interfaces;

import base.exceptions.MyFileCreateException;
import base.exceptions.TheSameFileWritingException;

public interface IWriteToFile {
    public boolean writeToFile(String[] infoToWrite) throws TheSameFileWritingException, MyFileCreateException;
}
