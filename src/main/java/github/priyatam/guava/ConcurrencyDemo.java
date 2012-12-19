package github.priyatam.guava;


import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

class ConcurrencyDemo {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello future";
            }
        });

       Futures.addCallback(future, new FutureCallback<String>() {
           @Override
           public void onSuccess(String result) {
               System.out.println("I got the result: " + result);
           }

           @Override
           public void onFailure(Throwable t) {
               System.out.println("Encounter an exception!");
               t.printStackTrace();
           }
       });
    }


}
