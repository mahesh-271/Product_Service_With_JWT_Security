package com.product.management.product_management.sequential;


import com.product.management.product_management.config.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class Controller1 {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/url1")
    public String getListOfString(){
        return "Value1";
    }

    @GetMapping("/url2")
    public String getListOfString1(){
        return "Value2";
    }

//    @GetMapping("/sequential")
//    public String callSequentially() {
//
//        long startTime = System.currentTimeMillis();
//
//        // -------- Call 1 --------
//        long call1Start = System.currentTimeMillis();
//
//        log.info("Call 1  start Time :" + call1Start);
//        String string1 =
//                restTemplate.getForObject("http://localhost:8080/url1", String.class);
//        long call1Time = System.currentTimeMillis() - call1Start;
//
//        log.info("Call 1 (/url1) time: {} ms", call1Time);
//
//        // -------- Call 2 --------
//        long call2Start = System.currentTimeMillis();
//
//        log.info("Call 2  start Time :" + call2Start);
//
//        String string2 =
//                restTemplate.getForObject("http://localhost:8080/url2", String.class);
//        long call2Time = System.currentTimeMillis() - call2Start;
//
//        log.info("Call 2 (/url2) time: {} ms", call2Time);
//
//        // -------- Total --------
//        long totalTime = System.currentTimeMillis() - startTime;
//        log.info("Total sequential time: {} ms", totalTime);
//
//        return "Sequential Calls are : " + string1 + " : " + string2;
//    }

    @GetMapping("/sequential")
    public String callSequentially() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("url1");
        String string1 =
                restTemplate.getForObject("http://localhost:8080/url1", String.class);
        stopWatch.stop();

        stopWatch.start("url2");
        String string2 =
                restTemplate.getForObject("http://localhost:8080/url2", String.class);

        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

        return "Sequential Calls are : " + string1 + " : " + string2;
    }

    @GetMapping("/parallel")
    public String callsParallel() throws ExecutionException, InterruptedException {

        long totalStart = System.currentTimeMillis();

        CompletableFuture<String> result1 = CompletableFuture.supplyAsync(()->
        {
                    long startTime = System.currentTimeMillis();
                    String response1 = restTemplate.getForObject("http://localhost:8080/url1", String.class);

                    long totalResponseTimeAfterCall1 = System.currentTimeMillis() - startTime;

                    log.info("Total response time ; "+ totalResponseTimeAfterCall1);

                    return response1;
                });

        CompletableFuture<String> result2 = CompletableFuture.supplyAsync(()->
        {
            long secondStartTime = System.currentTimeMillis();

            String response2 = restTemplate.getForObject("http://localhost:8080/url2", String.class);

            long totalResponseTimeAfterSecondCall = System.currentTimeMillis() -  secondStartTime;

            log.info( "Total time for second call is :" + totalResponseTimeAfterSecondCall);

            return response2;
        });

        CompletableFuture.allOf(result1 , result2).join();

        long totalTimeforTwoCalls = System.currentTimeMillis() - totalStart;

        log.info( "Total time for Both Calls are :" + totalTimeforTwoCalls);

        return "Parallel result: " + result1.get() + " | " + result2.get();
    }
}
