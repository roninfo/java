package coderbyte;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        //capitalizeStartWord();

        int x = 45;
        LocalTime localTime = LocalTime.ofSecondOfDay(x * 60);
        localTime.toString();
        System.out.println(localTime.getHour()+":"+localTime.getMinute());

    }

    private static void capitalizeStartWord() {
        String str = "i ran there";
        String[] s = str.split(" ");

        StringBuilder valor = new StringBuilder("");

        for (String var : s) {
            var = var.replaceFirst(String.valueOf(var.charAt(0)), String.valueOf(var.charAt(0)).toUpperCase());
            valor.append(var);
            valor.append(" ");
        }
        System.out.println(valor.toString());
    }
}
