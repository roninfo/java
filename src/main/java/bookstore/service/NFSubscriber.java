package bookstore.service;

import bookstore.model.NF;

import java.util.concurrent.Flow;

public class NFSubscriber implements Flow.Subscriber<NF> {

    private Flow.Subscription subscription;
    public static final long TOTAL_REQUESTS = 1;//Long.MAX_VALUE;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onNext(NF item) {
        WSPrefeitura.emitir(item);
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onCompleted");
    }
}
