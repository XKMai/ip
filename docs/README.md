# Iris User Guide

Iris is a simple JavaFX desktop assistant that helps you **track tasks** and **manage contacts** through natural text commands.

---

## Features

- Add, list, search, mark, unmark, and delete tasks.
- Add, list, and delete contacts.
- Deadlines and events support date/time ranges.

---

## Getting Started

1. **Build / Run**
   ```bash
   ./gradlew run
   ```

# Iris Command Reference

All commands are typed into the input box at the bottom of the Iris window.  
Numbers for tasks and contacts are **1-based** (the first item shown by `list` or `contacts` is `1`).

---

## General

| Command | Description | Example |
| ------- | ----------- | ------- |
| `bye`   | Exit Iris.  | `bye`   |

---

## Task Commands

| Command                                                             | Description                            | Example                                                        |
| ------------------------------------------------------------------- | -------------------------------------- | -------------------------------------------------------------- |
| `list`                                                              | List all tasks.                        | `list`                                                         |
| `todo <description>`                                                | Add a to-do without any date.          | `todo Read a book`                                             |
| `deadline <description> /by <YYYY-MM-DD HHmm>`                      | Add a task with a deadline.            | `deadline Submit report /by 2025-09-22 1800`                   |
| `event <description> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>` | Add an event with start and end times. | `event Team meeting /from 2025-09-23 1000 /to 2025-09-23 1130` |
| `mark <task-number>`                                                | Mark a task as **done**.               | `mark 2`                                                       |
| `unmark <task-number>`                                              | Mark a task as **not done**.           | `unmark 2`                                                     |
| `delete <task-number>`                                              | Delete a task by number.               | `delete 3`                                                     |
| `find <keyword>`                                                    | Search for tasks containing a keyword. | `find report`                                                  |

> **Date/Time Format:** `YYYY-MM-DD HHmm` (24-hour).  
> Example: `2025-09-22 1800`

---

## Contact Commands

| Command                                | Description                 | Example                                          |
| -------------------------------------- | --------------------------- | ------------------------------------------------ |
| `contacts`                             | List all saved contacts.    | `contacts`                                       |
| `contact-add <name>, <phone>, <email>` | Add a new contact.          | `contact-add Alice, 555-1234, alice@example.com` |
| `contact-delete <contact-number>`      | Delete a contact by number. | `contact-delete 1`                               |

---

## Notes

- Arguments in angle brackets (`< >`) are placeholders—replace them with your own text.
- Commands are **case-sensitive** and should be entered exactly as shown.
- Use the numbers displayed in the `list` or `contacts` output when specifying `<task-number>` or `<contact-number>`.
