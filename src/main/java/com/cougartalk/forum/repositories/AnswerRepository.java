package com.cougartalk.forum.repositories;

import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.cougartalk.forum.entities.Answer;
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

    /**
     * Sets the usefulness of an answer.
     *
     * @param bool the boolean meaning if it is usefull or not, true for usefull and false for not usefull.
     * @param id the string containing the document id.
     */
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

    /**
     * Method that deletes an answer by its id.
     *
     * @param id the string containing the answer id.
     */
    public void deleteAnswerById(String id) {
        firestore.collection("answer").document(id).delete();
    }

    /**
     * Counts how many answers have a user by the user id.
     *
     * @param id the string containing the user id.
     *
     * @return {@code int} the answer quantity, 0 if none.
     */
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

    /**
     * Counts how many usefull answers have a user by the user id.
     *
     * @param id the string containing the user id.
     * @param useful the boolean telling that the answer must be usefull.
     *
     * @return {@code int} the useful answer quantity, 0 if none.
     */
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

    /**
     * Counts how many answers have a topic by the topic id.
     *
     * @param id the string containing the topic id.
     *
     * @return {@code int} the answer quantity, 0 if none.
     */
    public int countAnswersByTopic_Id(String id) {
        try {
            int size = firestore.collection("answer").whereEqualTo("topicid", id).get().get().getDocuments().size();

            System.out.println("User :" + id + " have " + size + " answer useful");
            return size;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Gets the answers from an user and sort it in a descending order by creation date.
     *
     * @param id the string containing the user id.
     *
     * @return {@code List<Answer>} of answer in descending order if it is non-null, {@code ""} null otherwise.
     */
    public List<Answer> findAnswerByUser_IdOrderByCreatedDateDesc(String id) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("userid", id).get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Answer newAnswer = new Answer(document.getData());

                newAnswer.setRealId(document.getId());
                list.add(newAnswer);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Gets the useful answers from an user and sort it in a descending order by creation date.
     *
     * @param id the string containing the user id.
     * @param useful the boolean telling that the answer must be usefull.
     *
     * @return {@code List<Answer>} of useful answer in descending order if it is non-null, {@code ""} null otherwise.
     */
    public List<Answer> findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(String id, boolean useful) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("userid", id).whereEqualTo("useful", useful)
                    .get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Answer newAnswer = new Answer(document.getData());

                newAnswer.setRealId(document.getId());
                list.add(newAnswer);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Gets answers by a topic id.
     *
     * @param id String containing the topic id.
     *
     * @return {@code List<answer>} the answer List, 0 if none.
     */
    public List<Answer> findAnswerByTopic_Id(String id) {
        try {
            List<Answer> list = new Vector<>();

            List<QueryDocumentSnapshot> documents = firestore.collection("answer").whereEqualTo("topicid", id)
                    .get().get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Answer newAnswer = new Answer(document.getData());

                newAnswer.setRealId(document.getId());
                list.add(newAnswer);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Add an answer.
     *
     * @param answer the Answer object containing the answer data.
     */
    public void save(Answer answer) {

        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();

        docData.put("content", answer.getContent());
        docData.put("createdDate", answer.getCreatedDate());
        docData.put("topicid", answer.getTopicId());
        docData.put("userid", answer.getUserId());
        docData.put("useful", answer.isUseful());
        docData.put("username", answer.getUsername());
        docData.put("topictitle", answer.getTopicTitle());

        firestore.collection("answer").add(docData);

        System.out.println("Answer of userid " + answer.getUserId() + " added");
    }

}
