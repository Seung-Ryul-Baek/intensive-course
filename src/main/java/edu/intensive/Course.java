package edu.intensive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Course {
    @Id
    @GeneratedValue
    Long courseId;
    String courseName;

    @PrePersist
    public void onPrePersist(){
        CourseRegistered ordered = new CourseRegistered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        CourseDeleted courseDeleted = new CourseDeleted();
        BeanUtils.copyProperties(this, courseDeleted);
        courseDeleted.publishAfterCommit();

    }
}
