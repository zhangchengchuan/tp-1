---
layout: page
title: User Guide
---

ManageMe is a **desktop app for time management and resource organisation, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ManageMe can get your tasks done faster than traditional GUI apps.

# Table of Contents
1. Features
    1. Tasks
        1. Add a Task: `todo`, `event`, `deadline`
        2. Read a Task: `readTask`
        3. Update a Task's Details: `updateTask`
        4. Delete a Task: `deleteTask`
    2. Modules
        1. Add a Module: `addMod`
        2. Read a Module: `readMod`
        3. Update a Module's Details: `updateMod`
        4. Delete a Module: `deleteMod`
    3. Calendar
    4. Others
        1. Get help: `help`
        2. Exit program: `exit`
2. Command Summary

--------------------------------------------------------------------------------------------------------------------

## UI Mockup:

![Ui](images/Ui1.png)

![Ui](images/Ui2.png)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. E.g. `todo DESCRIPTION`
  , here `DESCRIPTION` is the parameter.<br>

* Items in square brackets are optional. E.g. `todo DESCRIPTION [/mod CS2103]`<br>

</div>

--------------------------------------------------------------------------------------------------------------------
### **Task:**

#### Adding a task: `todo`, `deadline`, `event`

Adds a task to the address book. The task must be one of the three categories:

1. `todo`: A task not associated with a date or time
2. `deadline`: A task that must be done before a deadline
3. `event`: A task that takes place over a time window

Optionally, a task can be linked to a module.

Format:

    todo DESCRIPTION [/mod MODULE]
    deadline DESCRIPTION /by DATE TIME [/mod MODULE]
    event DESCRIPTION /at DATE START_TIME END_TIME [/mod MODULE]

#### Read details of a task: `readTask`
View a task in detail.

Format: `readTask INDEX`
- Read task details at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...
- Tasks will be displayed in this format: [type][status] description (date time)
    - Type: T for `todo`, D for `deadline`, E for `event`
    - Status: X for done, blank for not done
    - Date is in the format: Month Day Year
    - Time is in 24-hour format

Example: `readTask 3`

#### Update a task: updateTask
Update an existing task in the task list.

Format: `updateTask INDEX CATEGORY CONTENT`
- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...
- `CATEGORY` must be one of the following:
    - `description`
    - `date`
    - `time`
    - `start_time`
    - `end_time`
    - `mod`
- The format of the `CONTENT` must match the `CATEGORY`. E.g. if `CATEGORY` is the `date`, the `CONTENT` must be a valid date like 2021-09-08
- Existing values will be updated to the input values

Example: `updateTask 3 description buy milk`

#### Deleting a task: `deleteTask`
Deletes the specified task from the task list.

Format: `deleteTask INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index **must be a positive integer** 1, 2, 3, ...

Example: `deleteTask 2`
