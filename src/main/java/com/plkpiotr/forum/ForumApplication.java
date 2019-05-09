package com.plkpiotr.forum;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.plkpiotr.forum.repositories.AnswerRepository;
import com.plkpiotr.forum.repositories.TopicRepository;
import com.plkpiotr.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ForumApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Firestore firestore() {
        try {
            InputStream serviceAccount = new FileInputStream("cougartalk-f9536-firebase-adminsdk-5k9js-98de235d79.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            return FirestoreClient.getFirestore();
        } catch(Exception e){
        }
        return null;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public TopicRepository topicRepository() {
        return new TopicRepository();
    }

    @Bean
    public AnswerRepository answerRepository() {
        return new AnswerRepository();
    }

    public static void main(String[] args) {

        SpringApplication.run(ForumApplication.class, args);

        try {


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
