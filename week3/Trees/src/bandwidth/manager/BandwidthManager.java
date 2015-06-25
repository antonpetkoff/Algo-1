package bandwidth.manager;

import online.median.Heap;

public class BandwidthManager {

    public enum PRIORITIES {
        ICMP, UDP, RTM, IGMP, DNS, TCP
    }

    public static final class Packet {
        public final String protocol;
        public final String payload;

        public Packet(String protocol, String payload) {
            this.protocol = protocol;
            this.payload = payload;
        }
    }

    private Heap<Packet> priorityQueue;
    public static final String EMPTY_MSG = "Nothing to send!";

    public BandwidthManager() {
        priorityQueue = new Heap<Packet>((o1, o2) -> PRIORITIES.valueOf(o1.protocol).ordinal()
                - PRIORITIES.valueOf(o2.protocol).ordinal());
    }

    // receives a packet with specified protocol and pay-load
    public void rcv(String protocol, String payload) {
        priorityQueue.insert(new Packet(protocol, payload));
    }

    // returns the pay-load of the packet which should be sent
    public String send() {
        return priorityQueue.size() == 0 ? EMPTY_MSG : priorityQueue.remove().payload;
    }

}