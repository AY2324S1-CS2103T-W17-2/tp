---
layout: default.md
title: "You Wei's Project Portfolio Page"
---

### Project: LetsGetHired

_LetsGetHired_ is a **desktop application** designed to assist you in
efficiently **tracking and managing your
internship applications and their progress**.

In addition to these capabilities, we recognize the importance of **speed and
efficiency**. Thus, _LetsGetHired_ is
**optimized for use via a Command Line Interface (CLI)**, but you still enjoy
the advantages of a Graphical User Interface
(GUI). If you are a swift typist, _LetsGetHired_ can streamline your internship
tracking **faster than many standard
GUI-based tools available.**

Given below are my contributions to the project.

* **New Feature**:
  * **Created the `SelectView` panel in the UI.** 
    [#58](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/58) and
    [#105](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/105)
    * _What it does_: Displays a larger panel beside the `InternApplicationListView`
      that serves to display all the details of the intern application.
    * _Justification_: It is our intention to allow users to as many notes as they want
      on an intern application. The `InternApplicationListCard` alone is not large enough to 
      display all the information in a neat and easy to read manner. Hence, a larger panel in
      the UI is needed.
    * _Highlights_: The panel is made with a table from a `GridPane` in which each table 
      cell will house specific data so that we can display text in a neat and text-aligned
      manner.
  
  * **Display data encapsulated in InternApplication onto SelectView**
    [#58](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/58)
    * _What it does_: Displays the data of an `InternshipApplication` onto the `SelectView`.
    * _Justification_: Important for users to be able to accurately see the important
      information of the `InternApplication` in the `SelectView`
    * _Highlights_: Main logic behind this implementation is the creation of 
      `SelectView#displayDetails(InternApplication i)` which extracts the value of each field
      and displays them into the corresponding `TextField` or `TextArea`. The function is then
      triggered whenever a command is executed in `MainWindow#executeCommand(String commandText)`

  * **Display InternApplication details by selecting or clicking the card in the InternApplicationListPanel**
    [#58](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/80) and
    [#105](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/105) and
    [#174](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/174)
    * _What it does_: Displays the data of an `InternshipApplication` onto the `SelectView` when the card is
      selected with a mouseclick or arrow button.
    * _Justification_: Give users the option of using the mouse and arrow keys instead.
    * _Highlights_: Sets a listener function in the `ListView` which calls the appropriate functions
      whenever a change in list cell selection occurs

* **Enhancements to existing features**:
  * **Enhanced notes feature implemented to allow for multiple notes rather than single note**
    [#103](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/103)
    * _What it does_: Allows each individual `InternApplication` to encapsulate a list of notes 
    * _Justification_: Allow users to add as many notes as they want on each entry, rather than 
      being limited to one single note per entry.
    * _Highlights_: The `InternApplication` class will hold a `List<Note` of instead of `Note`. 
      Subsequently, the note field in the json is also modified to hold an array of strings. 

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tyouwei&breakdown=true)
    summarizes my contributions to the codebase
  * [PRs authored by me](https://github.com/AY2324S1-CS2103T-W17-2/tp/pulls?q=is%3Apr+author%3Atyouwei) -
    over 20+ PRs authored and merged

* **Documentation**:
  * **User Guide**:
    * Added the Delete section
    * Updated the Note section in lieu of multiple notes feature
  * **Developer Guide**:
    * Updated the Note section in lieu of multiple notes feature
      [#170](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/170)
    * Added the SelectView UI Implementation section
      [#87](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/87)
    * Added the Sort Default Order section in Appendix
      [#215](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/215)
    * Added the Edit Notes section in Appendix
      [#215](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/215)
    * Added the Meaningful Sort Status section in Appendix
      [#215](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/215)

* **Community**:
  * [PRs reviewed by me](https://github.com/AY2324S1-CS2103T-W17-2/tp/pulls?q=is%3Apr+reviewed-by%3Atyouwei) -
    over 10+ PRs reviewed
  * [Bug reports raise by me](https://github.com/AY2324S1-CS2103T-W17-2/tp/issues?q=is%3Aissue+author%3Atyouwei) -
    posted 3 bug reports to help team improve the product
