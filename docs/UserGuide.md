### MODULES:
#### Adding a module: `addMod`
Adds a module into the module list. A module contains its name and zoom links. It can also be associated with any 
number of Todo, Deadline, Event tasks (like exams, assignments). These Tasks are added in the Task tab with a `/mod 
MODULE`.

Format: `addMod NAME [/link LINK_NAME LINK]` <br/>
Examples: `addMod CS2103 /link tutorial https://...`

#### Read details of a module: `readMod`
View module in detail. Creates a pop-up window to show the course name, link, and all tasks associated with the course.

Format: `readMod NAME`<br/>
Examples: `readMod CS2103`

#### Update a module: `updateMod`
Update an existing module in the address app.

Format: `updateMod NAME CATEGORY CONTENT`
* Deletes the mod by the specified `NAME`.<br/>
* `CATEGORY` must be one of the following:
  * `name`
  * `link`
* The format of the `CONTENT` must match the `CATEGORY`. E.g., if `CATEGORY` is `name`, the `CONTENT` `must be a valid name like 
    CS2103
* Existing values will be updated to the input values.

Example: `updateMod CS2103 link https://...`

#### Deleting a module: `deleteMod`
Deletes the specified mod from the mod list.

Format: `deleteMod NAME`
* Deletes the mod by the specified `NAME`. 

Examples: `deleteMod CS2103` deletes CS2103 from the list.
