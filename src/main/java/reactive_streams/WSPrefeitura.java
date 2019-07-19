package reactive_streams;

public class WSPrefeitura {
    public static void emitir(NF nf) {
        try {
            System.out.println("emitindo... na thread " + Thread.currentThread().getName());
            System.out.println("nota emitida para o item: " +nf);
            Thread.sleep(5000);
            System.out.println("emitido!");
        } catch (Exception exc) {
            System.out.println("Erro ao emitir");
        }
    }
}
