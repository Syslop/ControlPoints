package notebook.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static notebook.model.UtilsModel.isEmptyLine;

public class FileOperationImpl implements FileOperation {

    private String fileName;

    public FileOperationImpl(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<String> readAllLines() {

        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            //������� ������ FileReader ��� ������� File
            FileReader fr = new FileReader(file);
            //������� BufferedReader � ������������� FileReader ��� ����������� ����������
            BufferedReader reader = new BufferedReader(fr);
            // ������� ������� ������ ������
            String line = reader.readLine();
            if (!isEmptyLine(line)) {
                lines.add(line);
            }
            while (line != null) {
                // ��������� ��������� ������ � �����
                line = reader.readLine();
                if (!isEmptyLine(line)) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : lines) {
                // ������ ���� ������
                writer.write(line);
                // ������ �� ��������
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}