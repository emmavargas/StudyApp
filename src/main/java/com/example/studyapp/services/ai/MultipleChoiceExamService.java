package com.example.studyapp.services.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.example.studyapp.entities.Course;
import com.example.studyapp.entities.Topic;
import com.example.studyapp.services.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class MultipleChoiceExamService {

    private final ChatClient chatClient;

    private final ResourceLoader resourceLoader;
    private final CourseService courseService;

    public MultipleChoiceExamService(ChatClient.Builder chatClient, CourseService courseService, ResourceLoader resourceLoader) {
        this.chatClient = chatClient.build();
        this.courseService= courseService;
        this.resourceLoader = resourceLoader;

    }


    public ExamChoiceDto getMultipleChoice(Long id, CourseExamDto courseExamDto) {
        String json = generateChoiceExam(id, courseExamDto);
        json =json.replace("```json", "");
        json = json.replace("```", "");
        System.out.println(json);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExamChoiceDto examChoiceDto = objectMapper.readValue(json, ExamChoiceDto.class);
            return examChoiceDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }



    public String generateChoiceExam(Long id, CourseExamDto courseExamDto) {
        String data = getDataCourse(id, courseExamDto);
        Resource resource = resourceLoader.getResource("classpath:prompts/system.txt");
        String content="";
        try {
            content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            //System.out.println(data);
            //System.out.println(content);
            return this.chatClient.prompt()
                    .user(data)
                    .system(content)
                    .call()
                    .content();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo system.txt", e);
        }
    }


    private String getDataCourse(Long id, CourseExamDto courseExamDto) {
        Course course = courseService.getCourse(id);
        String titleCourse = course.getTitle();
        List<Topic> topics = course.getTopics();

        System.out.println(topics.isEmpty());

        String topicsX = String.join(",", courseExamDto.getTopics());

        System.out.println(topicsX);

        List<Topic> topics2 = topics.stream().filter(topic ->
                topic.getTitle().contains(topicsX)
                ).toList();

        System.out.println(topics2.isEmpty());

        StringBuilder data = new StringBuilder();
        data.append("Titulo: ").append(titleCourse).append("\n");
        data.append("Bibliografia: ").append(course.getContentBibliography()).append("\n");
        data.append("Temas:").append("\n");
        for(Topic topic : topics2) {
           data.append(topic.getTitle()).append("\n");
            System.out.println(data.toString());
           data.append(topic.getDescription()).append("\n");
            System.out.println(data.toString());

            data.append(topic.getBibliography()).append("\n");
            System.out.println(data.toString());

            data.append("\n");
        }
        return data.toString();
    }


}
