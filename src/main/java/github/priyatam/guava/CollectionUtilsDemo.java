package github.priyatam.guava;

import com.google.common.base.*;
import com.google.common.collect.*;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Constraints.constrainedList;

// http://code.google.com/p/guava-libraries/wiki/CollectionUtilitiesExplained
class CollectionUtilsDemo {

    public static void main(String[] args) {
        constraints();
        immutableCollections();
        unionsAndIntersections();
        immutableCollectionsWithBuilders();
        reverseLists();
        mapUtilities();
    }

    static void constraints() {
        List<String> list = Lists.newArrayList("boston", "nyc", "sfo");

        Constraint<String> checkListStr = new Constraint<String>() {
            @Override
            public String checkElement(String element) {
                checkArgument(!element.startsWith("n"), "Hello, [" + element + "]");
                return element;
            }
        };

        // Add constraint
        List<String> constraintList = constrainedList(list, checkListStr);

        try {
            constraintList.add("dc");
            constraintList.add("new jersey"); // throw IllegalArgumentException
        }
        catch (IllegalArgumentException e) {
            System.out.println("Collection Constraint Failed: " + Throwables.getStackTraceAsString(e));
        }

        for(String s : list) {
            System.out.println(s);
        }
    }

    static void immutableCollections() {
        // List
        List<Integer> integerList = ImmutableList.of(4, 4, 5, 6, 7);
        System.out.println(integerList.toString());

        // Set
        Set<Integer> intSet = ImmutableSet.of(6, 7, 7, 8, 9, 10);
        System.out.println(intSet.toString());

        // Map
        Map<String, String> capitalMap = ImmutableMap.of(
                "New Mexico", "Santa Fe",
                "Texas", "Austin",
                "Arizona", "Phoenix");
        System.out.println(capitalMap.toString());
    }

    static void unionsAndIntersections() {
        // Some customers
        User bob = new User(1, "Bob");
        User lisa = new User(2, "Lisa");
        User stephen = new User(3, "Stephen");
        User ken = new User(null, "Ken");

        ImmutableSet<User> customers1 = ImmutableSet.of(bob, lisa, stephen);
        ImmutableSet<User> customers2 = ImmutableSet.of(stephen, ken);

        System.out.println("Union: "+ Sets.union(customers1, customers2).size());

        System.out.print("Stephen: " + ImmutableSet.of(stephen));
        System.out.println("Intersection:" + Sets.intersection(customers1, customers2));
    }

    static void immutableCollectionsWithBuilders() {
        // List
        List<Integer> intList =
                ImmutableList.<Integer>builder()
                        .add(1, 2, 3, 4, 5)
                        .addAll(Arrays.asList(6, 7, 8, 9, 10))
                        .build();
        System.out.println(intList.toString());

        // Set
        Set<Integer> intSet = ImmutableSet.<Integer>builder()
                .add(1, 2, 3, 4, 5)
                .addAll(Arrays.asList(5, 6, 7, 8, 9, 10))
                .build();
        System.out.println(intSet.toString());

        try {
            intSet.add(12);
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Can't add to an immutable set: " + Throwables.getStackTraceAsString(e));
        }

        // Map
        Map<String, String> capitalMap = new ImmutableMap.Builder<String, String>()
                .put("Brazil", "Brasilia")
                .put("United States", "Washington, DC")
                .put("Portugal", "Lisbon")
                .build();
        System.out.println(capitalMap.toString());
    }

    static void reverseLists() {
        List<String> list = Lists.newArrayList("one", "two", "three");
        System.out.println("Reversing List: " + Lists.reverse(list));
    }

    static void mapUtilities() {
       List<String> strings = Lists.newArrayList("hello", "world!", "lan", "bo", "shansun");

       ImmutableMap<Integer, String> uniqueIndex = Maps.uniqueIndex(strings, new Function<String, Integer>() {

           @Override
           public Integer apply(String input) {
               return input.length();
           }
       });

       System.out.println(uniqueIndex);

       ImmutableMap<String, Integer> left = ImmutableMap.<String, Integer> of("a", 1, "b", 2, "c", 3);
       ImmutableMap<String, Integer> right = ImmutableMap.<String, Integer> of("b", 2, "c", 4, "d", 5);

       MapDifference<String, Integer> difference = Maps.difference(left, right);

       System.out.println(difference.entriesInCommon());

       System.out.println(difference.entriesDiffering());

       System.out.println(difference.entriesOnlyOnLeft());

       System.out.println(difference.entriesOnlyOnRight());
    }

    static void useFluentIterable() {
        final Collection<String> filtered = FluentIterable //
                .from(Lists.newArrayList("hello", "world", "lanbo", "shansun", "xujun")) //
                .transform(new Function<String, String>() { //

                    @Override
                    public String apply(String input) {
                        return input == null ? "" : Strings.repeat(input, 3);
                    }
                }) //
                .filter(new Predicate<String>() {
                    @Override
                    public boolean apply(String input) {
                        return !input.contains("r");
                    }
                }) //
                .limit(3)
                .skip(1)
                .toImmutableList();

        System.out.println(filtered);
    }

    static class User {

        private Integer id;
        private String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof User)) {
                return false;
            }

            User that = (User) obj;
            return Objects.equal(id, that.getId())
                    && Objects.equal(name, that.getName());

        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id, name);
        }

        @Override
        public String toString() {
            return name + " (id " + id + ")";
        }

        public Integer getId() {
            return id;
        }

        public boolean isSick() {
            return false;
        }

        public String getAddress() {
            return null;
        }

        public String getName() {
            return name;
        }
    }

}