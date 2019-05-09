package com.plkpiotr.forum.repositories;

import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.plkpiotr.forum.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Component
public class AnswerRepository {

    @Autowired
    private Firestore firestore;

    public void setUsefulForAnswer(@Param("bool") Boolean bool, @Param("id") String id) {
        try {
            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo(FieldPath.documentId(), id).get().get().getDocuments();

            QueryDocumentSnapshot document = documents.get(0);
            document.getReference().update("useful", bool);
            System.out.println("Answer : " + document.getId() + " updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };

    public void deleteAnswerById(String id) {
        firestore.collection("answer").document(id).delete();
    }

    public int countAnswersByUser_Id(String id) {
        try {
            int size = firestore.collection("answer").whereEqualTo("userid", id).get().get().getDocuments().size();

            System.out.println("Answer :" + size + " for user " + id);
            return size;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;

    }

    public int countAnswersByUser_IdAndUseful(String id, boolean useful) {
        try {
            int size = firestore.collection("answer").whereEqualTo("userid", id).whereEqualTo("useful", useful).get().get().getDocuments().size();

            System.out.println("User :" + id + " have " + size + " answer useful");
            return size;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int countAnswersByTopic_Id(Long id) {
        try {
            int size = firestore.collection("answer").whereEqualTo("topicid", id).get().get().getDocuments().size();

            System.out.println("User :" + id + " have " + size + " answer useful");
            return size;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<Answer> findAnswerByUser_IdOrderByCreatedDateDesc(String id) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("userid", id).get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                list.add(new Answer(document.getData()));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Answer> findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(String id, boolean useful) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("userid", id).whereEqualTo("useful", useful)
                    .get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                list.add(new Answer(document.getData()));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Answer> findAnswerByTopic_Id(Long id) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("topicid", id)
                    .get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                list.add(new Answer(document.getData()));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void save(Answer answer) {

        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();

        if (answer.getContent() != null)
            docData.put("content", answer.getContent());

        if (answer.getCreatedDate() != null)
            docData.put("createdDate", answer.getCreatedDate());

        if (answer.getTopicId() != null)
            docData.put("topicid", answer.getTopicId());

        if (answer.getUserId() != null)
            docData.put("userid", answer.getUserId());

        docData.put("useful", answer.isUseful());

        firestore.collection("answer").add(docData);

        System.out.println("Answer of userid " + answer.getUserId() + " added");
    }

}
