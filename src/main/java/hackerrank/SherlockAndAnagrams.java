package hackerrank;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SherlockAndAnagrams {

    @Test
    public void init() {
        String s = "ifailuhkqq";
        sherlockAndAnagrams(s);
    }

    private void sherlockAndAnagrams(String str) {
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                char[] subChars = str.substring(i, j).toCharArray();
                Arrays.sort(subChars);
                String restSub = String.valueOf(subChars);

                map.put(restSub, map.getOrDefault(restSub, 0) + 1);
            }
        }

        for (int i : map.values()) {
            if (i == 2) {
                count++;
            } else if (i > 2) {
                int x = i*(i-1)/2;
                count+=x;
            }
        }
        System.out.println(count);
    }
}
