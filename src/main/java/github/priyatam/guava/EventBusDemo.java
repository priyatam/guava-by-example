package github.priyatam.guava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

// http://code.google.com/p/guava-libraries/wiki/EventBusExplained
class EventBusDemo {

    public static void main(String[] args) {
        simple();
        multiListener();
        deadEvent();
    }

    static void simple() {
        // given
        EventBus bus = new EventBus("test");
        EventListener listener = new EventListener();
        bus.register(listener);

        // when
        System.out.println("Posting event...");
        bus.post(new TestEvent(200));
        System.out.println("Posted event.");

        // then
        System.out.println(listener.getLastMessage());
    }

    static void multiListener() {
        EventBus bus = new EventBus("test");
        MultiListener listener = new MultiListener();
        bus.register(listener);

        System.out.println("Posting event [100]...");
        bus.post(100);
        System.out.println("Posted event [100].");
        System.out.println("Posting event [900]...");
        bus.post(900L);
        System.out.println("Posted event [900].");

        System.out.println(listener.getLastInteger());
        System.out.println(listener.getLastLong());
    }

    static void deadEvent() {
        EventBus bus = new EventBus("test");
        DeadEventListener listener = new DeadEventListener();
        bus.register(listener);

        bus.post(new TestEvent(300));

        System.out.println(listener.notReceived);
    }

    // Sample Inner classes
    static class DeadEventListener {
        boolean notReceived = false;

        @Subscribe
        public void listen(DeadEvent event) {
            System.out.println("Received dead event... [" + event + "]");

            notReceived = true;
        }

        public boolean isNotReceived() {
            return notReceived;
        }
    }

    static class TestEvent {
        private final int message;

        public int getMessage() {
            return message;
        }

        public TestEvent(int message) {
            super();
            this.message = message;
        }
    }

    static class MultiListener {
        private Integer lastInteger;
        private Long lastLong;

        @Subscribe
        public void listenInteger(Integer event) {
            System.out.println("Received integer event... [" + event + "]");
            lastInteger = event;
        }

        @Subscribe
        public void listenLong(Long event) {
            System.out.println("Received long event... [" + event + "]");
            lastLong = event;
        }

        public Integer getLastInteger() {
            return lastInteger;
        }

        public Long getLastLong() {
            return lastLong;
        }
    }

    static class EventListener {
        private int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            System.out.println("Received event.. [" + event.getMessage() + "]");
            lastMessage = event.getMessage();
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }
}
