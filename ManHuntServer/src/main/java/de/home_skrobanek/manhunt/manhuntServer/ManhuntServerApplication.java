package de.home_skrobanek.manhunt.manhuntServer;

import de.home_skrobanek.manhunt.manhuntServer.encryption.RSAUtil;
import de.home_skrobanek.manhunt.manhuntServer.sql.SQLManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.LocalDate;

@SpringBootApplication
public class ManhuntServerApplication {

	public static SQLManager sql;
	public static RSAUtil serverRSA;

	public static LocalDateTime DATE_OF_GAME;
	public static int INTERVAL_OF_POSITIONS = 15;

	public static void main(String[] args) {
		DATE_OF_GAME = LocalDateTime.from(LocalDateTime.of(2024, 3, 15, 13, 00));

		sql = new SQLManager();
		serverRSA = new RSAUtil();

		SpringApplication.run(ManhuntServerApplication.class, args);

	}

}
