package reactive_streams.processors;

import reactive_streams.NF;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class NFFilterNameProcessor extends SubmissionPublisher<NF> implements Flow.Processor<NF, NF> {

    public static final int TOTAL_REQUESTS = 1;
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(TOTAL_REQUESTS);
    }

    @Override
    public void onNext(NF item) {
        if (item.getCliente().length() > 10) {
            submit(item);
        } else {
            System.out.println("Cliente com valor invalido");
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
