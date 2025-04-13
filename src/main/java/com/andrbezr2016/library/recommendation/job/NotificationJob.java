package com.andrbezr2016.library.recommendation.job;

import com.andrbezr2016.library.recommendation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationJob {

    private final NotificationService notificationService;
}
