package phone.book;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import phone.book.PhoneBook.Contact;
import static org.junit.Assert.*;
import static phone.book.PhoneBook.lookupNames;

public class TestPhoneBook {

    @Test
    public void testPhoneBook() {
        List<Contact> phoneBook = Arrays.asList(new Contact(1, "Stanislav"), new Contact(15, "Rado"),
                new Contact(6, "Ivan"), new Contact(8, "Ivan"));

        List<Integer> numbers = Arrays.asList(15, 8);
        
        List<String> result = lookupNames(phoneBook, numbers);
        
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), "Rado");
        assertEquals(result.get(1), "Ivan");
    }
    
}
