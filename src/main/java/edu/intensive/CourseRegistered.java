package edu.intensive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourseRegistered extends AbstractEvent{
    Long courseId;
    String courseName;
    public CourseRegistered() {
        super();
    }
}
