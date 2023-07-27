# demo-charity-project

- Introduction

The charity project will post information about difficult situations, needing the help of benefactors and volunteers. 
Making it easy for every one to join hands to raise money with millions of people, helping disadvantaged people across 
the country. And it helps to make donation management easier.


- Prerequisites

Before you continue, ensure you meet the following requirements:
* You have installed JDK 17 or over.
* You are using an IDE and a DBMS (mysql or postgresql). Currently, using postgresql.
* You can use demo on: https://demo-charity-project.onrender.com


- Use

Due to demo project. 1 default administrator account initially to access application
Email: admin@gmail.com
Password: 113


- List of features - LoF

Admin:
* Manage user list: Add, update, find, lock, unlock, delete User.
* Manage donation list: Add, update, find, delete Donation.
  Update donation: Can only be deleted on initialization.
  Only donation status (Đang quyên góp), user can donate.
* Confirm donation information (donation's money from users).

User:
* Donate to donations in donation status (Đang quyên góp).


- Technologies Used

Spring Boot 3, Thymeleaf, JPA.
Docker and Render website to deploy.


- Acknowledgments

No security (No use Spring Security).
Data maybe duplicated.
The first demo web application I tried to do. So there were a lot of mistakes, flaws.
Thank you!
