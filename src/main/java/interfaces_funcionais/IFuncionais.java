package interfaces_funcionais;

import java.util.List;

public class IFuncionais {

    public static void main(String args[]) {
        runnerBefore();
        runnerActual();
        thread();
        validaTeclas("asdsdfsdfsdfsgf");
    }

    private static void runnerBefore() {
        Runnable runn = new Runnable() {
            @Override
            public void run() {
                var itemsGenerics = List.of("ba1","ba2",10,true,false,'z');
                itemsGenerics.forEach(System.out::println);
            }
        };

        new Thread(runn).start();
    }

    private static void runnerActual() {
        Runnable runn = () -> {
                var itemsGenerics = List.of("","otherList","aa1","aa2",20,true,false,'z');
                itemsGenerics.forEach(System.out::println);
        };
        new Thread(runn).start();
    }

    private static void thread() {
        new Thread(() -> {
            List.of("\n******","otherList","a1t","a2t",30,true,false,'z').forEach(System.out::println);
        }).start();
    }

    interface Validador<T> {
        boolean valida(T t);
    }

    private static void validaTeclas(String teclas) {
        Validador<String> valida = valor -> valor.contains("asd");

        if (valida.valida(teclas)) {
            System.out.println("Tem as teclas");
        }
    }

}
