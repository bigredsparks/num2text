package com.sonatype.num2text;

import com.sonatype.num2text.services.Num2TextGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class Num2TextApplication implements CommandLineRunner {

	@Autowired
	Num2TextGeneratorImpl num2TextGenerator;

	public static void main(String[] args) {
		// run the application then close
		SpringApplication.run(Num2TextApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {

		// process command line args
		if (args.length > 0) {
			// iterate over each input argument and attempt to convert each
			for (String arg : args) {
				System.out.println(num2TextGenerator.generateText(arg));
			}
		}
		else {
			// print usage
			System.out.println("Usage: java -jar num2text.jar {number}");
		}
	}
}
