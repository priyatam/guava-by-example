package github.priyatam.guava;

import com.google.common.base.*;

import java.util.Arrays;

// http://code.google.com/p/guava-libraries/wiki/StringsExplained
class StringsDemo {

    public static void main(String[] args) {
        joiner();
        splitter();
        charMatcher();
        charsets();
        caseFormat();
        utils();
    }

    static void joiner() {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String join = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(join);
        String join2 = Joiner.on(",").useForNull("not-number").join(Arrays.asList(1, 4, 6, null, 8));
        System.out.println(join2);
    }

    static void splitter() {
        Iterable<String> split = Splitter.on(",") //
                .trimResults()
                .omitEmptyStrings()
                .limit(4)
                .split("foo,bar,, qux");

        for (String s : split) {
            System.out.print(s);
        }

        Iterable<String> split2 = Splitter.fixedLength(3).split("foobarqux");
        for (String s : split2) {
            System.out.println(s);
        }
    }

    static void charMatcher() {
        String replaceFrom = CharMatcher.JAVA_DIGIT.replaceFrom("hello1234", '*');
        System.out.println(replaceFrom);
    }

    static void charsets() {
        byte[] bytes = "hello1234".getBytes(Charsets.UTF_8);
        System.out.println(bytes);
    }

    static void caseFormat() {
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));
    }

    static void utils() {
        System.out.println(Strings.isNullOrEmpty("hello world"));
        System.out.println(Strings.nullToEmpty(null));
        System.out.println(Strings.padStart("hello world", 2, '2'));
        System.out.println(Strings.padEnd("hello world", 2, '2'));
        System.out.println(Strings.repeat("hello ", 3));
    }
}
