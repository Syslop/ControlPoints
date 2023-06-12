package base.classes.checkInputData;

import base.abstractClasses.ACheckData;
import base.exceptions.BirthdayFormatException;

public class CCheckBirthday extends ACheckData {
    @Override
    public boolean checkElement(String data) {
        String[] dataArray = data.split("\\.");
        if(dataArray.length!=3 || dataArray[0].length()!=2 || dataArray[1].length()!=2 || dataArray[2].length()!=4)
            throw new BirthdayFormatException();

        for (String s : dataArray) {
            for (int j = 0; j < s.length(); j++) {
                if (!(Character.isDigit(s.charAt(j))))
                    throw new BirthdayFormatException();
            }
        }
        return true;
    }
}
