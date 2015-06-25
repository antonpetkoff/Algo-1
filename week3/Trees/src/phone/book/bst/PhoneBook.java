package phone.book.bst;

public class PhoneBook {

    public static final class Contact implements Comparable<Contact> {
        public final String name;
        public final int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return name + " " + number;
        }
        
        @Override
        public int compareTo(Contact o) {
            return name.compareTo(o.name);
        }
    }

    private BinarySearchTree<Contact> bst;
    public static final int NOT_FOUND = -1;
    
    public PhoneBook() {
        bst = new BinarySearchTree<Contact>();
    }
    
    // inserts a new contact
    public void insert(Contact contact) {
        bst.insert(contact);
    }

    // lookup a name and print its phone number
    public int lookup(String name) {
        Contact result = bst.search(new Contact(name, 0));
        return result == null ? NOT_FOUND : result.number;
    }

    // list all records in an alphabetical order
    public void list() {
        bst.traverse();
    }

    // remove a record for a given name
    public void remove(String name) {
        bst.remove(new Contact(name, 0));
    }

    public int size() {
        return bst.size();
    }
    
}