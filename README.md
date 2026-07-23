# job-application-tracker
Full-stack job application tracker built with Spring Boot, PostgreSQL and Docker.

N+1 query prevention : Associations are lazily fetched with @EntityGraph on repository methods, so retrieving applications issues a single query joining company and interviews rather than one query per row per association.
