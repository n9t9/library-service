package com.sample.subscriptonservice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscriptions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    private String book_id;
    private String subscriber_name;
    private String date_subscribed;
    private String date_returned;
}