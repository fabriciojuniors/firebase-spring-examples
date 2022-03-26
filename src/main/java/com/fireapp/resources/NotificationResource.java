package com.fireapp.resources;

import com.fireapp.models.Notificacao;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/firebase/message")
public class NotificationResource {

    public final String TOKEN = "cfnHqgHMTpuPxdNZ4OvO6h:APA91bGCHXHU5vaOXkPBwCAHfufItKvqMt0r9PDwU7xEE62CgCqZt5NSsb2_gpd3ckMR8kdqJ94nbir8VBJRO_IgT3TE-sGFLH1QDMbgl28ZAzjmMm4K2pEQovQHiKXZkZ1hIFnRt-7s";

    public static final Logger logger = Logger.getLogger(String.valueOf(NotificationResource.class));


    @PostMapping
    public void send(@RequestBody Notificacao notificacao) {
        try {

            Notification notification = Notification.builder()
                    .setTitle(notificacao.getTitulo())
                    .setBody(notificacao.getCorpo())
                    .build();

            Message message = Message.builder()
                    .setNotification(notification)
                    .setToken(TOKEN)
                    .build();

            /*MULTIPLOS DISPOSITOS
            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(notification)
                    .addAllTokens(Arrays.asList("token1", "token2"))
                    .build();
            String response = FirebaseMessaging.getInstance().sendMulticast(message);
             */

            String response = FirebaseMessaging.getInstance().send(message);

            logger.info("Mensagem enviada com sucesso. " + response);
        } catch (FirebaseMessagingException e) {
            logger.info("Erro ao enviar notification: " + e.getMessage());
        }

    }
}
