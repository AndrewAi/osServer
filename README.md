# osServer
GMIT, Operating Systems 1, Project, Semester 1 2016/17. MultiThreaded TCP  Client-Server Banking system. 


# Project Brief:
Your	project	is	to	write	a	Multi-threaded	TCP	Server	Application	which	allows	multiple	customers to	
update	their	bank	accounts	and	send	payments	to	other	bank	accounts.	The	service should	allow	the	
users	to:

1. Register with the	system
• Name
• Address
• Bank	A/C	Number
• Username	
• Password
2. Log-in	to	the	banking system	from the	client	application	to the	server	application.
3. Change	customer	details.
4. Make	Lodgements	to	their	Bank	Account.
5. Make	Withdrawal	from	their	Bank	Account	(Note:	Each	User	has	a	credit	limit	of	€1000).
6. View	the	last	ten	transactions	on	their	bank	account.
Server	Application	Rules
1. The	 server application should	 not provide	 any	 service	 to	 a	 client	 application	 that	 can	
complete	the	authentication.
2. The	server	should	hold	a	list	of	valid	users	of	the	service	and	a	list	of	all	the	users	previous	
transactions.
3. When	the	user	logs	in	they	should	receive	their	current	balance.


#Design Decisions:

I tried to keep my design as close to what I know a real Bank application/ mobile banking app is like.
This infuenced me in parts, such as not asking the user to create their own account, I generate a unique account for them.
As in a real banking application you would not be asked to create your own account number.

I used an arraylist that holds objects of type Account. Account is a class that holds member variables (Name,Address, etc) and a construtor
to create Account objects.
The accounts Array list was a simple lightway to hold all the accounts and used to itereate through the accounts and check information (i.e when user account login creditionals during
authentication.) and also to selectively pick a requested account.

Once the user succesfully logs in. the accountLocation integer variable is used to save the array index of the account that is logged in.

When you run the program intially if no accounts exist you will be asked to create an account.
Then you will have the optiom to Login or create an account.

once logged in you will be presented with menu only for users that are logged in.
Options presented : 1. change details, 2. Make Lodgement, 3. Make Withdrawal, 4. Logout.

#### Change Details:
Rather then having the user enter in all their details again. I chose to ask them with details they would like to change first,
then proceed to allow them to enter new details. I did it this way as to not annoy the user having to enter in everything again,
and it makes it more straightforward, eaiser and quicker. 
when the user selects details they would like to change they will be shown the current value for that detail.

##### Make Lodgment / Withdrawal :
Are done with the same method "setBalance" I felt it wasnt necessary for two methods as lodgement and withdrawal preform very similar opertions,
This cuts down on code and also simplifies it. I pass an interger through the setBalance method to signal whether a lodgment(1) or withdrawal(2)
should take place.
I do similary in the addTransaction method, with is part of the last10Transaction History feature.

##### Logut:
the user can logut and be presented with the main menu (login,register account). 
Also in the bankground when the user logs out the updated accounts (i.e if the user makes a lodgment) will be written to file.


#To Run this application :
download or clone this repo and run Server.java first. which will start the server and have it listen for incoming client-socket requests.
then run Client.java . will be then asked to enter the public ip address of the computer that Server.java is running on.
When I tested this locally on my laptop it was the ip address of my laptop.
Whem I tested on the Amazon websever it was the public Ip address.


Motivated by Paul Simon and others ;)
