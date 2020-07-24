package vn.chohoa.flower.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DocumentParam;
import vn.chohoa.flower.model.Notification;
import vn.chohoa.flower.model.Person;
import vn.chohoa.flower.repository.NotificationRepository;
import vn.chohoa.flower.util.Constant;

import javax.swing.text.Document;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FCMService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    public String pushNotification(PnsDTO pnsDTO) {
        Message message = Message.builder()
                .putData("content", pnsDTO.getContent())
                .setToken(pnsDTO.getToken())
                .build();
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.info(e.getMessage());
        }
        return response;
    }


    public String savePersonDetailDTO(PersonDTO p) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("users")
                .document(p.getUserName())
                .set(new PersonDTO());
        try {
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ActionDTO sendMessage(NotificationNewDTO p) {

       Notification no = (Notification)
                notificationService.insert(p).getValue()
                        .get(Constant.RESPONSE.JSON_KEY.RETURN_VALUE);

        Firestore firestore
                = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> colFuture = firestore.collection("notification")
                .document(no.getId().toString())
                .set(no);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, no)
                        .build()
        );
    }

    public ActionDTO getDocument(DocumentParam p) {

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(p.getCollection()).document(p.getDocumentName());

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        Notification no = null;

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
           log.info(e.getMessage());
        } catch (ExecutionException e) {
            log.info(e.getMessage());
        }
        if (document.exists()){
            no = document.toObject(Notification.class);
        }else{
            no =null;
        }
        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,no)
                        .build()
        );
    }

    public ActionDTO updateNotification(NotificationUpdateDTO p) {

        Notification no = (Notification)
                notificationService.update(p).getValue()
                        .get(Constant.RESPONSE.JSON_KEY.RETURN_VALUE);

        Firestore firestore
                = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> colFuture = firestore.collection("notification")
                .document(p.getId().toString())
                .set(no);

        return new ActionDTO(
                ImmutableMap.builder()
                        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, no)
                        .build()
        );
    }


}
