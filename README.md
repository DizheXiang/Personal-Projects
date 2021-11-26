# Receipt Recorder

## A manager of your expenses

**Function**:
- enter your expense *amount* and *date*
- summary the *total* expense for one day
- set and check *budget*

## User Stories

- As a user, I want to be able to save the entire state of the receipt recorder app
- As a user, I want to be able to reload the state of the receipt recorder app
- As a user, I want to be able to add a receipt to my expenses record
- As a user, I want to be able to load a receipt to my expenses record
- As a user, I want to be able to change a receipt to my expenses record
- As a user, I want to be able to delete a receipt to my expenses record
- As a user, I want to be able to see a total amount of spending in one day
- As a user, I want to be able to set a budget
- As a user, I want to be able to change a budget
- As a user, I want to be able to check whether total spending exceed budget

## Phase 4: Task 2
### Sample EventLog

Wed Nov 24 12:38:07 PST 2021
Change the budget

Wed Nov 24 12:38:07 PST 2021
Add a new Receipt

Wed Nov 24 12:38:07 PST 2021
Add a new Receipt

Wed Nov 24 12:38:27 PST 2021
Add a new Receipt

Wed Nov 24 12:38:44 PST 2021
Receipt item changed

Wed Nov 24 12:38:44 PST 2021
Receipt amount changed

Wed Nov 24 12:38:49 PST 2021
Remove one receipt

Wed Nov 24 12:39:08 PST 2021
Change the budget

## Phase 4: Task 3
If I have more time, I'd like to add date is a new data inside receipts,
so that the user is able to check the spending history for specific day. 
Also, the gui version of ui has a high coupling which make this project 
more fragile. For example, the ChangeReceipt class and AddReceipt class have very similiar
functionality and structure. I would try to reduce the coupling if I have more time.