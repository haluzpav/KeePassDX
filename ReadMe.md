# Front-end tests for Android Keepass DX

Keepass DX is a material design Keepass Client for manage keys and passwords in crypt database for your android device. For full description of the original project see <a href="https://github.com/Kunzisoft/KeePassDX">its GitHub page</a>.

This project adds automated front-end test and is written in JUnit, Selenium and Appium frameworks. Done as a semestral project at an university.

## Used test design methods

- equivalence classes
- cc, dc, c/dc, mc/dc
- pairwise
- process testing with test depth levels 1 and 2
- data consistency for all data objects, including complete R-coverage

## Coverage

Guesstimated overall coverage is 70%.

### Parts of the application covered in tests

- creating and opening a database with given path
- error states concerning password
- adding, editing and deleting groups and entries
- various fields on entry-edit screen
- password generator
- state transitions between screens

### Parts with 0% coverage

- database file selection using system file browser, handling no-access and similar exceptions
- using key-file as other way to open a database with
- settings and their effects
