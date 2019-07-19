package type_inference;

import java.util.ArrayList;

public class TypeInferenceRun {
    public static void main (String var[]) {
        var lista = new ArrayList<>();
        lista.add("Roni Palacio");
        lista.add(34);
        lista.add("anos");
        lista.add(77.8);
        lista.add("Kilos");
        lista.add(true);
        lista.add('a');

        for(var x : lista) {
            if (x instanceof Character) {
                System.out.println(x + " i'm char");
            }
            if (x instanceof String) {
                System.out.println(x + " i'm string");
            }
            if (x instanceof Integer) {
                System.out.println(x + " i'm integer");
            }
            if (x instanceof Float) {
                System.out.println(x + " i'm float");
            }
            if (x instanceof Double) {
                System.out.println(x + " i'm double");
            }
            if (x instanceof Boolean) {
                System.out.println(x + " i'm boolean");
            }
        }
    }
}
