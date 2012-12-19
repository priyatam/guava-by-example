package github.priyatam.guava;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

public class ThrowablesDemo {
    public static void main(String[] args) {
        basic();
    }

    static void basic() {
        Throwable thrown = null;

        // Checked Exception -> Unchecked Exception
        try {
            sneakyThrow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
            thrown = e;
        }

        // Raw sneaky throw
        try {
            rawSneakyThrow();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // Get stack trace
        System.out.println(Throwables.getStackTraceAsString(thrown));

        // Get exception root cause
        System.out.println(Throwables.getStackTraceAsString(Throwables.getRootCause(thrown)));
    }

    static void sneakyThrow() throws IOException, SQLException {
        try {
            throw new StackOverflowError("this is a error");
        } catch (NullPointerException e) {
            System.err.println("Fucking NEP");
        } catch (Throwable e) {
            Throwables.propagateIfInstanceOf(e, IOException.class);
            Throwables.propagateIfInstanceOf(e, SQLException.class);
            Throwables.propagate(e);
        }
    }

    public static void rawSneakyThrow() {
        try {
            Long.valueOf("1233d");
    // throw new Error("hello");
        } catch (Throwable e) {
            RawSneakyThrowUtil.sneakThrow(e);
        }
    }

    static class RawSneakyThrowUtil {
        static public RuntimeException sneakThrow(Throwable t) {
            if (t == null) {
                throw new NullPointerException();
            }

            RawSneakyThrowUtil.<Error>sneakyThrow0(t);

            return null;
        }

        @SuppressWarnings("unchecked")
        static private <T extends Throwable> T sneakyThrow0(Throwable t) throws T {
            throw (T) t;
        }
    }
}
