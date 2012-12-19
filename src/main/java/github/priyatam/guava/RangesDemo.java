package github.priyatam.guava;


import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

class RangesDemo {

    public static void main(String[] args) {
        System.out.println(Ranges.lessThan(10));

        System.out.println(Ranges.closed(1, 12));

        Range<Integer> range = Ranges.open(1, 20);

        for (Integer i : range.asSet(DiscreteDomains.integers())) {
            System.out.print(i);
        }
    }
}
