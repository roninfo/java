package hackerrank;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MarkAndToys {

    @Test
    public void solution() {
        int[] prices = {1, 12, 5, 111, 200, 1000, 10};
        int k = 50;

        System.out.println(maximumToys(prices, k));
    }

    int maximumToys(int[] prices, int k) {

        List<Integer> collect = Stream.of(prices).flatMapToInt(n -> Arrays.stream(n)).sorted().boxed().collect(Collectors.toList());

        int sum = 0;
        int count = 0;
        for (int i : collect) {
            if ((sum + i) <= k) {
                sum += i;
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
        return count;
    }

}
