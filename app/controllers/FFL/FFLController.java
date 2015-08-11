package controllers.FFL;

import models.League;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.*;

import views.html.*;

public class FFLController extends Controller{

    public Result showFFL(){
        League dtb = new League(12);
        Map<String,Integer> standings = new HashMap<String,Integer>();
        standings.put("Travis", 12);
        standings.put("Ramsey", 11);
        standings.put("Drew", 10);
        standings.put("Jackie", 9);
        standings.put("Daniel", 8);
        standings.put("Kyle", 7);
        standings.put("Jordan", 6);
        standings.put("Nick", 5);
        standings.put("Richey", 4);
        standings.put("Jonathan", 3);
        standings.put("Tyler", 2);
        standings.put("Maff", 1);
        dtb.setStandings(standings);

        dtb.determineOrder();
        List<String> order = dtb.getDraftOrder();

        return ok(ffl.render(order));
    }

}
