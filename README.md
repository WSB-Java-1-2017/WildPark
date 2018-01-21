# WildPark 

Wild Park Simulation project at WSB Java Training Level 1.

How to start application:

Configure JDK at your computer == set PATH environment variable to be able to use javac and java tools.
Download src/wildpark package.
Extract files. You will get 'src' folder with wildpark folder inside.
In Command Prompt / Console go to the root folder in which 'src' folder exists. Use cd command to enter the right folder.
Create 'out' folder - the foler named 'out'. This 'out' folder must be located in the same directory in which 'src' folder exists. Use the following console command:
mkdir out

Compile the application - enter the following command:
javac -d out -cp src src/wildpark/WildPark.java

Run the application - enter the following command:
java -cp out wildpark.WildPark
