package com.example.habitustracker.firebase

import android.util.Log

/**
 * Servicio base preparado para notificaciones Firebase Cloud Messaging (FCM).
 * Para activarlo en producción, añade la dependencia en Gradle:
 * implementation("com.google.firebase:firebase-messaging-ktx")
 * e importa: com.google.firebase.messaging.FirebaseMessagingService
 * e importa: com.google.firebase.messaging.RemoteMessage
 */
class MyFirebaseMessagingService {
    
    // Simula recibir un token de notificación
    fun onNewToken(token: String) {
        Log.d("FCM", "Nuevo token de Firebase: $token")
        // Aquí se enviaría el token al servidor backend del usuario
    }

    // Simula recibir un mensaje/notificación push
    fun onMessageReceived(title: String, body: String) {
        Log.d("FCM", "Notificación recibida: $title - $body")
        // Lógica para mostrar notificación local al usuario
    }
}
