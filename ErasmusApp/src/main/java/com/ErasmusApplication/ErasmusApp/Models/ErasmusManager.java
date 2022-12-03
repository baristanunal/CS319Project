package com.ErasmusApplication.ErasmusApp.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
@Scope("singleton")
public class ErasmusManager {
    private DocumentManager documentManager;
    private NotificationManager notificationManager;

    @Autowired
    public ErasmusManager(DocumentManager documentManager, NotificationManager notificationManager) {
        this.documentManager = documentManager;
        this.notificationManager = notificationManager;
    }
}
