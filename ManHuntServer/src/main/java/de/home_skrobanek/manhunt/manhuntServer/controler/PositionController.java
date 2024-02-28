package de.home_skrobanek.manhunt.manhuntServer.controler;

import de.home_skrobanek.manhunt.manhuntServer.ManhuntServerApplication;
import de.home_skrobanek.manhunt.manhuntServer.dao.Message;
import de.home_skrobanek.manhunt.manhuntServer.dao.Player;
import de.home_skrobanek.manhunt.manhuntServer.dao.Position;
import de.home_skrobanek.manhunt.manhuntServer.dao.PositionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@CrossOrigin
@RestController
public class PositionController {

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/position/init", consumes = {"application/json"})
    public Message initialRequest(@RequestBody Player player){
        System.out.println("User requested game start.");

        if(ManhuntServerApplication.sql.existsPlayerWithDeviceID(player.getDevice_id())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            return new Message(formatter.format(ManhuntServerApplication.DATE_OF_GAME) + " | interval="+ ManhuntServerApplication.INTERVAL_OF_POSITIONS, true);
        }
        else
            return new Message("Authentication failed", false);
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/position/request/all", consumes = {"application/json"})
    public Message requestAllPositions(@RequestBody PositionRequest positions){
        System.out.println("User requested game start.");

        if(ManhuntServerApplication.sql.existsPlayerWithDeviceID(positions.getDevice_id())){


            return new Message("", true);
        }
        else
            return new Message("Authentication failed", false);
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/position/add", consumes = {"application/json"})
    public Message addPosition(@RequestBody Position position){
        System.out.println("User requested game start.");

        if(ManhuntServerApplication.sql.existsPlayerWithDeviceID(position.getPlayer().getDevice_id())){
            ManhuntServerApplication.sql.addPosition(position);

            return new Message("Added position", true);
        }
        else
            return new Message("Authentication failed", false);
    }
}
