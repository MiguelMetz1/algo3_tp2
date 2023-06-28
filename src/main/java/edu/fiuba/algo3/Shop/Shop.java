package edu.fiuba.algo3.Shop;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Shop.Provider.Provider;
import edu.fiuba.algo3.TypeData.Credits.Credits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;

import java.util.HashMap;

public class Shop {

    HashMap<String, Provider> providers;
    Buyer buyer;

    public Shop( Buyer buyer ){
        this.providers = new HashMap<>();
        this.buyer = buyer;
    }

    public void buy(String article) throws InsuficientCredits, NonExistentArticle {

        if( !this.providers.containsKey(article) ) {
            throw new NonExistentArticle("The article that you wanted is not in the shop.");
        }
        Credits cost = this.providers.get(article).articleCost();
        buyer.wasteCredits(cost);
        Defense defense = this.providers.get(article).newArticle();
        buyer.giveDefense(defense);
    }

    public void addArticle( String articleName, Provider provider){
        Credits articleCost = provider.articleCost();
        if( !articleCost.lowerCreditsThan(new Credits(0)) ){
            this.providers.put(articleName, provider);
        }

    }

}
