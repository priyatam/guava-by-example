package github.priyatam.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Files;

import java.io.File;
import java.util.List;

class IODemo {

    public static void main(String args[]) {

    }

    static void files() {
        File file = new File("woop.txt");
        try {
            Files.touch(file);

            Files.write("Hey sailor!\n hello li", file, Charsets.UTF_8);

            Files.toByteArray(file);
            Files.newInputStreamSupplier(file);
            Files.readFirstLine(file, Charsets.UTF_8);
            Files.toString(file, Charsets.UTF_8);
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            lines.get(0);
            lines.get(1);
            Files.getFileExtension(file.getName());
            file.delete();

        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

}
