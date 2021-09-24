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



[comment]: <> (## Quick start)

[comment]: <> (1. Ensure you have Java `11` or above installed in your Computer.)

[comment]: <> (1. Download the latest `addressbook.jar` from [here]&#40;https://github.com/se-edu/addressbook-level3/releases&#41;.)

[comment]: <> (1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.)

[comment]: <> (1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>)

[comment]: <> (   ![Ui]&#40;images/Ui.png&#41;)

[comment]: <> (1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>)

[comment]: <> (   Some example commands you can try:)

[comment]: <> (   * **`list`** : Lists all contacts.)

[comment]: <> (   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.)

[comment]: <> (   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.)

[comment]: <> (   * **`clear`** : Deletes all contacts.)

[comment]: <> (   * **`exit`** : Exits the app.)

[comment]: <> (1. Refer to the [Features]&#40;#features&#41; below for details of each command.)

--------------------------------------------------------------------------------------------------------------------

## Features

[comment]: <> (<div markdown="block" class="alert alert-info">)

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. E.g. `todo DESCRIPTION`
  , here `DESCRIPTION` is the parameter.<br>

[comment]: <> (  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.)

* Items in square brackets are optional. E.g. `todo DESCRIPTION [/mod CS2103]`<br>

--------------------------------------------------------------------------------------------------------------------

[comment]: <> (  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.)

[comment]: <> (* Items with `…`​ after them can be used multiple times including zero times.<br>)

[comment]: <> (  e.g. `[t/TAG]…​` can be used as ` ` &#40;i.e. 0 times&#41;, `t/friend`, `t/friend t/family` etc.)

[comment]: <> (* Parameters can be in any order.<br>)

[comment]: <> (  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.)

[comment]: <> (* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>)

[comment]: <> (  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.)

[comment]: <> (* Extraneous parameters for commands that do not take in parameters &#40;such as `help`, `list`, `exit` and `clear`&#41; will be ignored.<br>)

[comment]: <> (  e.g. if the command specifies `help 123`, it will be interpreted as `help`.)

[comment]: <> (</div>)

[comment]: <> (### Viewing help : `help`)

[comment]: <> (Shows a message explaning how to access the help page.)

[comment]: <> (![help message]&#40;images/helpMessage.png&#41;)

[comment]: <> (Format: `help`)


[comment]: <> (### Adding a person: `add`)

[comment]: <> (Adds a person to the address book.)

[comment]: <> (Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`)

[comment]: <> (<div markdown="span" class="alert alert-primary">:bulb: **Tip:**)

[comment]: <> (A person can have any number of tags &#40;including 0&#41;)

[comment]: <> (</div>)

[comment]: <> (Examples:)

[comment]: <> (* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`)

[comment]: <> (* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`)

[comment]: <> (### Listing all persons : `list`)

[comment]: <> (Shows a list of all persons in the address book.)

[comment]: <> (Format: `list`)

[comment]: <> (### Editing a person : `edit`)

[comment]: <> (Edits an existing person in the address book.)

[comment]: <> (Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`)

[comment]: <> (* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​)

[comment]: <> (* At least one of the optional fields must be provided.)

[comment]: <> (* Existing values will be updated to the input values.)

[comment]: <> (* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.)

[comment]: <> (* You can remove all the person’s tags by typing `t/` without)

[comment]: <> (    specifying any tags after it.)

[comment]: <> (Examples:)

[comment]: <> (*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.)

[comment]: <> (*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.)

[comment]: <> (### Locating persons by name: `find`)

[comment]: <> (Finds persons whose names contain any of the given keywords.)

[comment]: <> (Format: `find KEYWORD [MORE_KEYWORDS]`)

[comment]: <> (* The search is case-insensitive. e.g `hans` will match `Hans`)

[comment]: <> (* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`)

[comment]: <> (* Only the name is searched.)

[comment]: <> (* Only full words will be matched e.g. `Han` will not match `Hans`)

[comment]: <> (* Persons matching at least one keyword will be returned &#40;i.e. `OR` search&#41;.)

[comment]: <> (  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`)

[comment]: <> (Examples:)

[comment]: <> (* `find John` returns `john` and `John Doe`)

[comment]: <> (* `find alex david` returns `Alex Yeoh`, `David Li`<br>)

[comment]: <> (  ![result for 'find alex david']&#40;images/findAlexDavidResult.png&#41;)

[comment]: <> (### Deleting a person : `delete`)

[comment]: <> (Deletes the specified person from the address book.)

[comment]: <> (Format: `delete INDEX`)

[comment]: <> (* Deletes the person at the specified `INDEX`.)

[comment]: <> (* The index refers to the index number shown in the displayed person list.)

[comment]: <> (* The index **must be a positive integer** 1, 2, 3, …​)

[comment]: <> (Examples:)

[comment]: <> (* `list` followed by `delete 2` deletes the 2nd person in the address book.)

[comment]: <> (* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.)

[comment]: <> (### Clearing all entries : `clear`)

[comment]: <> (Clears all entries from the address book.)

[comment]: <> (Format: `clear`)

[comment]: <> (### Exiting the program : `exit`)

[comment]: <> (Exits the program.)

[comment]: <> (Format: `exit`)

[comment]: <> (### Saving the data)

[comment]: <> (AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.)

[comment]: <> (### Editing the data file)

[comment]: <> (AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.)

[comment]: <> (<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**)

[comment]: <> (If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.)

[comment]: <> (</div>)

[comment]: <> (### Archiving data files `[coming in v2.0]`)

[comment]: <> (_Details coming soon ..._)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## FAQ)

[comment]: <> (**Q**: How do I transfer my data to another Computer?<br>)

[comment]: <> (**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## Command summary)

[comment]: <> (Action | Format, Examples)

[comment]: <> (--------|------------------)

[comment]: <> (**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`)

[comment]: <> (**Clear** | `clear`)

[comment]: <> (**Delete** | `delete INDEX`<br> e.g., `delete 3`)

[comment]: <> (**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`)

[comment]: <> (**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`)

[comment]: <> (**List** | `list`)

[comment]: <> (**Help** | `help`)
