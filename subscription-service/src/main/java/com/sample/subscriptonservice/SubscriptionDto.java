package com.sample.subscriptonservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private String book_id;
    private String subscriber_name;
    private String date_subscribed;
    private String date_returned;
}