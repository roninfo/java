package hackerrank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RackerRankRun {
    public static void main(String args[]) {
        List<Long> longs1 = Arrays.asList(1l, 2l, 2l, 4l);
        List<Long> longs = Arrays.asList(1l, 3l, 9l, 9l, 27l, 81l);
        List<Long> integers = Arrays.asList(1l, 5l, 5l, 25l, 125l);
        long r = integers.size();

        countTriplets(integers, r);
        countTriplets(longs, longs.size());
        countTriplets(longs1, longs1.size());
    }

    static long countTriplets(List<Long> arr, long r) {

        System.out.println(arr.toString());
        System.out.println(count(arr, r));

        return 0;

    }

    private static long count(List<Long> arr, long r) {
        long conjuntos = 0l;
        List<Long> lista = new ArrayList<>();

        Map<Long, Long> controlRepeat = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            Long aLong = arr.get(i);
            long count = arr.stream().filter(n -> n == aLong).count();
            if (count > 1) {
                controlRepeat.put(arr.get(i), count);
            }
        }

        List<Long> uniques = arr.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        uniques.sort((a,b) -> Long.compare(a,b));

        Long repeat = 0l;
        for (int i = 0; i <= uniques.size(); i++) {
            if (r - i < 3) {
                break;
            }

            for (int interno = i; interno < uniques.size(); interno++) {
                Long numActual = uniques.get(interno);
                lista.add(numActual);

                if (lista.size() == 3) {
                    conjuntos++;

                    if (lista.stream().anyMatch(controlRepeat::containsKey)) {
                        for (Long n : lista) {
                            if (controlRepeat.containsKey(n)) {
                                if( repeat < (controlRepeat.get(n) - 1)) {
                                    repeat++;
                                    i--;
                                } else {
                                    repeat = 0l;
                                }
                            }
                        }
                    }
                    lista.clear();
                    break;
                }
            }
        }


        return conjuntos;
    }

    private static void usernamesSystem() {
        int n = 4;
        String names = "roni;roni;larissa;roni;larissa;roni";

        String[] split = names.split(";");
        Map<String, Integer> mapa = new HashMap<>();

        List<String> namesList = new ArrayList<>();
        for (String name : split) {
            if (mapa.containsKey(name)) {
                int increment = mapa.get(name)+1;
                mapa.put(name, increment);
                namesList.add(name+increment);
            } else {
                mapa.put(name, 0);
                namesList.add(name);
            }
        }

        System.out.println(namesList);
    }

    private static void repeatedString() {
        //        aba
        //        10
        String s = "abaabaaa";
        long n = 45;

        long count = Stream.of(s.split("")).filter(sa -> sa.equals("a")).count();

        long factor = (n/s.length());
        count = factor * count;

        long mod = (n%s.length());

        for(int i=0 ; i < mod ; i++) {
            if(s.charAt(i) == 'a') {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void jumpClouds() {
        String sky = "0 0 1 0 0 1 0;0 0 0 0 1 0 0;0 0 0 0 0 1 0 0 1 0 0 0 0 1 0;0 0 1 0 0 1 0;0 0 0 1 0 0";

        String[] groupsClouds = sky.split(";");

        for (String groupClouds : groupsClouds) {
            String[] clouds = groupClouds.split("\\s");
            int cumulus = 0;
            int jumps = 0;

            for (int i =0; i < clouds.length; i++) {
                String nextCloud = "-1";

                if (i+1 < clouds.length) {
                    nextCloud = clouds[i+1];
                }

                if (nextCloud.equals("-1")) {
                    break;
                }

                if (clouds[i].equals("1")) {
                    cumulus = 0;
                    continue;
                }

                if (clouds[i].equals("0")) {
                    cumulus++;
                    jumps++;

                    if (cumulus == 2 && nextCloud.equals("0")) {
                        jumps--;
                        cumulus=0;
                    }
                }
            }
            System.out.println(clouds+": "+jumps);
        }
    }

    private static void upOrDownHill() {
        String hills = "DDUUDDUDUUUD;UDDDUDUU;UUDDDDUUUUUDDD;DDUUUUDD;UDDDUU;DDDUUUUDDU";
        String[] split = hills.split(";");

        for (String hill : split) {

            char[] steps = hill.toUpperCase().toCharArray();

            int upDown = 0;
            int level = 0;

            for(char upOrDown : steps) {

                if (upOrDown == 'D') {
                    upDown++;
                } else if (upOrDown == 'U') {
                    upDown--;
                }

                if (upDown == 0 && upOrDown == 'U') {
                    level++;
                }
            }

            System.out.println(hill +": "+level);
        }
    }

    private static int sockMerchant() {

        int[] nums = {10,10,10,10,50,20, 20, 10, 10, 30, 50, 10, 20};
        int qntd = nums.length;
        int retorno = 0;

        Map<Integer, Integer> numericos = new HashMap<>();

        for (int num : nums) {
            if (numericos.containsKey(num)) {
                numericos.put(num, numericos.get(num)+1);
            } else {
                numericos.put(num, 1);
            }
        }

        for (Integer key : numericos.keySet()) {
            if (numericos.get(key) > 1) {
                retorno += numericos.get(key) / 2;
            }
        }

        return retorno;
    }
}
