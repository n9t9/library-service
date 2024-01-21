package com.sample.subscriptonservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subscriptions")
@AllArgsConstructor
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> saveSubscription(@RequestBody Subscription subscription){
        Subscription savedSubscription = subscriptionService.saveSubscription(subscription);
        return new ResponseEntity<>(savedSubscription, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Subscription> getSubscriptions()
    {
        return subscriptionService.getSubscriptions();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getSubscription(@PathVariable("id") String subscriptionId){
        ResponseDto responseDto = subscriptionService.getSubscription(subscriptionId);
        return ResponseEntity.ok(responseDto);
    }

}
