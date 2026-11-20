# CS2114-Project3-Group16
# Print Panic 
Print Panic is a console game derived from the concepts of a printer- the player manages a printer while also trying to prevent a growing load of print jobs. The player has to print the jobs in order of each other, refill paper when needed, and is responsible for clearing printer jams as well as managing the order of pending jobs.

The original program used a linked queue to process print jobs in first-in, first-out (FIFO) order. For Project 4, we updated the queue to support priority-based printing. Each print job contains a requester, number of pages, description, and priority level.

New jobs and printer jams will take place randomly as part of the game, requiring the player to manage limited paper resources and also keep track of the pending jobs to be completed.

# How To Run The Program
1. Clone or download the GitHub repo.
2. Open a project in a Java development environment.
3. Ensure names.txt and description.txt are in the working directory.
4. Compile the Java source files.
5. Run Main.java and follow the console instructions.

# Game Controls
| Input | Action                       |
| ----- | ---------------------------- |
| 1     | Print the next job           |
| 2     | Refill paper to maximum      |
| 3     | Clear a printer jam          |
| 4     | Skip the next job            |
| 0     | Quit the game                |

The Printer begins with 50 sheets of paper and 3 pending jobs. 

New jobs have a 50% chance of appearing after an action.

Printer jams have a 15% chance under applicable conditions.

The player loses if the number of pending jobs exceeds 15.

Printing requires sufficient paper and a printer that is not jammed.

Skipping a job does not use paper.

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

# Project 4: Priority Queue Feature

For Project 4, we updated Print Panic by implementing a priority queue for print jobs.

Previously, jobs were processed in the order they arrived using a FIFO queue. With the new feature, each print job is assigned a priority level that determines its position in the queue.

We introduced 3 priority levels:
1) High(3): printed first.
2) Normal(2): printed after high-priority jobs.
3) Low(1): printed last.

Jobs with the same priority are still processed in their original order of arrival.

To implement this feature, we updated Print_Job.java to store a priority value and modified the enqueue() method in Queue.java to insert jobs according to priority.

We also updated Text_Reader.java to generate random priority levels for new print jobs.

This feature makes the simulation more realistic because important documents can be processed before less urgent ones.

# Changes from the Original Spec

# Skip-and-Requeue

We added a fourth menu option, Skip Next Job, which allows the player to postpone the job at the front of the queue.

Skipping moves the selected job back into the queue without printing it. The next available job becomes the front job.

This feature uses no paper and allows players to manage their printing workload.

The skip functionality uses queue operations to reposition jobs while maintaining the queue's ordering rules.

Priority-Based Printing

The original FIFO implementation was modified to support three levels of priority.

Higher-priority jobs are processed before lower-priority jobs, while jobs with equal priority preserve their arrival order.

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
Our original design used a standard FIFO queue, where print jobs were processed in the order they came in. For this deliverable, we changed the design to support priority based printing. 

We noticed that we did not need to completely change the queue we already had, and instead we were able to modify the enqueue() method to insert jobs based on their priority while keeping the rest of the queue structure mostly the same.

We also had think about what would happen when multiple jobs had the same priority, and decided to preserve the original order of arrival so the queue would still be fair. 

This updated feature helped us understand how a small change could have a larger effect on the program and how it operates.  It also reinforced the importance of testing edge cases and keeping the model separate from the user interface.

# Next Steps 
For our final build, we plan to focus on making sure the priority queue works the way it should with the rest of the printer simulation.

Our main goals:
1) 
2) 
3) 


# Your change feature.
For project 4, we updated Print Panic by implementing a priority queue for print jobs. Before, they were proceesed in the order they arrived using a first in, first out queue. With the new feature added, print jobs are assigned to a priority level that will decide when they are going to be printed. 

Jobs with the same level of priority are still printed in the order they arrived. To implement this feature, we updated the Print_Job class to 
store a priority value and we changed the enqueue() method in Queue.java to insert jobs in order of priority. We changed Text_Reader.java to create random priority levels for new print jobs. 

This feature will make the printer simulation more realistic, because documents that are more important can be processed first. 

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

