# Homework Sorter

Sorts homework by deadlines

## The Problem

My homework is scattered across different websites and platforms (Ed, Pearson, Brightspace), 
so it is very tedious and time-consuming to keep track of it all at once. 
Jumping across websites to find each homework assignment, completely rewriting my homework list, 
and then sorting my list for deadlines and due dates has been a source of frustration for me.

## How It Works

First, the program runs a loop for receiving input so that, as long as the program is not 
done receiving input, the program will continue to receive input for each homework entry.
Next, the program continues to receive input ( via IO.readln() ) for each homework entry. 
Each entry of input is added to an ArrayList (ArrayList will be imported).
Once loop has ended (program is done receiving input), contents of ArrayList will be 
sorted by deadlines (custom-made method that puts more imminent deadlines above and 
later deadlines below). Finally, sorted list will be written into a csv or a txt file. 
End of program (I guess this is how to say the program ends?).

(how to put diagram in here???)

![System diagram]("")

## Tech Stack

- **Language:** Java
- **Libraries:** java.util.ArrayList
- **External tools:** not sure yet

## Setup

Step-by-step: how does someone who just cloned your repo get it
running?

(how to do this?)

```bash
git clone https://github.com/WonshinKim/Homework-Sorter
cd HomeWork-Sorter
./install.sh   # or whatever
```

## Usage

How to actually run the thing, with examples.

(How to write this?)

```bash
./backup.sh ~/Documents
```

## Roadmap

Features you haven't built yet but plan to (pull these from your
"Schedule" quadrant in the Eisenhower Matrix).

- [ ]List importance level of each homework next to each entry
- [ ] Rewrite csv or txt file when changes are made
- [ ] Enable MVP to clear out completed homework when asked to
- [ ] Receive individual new entries for new homework assignments
- [ ] Print entire contents of list when prompted


## Author

Wonshin Kim, https://github.com/WonshinKim.
