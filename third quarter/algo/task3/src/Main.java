public class Main {

    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(121, "Иванов Иван Иванович", "+7987654321"));
        contactList.addToEnd(new Contact(122, "Иванов Сергей Иванович", "+7987654322"));
        contactList.addToEnd(new Contact(123, "Иванов Андрей Иванович", "+7987654323"));
        contactList.addToEnd(new Contact(124, "Иванов Тимофей Иванович", "+7987654324"));
        contactList.addToEnd(new Contact(125, "Иванов Александр Иванович", "+7987654325"));

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
