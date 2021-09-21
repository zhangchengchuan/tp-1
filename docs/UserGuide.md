###**Task:**

####Adding a task: `todo`, `deadline`, `event`

Adds a task to the address book. The task must be one of the three categories:

1. `todo`: A task not associated with a date or time
2. `deadline`: A task that must be done before a deadline
3. `event`: A task that takes place over a time window

Optionally, a task can be linked to a module.

Format:

    todo DESCRIPTION [/mod MODULE]
    deadline DESCRIPTION /by DATE TIME [/mod MODULE]
    event DESCRIPTION /at DATE START_TIME END_TIME [/mod MODULE]

####Read details of a task: `readTask`
View a task in detail.

Format: `readTask INDEX`
- Read task details at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...
- Tasks will be displayed in this format: [type][status] description (date time)
    - Type: T for `todo`, D for `deadline`, E for `event`
    - Status: X for done, blank for not done
    - Date is in the format: Month Day Year
    - Time is in 24-hour format

Example: `readTask 3`

####Update a task: updateTask
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

####Deleting a task: `deleteTask`
Deletes the specified task from the task list.

Format: `deleteTask INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list
- The index **must be a positive integer** 1, 2, 3, ...

Example: `deleteTask 2`