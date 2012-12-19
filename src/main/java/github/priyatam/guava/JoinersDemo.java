package github.priyatam.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.util.*;

class JoinersDemo {

    public static void main(String args[]) {
      before();
      after();
      mapBefore();
      mapAfter();
    }

    static void before() {
        List<String> list = new ArrayList<String>();
        list.add("Manny");
        list.add("Moe");
        list.add("Java");
        StringBuffer buffer = new StringBuffer();
        boolean started = false;
        for (String item : list) {
            if (started) {
                buffer.append(",");
            }
            buffer.append(item);
            started = true;
        }
        System.out.println("Before:" + buffer.toString());
    }

    static void after() {
        String str = Joiner.on(',').join("Manny", "Moe", "Java");
        System.out.println("After:" + str);
    }

    static void mapBefore() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("New Mexico", "Santa Fe");
        map.put("Texas", "Austin");
        map.put("Arizona", "Phoenix");
        StringBuffer stringBuffer = new StringBuffer();
        boolean started = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (started) stringBuffer.append(", ");
            stringBuffer.append(entry.getKey());
            stringBuffer.append(" -> ");
            stringBuffer.append(entry.getValue());
            started = true;
        }
        System.out.println("Before:" + stringBuffer.toString());
    }

    static void mapAfter() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("New Mexico", "Santa Fe");
        map.put("Texas", "Austin");
        map.put("Arizona", "Phoenix");

        String str = Joiner.on(", ").
                withKeyValueSeparator(" -> ").
                join(ImmutableMap.of("New Mexico", "Santa Fe", "Texas", "Austin", "Arizona", "Phoenix"));

        System.out.println("After:" + str);
    }

}
