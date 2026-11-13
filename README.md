# CS2114-Project3-Group16
# Print Panic 
Print Panic is a console game derived from the concepts of a printer- the player manages a printer while also trying to prevent a growing load of print jobs. The player has to print the jobs in order of each other, refill paper when needed, and is responsible for clearing printer jams.

The program uses a linked queue to monitor and manage print jobs first in, first out (FIFO) order. Each printing job has a requester, number of pages, and description.

New jobs and printer jams will take place randomly as part of the game, requiring the player to manage limited paper resources and also keep track of the pending jobs to be completed.

# How To Run The Program
1. Clone or download the GitHub repo.
2. Open a project in a Java development environment.
3. Ensure names.txt and description.txt are in the working directory.
4. Compile the Java source files.
5. Run Main.java

# Game Controls
| Input | Action                       |
| ----- | ---------------------------- |
| 1     | Print the next job           |
| 2     | Refill paper to maximum      |
| 3     | Clear a printer jam          |
| 4     | Skip the next job            |
| 0     | Quit the game                |

The Printer begins with 50 sheets of paper and 3 pending jobs. New jobs have a 50% chance of appearing after an action, and printer jams have a 15% chance under applicable conditions.

The player loses if the number of jobs pending is above 15.

# Program Structure
The project separates the printer's internal logic from user interaction.


| Class | Responsibility |
|-------|----------------|
| Queue.java | Implements a linked queue to store print jobs in FIFO order. |
| Print_Job.java | Represents an individual print job and its information. |
| Printer.java | Manages paper, printing, and printer jams. |
| Printer_View.java | Displays printer status, game controls, and messages. |
| Text_Reader.java | Reads text files to generate random print jobs. |
| Main.java | Controls the game and connects the model and view. |

The Printer and Queue classes do not depend on Printer_View, allowing the model to be tested on its own.

## Testing

`QueueTest.java`, `PrinterTest.java`, and `Print_JobTest.java` cover
the linked queue, printer operations, and print job behavior.

In a manual console run in Eclipse, we verified that refilling the tray
increased the paper count from 44 to 50 while the printer was jammed.
Refilling again when the tray was full left it at 50 sheets. Clearing
the jam returned the printer to Ready, and the next job printed
successfully.

## What is left to finish the MVP?

1. Verify that invalid menu input is handled without crashing.
2. Make sure test coverage is sufficient and meets rubric criteria.

Added testing will cover paper capacity, printing behavior, inputs that aren't valid, and queue preservation when a job can't be printed successfully.

# Reflection
During the process, we separated the model into classes with specified responsibilites to ensure the code was easy to maintain and build test cases for.

The linked queue allows us to print jobs in the order they come in (FIFO). Separating the printer from the view allows the printer behaviors to be tested without needing console interaction. 

# Changes from Original Spec
We added a fourth menu option, **Skip next job**, so players can postpone the job at the front of the queue. Skipping moves that job to the back and brings the next job to the front. The other jobs keep their relative order, and skipping uses no paper.


# Next Steps 
1. Complete and verify tests for the core Queue methods. 
2. Expand PrinterTest.java to cover paper limits, paper jams, and unsuccessful print attempts
3. Add tests for invalid file paths and empty input files in Text_ReaderTest.java
4. Verify that Printer_View.getUserChoice() handles invalid input without crashing.
5. Review and improve code documentation.

# Your change feature.
Our suggested non-trivial feature is a skip-and-requeue feature. The user will be able to move to the next pending print job to the back of the queue without actually printing it. This is non-trivial because it changes the model's behavior and is accessible through the contoller and view.

This feature would require changes to the queue operations, controller, and view.
Status: Planned; implementation not yet verified.
A quick check is to start with A, B, C, press 4, and confirm the order becomes B, C, A with the same paper count.

# Which stretch goals will you attempt?
Primary: Skip-and-requeue.

Deferred:
- Ink management
- Different paper types

For this deliverable, we plan to prioritize completing the MVP and implementing the skip-and-requeue feature. This allows users to move the next printing job to the end of the queue without printing it.  This would involve Queue.dequeue(), Queue.enqueue(), and updates to the controller and view. 

Additional stretch goals can also include:
1. Ink Management: 
Add an ink level that decreases as jobs are printed. Allows users to refill ink, and prevents printing when there is insufficient ink. We will update Printer.java to track ink levels, and update printer_view.java to display remaining ink.

2. Different Paper Types:
Introduce paper types such as standard, glossy, and cardstock. Allow print jobs to require a specific paper type. Prevents printing when the incorrect paper type is loaded. Update Print_Job.java to store paper requirements. Update Printer.java to check paper compatibility. Update Printer_View.java to display paper settings. 

**Deferred Features:**
Ink management and different paper types are not required for the MVP. These features will only be considered after the core game and primary change feature are done.


# What new tests will you need?
1. Test skip-and-requeue with multiple jobs
2. Test skip-and-requeue with empty queue.
3. Test that skipping with a single job leaves that job at the front and uses no paper.

# Attribution
The following table documents team contributions to each
class and test file in the Print Panic project.

| File | Team Member(s) | Contributions |
|------|----------------|---------------|
| Main.java | [Victor] | Wrote and revised the main game logic and controller. |
| MainTest.java | [Chris] | Wrote and revised the main game testing logic. |
| Printer.java | [Victor] | Implemented and tested printer operations, paper management, and jams. |
| Printer_View.java | [Victor] | Created and reviewed the console interface and user input handling. |
| Printer_ViewTest.java | [Chris, Madison] | Created and revised the testing methods and logic for Printer_View. |
| Queue.java | [Victor] | Implemented the linked queue and FIFO operations. |
| Print_Job.java | [Victor] | Created and tested the print job class and its methods. |
| Text_Reader.java | [Victor] | Implemented file reading and random print job generation. |
| Text_ReaderTest.java | [Chris, Madison] | Created and revised the testing logic for Text_Reader. |
| QueueTest.java | [Madison, Chris] | Wrote and ran tests for queue operations and edge cases, revisions for better test coverage. |
| PrinterTest.java | [Madison, Chris] | Wrote and ran tests for printer functionality, revisions for better test coverage. |
| Print_JobTest.java | [Chris] | Wrote and ran tests for print job behavior. |
| README.md | Madison, Chris | Drafted and organized project documentation. |


# Gen AI disclosure
1. Victor-used GenAi to help write and revise code.
2. Chris- Used GenAI to fix test methods and understand where they were failing.
3. Madison- Used GenAI to fix test file issues and why mine were not correctly running, and distinguish what needed to go on the readme.

