![Logo](https://github.com/Vitor-W-Espindola/Bookshelter-Desktop-App/blob/master/Images/Logo.png)

## Summary



  - [What's the deal ?](#what's-the-deal?)

  - [Proposal](#Proposal)

  - [MySQL Tables](#mysql-tables)

  - [Java](#java)

  - [Data Class](#data-class)

  - [Frames](#login-frame)

  - [ClickActions Class](#click-actions-class)

    

## What's the deal ?


<p align="center">Today, Bookshelter launched its <b>brand new application</b> for <b>Desktop</b> users from all around the globe.</p>

<p align="center">It has never been so easy to <b>store your book shelf</b> and <b>all your book info</b> in just a little piece of code.</p>

<p align="center">Developed by <b>Vitor W. Espindola</b>, we are presenting for the world a <b>dinamic, safe and friendly
environment</b> for people to store history, filosofy, science, humor or whatever book someone would like to keep it in.</p>
<p align="center">
    <img alt="App_Frame" src="https://media.giphy.com/media/UkAspmh0ogOiuKASva/giphy.gif"/>
</p>



## Proposal

Alright. Actually, BookShelter is a small project made only for study proposes. 
A few weeks ago I wrote my first SQL code lines. And when I got my first SQL table on screen, I've instantly
became really excited about the idea of making an application using my previous Java knowledge and MySQL Database.

This project was developed using:

- Java
- Java Swing
- Java Object-Oriented Programming
- SQL
- MySQL DBMS
- MySQL Workbench

## MySQL Tables

This application stores all data in two local MySQL tables:

<b>Users</b>

| user_id | name     | password |      |
| ------- | -------- | -------- | ---- |
| 1       | Vitor123 | 12345    |      |
| 2       | Tomaz110 | tmz110   |      |

<b>Books</b>

| book_id | owner    | name     | author          | tot_pages | current_page | progress | note                        |      |
| ------- | -------- | -------- | --------------- | --------- | ------------ | -------- | --------------------------- | ---- |
| 1       | Vitor123 | Phaedo   | Plato           | 105       | 86           | 81       | For sure a must-read, 10/10 |      |
| 2       | Tomaz110 | Macbeth  | Shakespeare     | 128       | 52           | 40       | Best book I read this week  |      |
| 3       | Vitor123 | Bartleby | Herman Melville | 40        | 15           | 37       | "I prefer not to."          |      |


Using these two tables, the app is able to store each users book and return them when requested.

## Java

As mentioned, I've used OOP when coding.<br>My objective was to reach the most I could in terms of code <b>beauty, visibility and organization</b>.

List of classes:

- LoginFrame.java
- AppFrame.java
- AddBookFrame.java
- EditBookFrame.java
- ClickActions.java
- Data.java
- Main.java

Since I was dealing with Java Swing, <b>each Frame used in the application is configured by its own class</b>. 
<br>E.g.: LoginFrame.java, AppFrame.java, AddBookFrame.java and EditBookFrame.java

By doing that, I instanced all classes as static objects in Main.java class. Then, I could <b>manipulate their own components without any headache</b>.

I also wrote Data.java and ClickActions.java.

- Data.java stores user data, control variables and all current book info showing on screen.
- ClickActions.java stores all methods called when user click on the almost all interface buttons.

## Data Class

Data.java class store <b>all information we are currently using</b>. For example, *book.index* (what book from all your books frame is displaying), *book.quantity* (how many books you got), all the info about the current book in you see on screen, and other things.

 *(Take a quick look at this class and you'll get what I mean)*



> As I said, I've wrote it in order to use intuitively along the code. 
> (It works for me, I hope it works for you too)



All information Data.java stores:

       *User info*

- user_id

- username;

- password

  

  *Current book info*

- book_id

- book_owner

- book_name

- book_author

- book_tot_pages

- book_current_page

- book_progress;

- book_note

  

  *Control variables*

- book_index = 0

- book_quantity




## Login Frame

<p align="center">
    <img alt="Login_Frame" src="https://media.giphy.com/media/hs3Livkbe2uB5Qg2v6/giphy.gif"/>
</p>


Login Frame is a simple frame which provide privacy and a sharable environment among users.

List of Components (at least the most important ones):

- username
- password
- login_button
- signup_button
- warning

Almost all action a button performs are stored in ClickActions.java, so every time a button got clicked, a method in ClickActions.java will be called. 

The Login button perform *ClickActions.loginClick()* method, which queries <b>"SELECT * FROM users WHERE username = 'field_username' AND password = 'field_password'"</b> against users table.

> This method checks if query returns a not null row, and if it doesn't, Login Frame became invisible and
> we begin to see AppFrame showing up. Otherwise, label_warning provides a status.


The SignUp button perform *ClickActions.signUpClick()* method, which queries <b>"SELECT * FROM users WHERE username = 'field_username'"</b> against users table.

> This method checks whether user is already registered in users table or not, display a message by label_warning.
> If it's not, it will execute <b>"INSERT INTO users (username, password) VALUES ('field_username', 'field_password')"</b> against users table.


## App Frame

App Frame is where magic happens. Here is where you got the control of all your books and their info.

List of Components:

- title
- author
- total_of_pages
- current_page
- progress
- note
- next_button
- back_button
- add_button
- edit_button
- remove_button

Almost all button click calls a method from ClickActions.java

The **Next** and **Back** buttons call *ClickActions.updateAppFrame()* method.

> Basically, this method returns only the next/previous book from all user books by performing a query against *books* table:
>
> **"SELECT * FROM books WHERE owner = '*Data.getData_username()*' LIMIT *Data.getBook_index()* OFFSET *Data.getBook_index()* - 1"**

The **Add** button turns AddBookFrame visible and disables AppFrame. 

> We'll take a deeper look at AddFrame later.

The **Edit** button turns BookEditFrame visible and disables AppFrame. 

> (Makes the same thing as the Add button.)

The **Remove** button calls *ClickActions.removeClick()* method. 

>  (Very intuitive, isn't it ?)

> Besides some conditions this method checks before do something, basically what it does is performing a query against books table;
>
> **"DELETE FROM books WHERE**
>
> **owner = \"*Data.getData_book_owner()*\" AND name = \"*Data.getData_book_name()*\" AND tot_pages = *Data.getData_book_tot_pages()* AND current_page = *Data.getData_book_current_page()* AND progress = *Data.getData_book_progress()* AND note = \"*Data.getData_book_note()*\""**

> (We use the Data class when getting a info !)

## Add Book Frame

<p align="center">
    <img alt="App_Book_Frame" src="https://media.giphy.com/media/Zl2JfVJCi2IFvcX8rm/giphy.gif"/>
</p>


Add Book Frame is a simple frame where user adds a new book to database. 

This new book will be linked to the user by the *owner* table column, so no one else can have access to it.

List of Components:

- title
- author
- total_of_pages
- current_page
- note
- done_button
- cancel_button

The **Done** button calls *ClickActions.addBookClickDone()* method and simply add to the *book* table a new row with the values specified by user.

> INSERT INTO books (owner, name, author, tot_pages, current_page, progress, note) VALUES (\"*Data.getData_book_owner()*\", \"name\", \"author\", \"total_of_pages\", \"current_pages\", \"(current_page/total_of_pages)\*100\", \"note\")""

> Note that progress is set by (current_page/total_of_pages)\*100.

> When clicking done the new book will be displayed on App Frame.

The **Cancel** button simply turn Add Book Frame invisible again.

## Edit Book Frame

<p align="center">
    <img alt="Edit_Book_Frame" src="https://media.giphy.com/media/yzNqOZE08lqNEaof6T/giphy.gif"/>
</p>


Edit Book Frame pop up its frame, where user can edit the values of the current book displayed on screen.

List of Components:

- title
- author
- total_of_pages
- current_page
- note
- done_button
- cancel_button

Without much secret, the **Done** button calls *ClickActions.editBookClickDone()* and perform execute a SQL command against books table.

> "UPDATE books SET name=\"name\", author=\"author\", tot_pages=\"total_of_pages\", current_page=\"current_page\", progress=\"(current_page/total_of_pages)\*100\", note=\"note\" WHERE book_id=\"*Data.getData_book_id()*\" AND owner=\"*Data.getData_username()*\" AND name=\"name\" AND author=\"*Data.getData_book_author()*\" AND tot_pages=\"*Data.getData_book_tot_pages()*\" AND current_page=\"*Data.getData_book_current_page()*\" AND progress=\"*Data.getData_book_progress()*\" AND note=\"*Data.getData_book_note()*\""				

> I know this was a little bit long, but, I want to be 100% sure that book displayed on screen is the one which will be changed.

The **Cancel** button simply turn Edit Book Frame invisible again.

## Click Actions Class

As I've mentioned, here we store almost all methods called when a button got clicked.

List of methods:

- *loginClick()*
- *signUpClick()*
- *updateAppFrame()*
- *addBookClickDone()*
- *editBookClickDone()*
- *removeClick()*

> Since *loginClick()* and *signUpClick()* have already been shown before, let's focus on the most complex ones.

## The updateAppFrame() Method

The *updateAppFrame()* method **is called every time another method finishes its work**.

I mean, when we **add**, **edit** or **remove** a book from database, we also need to **update user's application screen**.

The **next** and **back** buttons also call this method in order to let user browse through its books.

This method can be represented by the following flow chart:

![updateAppFrame() Flowchart](https://github.com/Vitor-W-Espindola/Bookshelter-Desktop-App/blob/master/Images/updateAppFrame().png)

## The addBookClickDone() Method

The *addBookClickDone()* method is quite simple.

![addBookClickDone() Flowchart](https://github.com/Vitor-W-Espindola/Bookshelter-Desktop-App/blob/master/Images/addBookClickDone().png)

## The editBookClickDone() Method

The *editBookClickDone()* method is very similar with the *addBookClickDone()* method.a

![editBookClickDone() Flowchart](https://github.com/Vitor-W-Espindola/Bookshelter-Desktop-App/blob/master/Images/editBookClickDone().png)

## The removeClick() Method

The *removeClick()* method might be the most complex one. It has some conditions in order to check the index and the quantity.

> It doesn't allow user to remove a non-existent book, for example.

<p align="center">
    <img alt="removeClick()" src="https://media.giphy.com/media/ChlXQKymmNTLRR61eP/giphy.gif"/>
</p>

![removeClick() Flowchart](https://github.com/Vitor-W-Espindola/Bookshelter-Desktop-App/blob/master/Images/removeClick().png)

