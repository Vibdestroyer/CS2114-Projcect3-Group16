# CS2114-Projcect3-Group16
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
| Input | Action |
|-------|--------|
| 1 | Print the next job |
| 2 | Refill paper |
| 3 | Clear a printer jam |
| 0 | Quit the game |

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

# Testing
Testing focuses on verifying the queue, printer behavior and ensuring it acts as proposed, and print job validation.

The core Queue methods we used are: 
1) enqueue()
2) dequeue()
3) peek()
4) isEmpty()
5) size()

Added testing will cover paper capacity, printing behavior, inputs that aren't valid, and queue preservation when a job can't be printed successfully.

# Reflection
During the process, we separated the model into classes with specified responsibilites to ensure the code was easy to maintain and build test cases for.

The linked queue allows us to print jobs in the order they come in (FIFO). Separating the printer from the view allows the printer behaviors to be tested without needing console interaction. 

# Changes from Original Spec
Some changes from our original spec include


# Next Steps 
1. Complete and verify tests for the core Queue methods. 
2. Expand PrinterTest.java to cover paper limits, paper jams, and unsuccessful print attempts
3. Add tests for invalid file paths and empty input files in Text_ReaderTest.java
4. Verify that Printer_View.getUserChoice() handles invalid input without crashing.
5. Review and improve code documentation.

# What is left to finish the MVP? The remaining features and fixes, in the order you will do them
1. 
2.
3.
4.

# Your change feature.
Our suggested non-trivial feature is a skip-and-requeue feature. The user will be able to move to the next pending print job to the back of the queue without actually printing it. This is non-trivial because it changes the model's behavior and is accessible through the contoller and view.

This feature would require changes to the queue operations, controller, and view.

# Which stretch goals will you attempt?
For this deliverable, we plan to prioritize completing the MVP and implementing the skip-and-requeue feature. Additional stretch goals can also include 

# What new tests will you need?
1. Test skip-and-requeue with multiple jobs
2. Test skip-and-requeue with empty queue.
3. Test that jobs remain at the correct FIFO order.

# Who does what?



# Attribution
The following table documents team contributions to each
class and test file in the Print Panic project.

| File | Team Member(s) | Contributions |
|------|----------------|---------------|
| Main.java | [Name] | Wrote and revised the main game logic and controller. |
| Printer.java | [Name] | Implemented and tested printer operations, paper management, and jams. |
| Printer_View.java | [Name] | Created and reviewed the console interface and user input handling. |
| Queue.java | [Name] | Implemented the linked queue and FIFO operations. |
| Print_Job.java | [Name] | Created and tested the print job class and its methods. |
| Text_Reader.java | [Name] | Implemented file reading and random print job generation. |
| QueueTest.java | [Madison] | Wrote and ran tests for queue operations and edge cases. |
| PrinterTest.java | [Madison] | Wrote and ran tests for printer functionality. |
| Print_JobTest.java | [Name] | Wrote and ran tests for print job behavior. |
| README.md | Madison | Drafted and organized project documentation. |


# Gen AI disclosure
Victor-
Chris- 
Madison-

