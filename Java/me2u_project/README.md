# Me2U
This project involved developing an application that would allow recipients (people in need) to better connect with donors.
My contributions are shown here (only class I didn't fully write is Cart). This particular bit implements our donor user story;
as a donor, I want to be able to donate items and see which items are currently needed by the organization. 

The program has an inventory object to keep track of current inventory. The inventory is serialized into 2 files; item and packageList. The design pattern utilized here is Model-View-Controller. 

This was my first foray into creating a GUI. I created the view in Netbeans and extrapolated the controls in order to make the project strict MVC. To run this project, make sure to have the 2 serializable files item and packageList in the root directory of the project. A zipped Netbeans version of the project is provided.
