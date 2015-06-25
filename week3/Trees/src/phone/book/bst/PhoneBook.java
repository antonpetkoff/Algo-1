package phone.book.bst;

public class PhoneBook {

    public static final class Contact {
        public final String name;
        public final int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    // inserts a new contact
    public void insert(Contact contact) {
        // TODO
    }

    // lookup a name and print its phone number
    public int lookup(String name) {
        // TODO
        return 0;
    }

    // list all records in an alphabetical order
    public void list() {
        // TODO
    }

    // remove a record for a given name
    public void remove(String name) {
        // TODO
    }
}