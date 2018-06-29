Test Me
======
This project is a pet project. It is made in order to try myself in web application 
development using Spring, Hibernate and Angular.

Project is information portal for students, it should allow them to:

* login, maintain personal profiles
* select end pass courses
* read texts, watch video lessons
* pass tests and receive checked results

For the first steps it will be just system for plain online tests.

Also should exist userEntity-moderator which is able to:
* create tests and courses
* manage students activity, check their marks

REST Maping
======
method|uri|action
------|------------------------|------
POST | rest/courses| create course request body
POST | rest/course/{**id**}/tests| insert test from request body in to course by course id URL param
POST | rest/course/{**courseId**}/test/{**testId**}/questions| insert question from request body in to test by URL params


Postman hints:
---
* Course example:
```json
{  
   "id":2,
   "courseName":"Java Core",
   "courseDescription":"Java Core for beginners. Basic syntax.",
   "tests":[],
   "subscribedUsers":[]
}
```
* Test example:
```json
{  
   "id":9,
   "testDescription":"Test about java regex",
   "questions":[]
}
```
* Question example:
```json
{  
   "id":4,
   "question":"How to isert string in to pool?",
   "answerForNoOptions":".intern()",
   "type":"without_options",
   "options":[]
}
```

