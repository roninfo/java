package reactive_streams;

import reactive_streams.processors.NFFilterNameProcessor;
import reactive_streams.processors.NFFilterProcessor;
import reactive_streams.processors.NFTransformerProcessor;
import reactive_streams.subscribe.NFSubscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class EmitirNF {

    public static void main(String arg[]) throws InterruptedException {

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>(executorService, 1);

        NFFilterProcessor nfFilterProcessor = new NFFilterProcessor();
        NFFilterNameProcessor nfFilterNameProcessor = new NFFilterNameProcessor();
        NFTransformerProcessor transformerProcessor = new NFTransformerProcessor(item -> item.toUpperCliente());

        publisher.subscribe(nfFilterNameProcessor);
        nfFilterNameProcessor.subscribe(nfFilterProcessor);
        nfFilterProcessor.subscribe(transformerProcessor);
        transformerProcessor.subscribe(new NFSubscriber());

        String thread = Thread.currentThread().getName();
        System.out.println("Thread principal: " + thread);

        System.out.println("Gerando a nota");

        NF nf = new NF("Roni Palacio", "Livro Java 12", 29.99);
        publisher.submit(nf);

        nf = new NF("Ron", "Livro Java sem valor", 10);
        publisher.submit(nf);

        nf = new NF("Roni Palacio", "Livro Java sem valor", 0);
        publisher.submit(nf);

        for (int i = 1; i < 10 ; i++) {
            nf = new NF("Emissões até 10: "+i, "Livro Java "+i, i);
            publisher.submit(nf);
        }

        System.out.println("Parabens pelo livro");
        publisher.close();

        Thread.sleep(30000);
    }
}