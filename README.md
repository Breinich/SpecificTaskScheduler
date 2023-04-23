# Specific Task Scheduler
The program simulates the operation of a complex scheduler.

The globally preemptive, static priority scheduler runs the following scheduling algorithms:
1.  High priority level (priority = 1) RR scheduler, time slot: 2
2.  Low priority level (priority = 0) SRTF scheduler

## Input (standard input, stdin)
Data of one (max. 10) task per line. Structure of a line (comma separated):

letter of the task (A, B, C...) /n
task priority (0 or 1)

the start time of the task (whole number >= 0), it can already run in the next time slot (0: it already exists when the scheduler is started), in the case of new tasks arriving at the same rate, the ABC sequence decides
CPU stroke time of the task (integer >= 1)

### Example:

A,0,0,6

B,0,1,5

C,1,5,2

D,1,10,1

The end of the input is indicated by EOF (there must be a line feed before it, and an empty line may also occur).

## Output (standard output, stdout)
In the first line of the output, the running order of the tasks with their letters (only letters, no spaces).
In the second line, the total waiting time per bag, in the order of their arrival (not necessarily alphabetical), in the following form (separated by commas, without spaces):

1. task letter : waiting time, 2. letter : waiting time, ...

### Example (response to input above):

ACABDB

A:2,B:8,C:0,D:0

## Test data

A,1,2,7 

B,1,2,3 

ABABA 

A:3,B:4

Q,0,5,8 

P,1,7,2 

QPQ 

Q:2,P:0

A,0,0,5

B,0,0,4

C,0,1,3

D,0,2,1

BDBCA

A:8,B:1,C:4,D:0


A,0,0,3

B,1,0,2
C,0,3,3
D,1,4,1

BADAC
A:3,B:0,C:3,D:0
An encore, a bit more catchy:

A,0,0,5
B,0,1,3
C,1,1,1
D,0,4,1
E,1,3,2

ACBEDBA
A:7,B:4,C:0,E:0,D:1
Finally, a debatable case:

A,1,3,5 
D,1,6,1 

ADA 
A:1,D:1

> Arguably, if A's time slot expires, it can start a completely new one (and because of this, D has to wait a beat), or it can continue running, but if another task (D) comes along, then there is an immediate rescheduling. In the solution, proceed in such a way that you can start a new time slice, i.e. it runs for 4 time units, then comes a time unit of D, and finally a step of the remainder of A!
