---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

Welcome to ManageMe’s Developer Guide (DG).

ManageMe is a **lightweight but powerful desktop application built to help university students manage their school life, available on Windows, Linux and Mac**. Students can add modules, tasks, schedules and online learning resources easily into ManageMe and access them with simple commands. The application is optimized for use via a **Command Line Interface (CLI)** but also provides a simple and convenient Graphical User Interface (GUI) for interaction.

The detailed guide below will provide to developers like yourself, information such as how exactly ManageMe is built, the architectural diagrams, user stories, product scope, common use cases and non-functional requirements. At the end of this guide, we hope that you gained a much greater understanding of how ManageMe works and hopefully, you are able to contribute to this project as well.

The DG is formatted in a way that every single section is preceded by a header. In those sections, there will be some screenshots and brief explanations to better your understanding. Terms that are in `this format` signifies that it is a part of the codebase, such as a class or a directory. E.g `Logic`, `Storage`


In the event that you are lost on the page, scroll down to the end of each section and click on the return to [Table of Contents](#table-of-contents).

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the educational project [AddressBook Level3](https://github.com/se-edu/addressbook-level3)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

Return to [Table of Contents](#table-of-contents).

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deleteTask 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside components being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

<br><br>

The sections below give more details of each component.

Return to [Table of Contents](#table-of-contents).

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W11-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MmMainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ModuleListPanel`, `TaskListPanel` etc. All these, including the `MmMainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MmMainWindow`](https://github.com/AY2122S1-CS2103T-W11-3/tp/blob/master/src/main/java/seedu/address/ui/MmMainWindow.java) is specified in [`MmMainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W11-3/tp/blob/master/src/main/resources/view/MmMainWindow.fxml)


The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Module` and `Task` objects residing in the `Model`.

Return to [Table of Contents](#table-of-contents).

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ManageMeParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddTaskCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deleteTask 1")` API call.

![Interactions Inside the Logic Component for the `deleteTask 1` Command](images/DeleteTaskSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTaskCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClassesManageMe.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ManageMeParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddTaskCommandParser`) which uses the other classes shown above to parse the user command and create an `XYZCommand` object (e.g., `AddTaskCommand`) which the `ManageMeParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddTaskCommandParser`, `DeleteTaskCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

Return to [Table of Contents](#table-of-contents).

### Model component
Here's a (partial) class diagram of the `Model` component:

**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W11-3/tp/blob/master/src/main/java/manageme/model/Model.java)

<img src="images/MMModelClassDiagram.png" width="450" />


The `Model` component,

* store the object data of ManageMe i.e., all `Module` and `Task` objects (which are contained in a
  `UniqueModuleList`
  object
  and a `UniqueTaskList` respectively).
* stores the currently 'selected' `Task` objects (e.g., results of a search query) as a separate _filtered_ list
  which
  is exposed to outsiders as an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

[comment]: <> (<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative &#40;arguably, a more OOP&#41; model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>)

Return to [Table of Contents](#table-of-contents).

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/MMStorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both ManageMe data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `ManageMeStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

Return to [Table of Contents](#table-of-contents).

### Common classes


Classes used by multiple components are in the `manageme.commons` package.

Return to [Table of Contents](#table-of-contents).

### Time component

[comment]: <> (**API** : [`Storage.java`]&#40;https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java&#41;)

<img src="images/MMTimeClassDiagram.png" width="550" />

The `Time` component,
* is used as a standalone component to facilitate the tracking of time.
* enables notifications by comparing tasks' start and end time with system time.

Return to [Table of Contents](#table-of-contents).

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### [Proposed] Set reminder to pop a specified amount of time before task starts
Currently the task reminder pops up at the moment when the task starts, or when the task is ongoing. However, this feature will be more useful if the user can set the reminder to pop a certain time before the task starts.

### [Proposed] Add recurring tasks
Currently

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

Return to [Table of Contents](#table-of-contents).

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

A Digital-Age student who...
* Needs to organize multiple tasks (events, tasks, and deadlines), some of them involve computer files stored locally.
* Needs to organize multiple modules with many links to relevant online resources.
* Prefers visualizing all tasks and modules on a calendar for a clear and quick presentation.
* Prefers desktop application over the mobile application.
* Prefers typing over the use of the mouse.
* Comfortable with the use of Command Line Interface (CLI).

**Value proposition**:
* Manage modules and tasks faster than a typical mouse/GUI-driven app.
* Manage multiple web links and file paths associated with modules and tasks.
* Summarises crucial information clearly such as:
    1) Tasks due Today and
    2) Upcoming Events on the Calendar

Return to [Table of Contents](#table-of-contents).


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                                                       | So that I can…​                                                                                        |
| -------- | ------------------------------------------ | --------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `* * *`  | Forgetful User                             | Add tasks                                                             | I can keep track of what I have done, and need to do.                                                     |
| `* * *`  | Forgetful User                             | Add optional begin and end times to tasks                             |                                                                                                           |
| `* * *`  | Forgetful User                             | Mark a task as done                                                   |                                                                                                           |
| `* * *`  | Forgetful User                             | View all my tasks                                                     |                                                                                                           |
| `* * *`  | Forgetful User                             | Associate a task with a module                                        | I know which module each task belongs to               |
| `* * *`  | Forgetful User                             | Edit my tasks                                                         | I I can change information related to a task                          |
| `* * *`  | Forgetful User                             | Delete my tasks                                                       | I can get rid of unwanted tasks.                                                                     |
| `* * *`  | User                                       | Search for a task using keywords                                      | I do not have to waste time browsing through my entire task list to find a task.                    |
| `* * *`  | Student                                    | Add modules                                                           | I can store all relevant information and links related to a module together.                              |
| `* * *`  | Student                                    | View all my modules                                                   | I can keep track of the different modules that I am taking this semester.                              |
| `* * *`  | Student                                    | View all tasks and links associated with a module                     | I can view all module-related tasks and links in one place                             |
| `* * *`  | Student                                    | Edit my modules                                                       | I can make changes to information related to a module.                                                    |
| `* * *`  | Student                                    | Delete my modules                                                     | I can get rid of modules that I am no longer taking.                                                      |
| `* * *`  | Busy user                                  | View calendar                                                         | I can check the dates easily.                                                                             |
| `* * *`  | Busy Student                               | View all tasks in a single day in the calendar according to its date  | I can know what happens on any particular date.                                 |
| `* * *`  | Busy Student                               | See which days have ongoing tasks in a calendar                       | I can see which days I am free                                                                            |
| `* *`    | Digital-Age Student                        | Add file paths in my local computer to modules                        | I can access module-related files directly from the application.                                          |
| `* *`    | Digital Age Student                        | Add web links to modules                                              | I can access module-related links conveniently.                                                           |
| `* `     | User                                       | Search for a link using keywords                                      | I do not have to waste time opening module or task tab to find a link.                                    |
| `* *`    | New User                                   | Open the command summary to help me navigate the application          | I know how to use the application to perform the functions that I want.                                   |
| `* *`    | Experienced User                           | Close the command summary                                             | It does not take up unnecessary screen space after I am familiar with the commands.                       |
| `*`      | Long-term user                             | Archive all the data                                                  | I store away old data to make space for new data.                                                         |


Return to [Table of Contents](#table-of-contents).


### Common Use cases

(For all use cases below, the **System** is `ManageMe` unless specified otherwise)

<div markdown="block" class="alert alert-info">

**:information_source: Usage**<br>

*  All “generic” use cases below can be applied to Tasks, Modules, and Links<br>

</div>

**Use case: Generic Add**

**MSS:**
1. User requests to add a task/module/link with its details.
2. System adds the new task/module/link and displays it. <br>
   Use case ends.

**Extensions:**
*1a. Compulsory parameters are missing, including names, description for tasks, and address for links
*1b1. The system shows the missing parameter error message and shows the correct format
Use case resumes from step 1.
*1b. Parameters entered are invalid, including invalid characters in the name, invalid date-time in the task, and invalid addresses in links.
* 1b1. System shows error in format.
Use case resumes from step 1.


**Use case: Generic Edit**

**MSS:**
1. User requests to edit a specified task/module/link and enters the new details.
2. System updates the details and displays the edited task. <br>
   Use case ends.

**Extensions:**
* 1a. User enters an invalid task index<br>
    * 1a1. System shows error in reading index.
      Use case resumes from step 1.
* 1b.Parameters entered are of invalid format, including invalid characters in names, invalid date-time for tasks, and invalid address for links.
    * 1a1. System shows error in parsing data.
      Use case resumes from step 1. <br><br>


**Use case: Generic Delete**

**MSS:**

1. User requests to delete a specified task/module/link.
2. System removes the specified task/module/link from the task list.
3. System updates the GUI to show the new task list. <br>
   Use case ends.

**Extension:**

* 1a. User enters an invalid task index
    * 1a1. System shows error in reading index.
      Use case resumes from step 1.


**Use case: Generic Find**

**MSS:**

1. User requests to search for specific tasks/modules/links by keyword.
3. System updates the GUI to show a list of all modules whose name contains the keyword. <br>
   Use case ends.

**Extension:**

* 1a. User does not enter a keyword. <br>
    * 1a1. System shows invalid format error.
      Use case resumes from step 1.


**Use case: Generic List**

**MSS:**

1. User requests to list all tasks/modules/links.
3. System updates the GUI to show the full list of keywords. <br>
   Use case ends.


Return to [Table of Contents](#table-of-contents).

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The system will not be responsible for checking the validity of external links entered into the system, such as Zoom links or Luminus links. They must be checked beforehand by the user.


Return to [Table of Contents](#table-of-contents).

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Digital-age:** A post-pandemic world where many schools have switched to online teaching and learning.
* **Module:** A course taken by a student, typically in a university.
* **Online resources:** Websites used by many schools in the post-pandemic world for teaching, file submission, and assessment. For example, Zoom, Luminus, Exemplify etc.
* **Command Line Interface (CLI):** An interface where the user primarily gives command to the computer by typing text lines, instead of clicking and dragging graphic components.


--------------------------------------------------------------------------------------------------------------------

Return to [Table of Contents](#table-of-contents).


## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy it into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.



### Deleting a task

1. Deleting a task while all tasks are being shown

    1. Prerequisites: List all tasks using the `list` command. Multiple tasks in the list.

    1. Test case: `deleteTask 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact are shown in the status message. The timestamp in the status bar is updated.

    1. Test case: `deleteTask 0`<br>
       Expected: No task is deleted. Error details are shown in the status message. The status bar remains the same.

    1. Other incorrect delete commands to try: `deleteTask`, `deleteTask x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

       
### Pop-up window

1. Test whether the pop-up window works in the app

Add a task with a starting time just 1 minute away from your current time .<br>
Expected: A reminder will appear in a pop-up window for this ongoing task 1 min later.

Type readMod for an existing module (if no module exists, add one with a simple name)
Expected: A window appears that show all tasks and links associated with a module



### Saving data

1. Dealing with missing/corrupted data files

    1. If there is a `data/manageme.json` file in the same directory with the jar, open the file and remove a random bracket. Launch the program.<br>
       Expected: Warning of invalid JSON file will be shown in the terminal. An empty ManageMe is launched.

    2. If there isn't a `data/manageme.json` file in the same directory with the jar. Launch the program and add a random task. Close the program. Remove a random bracket from the data file. Relaunch the program.<br>
       Expected: Warning of invalid json file will be shown in the terminal. An empty ManageMe is launched.

Return to [Table of Contents](#table-of-contents).

