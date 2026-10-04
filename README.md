# Study Plan Bridge

Alua Rakhimzhanova, SE-2509  
Software Design Patterns — Assignment 3

## Overview

A Java console application that displays a weekly study plan.
The plan can be shown as a summary or a detailed view.
Each view supports plain text and Markdown formatting.
The formatter can be changed while the program is running.

The topic continues Study Plan Builder from Assignment 1.
This project focuses on displaying a plan using Bridge.
It does not require the previous project or external libraries.

## Pattern roles

- Abstraction: `StudyPlanView`
- Refined Abstractions: `SummaryStudyPlanView`, `DetailedStudyPlanView`
- Implementor: `PlanFormatter`
- Concrete Implementors: `PlainTextFormatter`, `MarkdownFormatter`
- Client: `Main`
- Data classes: `StudyPlan`, `StudySession`

`StudyPlanView` holds a reference to `PlanFormatter`.
The views choose which plan information to show.
The formatters handle titles, sections, text, and list items.
Neither formatter calculates study time or selects sessions.

```text
StudyPlanView --------------------> PlanFormatter
    |                                  |
    +-- SummaryStudyPlanView            +-- PlainTextFormatter
    +-- DetailedStudyPlanView           +-- MarkdownFormatter
```

## Runtime switching

```java
StudyPlanView detailed = new DetailedStudyPlanView(plan, plainText);
printView("Detailed plan in plain text", detailed);
detailed.setFormatter(markdown);
printView("Same detailed plan after switching to Markdown", detailed);
```

The same view and study plan are used before and after the switch.
Only the formatter reference changes.
`Main` demonstrates all four combinations of views and formatters.
The sample plan contains three sessions with a total duration of 195 minutes.
Markdown is printed as source text in the console.
Its headings and lists can be rendered by a Markdown viewer.

## Clean Code principles

### 1. Meaningful names

`SummaryStudyPlanView` describes what is shown.
`MarkdownFormatter` describes how the output is formatted.
`durationMinutes` makes the time unit clear.

### 2. Single responsibility

`StudyPlan` stores plan data and calculates total study time.
View classes select the content. Formatter classes handle its presentation.
Console printing stays in `Main`.

### 3. Small methods and classes

Each formatter method handles one output element.
`printView()` prints one view, and `renderOverview()` builds the shared summary.

### 4. DRY

Both views reuse `renderOverview()` instead of repeating the same plan fields.
`validateText()` checks required text in one place.
Markdown escaping is shared by all methods in `MarkdownFormatter`.
The formatters contain only their own formatting rules, without duplicated plan logic.

### 5. Depend on abstractions

`StudyPlanView` uses `PlanFormatter`, not a specific formatter class.
There are no checks for concrete formatter types inside the views.
`Main` selects the concrete classes when it connects the objects.

### 6. Open for extension

A new formatter can implement `PlanFormatter` without changing existing views.
A new view can extend `StudyPlanView` and use both existing formatters.
The two class hierarchies can grow independently.

### 7. Validate data and protect state

Required text cannot be blank, and a plan needs at least one session.
Session duration must be between 15 and 240 minutes.
`List.copyOf()` protects the plan from changes to the original lists.
A view rejects a null plan or formatter.

## How to run

Use JDK 17 or newer. JDK 25 also works.

In IntelliJ IDEA, open the project folder, select a JDK as the Project SDK,
mark `src` as Sources Root if needed, and run `Main.main()`.

From a terminal in the project folder:

```text
javac -encoding UTF-8 -d out src/kz/edu/aitu/studyplan/*.java
java -cp out kz.edu.aitu.studyplan.Main
```

The output contains a summary in plain text, the same summary in Markdown,
a detailed plan in plain text, and the same detailed plan in Markdown.

## Why Bridge

There are two independently changing parts: the kind of view and the output format.
Composition connects them through `PlanFormatter`.
This avoids separate classes such as `MarkdownSummaryView` and `PlainTextSummaryView`.
Builder was used to create plans in Assignment 1.
Bridge is used here to separate a plan view from its formatting implementation.
