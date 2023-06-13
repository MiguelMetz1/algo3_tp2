package edu.fiuba.algo3.Shop;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Exceptions.WrongCost;
import java.util.HashMap;

public class Shop {

    HashMap<String, Provider> providers;

    Buyer buyer;
    public Shop( Buyer buyer ){//Dar el rol de Buyer al Player
        this.providers = new HashMap<>();
        this.buyer = buyer;
    }

    public Defense buy(String article) throws InsuficientCredits, NonExistentArticle {

        if( !this.providers.containsKey(article) ) {
            throw new NonExistentArticle("The article that you wanted is not in the shop.");
        }
        Credits cost = this.providers.get(article).articleCost();
        buyer.wasteCredits(cost);
        return this.providers.get(article).newArticle();
    }

    public void addArticle( String articleName, Provider provider) throws WrongCost {
        Credits articleCost = provider.articleCost();
        if( articleCost.lowerCreditsThan(new Credits(0)) ){
            throw new WrongCost("The cost of the article is lower than zero.");
        }

        this.providers.put(articleName, provider);
    }

}
