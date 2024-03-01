package de.home_skrobanek.manhunt.manhuntServer.controler;

import de.home_skrobanek.manhunt.manhuntServer.ManhuntServerApplication;
import de.home_skrobanek.manhunt.manhuntServer.dao.Message;
import de.home_skrobanek.manhunt.manhuntServer.dao.Player;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class GameController {

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/game/status", consumes = {"application/json"})
    public Message initialRequest(@RequestBody Player player){
        System.out.println("User requested game start.");

        //check authentication
        if(ManhuntServerApplication.sql.existsPlayerWithDeviceID(player.getDevice_id())){
            //Get current date
            LocalDateTime now = LocalDateTime.now();

            if(now.isBefore(ManhuntServerApplication.DATE_OF_GAME)) {
                if(now.isAfter(ManhuntServerApplication.DATE_OF_GAME_END)) {
                    return new Message("Game started", true);
                }
                else
                    return new Message("Game is over", false);
            }
            else
                return new Message("Game has not started yet", false);
        }
        else
            return new Message("Authentication failed", false);
    }
}
