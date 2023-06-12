package base.abstractClasses;

import base.interfaces.ICheckBirthday;
import base.interfaces.ICheckFullName;
import base.interfaces.ICheckQuantity;
import base.interfaces.ICheckSex;

public abstract class ACheckDataProcessor implements ICheckFullName, ICheckQuantity, ICheckBirthday, ICheckSex {
    protected ACheckQuantity checkQuantity;
    protected ACheckFullName checkFullName;
    protected ACheckData checkBirthday;
    protected ACheckData checkSex;

    public ACheckDataProcessor(ACheckQuantity checkQuantity, ACheckFullName checkFullName, ACheckData checkBirthday, ACheckData checkSex) {
        this.checkQuantity = checkQuantity;
        this.checkFullName = checkFullName;
        this.checkBirthday = checkBirthday;
        this.checkSex = checkSex;
    }
}
