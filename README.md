To build the application (while in the directory where the pom.xml is):
mvn clean install

To run the application on port 8080:
java -jar ./target/lab-escape-0.0.1-SNAPSHOT.jar

The rest API is then accessible like:
/lab/escape?lab={lab}&startX={startX}&startY={startY}

Example input

	OOOOOOOOOO
	O    O   O
	O OO O O O
	O  O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Starting point: (x=3, y=1)
 	
 	OOOOOOOOOO
	O    O   O
	O OO O O O
	O• O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Desired output:
	
	OOOOOOOOOO
	O••••O•••O
	O•OO•O•O•O
	O• O•O•O•O
	O OO•••O••
	O OOOOOOOO
	O        O
	OOOOOOOOOO