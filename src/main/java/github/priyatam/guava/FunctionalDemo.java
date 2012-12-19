package github.priyatam.guava;


import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

//http://code.google.com/p/guava-libraries/wiki/FunctionalExplained
class FunctionalDemo {

    public static void main(String[] args) {
        predicates();
        function();
    }

    static void predicates() {
        Predicate<Integer> isOdd = new Predicate<Integer>(){
            public boolean apply(Integer input) {
                return input % 2 != 0;
            }
        };

        List<Integer> unfiltered = Lists.newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        Collection<Integer> filtered = Collections2.filter(unfiltered, isOdd);

        System.out.println(filtered.toString());
    }

    static void function() {
        Function<Integer, Integer> doubleIt = new
                Function<Integer, Integer>() {
                    public Integer apply(Integer from) {
                        return from * 2;
                    }
                };

        List<Integer> untransformed = Lists.newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        List<Integer> transformed = Lists.transform(untransformed, doubleIt);

        System.out.println(transformed.toString());
    }
}
