package src.streamAndLambda.t2_WorkWIthNumbers;

import java.util.ArrayList;
import java.util.List;

public class WithoutStream {
    public void start(List<Integer> numbers) {
        ArrayList<Integer> sortedNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (num > 0 && num % 2 == 0) {
                sortedNumbers.add(num);
                //Collections.sort (sortedNumbers);
            }
        }
        for (int i = 0; i < sortedNumbers.size(); i++) {
            for (int j = i + 1; j < sortedNumbers.size(); j++) {
                if (sortedNumbers.get(i) > sortedNumbers.get(j)) {
                    int temp = sortedNumbers.get(i);
                    sortedNumbers.set(i, sortedNumbers.get(j));
                    sortedNumbers.set(j, temp);
                }
            }
        }
        System.out.println(sortedNumbers);
    }

}

