package phone.book.bst;

import org.junit.Test;

import phone.book.bst.PhoneBook.Contact;
import static org.junit.Assert.*;

public class TestPhoneBook {

    private PhoneBook pb = new PhoneBook();
    
    @Test
    public void testPhoneBookOperations() {
        pb.insert(new Contact(1, "Stanislav"));
        pb.insert(new Contact(15, "Rado"));
        assertEquals(2, pb.size());
        assertEquals(15, pb.lookup("Rado"));
        
        pb.insert(new Contact(6, "Ivan"));
        assertEquals(6, pb.lookup("Ivan"));
        pb.insert(new Contact(8, "Ivan"));
        assertEquals(8, pb.lookup("Ivan"));
        assertEquals(PhoneBook.NOT_FOUND, pb.lookup("Pesho"));
        assertEquals(3, pb.size());
        pb.remove("Ivan");
        assertEquals(PhoneBook.NOT_FOUND, pb.lookup("Ivan"));
        assertEquals(2, pb.size());
        
        pb.insert(new Contact(23, "Ani"));
        pb.insert(new Contact(44, "Pesho"));
        pb.insert(new Contact(77, "Eli"));
        pb.insert(new Contact(83, "Georgi"));
        assertEquals(6, pb.size());
        
        pb.list();
    }
    
}
