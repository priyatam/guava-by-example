package github.priyatam.guava;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;

// http://code.google.com/p/guava-libraries/wiki/PreconditionsExplained
class PreConditionsDemo {

    public static void main(String args[]) {
      //  validate(56);
        validate(102);
       // validate(null);
        validateArray(new ArrayList<Integer>(0));
    }

    static void validate(Integer grade) {
        checkNotNull(grade, "grade cannot be null");
        checkArgument (grade >= 0 && grade < 101, "Grade must be between 0 and 101");
        checkState(grade != null, "Grade is not set");
    }

    static void validateArray(ArrayList<Integer> grades) {
        checkElementIndex(0, grades.size() + 1, "Args is out of bound");
        checkPositionIndex(0, grades.size() + 1, "Args is out of bound");
    }
}
