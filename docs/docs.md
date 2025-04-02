# StudyApp - Spring Boot - docs
StudyHub es un proyecto proveniente de alumnos de la Universidad Nacional del Oeste para la materia de Interfaces de Usuario y Tecnologías Web.

 El mismo permitirá a los usuarios registrados crear y guardar materias con su respectiva Bibliografia, y dentro de esta, podrán guardar temas asociados a dicha materia. 

Motorizado por IA, a partir de estos contenidos podrán generar automáticamente exámenes de práctica para acompañarse durante el estudio. A continuación se documentan detalles básicos del funcionamiento del sistema de StudyHub para acompañar a la lectura del código fuente.


## Modulos del sistema
- `Autenticación`: se encarga de la autenticación de usuario (Register, Login)
- `Materias`: se encarga de las funcionalidades generales del sistema (Courses y Topics)
- `AI`: cubrirá la acción de la IA en la generación de exámenes.

## Entities
- `User`: usuario, contiene una lista de Course.
- `Profile`: perfil asociado a un User
- `Course`: materia/curso, contiene lista de Topic
- `Topic`: tema de una materia

(Ver el archivo ../requirements/der-study.drawio)

(Cada entidad tiene su `repository` asociado)

## Data Transfer Objects (DTOs)
- `UserLoginDto` permite el inicio de sesión enviando username y password
- `UserRegisterDto` permite registrar un usuario enviando username, email, name, lastname y password
- `CourseDto` permite manipular materias con los campos title y contentBibligraphy
- `TopicDto` permite manipular temas con los campos title, description y bibliography
- `CourseExamDto` permite manipular los campos title y topics para la generación de exámenes en base a lo enviado
- `examChoice/ExamChoiceDto` refiere al multiple choice que se enviará como respuesta. En el mismo se encuentra el titulo del curso al que hace referencia y los temas en base a los cuales hara el examen.
- `examChoice/ItemChoiceDto` refiere a los items existentes en ExamChoiceDto. Los items tienen una pregunta, opciones y una respuesta.
- `examChoice/OptionsQuestionDto` refiere a las opciones que tendra cada ItemChoice (4 respuestas, una correcta)


## Services
- ### CourseService.java
    - `showCourses()` devuelve la lista de Course asociada al User que tiene la sesión iniciada apelando al contexto de la aplicación.
    - `saveCourse(CourseDTO)` permite guardar un curso obteniendo los datos necesarios en la request desde un DTO. 
    - `updateCourse(CourseDTO, Long)` permite actualizar un curso con su ID y con lo que se envíe a través de un DTO.
    - `getCourse(Long)` permite obtener un curso específico con su ID.
    - `deleteCourse(Long)` permite eliminar un curso con su ID.
    - `saveTopic(Long, TopicDto)` permite guardar Topic en un Course específico utilizando su Id y los datos recibidos del DTO.
    - `updateTopic(Long, TopicDto, Long)` permite actualizar un Topic según la Id del curso al que pertenece, los datos obtenidos del DTO y la id del Course específico.
    - `deleteTopic(Long, Long)` permite eliminar un Topic específico de un Course específico.
    - `showTopics(Long)` permite listar todos los Topics asociados a un curso específico.
    - `getTopic(Long, Long)` permite obtener de un Course específico, un Topic específico.
    - `getUsernameContextSecurity()` se utiliza para las validaciones dentro de las funciones del servicio, ya que se asegura que los cursos a manipular no sólo existan en sí, sino que también existan en el usuario que tiene la sesión iniciada, apelando al contexto de la aplicación.

- ### ProfileService.java
    - `getProfileById(Long)` devuelve un perfil de usuario según su Id

- ### UserService.java
    - `saveUser(UserRegisterDto)` crea un usuario y un perfil asociado a ese usuario, y guarda ambos.

- ### ai/MultipleChoiceExamService.java
    - `getDataCourse(Long, courseExamDto)` se obtiene el Course mediante el id, y luego, el titulo del mismo y la lista de temas del mismo. Se realiza un filtrado de los temas, usando solo los que se hayan enviado en el DTO. Se devolveran los datos necesarios: Titulo, Bibliografia y Temas que hayan sido recibidos desde el DTO.
    - `generateChoiceExam(Long, courseExamDto)` se obtienen los datos de la materia utilizando la funcion getDataCourse. Se envia un Prompt que ya esta previamente desarrollado (resources/prompts/system.txt) al chatClient que generará la respuesta (el multiple choice)
    - `getMultipleChoice(Long, courseExamDto)` se usa la funcion generateChoiceExam con el id del curso y los datos recibidos del DTO. Se obtiene finalmente el ExamChoiceDto completo.

## Controllers

(Ver ../requirements/contratos.yaml para conocer los JSON de cada solicitud)

- ### CourseController.java (`/user`)
    - `POST /courses` agrega un Course a llamando a la función del servicio saveCourse()
    - `GET /courses` devuelve una lista de Course llamando a la función del servicio ShowCourses() 
    - `PUT /courses/{idCourse}` permite actualizar un curso existente llamando a la función del servicio updateCourse()
    - `GET /courses/{idCourse}` permite obtener un curso específico llamando a la función del servicio getCourse()
    - `DELETE /courses/{idCourse}` permite eliminar un curso específico llamando a la función del servicio deleteCourse()
    - `POST /courses/{idCourse}/topics` permite agregar un tema a un curso específico llamando a la función del servicio saveTopic()
    - `PUT /courses/{idCourse}/topics/{idTopic}` permite actualizar un topic llamando a la función del servicio updateTopic()
    - `DELETE /courses/{idCourse}/topics/{idTopic}` permite eliminar un topic llamando a la función del servicio deleteTopic()
     - `GET /courses/{idCourse}/topics` permite obtener todos los topics de un curso llamando a la función del servicio showTopics()
- ### UserController.java
    - `POST /register` permite registrar un usuario validando los datos de entrada obtenidos del DTO, llamando a la función del servicio saveUser().
    - `POST /login` permite iniciar sesión con un usuario registrado utilizando Spring Security y JSON Web Tokens (JWT).
- ### ai/MultipleChoiceExamController.java(`/user/courses`)
    - `POST /{id}/generar-examen` permite obtener el MultipleChoice generandolo desde el servicio de la IA (ver MultipleChoiceExamService.java para ver su flujo de ejecución).