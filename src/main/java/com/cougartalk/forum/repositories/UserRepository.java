package com.cougartalk.forum.repositories;

import com.cougartalk.forum.entities.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository  {

    @Autowired
    private Firestore firestore;

    public User getUserByUsername(String username) {
        try {
            ApiFuture<QuerySnapshot> query = firestore.collection("user").whereEqualTo("username", username).get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            QueryDocumentSnapshot document = documents.get(0);

            if (document.contains("username") && document.getString("username").equals(username)) {
                User user = new User(document.getData());
                System.out.println("Found user: " + user.getUsername());
                user.setRealId(document.getId());
                return user;
            }
            System.out.println("User :" + username + " not found");
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User getUserById(String Id) {

        try {
            ApiFuture<QuerySnapshot> query = firestore.collection("user").whereEqualTo(FieldPath.documentId(), Id).get();

            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            QueryDocumentSnapshot document = documents.get(0);
            User user = new User(document.getData());
            user.setRealId(document.getId());
            System.out.println("Found user by id: " + user.getId());
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User :" + Id + " not found");
        return null;
    }

    public List<User> findAll() {
        return null;
    }

    public void save(User user) {

        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();
        if (user.getUsername().endsWith("@cougars.csusm.edu"))
            docData.put("username", user.getUsername());
        if (user.getIntroduction() != null)
            docData.put("introduction", user.getIntroduction());
        docData.put("password", user.getPassword());
        docData.put("createdDate", user.getCreatedDate());

        firestore.collection("user").add(docData);

        System.out.println("User " + user.getUsername() + " added");

    }

/*    @Query (
        value = "(SELECT SUM(points) FROM (SELECT COUNT(topic.id_user) AS points FROM topic WHERE topic.id_user = :id" +
                " UNION ALL SELECT 2 * COUNT(answer.id_user) AS points FROM answer WHERE answer.id_user = :id UNION ALL " +
                "SELECT 3 * COUNT(answer.id_user) AS points FROM answer WHERE answer.id_user = :id AND answer.useful = TRUE) t)",
        nativeQuery = true
    ) */

    public Long getPoints(String id) {
        return 1L;
    }
}
