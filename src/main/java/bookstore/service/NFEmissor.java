package bookstore.service;

import bookstore.model.Book;
import bookstore.model.NF;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class NFEmissor {


    int availableProcessors = Runtime.getRuntime().availableProcessors();

    ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

    SubmissionPublisher<NF> publisher = new SubmissionPublisher<>(executorService, 1);

    public NFEmissor() {
        publisher.subscribe(new NFSubscriber());
    }

    public void emit(String clientName, Book book) {
        NF nf = new NF(clientName, book.getBook(), 39.99);
        publisher.submit(nf);
    }

    public void close() {
        this.publisher.close();
    }
}