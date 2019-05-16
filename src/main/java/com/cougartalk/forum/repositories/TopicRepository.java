package com.cougartalk.forum.repositories;

import com.cougartalk.forum.entities.Topic;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Component
public class TopicRepository {

    @Autowired
    private Firestore firestore;

    public int countTopicsByUser_Id(String id) {

        try {
            ApiFuture<QuerySnapshot> query = firestore.collection("topic").whereEqualTo("userid", id).get();

            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            System.out.println("User :" + id + " have " + documents.size() + " topic");
            return documents.size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Topic findTopicById(String id) {
        try {
            List<QueryDocumentSnapshot> documents = firestore.collection("topic").whereEqualTo(FieldPath.documentId(), id).get().get().getDocuments();

            QueryDocumentSnapshot document = documents.get(0);
            Topic topic = new Topic(document.getData());
            topic.setRealId(document.getId());
            System.out.println("Found user by id: " + topic.getId());
            return topic;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User :" + id + " not found");
        return null;
    }

    public List<Topic> findTopicsByCategoryOrderByCreatedDateDesc(String category) {
        try {
            List<Topic> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("topic").whereEqualTo("category", category).get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Topic newTopic = new Topic(document.getData());

                newTopic.setRealId(document.getId());
                list.add(newTopic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Topic> findTopicsByUser_IdOrderByCreatedDateDesc(String userid) {
        try {
            List<Topic> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("topic").whereEqualTo("userid", userid).get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Topic newTopic = new Topic(document.getData());

                newTopic.setRealId(document.getId());
                list.add(newTopic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Topic> findAll() {
        try {
            List<Topic> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("topic").get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Topic newTopic = new Topic(document.getData());

                newTopic.setRealId(document.getId());
                list.add(newTopic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void save(Topic topic) {

        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();
        docData.put("content", topic.getContent());
        docData.put("createdDate", topic.getCreatedDate());
        docData.put("category", topic.getCategory());
        docData.put("title", topic.getTitle());
        docData.put("userid", topic.getUserId());
        docData.put("username", topic.getUsername());

        firestore.collection("topic").add(docData);

        System.out.println("Topic of userid " + topic.getUserId() + " added");

    }
}