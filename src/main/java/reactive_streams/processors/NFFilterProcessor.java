package reactive_streams.processors;

import reactive_streams.NF;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class NFFilterProcessor extends SubmissionPublisher<NF> implements Flow.Processor<NF, NF> {

    private Flow.Subscription subscription;
    private static int TOTAL_REQUESTS = 1;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onNext(NF item) {
        if (item.hasValidAmount()) {
            submit(item);
        } else {
            System.out.println("Nota com valor menor ou igual a zero");
            System.out.println("#################\nItem:\n" + item+"\n#################\n");
        }
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
