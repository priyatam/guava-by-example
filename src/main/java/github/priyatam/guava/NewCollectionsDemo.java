package github.priyatam.guava;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

class NewCollectionsDemo {

    static void main(String args[]) {
        tables();
    }

    static void tables() {
        Table<Character, Integer, String> aTable = HashBasedTable.<Character, Integer, String> create();

        for (char a = 'A'; a <= 'C'; a++) {
            for (int b = 1; b <= 3; b++) {
                aTable.put(a, b, String.format("%c%d", a, b));
            }
        }

        System.out.println(aTable.column(2));
        System.out.println(aTable.row('A'));
        System.out.println(aTable.get('B', 2));
        System.out.println(aTable.contains('N', 4));
        System.out.println(aTable.containsColumn(3));
        System.out.println(aTable.containsRow('G'));
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowMap());
        System.out.println(aTable.remove('B', 3));
    }
}
