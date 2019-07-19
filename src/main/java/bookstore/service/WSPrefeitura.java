package bookstore.service;


import bookstore.model.NF;

public class WSPrefeitura {
    public static void emitir(NF nf) {
        try {
            System.out.println("emitindo...");
            System.out.println("Item: " + nf);
            Thread.sleep(5000);
            System.out.println("emitido!");
        } catch (Exception exc) {
            System.out.println("Erro ao emitir");
        }
    }
}
