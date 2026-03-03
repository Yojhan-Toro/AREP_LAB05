package arep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SpringBootApplication
public class Application {

	public static void main(String[] args)throws URISyntaxException, MalformedURLException {
		SpringApplication.run(Application.class, args);

		URL nyurl = new URI("https://ldbn.is.escuelaing.edu.co").toURL();

		System.out.println("Protocol: +" + nyurl.getProtocol());
		System.out.println("Host: +" + nyurl.getHost());
		System.out.println("Protocol: +" + nyurl.getProtocol());
		System.out.println("Protocol: +" + nyurl.getProtocol());
		System.out.println("Protocol: +" + nyurl.getProtocol());



	}



}
