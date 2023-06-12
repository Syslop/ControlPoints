package base.classes.checkInputData;

import base.abstractClasses.ACheckData;
import base.abstractClasses.ACheckDataProcessor;
import base.abstractClasses.ACheckFullName;
import base.abstractClasses.ACheckQuantity;

public class CCheckDataProcessor extends ACheckDataProcessor {
    public CCheckDataProcessor(ACheckQuantity checkQuantity, ACheckFullName checkFullName, ACheckData checkBirthday, ACheckData checkSex) {
        super(checkQuantity, checkFullName, checkBirthday, checkSex);
    }

    @Override
    public boolean checkBirthday(String birthday) {
        super.checkBirthday.checkElement(birthday);
        System.out.println("Birthday format is OK.");
        return true;
    }

    @Override
    public boolean checkFullName(String[] fullName) {
        super.checkFullName.checkFullName(fullName);
        System.out.println("Full name format is OK.");
        return true;
    }

    @Override
    public boolean checkQuantity(String[] data) {
        super.checkQuantity.checkQuantity(data);
        System.out.println("Quantity data is OK.");
        return true;
    }

    @Override
    public boolean checkSex(String sex) {
        super.checkSex.checkElement(sex);
        System.out.println("Sex format is OK.");
        return true;
    }
}
