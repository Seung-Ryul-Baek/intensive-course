package edu.intensive;

import edu.intensive.config.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler {
    @Autowired
    CourseRepository courseRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureApproved(@Payload LectureApproved lectureApproved) {
        if(lectureApproved.isMe()) {
            Optional<Course> course = courseRepository.findById(lectureApproved.getCourseId());

            Long cur = course.get().getCourseParticipants();
            if (cur == null) {
                cur = new Long(1);
            } else {
                cur++;
            }
            course.get().setCourseParticipants(cur);
            courseRepository.save(course.get());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureEvaluated(@Payload LectureEvaluated lectureEvaluated) {
        if (lectureEvaluated.isMe()) {
            Optional<Course> course = courseRepository.findById(lectureEvaluated.getCourseId());

            Double averageGrade = course.get().getCourseAverageGrade();
            if (averageGrade == null)
                averageGrade = new Double(0);
            Long gradedNumber = course.get().getCourseGradedNumber();
            if (gradedNumber == null)
                gradedNumber = new Long(0);
            averageGrade = (averageGrade*gradedNumber + lectureEvaluated.getGrade()) / (gradedNumber + 1);
            gradedNumber++;

            course.get().setCourseAverageGrade(averageGrade);
            course.get().setCourseGradedNumber(gradedNumber);
            courseRepository.save(course.get());
        }
    }

}
