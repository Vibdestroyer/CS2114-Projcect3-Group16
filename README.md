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
| 5     | Refill ink to 100 units      |
| 6     | Game over                    |

The Printer begins with 50 sheets of paper and 3 pending jobs. 

New jobs have a 50% chance of appearing after an action.

Printer jams have a 15% chance under applicable conditions.

The player loses if the number of pending jobs exceeds 15.

Printing requires sufficient paper, sufficient ink, and a printer that is not jammed. Each printed page consumes one sheet of paper and one unit of ink.

Skipping a job does not use paper.

The player loses if the number of jobs pending is above 15.

# Program Structure
The project separates the printer's internal logic from user interaction.


| Class | Responsibility |
|-------|----------------|
| Queue.java | Implements a linked priority queue that processes higher-priority jobs first while preserving arrival order for equal priorities. |
| Print_Job.java | Represents an individual print job and its information. |
| Printer.java | Manages paper, ink, printing, and printer jams |
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

# Priority-Based Printing

The original FIFO implementation was modified to support three levels of priority.

Higher-priority jobs are processed before lower-priority jobs, while jobs with equal priority preserve their arrival order.

# Ink Management

We implemented an ink management system that starts the printer with 100 units of ink. Each printed page consumes one unit of ink. If insufficient ink is available, the printer cannot complete the job, and the job remains in the queue.

Players can select option 5 to refill ink to 100 units.

We also added automated tests to verify ink consumption, refilling, and insufficient-ink behavior.

## Testing

## Testing

Our project includes automated tests for the core model classes:

- `QueueTest.java` tests queue operations, priority ordering, and edge cases.
- `PrinterTest.java` tests printer operations, paper management, ink consumption, refilling, and insufficient-resource behavior.
- `Print_JobTest.java` tests print job properties and behavior.

We manually tested the game through the console to verify that user actions correctly update the model and that the view displays the resulting state.

We verified that ink decreases after printing and that the game runs with the updated ink management feature.

Before final submission, we will verify complete normal and bad-input coverage for each core model method, including interleaved enqueue and dequeue operations.

The automated model tests do not require the console interface to run.

## What is left to finish the MVP?

1. Verify that invalid menu input is handled without crashing.
2. Make sure test coverage is sufficient and meets rubric criteria.

Added testing will cover paper capacity, printing behavior, inputs that aren't valid, and queue preservation when a job can't be printed successfully.

# Reflection

For this deliverable, we expanded Print Panic by implementing priority-based printing and ink management. Our original design used a FIFO queue, but we modified it to support different priority levels while preserving the arrival order of jobs with equal priorities.

### How Our Design Absorbed the Change

For priority-based printing, we modified `Queue.java` to insert jobs according to priority, `Print_Job.java` to store priority values, and `Text_Reader.java` to generate jobs with different priorities.

For ink management, we modified `Printer.java` to track available ink and prevent printing when insufficient ink is available. We also updated `Main.java` to handle the refill action and `Printer_View.java` to display the new menu option.

Our original design allowed us to introduce these features without completely rewriting the game. However, implementing ink management required changes across the model, controller, and view.

### Model and View Separation

The model continues to handle printer state and printing rules, while the controller processes user actions and the view displays information.

The view does not need to calculate ink consumption or determine job priority. These responsibilities remain in the model. The view only needs to display the relevant information and provide the option to refill ink.

This separation helped us introduce additional functionality without placing printer logic inside the user interface.

### Changes From Our Original Plan

Our implementation expanded beyond the original FIFO queue by introducing priority-based printing and ink management.

We also added tests for ink consumption, refilling, and insufficient ink. These tests help verify that printer resources are updated correctly and that jobs cannot print when the required resources are unavailable.

### What Still Needs Improvement

Before the final submission, we need to verify that all required normal and bad-input cases are covered by automated tests. We also need to confirm that the complete game handles invalid menu input and other edge cases without crashing.

### How We Could Make Future Changes Easier

A future improvement would be to separate consumable resources such as paper and ink into their own resource-management component.

This would allow us to introduce additional resources, such as different paper types, without repeatedly modifying the `Printer` class. It would also make the resource-management logic easier to test independently.

# Next Steps 
For our final build, we plan to focus on making sure the priority queue works the way it should with the rest of the printer simulation. Cosmetic fixes with allowing the user to add jobs and assign priorities to jobs in the queue.


# Remaining work for the final build
Our remaining priorities are:
1. Verify the priority queue processes jobs and preserves arrival order when jobs have = priorities
2. Complete automated tests for normal and bad-input cases across the core model methods.
3. Confirm invalid menu input, insufficient resources, and empty queues do not crash the game.
4. Review user interface and make sure displayed printer status reflects the model and what we want to happen.
5. Run complete tests and prepare for final demo.

Demo must be explicitly timed. Here's our plan:

Time	Demonstration
0:00–0:30	Introduce Print Panic and display the queue
0:30–1:00	Print a job and show paper and ink decreasing
1:00–1:30	Demonstrate skip-and-requeue
1:30–2:00	Demonstrate insufficient ink and refilling
2:00–2:30	Demonstrate jam handling
2:30–3:00	Show JUnit tests and explain model/view separation


# Your change feature.
For project 4, we updated Print Panic by implementing a priority queue for print jobs. Before, they were proceesed in the order they arrived using a first in, first out queue. With the new feature added, print jobs are assigned to a priority level that will decide when they are going to be printed. 

Jobs with the same level of priority are still printed in the order they arrived. To implement this feature, we updated the Print_Job class to 
store a priority value and we changed the enqueue() method in Queue.java to insert jobs in order of priority. We changed Text_Reader.java to create random priority levels for new print jobs. 

This feature will make the printer simulation more realistic, because documents that are more important can be processed first. 

A future improvement would be to separate consumable resources such as paper and ink into their own resource-management component. This would allow us to introduce additional resources without repeatedly modifying the Printer class.

# Which stretch goals will you attempt?
Implemented:

Priority-based printing

Skip-and-requeue functionality

Ink management and refilling

Deferred:

Different paper types, including standard, glossy, and cardstock

We decided to prioritize completing and testing our existing features rather than adding additional complexity before the final submission.


**Deferred Features:**
Different paper types are not required for the MVP. These features will only be considered after the core game and primary change feature are done.

We considered adding standard, glossy, and cardstock paper but prioritized completing and testing our existing features.

# Team Responsibilities

# Team Member

# Remaining Responsibilities

Victor

Verify printer functionality, priority queue behavior, and controller/view integration

Chris

Review test coverage and help resolve failing tests

Madison

Verify bad-input testing, review automated tests, update the README, and prepare the demonstration

Each team member will review the final project before submission.


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

