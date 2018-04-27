Построить веб-систему, поддерживающую заданную функциональность:
•	На основе сущностей предметной области создать классы их описывающие.
•	Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам. 
•	Оформление кода должно соответствовать Java Code Convention.
•	Информацию о предметной области хранить в БД, для доступа использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. В качестве СУБД рекомендуется MySQL или Oracle.
•	Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
•	Архитектура приложения должна соответствовать шаблону Model-View-Controller.
•	При реализации алгоритмов бизнес-логики использовать шаблоны GoF: Factory Method, Command, Builder, Strategy, State, Observer etc.
•	Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
•	В страницах JSP применять библиотеку JSTL и разработать собственные теги.
•	При разработке бизнес логики использовать сессии и фильтры.
•	Выполнить журналирование событий, то есть информацию о возникающих исключениях и событиях в системе обрабатывать с помощью Log4j.
•	Код должен содержать комментарии.

Вариант:
7.	Система Интернет-магазин. Администратор осуществляет ведение каталога Товаров. 
Клиент делает и оплачивает Заказ на Товары. Администратор может занести неплательщиков в “черный список”.

It's a java web-project with using Servlets and JSP. 
E-shop with user and admin part.
User could find a list of products on start page, add products to basket.
If user want to make order, he/she should sign in or sign up firstly,
Logined user has an ability to see all his/her orders.
Admin can go to his/her part by entering /admin in the end of url.
Admin can edit, delete and create products.
Also admin has an ability to see info about orders and users, 
and add users with unpayed orders to black list.
You can change the language of the site by clicking UA or EN link
on the right bottom of page.

Package e_shopping.admin_servlet consists of servlets which is used to work with admin.
Package e_shopping.user_servlet consists of servlets which is used to work with user.
Package e_shopping.beans consists of java beans.
Package e_shopping.conn conssists of utils for connection to DB.
Package e_shopping.filter consists of filters.
Package e_shopping.locale consists of files with language dictionaries.
Package e_shopping.utils consists of utils for DB requests, add and receive session, 
requests attributes and cookies, and also some useful functions.