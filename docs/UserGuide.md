---
layout: page
title: User Guide
---
* Table of Contents 
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction:
Hello! This is ManageMe, a **desktop app for time management and resource organisation**. It is optimized for use 
via a **Command Line Interface (CLI)** while still displaying a convenient Graphical User Interface (GUI). 

ManageMe is intended to be used by university students to manage your modules, tasks, schedules, and online learning
resources by typing simple commands. This user guide will give you a comprehensive view of our features, 
and teach you how to use the app proficiently.
<br><br>
--------------------------------------------------------------------------------------------------------------------

## Quick Start:
This section aims to provide a quick introduction to using the application.
It should take less than 5 minutes to read and will provide new users with the necessary knowledge to install 
and start using the application.

### Steps for installation:
1. Ensure you have Java 11 or above installed in your Computer.
2. [Download](https://github.com/AY2122S1-CS2103T-W11-3/tp/releases) the latest jar release of ManageMe.
3. Open your browser's download folder and locate the downloaded jar file. ManageMe can run on multiple platforms, 
including Windows, Mac and Linux.
4. Move the jar file into your desired folder. ManageMe will use this folder to store its data by default.
5. Start the app by double-clicking on the jar file, or if you are using command line, type `java -jar manageme.jar`.
6. Enjoy using the application!

### User Interface
Below is a screenshot of our User Interface (UI). As you can see, there are management panels for Modules, Tasks, 
Calendar, and Links which can contain both website links and local file paths on your computer. You can type command
lines in the lowest box, and the app's response will be shown in second-lowest box.

![Ui](images/UiHomepage.png)
<br>*Homepage of Application*

### Quick Tutorial
Type a command in the command box and press Enter to execute it.
Some example commands you can try:
* `addMod n/CS2103T`: Add a module named "CS2103T"
* `deleteMod 1`: Delete a module with index 1, or you can type whatever index you see.
* `addTask n/Do homework d/10 questions e/2021-11-10T23:59`: Add a task named "do homework"
with description "10 questions", and whose deadline is on 2021 Nov 10th 23:59.

Refer to the Features below for details of each command.
<br><br>
--------------------------------------------------------------------------------------------------------------------

## Features
This section gives a full list of all the application's features with details such as the format, some tips and
examples of using it.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. E.g. `addTask n_NAME`
  , here `TASK_NAME` is the parameter.<br>

* Items in square brackets are optional. E.g. `addTask n_NAME [mod/CS2103]`<br>

</div>
<br>


### Tasks:

#### Adding a task: `addTask`

Adds a task to the task list.

Format: `addTask n/NAME d/DESCRIPTION [mod/ASSOCIATED_MODULE_NAME] [s/START_DATETIME] [e/END_DATETIME]`

* A name and description for the task is compulsory.
* It is optional to include an associated Module name, a start datetime and an end datetime.
* A task created with a start datetime MUST also have an end datetime.
* Format for a `DATETIME` is as follows: `year-month-dayThr:min` e.g. `2021-10-29T23:59`

Example: `addTask n/Do CS2103T Assignment d/Refer to lecture 10 for examples mod/CS2103T s/2021-10-05T11:00
e/2021-10-07T23:59`
<br><br>

#### Edit a task: `editTask`
Edit an existing task in the task list.

Format: `editTask INDEX [n/NAME] [d/DESCRIPTION] [mod/MODULE_NAME] [s/START_DATETIME] [e/END_DATETIME]`
* The index refers to the index number shown in the displayed module list
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Format for a `DATETIME` is as follows: `year-month-dayThr:min` e.g. `2021-10-29T23:59`

Example: `editTask 3 d/buy milk`
<br><br>

#### Deleting a task: `deleteTask`
Deletes the specified task from the task list.

Format: `deleteTask INDEX`
* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Example: `deleteTask 2`
<br><br>

#### Find a task by keyword: `findTask`
Finds all tasks whose names contain any of the specified keywords.

Format: `findTask KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans.
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans.
* Tasks matching at least one keyword will be returned (i.e. OR search).

Example: `findTask work` returns `Do CS2100 work` and `Work out next week's plan`.
<br><br>

#### List all tasks : `listTask`
Display the full list of tasks. This command is used to return to the full list
of tasks after searching for specific tasks.

Format: `listTask`
<br><br>

#### Mark/Un-mark a task as done/undone: `markTask`
Marks/Un-marks the specified task from the task list as done/undone.

Format: `markTask INDEX`
- Marks the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example: `markTask 2`

Feature in UI:<br>

![Ui](images/UiOngoingTask.png)<br>
*An ongoing task not yet done will be in the default colour*
<br><br>

![Ui](images/UiDoneTask.png)<br>
A task marked as done will be coloured green.
<br><br>

#### Delete all done tasks : `deleteDoneTask`
Deletes all tasks that have been marked as done from the task list.

Format: `deleteDoneTask`
<br><br><br>



### Modules:
#### Adding a module: `addMod`
Adds a module with its name into the module list.

Format: `addMod n/NAME` <br/>
Examples: `addMod n/CS2103`
<br><br>

#### Read details of a module: `readMod`
View module in detail. Creates a pop-up window to show the course name, link, and all tasks associated with the course.

Format: `readMod INDEX`<br/>
* The index refers to the index number shown in the displayed module list
* The index **must be a positive integer** 1, 2, 3, ...

Examples: `readMod 2`
<br><br>

#### Edit a module: `editMod`
Edits an existing module in the mod list.

Format: `editMod INDEX [n/NAME]`
* Edits the mod by the specified `INDEX`.<br/>
* The index refers to the index number shown in the displayed module list
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example: `editMod 2 n/CS2103T`
<br><br>

#### Delete a module: `deleteMod`
Deletes the specified mod from the mod list.

Format: `deleteMod INDEX`
* Deletes the mod by the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, ...

Example: `deleteMod 2` deletes module No.2 from the list.
<br><br>

#### Find a module by keyword: `findMod`
Finds modules whose names contain any of the given keywords.

Format: `findMod KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans.
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans.
* Modules matching at least one keyword will be returned (i.e. OR search).

Example: `findMod computer` returns `Computer Organization` and `Computer Architecture`.
<br><br>

#### List all modules : `listMod`
Display the full list of modules. This command is used to return to the full list
of modules after searching for specific modules.

Format: `listMod`
<br><br><br>



### Links:
#### Add a link: `addLink`
Adds a link into the link list. A link contains its name and a website link for online learning.

Format: `addLink n/NAME a/LINK_ADDRESS [mod/MODULE_NAME]` <br/>
* A link should be in a valid uri format, beginning with https://, ftp:// of file:/
* Aftering tagging the module, the link will appear at in the readMod panel of the particular module

Examples: `addLink n/google a/https://www.google.com mod/CS1101S`
<br><br>

#### Edit a link: `editLink`
Edits an existing link in the link list.

Format: `editLink INDEX [n/NAME] [a/LINK_ADDRESS] [mod/MODULE_NAME]`
* Edits the link by the specified `INDEX`.<br/>
* The index refers to the index number shown in the displayed link list
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example: `editLink 2 n/amazon`
<br><br>

#### Delete a link: `deleteLink`
Deletes the specified mod from the mod list.

Format: `deleteLink INDEX`
* Deletes the mod by the specified `INDEX`.
* The index refers to the index number shown in the displayed link list.
* The index **must be a positive integer** 1, 2, 3, ...

Example: `deleteLink 2` deletes link No.2 from the list.
<br><br>

#### Open a link:
Format: `openLink INDEX`
* Opens the link identifies by the index in the link list. If it is a link for a webpage, it will open the link in a
  browser. It it is a path for a file, it will open the file using the default app.
* The index refers to the index number shown in the displayed link list
* The index **must be a positive integer** 1, 2, 3, ...

Example: `openLink 1` open link No. 1 from the list of links.
<br><br>

#### Find a link by keyword:
Find a link whose name contains the keyword given.

Format: `findLink KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans.
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans.
* Links with a name matching at least one keyword will be returned (i.e. OR search).

Example: `findLink computer` returns `Computer Organization https://....` and `Computer Architecture https://....`.
<br><br>

#### List all links : `listLink`
Display the full list of links. This command is used to return to the full list after findLink.

Format: `listLink`
<br><br><br>


### Calendar:
#### Change calendar to next month: `nextMonth`
Display the calendar for the next month. This command will 
update the entire calendar panel to display the calendar and the related task information for the following month.

Format: `nextMonth`
<br><br>

#### Change calendar to previous month: `prevMonth`
Similar to `nextMonth`. However, this command display the calendar for the previous month instead.

Format: `prevMonth`
<br><br>

#### Read details happening on a day: `readDay`
View a day in detail. Tasks happening on the specified day will be displayed.

Format: `readDay DATE`
* `DATE` given must be a valid date.
* Format for a `DATE` is as follows: `year-month-day` e.g. `2021-10-19`

Example: `readDay 2021-10-19`
<br><br><br>



### OTHERS:

#### Viewing help: `help`

Shows the command summary and the url to the full User Guide.

![help message](images/helpMessage.png)<br>

Format: `help`
<br><br>

#### Archive current data: `archive`

Resets the application data and saves the deleted application data into a timestamped file located in the data folder.
<br><br>

#### Reminder for overdue tasks:

Resets the application data and saves the deleted application data into a timestamped file located in the data folder.
<br><br>

#### Exiting the program : `exit`

Exits the program.


#### Saving the data

Data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
<br><br>

#### Reminder for overdue tasks:

Tasks that are overdue(past the end time) will automatically be coloured red for users to clearly see which tasks are
overdue.

Feature in UI:<br>
![Ui](images/UiOverDueTask.png)<br>
*A Task coloured red since it is overdue*

--------------------------------------------------------------------------------------------------------------------

## Command summary
This section serves as quick reference for all the available commands that can be used in the application.

Action | Format, Examples
--------|------------------
**AddTask** | `addTask n/NAME d/DESCRIPTION [mod/MODULE_NAME] [s/START_DATETIME] [e/END_DATETIME]`<br>e.g., `addTask n/Do Assignment d/Read Lecture 7 mod/CS2100 s/2021-10-05T12:00 e/2021-10-07T23:59`
**EditTask** | `editTask INDEX [n/NAME] [d/DESCRIPTION] [mod/MODULE_NAME] [s/START_DATETIME] [e/END_DATETIME]`<br>e.g., `editTask 3 d/buy milk`
**DeleteTask** | `deleteTask INDEX`<br>e.g., `deleteTask 3`
**findTask** | `findTask KEYWORD [MORE_KEYWORDS]`<br>e.g., `findTask work`
**ListTask** | `listTask`
**MarkTask** | `markTask INDEX`<br>e.g., `markTask 1`
**DeleteDoneTask** | `deleteDoneTask`
**AddModule** | `addMod n/NAME`<br>e.g.,`addMod n/CS2103`
**ReadModule** | `readMod INDEX`<br>e.g., `readMod 2`
**EditModule** | `editMod INDEX [n/NAME] [l/LINK]`<br>e.g., `editMod 2 n/CS2103T l/https://...`
**DeleteModule** | `deleteMod INDEX`<br>e.g., `deleteMod 2`
**FindModule** | `findMod KEYWORD [MORE_KEYWORDS]`<br>e.g., `findMod computer`
**ListModule** | `listMod`
**AddLink** | `addLink n/NAME l/LINK_ADDRESS [mod/MODULE_NAME]`<br>e.g., `addLink n/google a/https://www.google.com`
**DeleteLink** | `deleteLink INDEX`<br>e.g., `deleteLink 2`
**DeleteModLink** | `deleteModLink mod/MODULE_NAME i/INDEX`<br>e.g., `deleteModLink mod/CS1101S i/1`
**EditLink** | `editLink INDEX [n/NAME] [a/LINK_ADDRESS] [mod/MODULE_NAME]`<br>e.g.,`editLink 2 n/amazon`
**OpenLink** | `openLink INDEX` <br>e.g.,`openLink 1`
**FindLink** | `findLink KEYWORD`<br>e.g.,`findLink google`
**ListLink** | `listLink`
**NextMonth** | `nextMonth`
**PreviousMonth** | `prevMonth`
**ReadDay** | `readDay DATE`<br>e.g., `readDay 2021-10-19`
**Help** | `help`
**Archive** | `archive`
**Exit** | `exit`
