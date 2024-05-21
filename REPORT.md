# Advanced Multithreading


## Introduction
  There is two assignment. One about calculating pi number and another is to controlling threads with semaphore.


1. ## Pi calculate
 This code has two classes. One for defining thread and another is for other functions.

  1. SectionCalculate class:
  -  This class is in main class "PiCalculator" and includes a constructor which defines 'n'. 'n' is an integer variable that uses in Pi calculating formulas.
  You can see two kinds of formula to calculate Pi number: "Nilakantha series" & "Bailey-Borwein-Plouffe Formula".
  "Nilakantha series" has less accuracy than "BBP formula" so it's better to use  "BBP formula".

  2. The main class (PiCalculator):
  -  calculate function: this function gets a floating point (floating point sets the number of decimal for target number) and returns the calculated Pi number an a string.
  In this function we made a thread pool that includes 10 fixed threads.
  For loop makes about 10000 treads to calculate Pi number. Each thread calculates each section of established series. So the result would be more precise if it repeats more.
  After all, we should shout down the thread pool.  
  -  Main function: It uses to test the code.

2. ## Semaphore
  1. Controller class: It used to start the threads.
  2. Operator class: It used to defining the threads. 



## Notes
- In first formula ("Nilakantha series"), the starting point of n should be 1 and in second formula (BBP formula), it should starts with n=0.



## Resources
- https://observablehq.com/@rreusser/computing-with-the-bailey-borwein-plouffe-formula
- https://www.wikihow.com/Calculate-Pi

### Creator
- Mahan Tahmasbi
- email: m.tahmasbi1383@gmail.com