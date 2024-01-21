package com.sample.subscriptonservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private RestTemplate restTemplate;

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        String book_id = subscription.getBook_id();
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/books/" + book_id + "/available", Integer.class);
        if (responseEntity.getBody() < 1) {
            //throw error if available books are less than 1
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422), "No books available");
        }
        else
        {
             //update number of books in book_db from subscription service.
             restTemplate.put("http://localhost:8080/api/books/"+ book_id +"/borrowed/yes",  null);
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public ResponseDto getSubscription(String subscriptionId) {

        ResponseDto responseDto = new ResponseDto();
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        SubscriptionDto subscriptionDto = mapToSubscription(subscription);

        ResponseEntity<BookDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/books/" + subscription.getBook_id(),
                        BookDto.class);

        BookDto bookDto = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());

        responseDto.setSubscription(subscriptionDto);
        responseDto.setBook(bookDto);

        return responseDto;
    }

    @Override
    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    private SubscriptionDto mapToSubscription(Subscription subscription) {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setBook_id(subscription.getBook_id());
        subscriptionDto.setSubscriber_name(subscription.getSubscriber_name());
        subscriptionDto.setDate_subscribed(subscription.getDate_subscribed());
        subscriptionDto.setDate_returned(subscription.getDate_returned());
        return subscriptionDto;
    }
}
