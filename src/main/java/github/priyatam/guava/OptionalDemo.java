package github.priyatam.guava;


import com.google.common.base.Optional;

class OptionalDemo {

    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);

        System.out.println(possible.isPresent());
        System.out.println(possible.get());
        possible = Optional.absent();
        System.out.println(possible.or(0));
        System.out.println(possible.orNull());
    }

}
