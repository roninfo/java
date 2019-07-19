package new_switch;

public class NewSwitchRun {

    public static void main(String args[]) {
        numericString(1);
        numericString(2);
    }

    public static void numericString(int valor) {
        switch(valor) {
            case 1 -> System.out.println("printa 1");
            case 2 -> System.out.println("printa 2");
        };
    }
}
