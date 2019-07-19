package reactive_streams.processors;

import reactive_streams.NF;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class NFTransformerProcessor extends SubmissionPublisher<NF> implements Flow.Processor<NF, NF> {

    public static final int TOTAL_REQUESTS = 1;
    private Flow.Subscription subscription;
    private Function function;

    public NFTransformerProcessor(Function<NF,NF> function) {
        super();
        this.function = function;
    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onNext(NF item) {
        submit((NF) function.apply(item));
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
