package hackerrank;

import util.GetFileFromResources;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrequencyQueries {

    static String fileName = "inputs/frequency_input10.txt";

    static public void main(String[] args) {

        long start = System.currentTimeMillis();
        runWithArray();
//        runWithList();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

    static String freqQueryArray(List<int[]> queries) {
        final int INSERT = 1;
        final int DELETE = 2;
        Map<Integer, Integer> group = new HashMap<>();
        StringBuffer result = new StringBuffer();

        for (int[] query : queries) {

            Integer key = query[0];
            Integer value = query[1];

            if (key.equals(INSERT)) {
                group.put(value, group.getOrDefault(value, 0) + 1);
            } else if (key.equals(DELETE)) {
                Integer valueMap = group.get(value);
                if (valueMap != null && valueMap >= 1 ) {
                    group.put(value, valueMap - 1);
                }

            } else {
                result.append(group.containsValue(value) ? "1\n" : "0\n");
            }
        }
        return result.toString();
    }

    static String freqQuery(List<List<Integer>> queries) {
        final int INSERT = 1;
        final int DELETE = 2;
        Map<Integer, Integer> group = new HashMap<>();
        StringBuffer result = new StringBuffer();

        for (List<Integer> query : queries) {

            Integer value = query.get(1);

            switch (query.get(0)) {
                case INSERT:
                    group.put(value, group.getOrDefault(value, 0) + 1);

                case DELETE:
                    Integer valueMap = group.get(value);
                    if (valueMap != null && valueMap >= 1 ) {
                        group.put(value, valueMap - 1);
                    }

                default: result.append(group.containsValue(value) ? "1\n" : "0\n");
            }
        }
        return result.toString();
    }

    static private void runWithArray() {
        File file = GetFileFromResources.INSTANCE.getFileFromResources(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            String my = freqQueryArray(queries);

            //System.out.println(my);
            //.stream()
              //      .collect(Collectors.joining("\n")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runWithList() {

        try {

            File file = GetFileFromResources.INSTANCE.getFileFromResources(fileName);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> queries = new ArrayList<>();

            IntStream.range(0, q).forEach(i -> {
                try {
                    queries.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            String ans = freqQuery(queries);

           // System.out.println(ans);

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
