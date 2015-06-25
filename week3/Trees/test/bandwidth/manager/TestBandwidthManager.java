package bandwidth.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBandwidthManager {

    private BandwidthManager bm = new BandwidthManager();
    
    @Test
    public void testBandwidthManager() {
        bm.rcv("UDP", "zxchzrkljhklzrjlkhklzr");
        bm.rcv("TCP", "ghljkajklhgjklare");
        bm.rcv("ICMP", "ping87.129.54.123");
        
        assertEquals("ping87.129.54.123", bm.send());
        assertEquals("zxchzrkljhklzrjlkhklzr", bm.send());
        bm.rcv("DNS", "maps.google.com");
        assertEquals("maps.google.com", bm.send());
        bm.rcv("TCP", "aejkgjkaegaegae");
        assertEquals("ghljkajklhgjklare", bm.send());
        assertEquals("aejkgjkaegaegae", bm.send());
        
        assertEquals(BandwidthManager.EMPTY_MSG, bm.send());
    }
    
}
