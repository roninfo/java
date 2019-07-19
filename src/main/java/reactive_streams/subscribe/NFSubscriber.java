package reactive_streams.subscribe;

import reactive_streams.NF;
import reactive_streams.WSPrefeitura;

import java.util.concurrent.Flow;

public class NFSubscriber implements Flow.Subscriber<NF> {

    private Flow.Subscription subscription;
    public static final long TOTAL_REQUESTS = 1;//Long.MAX_VALUE;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(TOTAL_REQUESTS);
        System.out.println("onSubscribe");
    }

    @Override
    public void onNext(NF item) {
        System.out.println("onNext");
        WSPrefeitura.emitir(item);
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError:");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onCompleted");
    }
}
