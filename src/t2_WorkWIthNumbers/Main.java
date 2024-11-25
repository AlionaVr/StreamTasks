package src.t2_WorkWIthNumbers;


import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        StreamWork streamWork = new StreamWork();
        WithoutStream withoutStream = new WithoutStream();
        streamWork.start(numbers);
        withoutStream.start(numbers);
    }
}
