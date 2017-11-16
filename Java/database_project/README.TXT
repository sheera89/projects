Dependencies-
mysql workbench
InnoDB
Windows 64-bit (due to the mysql connector I'm using)

Steps to run program:
1. Connect to your mysql database via myql workbench;

2. Import the database by clicking on Server -> Data Import and selecting the folder titled "create_database" in my submission.
   The schema should show up in the text area to the bottom left. 
   
3. Click on Start Import.

4. The database should show up in your Schema tab.

5. Get the hostname, port, and username from mysql workbench by clicking on
   Database -> Connect to Database.

6. The information should be there. Write it down somewhere or prepare to copy and paste.

7. Open the credentials.txt file in my submission folder. Replace username with your username and password with your password.
   Replace the ip address field with the hostname and replace port as well. NOTE: LEAVE THE PARENTHESES IN THERE.

8. Now double click the workout_tracking_system.jar file. 

9. You can login with any of the credentials listed below. Please note the error checking uses a mysql query and there is 
   a pop-up box for invalid entries.
     User ID   Password
	 13990    IYT67GDN6QC
	 113895   WRQ16TSU5PF
	 128201   EWZ08XLI0GF
	 191528   NOL90NVM4OU
	 655068   EVO23HBW8RL
	 810391   XDG98GGT9UB

10. To see changes to data including changes to the food_consumed table, drinks_consumed table, workouts table you can check 
    via the JTables inside the program or by querying the tables in workbench after the data has been manipulated.
