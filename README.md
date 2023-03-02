# CS319Project
frontend repo: https://github.com/ugurcanaltun/cs319-project-frontend

video: https://www.youtube.com/watch?v=ZgadC4pmx84
# Authors
 - [Ahmet Şahin](https://github.com/ahmet541)
 - [Barış Tan Ünal](https://github.com/baristanunal)
 - [Kaan Berk Kabadayı](https://github.com/kbkabadayi)
 - [Uğur Can Altun](https://github.com/ugurcanaltun)
 - [Yusuf Şenyüz](https://github.com/YusufSenyuz)
 
 <h1 align="center"> Erasmus Management System: Web-based extension to the existing Bilkent Erasmus Application </h1>
 
## Description
Erasmus Management System is a web-based extension to the existing Bilkent Erasmus Application that intends to enhance the usage of the application by moving all of the paperwork and communication done outside of the scope of the application to the digital grounds, which is inside of the scope of the current Bilkent Erasmus Application with an user-friendly user interface. 
 
## Aim
The aim of this project is to enhance the usage of Bilkent Erasmus Application by the current users and potential users that are neglected of the applicaton due to current undigitized means of processing tasks that are related to Erasmus and exchange programs. We intend to improve the standards of carrying out tasks related to Erasmus and other exchange programs by digitizing the remaining undigitized paperwork and means of communication and support the current program by providing high-level features that serve the aim that is mentioned above.
 
## Summary of the High-Level Features That Will Be Provided
* Paper and email usage is minimised as much as possible by transporting the procedures to digital based platform.
* Students are able to see their Erasmus and paperwork status with color indicators.
* To-do list is available for Erasmus and exchange coordinators with user-friendly user interface to improve their means of tracking their tasks related to Erasmus and other exchange coordinators. If any matter related to Erasmus needs the attention of the coordinators, the matter that needs to be handled is viewed as a task in a to-do list and coordinators will be notified of their tasks. Coordinators will not need to check their emails and other platforms to check the status of students and other matters and maintain their work schedule with only this to-do list. 
* Workload of departments coordinators can be distributed with coordinators choice and it can be customized at any time by the coordinators. 
* All comunications related with Erasmus and other exchange programs will be done through the extension's messaging platform. 
* Different user types (stakeholders) are available to make the extension available to all actors in Erasmus and other exchange programs in Bilkent. 
* Excel files that are provided to coordinators are fetched to the extension with user-friendly user interface with other external beneficial informations. 
* Informations necessary to any user of the program that are outside of the scope of the application are fetched to the extension with suitable user interface. 
* Faculty administration board, coordinator decisions and student movement related to Erasmus application process (eg. Preapproval stage, course transfer stage) is done through the extension and can be tracked by relevant user types.
* Important stages of application process such as preapproval and course transfer are digitized and done through the extension. Processes done outside of the application related to these prominent stages are moved to the extension and overall status of these stages can be tracked by user types momentarily.

### Planned User Types (StakeHolders):
1. Student
    1. Undergraduate Student
    2. Graduate Student
2. Department Erasmus Coordinator(s)
3. Administrative Erasmus Coordinator
4. Course Coordinator
5. Faculty Administration Committee
    1. Dean / Director
    2. Chair
6. International Students Office
7. BCC (Admin)

### Additional Notes:
* Exchange quotas are merged for all departments whereas Erasmus quotas are separate for each department.
* When an opening happens (someone cancels their Erasmus application) the next student IN THE WAITING LIST is asked if they want to be placed to that university with vacancy (waiting bin algorithm).
* During the semester before the mobility, Bilkent sends the nominations to the universities.
* Students will then apply to the university using the link provided by the Erasmus partner.
* During the semester before the mobility, finish course pre-approval process:
    * All MUST COURSES must be approved by the corresponding Bilkent instructor.
    * Electives can be approved by the coordinators.
    * Sending the syllabus is a MUST. 
    * There is a list of previously accepted courses, no need to re-approve for those courses.
* Pre-approval forms are checked by the Faculty Administration Board (a user type in our program?).
* For minor degrees, TWO approval processes should be held for major and minor degrees SEPARATELY.
* There can be a user type for instructors for them to approve the syllabuses.
* Should consider the international student office to be a user type or not.
* Direct messaging or clickable email links should be added for communication between Erasmus coordinators and outgoing students.
* International student office checks for the language requirements during the visa process after the application is approved.
* The “sample score table” is given by the student office and we should assume it to be ready.
* At the end of the mobility, the courses taken in the transcript and in the pre-approval form MUST match for Erasmus approval.
* Students MUST be able to see their application status through the app.
* The Excel documents that professors receive via email can be presented to the professor with a user-friendly user interface.
* Relevant information like email of the student must be fetched along with the information in the sample score table while creating the user interface for students that will go to Erasmus.

### Possible Corner Cases:
* The courses that were accepted before but their syllabus have changed.
* Host institution failed to open the course for Erasmus student, (pre approval process starts again).

## Build Instructions
We used the iText library and person who will build our application should have the iText library in his/her computer. The iText can be downloaded from this [link](https://search.maven.org/search?q=a:itextpdf). After you visit the this webpage you should download the iTextPdf ( select the com.itextpdf and then select jar). In the absence of this library, the program will possibly crash.

To run backend:
Download Intellij Ultimate edition.
Download PostgreSQL 15 from enterprisedb. Give password to your account ( which is asked to you to finish the building process of PostgreSQL)
Clone our code from Github to your computer.
After downloading PostgreSQL, access psql from the command line.
If you are using windows write “psql -U postgres” to console and write the password that you gave.
If you are using macOS, write “psql” to the console and write the password that you gave.
Then write “CREATE DATABASE erasmusappdb;” to the console. ( Be sure you logged on psql successfully)
Then write
If you are using windows write ‘GRANT ALL PRIVILEGES ON DATABASE “erasmusappdb” TO postgres;’ to console.
If you are using macOS, ‘GRANT ALL PRIVILEGES ON DATABASE “erasmusappdb” TO $DefaultUser;’ to console. ( $DefaultUser is the default use of your computer. After you wrote psql in console, psql shows your default user.)
Open Intellij, download all necessary files which are shown by Intellij IDEA. Then update the “/resources/application.properties” file.(write your username and password)
Click on the “Database” field on the right side of the Intellij idea. ( You have to use the Intellij Ultimate edition. Otherwise you have to follow different procedure which we did not use)
Run a program and open “http://localhost:3000/” from your browser.


To run frontend:
Clone code from github.
Write “npm install”.
Then write “npm start”.

After these steps the application is working.

