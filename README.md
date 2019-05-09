# num2text - Number to Text Generator

### Overview
This Application converts input number to text.  
For example, an input of 101 would generate the text: "one hundred one".

The application allows for multiple numbers to be provided as space-delimited arguments.  Each conversion is printed to a separate line.

The application converts positive number in the range of signed 32-bit integers
(-2147483648 through 2147483647)

Any input outside this range or a non-numeric input will result in an error.  

### Build Instructions

To build the application:  
`./mvnw clean package`

To run the application:  
`java -jar ./target/num2text-0.0.1-SNAPSHOT.jar {list_of_inputs}`

