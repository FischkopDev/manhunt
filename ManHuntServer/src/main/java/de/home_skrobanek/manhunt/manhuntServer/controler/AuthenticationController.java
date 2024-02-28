package de.home_skrobanek.manhunt.manhuntServer.controler;

import de.home_skrobanek.manhunt.manhuntServer.ManhuntServerApplication;
import de.home_skrobanek.manhunt.manhuntServer.dao.Player;
import de.home_skrobanek.manhunt.manhuntServer.dao.Message;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationController {

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/player/encryption", method = RequestMethod.GET, consumes = {"application/json"} )
    public Message encryption(){
        System.out.println("User requested encryption key");
        Message m = new Message(ManhuntServerApplication.serverRSA.getPublicKey().toString(), true);
        return m;
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/player/authentication", method = RequestMethod.POST, consumes = {"application/json"} )
    public Message authenticate(@RequestBody Player player){
        System.out.println("User tried to authenticate");

        if(!ManhuntServerApplication.sql.existsPlayerWithDeviceID(player.getDevice_id())){
            ManhuntServerApplication.sql.registerPlayer(player);

            return new Message("New user registered", true);
        }

        return new Message("User already exists", false);
    }


}
