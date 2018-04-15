package e_shopping.utils;

import javafx.util.Pair;

public class UsefulFunctions {

	public static Pair<Integer, Integer> splitString(String priceStr) {
		String[] prices = priceStr.split("\\.");
		Pair<Integer, Integer> res;
		int price_;
		int price__;
    	if(prices.length == 0) {
    		price_ = Integer.parseInt(priceStr);
    		price__ = 0;
    	}
    	else if(prices.length == 1){
    		price_ = Integer.parseInt(prices[0]);
    		price__ = 0;
    	}
    	else {
    		price_ = Integer.parseInt(prices[0]);
    		price__ = Integer.parseInt(prices[1]);
    	}
    	res = new Pair(price_, price__);
    	return res;
	}
}
