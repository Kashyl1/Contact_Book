# Contact_Book

A Java console application.
This program allows you to add Contacts like person/organization to your Contact book.
User can customize for 
Person:
name, surname, birthdate, phone number, gender

Organization:
name, address, phone number

User can also see information about the last edit and created record, and save it to database from command-line arguments

# Technologies Used
-Java


# How to save/load database (Intellij)
1) Right click on main class
2) Click "Modify Run Configuration..."
3) Find field "Program arguments" 
4) Write for example: contacts.db
If your database has not been created yet, the program will create it automatically


# Sample application session:
(Already created database with records)

```
[menu] Enter action (add, list, search, count, exit):
> list
1. Kamil Babinski
2. Pizza shop
3. Emilia Wielkowska
4. Bank 4200
5. MBank
6. National Bank
[list] Enter action ([number], back):
> 1
Name: Kamil
Surname: Babinski
Birth date: 2002-05-17
Gender: M
Number: 673 993 231
Time created: 2023-03-21T19:53
Time last edit: 2023-03-21T19:53
[record] Enter action (edit, delete, back): 
> edit
Select a field (name, surname, birth, gender, number):
> surname
Enter surname:
> Kowalski
Saved
[record] Enter action (edit, delete, back):
> back

[menu] Enter action (add, list, search, count, exit):
> list
1. Kamil Kowalski
2. Pizza shop
3. Emilia Wielkowska
4. Bank 4200
5. MBank
6. National Bank
[list] Enter action ([number], back):
> 3
Name: Emilia
Surname: Wielkowska
Birth date: 2001-09-26
Gender: F
Number: 321 123 456
Time created: 2023-03-21T19:54
Time last edit: 2023-03-21T19:54
[record] Enter action (edit, delete, back): 
> delete
The record has been removed!

[menu] Enter action (add, list, search, count, exit):
> list
1. Kamil Kowalski
2. Pizza shop
3. Bank 4200
4. MBank
5. National Bank
[list] Enter action ([number], back):
> back

[menu] Enter action (add, list, search, count, exit):
> search
Enter search query: 
> bank
Found results:
1. Bank 4200
2. MBank
3. National Bank
[search] Enter action ([number], back, again): 
> 2
Organization name: MBank
Address: sloneczna
Number: 291 321 568
Time created: 2023-03-21T19:55
Time last edit: 2023-03-21T19:55
[record] Enter action (edit, delete, back): 
> edit
Select a field (name, address, number):
> address
Enter address:
> Sloneczna 15
Saved
[record] Enter action (edit, delete, back): 
> back
[search] Enter action ([number], back, again):
> again
Enter search query:
> bank
Found results:
1. Bank 4200
2. MBank
3. National Bank
[search] Enter action ([number], back, again):
> 2
Organization name: MBank
Address: Sloneczna 15
Number: 291 321 568
Time created: 2023-03-21T19:55
Time last edit: 2023-03-21T19:57
[record] Enter action (edit, delete, back):
> delete
The record has been removed!
[search] Enter action ([number], back, again): 
> back

[menu] Enter action (add, list, search, count, exit):
> count
The Phone Book has 4 records.

[menu] Enter action (add, list, search, count, exit):
> list
1. Kamil Kowalski
2. Pizza shop
3. Bank 4200
4. National Bank
[list] Enter action ([number], back):
> back

[menu] Enter action (add, list, search, count, exit):
> add
Enter the type (person, organization):
> organization
Enter the organization name:
> My Future Job
Enter the address:
> Everywhere
Enter the number:
> 691 551 761
The record added.

[menu] Enter action (add, list, search, count, exit):
> list
1. Kamil Kowalski
2. Pizza shop
3. Bank 4200
4. National Bank
5. My Future Job
[list] Enter action ([number], back):
> 5
Organization name: My Future Job
Address: Everywhere
Number: 691 551 761
Time created: 2023-03-21T19:58
Time last edit: 2023-03-21T19:58
[record] Enter action (edit, delete, back):
> delete
The record removed!
The record has been removed!

[menu] Enter action (add, list, search, count, exit):
> exit

Process finished with exit code 0
```
