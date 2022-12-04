package com.ErasmusApplication.ErasmusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class ErasmusManager {
    private DocumentManager documentManager;
    private NotificationManager notificationManager;

    @Autowired
    public ErasmusManager(DocumentManager documentManager, NotificationManager notificationManager) {
        this.documentManager = documentManager;
        this.notificationManager = notificationManager;
    }

    // TODO
}
