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
        assertEquals(2, pb.size());
        assertEquals(15, pb.lookup("Rado"));
        
        pb.insert(new Contact("Ivan", 6));
        assertEquals(6, pb.lookup("Ivan"));
        pb.insert(new Contact("Ivan", 8));
        assertEquals(8, pb.lookup("Ivan"));
        assertEquals(PhoneBook.NOT_FOUND, pb.lookup("Pesho"));
        assertEquals(3, pb.size());
        pb.remove("Ivan");
        assertEquals(PhoneBook.NOT_FOUND, pb.lookup("Ivan"));
        assertEquals(2, pb.size());
        
        pb.insert(new Contact("Ani", 23));
        pb.insert(new Contact("Pesho", 44));
        pb.insert(new Contact("Eli", 77));
        pb.insert(new Contact("Georgi", 83));
        assertEquals(6, pb.size());
        
        pb.list();
    }
    
}
