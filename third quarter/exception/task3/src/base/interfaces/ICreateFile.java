package base.interfaces;

import base.exceptions.FileCreateException;

import java.io.IOException;

public interface ICreateFile {
    public boolean createFile(String path) throws FileCreateException;
}
