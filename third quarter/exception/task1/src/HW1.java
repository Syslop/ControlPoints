public class HW1 {
    public static void main(String[] args) {
        String[] sArray = {"first str", null, "third str"};
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
        division(10, 0);
        printValueByIndex(array,12);
        printStringArray(sArray);

    }
    // division by zero
    public static void division(double firstNum, double secondNum){
        if(secondNum ==  0) throw new ArithmeticException("�� ���� ������ ������!!!");
        double result = firstNum/secondNum;
        System.out.printf("��������� ������� %f �� %f ����� %f.\n", firstNum, secondNum, result);
    }
    // out of bounds
    public static void printValueByIndex(int[] array, int index){
        if(index > array.length-1) throw new ArrayIndexOutOfBoundsException("�������� ��� ����� �������� �� ����������");
        else System.out.printf("������� � �������� %d ����� %d.\n",index, array[index]);
    }

    // Null pointer exception
    public static void printStringArray(String[] sArray){
        for (String item: sArray){
            if(item == null) throw new NullPointerException("������� �����������!!!");
            System.out.println(item);
        }
    }
}
