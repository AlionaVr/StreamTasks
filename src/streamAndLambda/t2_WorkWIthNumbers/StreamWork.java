package src.streamAndLambda.t2_WorkWIthNumbers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamWork {
    public void start(List<Integer> numbers) {
        String result = Arrays.toString(numbers.stream()
                .filter(num -> num > 0)
                .filter(num -> num % 2 == 0).sorted(Comparator.naturalOrder())
                .toArray(Integer[]::new));
        System.out.println(result);
    }
}
