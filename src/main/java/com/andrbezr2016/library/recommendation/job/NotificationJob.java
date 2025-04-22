package com.andrbezr2016.library.recommendation.job;

import com.andrbezr2016.library.recommendation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationJob {

    private final NotificationService notificationService;

    @Scheduled(initialDelayString = "${recommendation-service.notification-job.initial-delay}", fixedDelayString = "${recommendation-service.notification-job.fixed-delay}")
    public void sendNotification() {
        boolean isOk = notificationService.sendNotification();
    }
}
