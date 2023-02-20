package com.mehrab.notification;

import com.mehrab.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .message(notificationRequest.message())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("mehrab")
                        .sentAt(LocalDateTime.now())
                .build());
    }
}
