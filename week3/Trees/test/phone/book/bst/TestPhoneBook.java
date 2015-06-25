package phone.book.bst;

import org.junit.Test;

import phone.book.bst.PhoneBook.Contact;
import static org.junit.Assert.*;

public class TestPhoneBook {

    private PhoneBook pb = new PhoneBook();
    
    @Test
    public void testPhoneBookOperations() {
        pb.insert(new Contact("Stanislav", 1));
        pb.insert(new Contact("Rado", 15));
        assertEquals(15, pb.lookup("Rado"));
        pb.insert(new Contact("Ivan", 6));
        assertEquals(6, pb.lookup("Ivan"));
        pb.insert(new Contact("Ivan", 8));
        assertEquals(8, pb.lookup("Ivan"));
    }
    
}
