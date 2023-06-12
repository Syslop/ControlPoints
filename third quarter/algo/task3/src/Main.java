public class Main {

    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(121, "������ ���� ��������", "+7987654321"));
        contactList.addToEnd(new Contact(122, "������ ������ ��������", "+7987654322"));
        contactList.addToEnd(new Contact(123, "������ ������ ��������", "+7987654323"));
        contactList.addToEnd(new Contact(124, "������ ������� ��������", "+7987654324"));
        contactList.addToEnd(new Contact(125, "������ ��������� ��������", "+7987654325"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }
        contactList.reverse();

        System.out.println("-------------------------------------");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }
}
