package com.example.studyapp.services.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
@Service
public class MultipleChoiceExamStaticImpl implements MultipleChoiceService{

    @Override
    public ExamChoiceDto getMultipleChoice(Long id, CourseExamDto courseExamDto) {
        String json = "{\n" +
                "    \"course\": \"Analisis Matematico II\",\n" +
                "    \"topics\": [\n" +
                "        \"Logica\"\n" +
                "    ],\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de las siguientes proposiciones es una tautología?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"p ∧ ¬p\",\n" +
                "                \"option2\": \"p → q\",\n" +
                "                \"option3\": \"p ∨ ¬p\",\n" +
                "                \"option4\": \"p ∧ q\"\n" +
                "            },\n" +
                "            \"answer\": \"p ∨ ¬p\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de las siguientes opciones representa la negación correcta de la proposición 'Todos los gatos son negros'?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Ningún gato es negro\",\n" +
                "                \"option2\": \"Algunos gatos no son negros\",\n" +
                "                \"option3\": \"Todos los gatos son blancos\",\n" +
                "                \"option4\": \"Existe un gato que es negro\"\n" +
                "            },\n" +
                "            \"answer\": \"Algunos gatos no son negros\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"Si p es verdadero y q es falso, ¿cuál es el valor de verdad de la proposición (p → q) ∨ (¬p ∧ q)?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Verdadero\",\n" +
                "                \"option2\": \"Falso\",\n" +
                "                \"option3\": \"No se puede determinar\",\n" +
                "                \"option4\": \"Depende del contexto\"\n" +
                "            },\n" +
                "            \"answer\": \"Falso\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de las siguientes leyes lógicas permite simplificar la expresión (p ∧ q) ∨ (p ∧ ¬q)?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Ley de Morgan\",\n" +
                "                \"option2\": \"Ley de absorción\",\n" +
                "                \"option3\": \"Ley distributiva\",\n" +
                "                \"option4\": \"Ley de idempotencia\"\n" +
                "            },\n" +
                "            \"answer\": \"Ley distributiva\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de los siguientes conectivos lógicos es asociativo?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"→ (Implicación)\",\n" +
                "                \"option2\": \"¬ (Negación)\",\n" +
                "                \"option3\": \"∨ (Disyunción)\",\n" +
                "                \"option4\": \"⊕ (O exclusivo)\"\n" +
                "            },\n" +
                "            \"answer\": \"∨ (Disyunción)\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"Si la proposición 'Si llueve, entonces la calle se moja' es verdadera, ¿cuál de las siguientes proposiciones también debe ser verdadera?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Si la calle se moja, entonces llueve\",\n" +
                "                \"option2\": \"Si la calle no se moja, entonces no llueve\",\n" +
                "                \"option3\": \"Si no llueve, entonces la calle no se moja\",\n" +
                "                \"option4\": \"Llueve y la calle se moja\"\n" +
                "            },\n" +
                "            \"answer\": \"Si la calle no se moja, entonces no llueve\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de las siguientes expresiones representa la forma lógica de 'p es necesario para q'?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"p → q\",\n" +
                "                \"option2\": \"q → p\",\n" +
                "                \"option3\": \"p ↔ q\",\n" +
                "                \"option4\": \"¬p → ¬q\"\n" +
                "            },\n" +
                "            \"answer\": \"q → p\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Qué regla de inferencia se utiliza para deducir q de las premisas p → q y p?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Modus Tollens\",\n" +
                "                \"option2\": \"Modus Ponens\",\n" +
                "                \"option3\": \"Silogismo Hipotético\",\n" +
                "                \"option4\": \"Dilema Constructivo\"\n" +
                "            },\n" +
                "            \"answer\": \"Modus Ponens\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Cuál de las siguientes afirmaciones es equivalente a 'No es cierto que Juan es alto y Pedro es bajo'?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Juan no es alto y Pedro no es bajo\",\n" +
                "                \"option2\": \"Juan es alto o Pedro es bajo\",\n" +
                "                \"option3\": \"Juan no es alto o Pedro no es bajo\",\n" +
                "                \"option4\": \"Juan es bajo y Pedro es alto\"\n" +
                "            },\n" +
                "            \"answer\": \"Juan no es alto o Pedro no es bajo\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"question\": \"¿Qué nombre recibe la falacia que consiste en asumir que porque dos eventos ocurren juntos, uno causa al otro?\",\n" +
                "            \"options\": {\n" +
                "                \"option1\": \"Ad hominem\",\n" +
                "                \"option2\": \"Post hoc ergo propter hoc\",\n" +
                "                \"option3\": \"Argumentum ad populum\",\n" +
                "                \"option4\": \"Petición de principio\"\n" +
                "            },\n" +
                "            \"answer\": \"Post hoc ergo propter hoc\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExamChoiceDto examChoiceDto = objectMapper.readValue(json, ExamChoiceDto.class);
            return examChoiceDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
