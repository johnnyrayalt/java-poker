import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Poker;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Poker poker = new Poker();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/poker/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            poker.clearHands();
            poker.createDeck();
            for(int i = 0 ; i < 5; i++) {
                poker.dealCard("player");
                poker.dealCard("dealer");
            }
            model.put("poker", poker);
            return new ModelAndView(model, "currentHand.hbs");
        }, new HandlebarsTemplateEngine());

        get("/poker/win", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Integer playerHand = poker.checkHighestCard("player");
            Integer dealersHand = poker.checkHighestCard("dealer");
            if (playerHand > dealersHand) {
                model.put("win", "You win!!!!");
            } else {
                model.put("lose", "you lose fuckface");
            }
            model.put("playerHighCard", playerHand);
            model.put("dealerHighCard", dealersHand);
            return new ModelAndView(model, "winlose.hbs");
        }, new HandlebarsTemplateEngine());

    }

}

