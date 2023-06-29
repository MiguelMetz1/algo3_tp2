package edu.fiuba.algo3.Model.Shop;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Model.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Model.Shop.Provider.Provider;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

import java.util.HashMap;

public class Shop {

    HashMap<String, Provider> providers;
    Buyer buyer;

    public Shop( Buyer buyer ){
        this.providers = new HashMap<>();
        this.buyer = buyer;
    }

    public void buy(String article) throws InsufficientCredits, NonExistentArticle {

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
