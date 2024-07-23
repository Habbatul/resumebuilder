# Restful API for Resume (CV) Builder 
- **[Front-End Web By @Mulyana](https://cv-online-phi.vercel.app/)**<br>
- **[Swagger Documentation](https://resumebuilder.hqhan.my.id/swagger-ui/index.html#/)**

## About <a name="about"></a>
<p align="justify"> This is a Restful API for building resumes (CVs). For an example of a frontend web repository that uses this project, you can visit  <a href="https://github.com/mmulyana/cv-online"><b>this repository</b></a> made by <a href="https://github.com/mmulyana"><b>@mmulyana</b></a>. He created that frontend project using <code>ReactJS</code>, <code>TypeScript</code>, <code>React Query</code>, etc.</p>

>  <p align="justify">This RESTful API was developed within 3 days, so please understand if some features are not fully mature or if there are any bugs. However, after integration with the frontend, no bugs have been found so far. Please feel free to create an issue if you encounter any bugs.</p>

## Tech Stack <a name="stack"></a>
The RESTful API is built using Spring Boot. The technologies used in this project are as follows:
- Spring Boot (Spring Boot 3.3.1 with Java 21)
    - <a href="https://github.com/spring-projects/spring-data-jpa">Spring Data JPA</a>
    - <a href="https://github.com/spring-projects/spring-boot">Spring Web</a>
    - <a href="https://github.com/spring-projects/spring-security">Spring Security</a>
    - <a href="https://github.com/jwtk/jjwt">jjwt</a>
    - <a href="https://github.com/jakartaee/jaxb-api">jaxb-api</a>
    - etc
- Maven
- PostgreeSQL DBMS

**Server Tech Stack :** `Nginx`, `Ubuntu`, `Docker`, `Certbot`


## Feature <a name="feature"></a>
The MVP features we have agreed upon to ensure the application functions properly are as follows:
- Login & register
- Add, edit, delete resume
- Publish resume
- Show available skills
