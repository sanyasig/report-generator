
package com.sanyasi.trade_report.generator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyasi.trade_report.models.Report;
import com.sanyasi.trade_report.models.ReportItem;
import com.sanyasi.trade_report.models.Trade;

/**
 * @author sanyasig
 * Ranking of entities based on incoming and outgoing amount.
 */

public class AmountRanking extends ReportItem {
	private static final Logger log = LoggerFactory.getLogger(AmountUSDOutgoing.class);
	
	public Report genrateReport(ArrayList<Trade> trades) {
		log.info("genrating Report for Ranking of entities based on incoming and outgoing amount. " + trades.size() + " Trades");
		
		HashMap<String, Double> buyAmmount = new HashMap<String, Double>();
		HashMap<String, Double> sellAmmount = new HashMap<String, Double>();
		
		for(Trade trade : trades){
			
			if(trade.isBuy())
				updateEnriryAmount(trade, buyAmmount);
			else
				updateEnriryAmount(trade, sellAmmount);
			
		}
		
		LinkedHashMap<Object, Object> sortedSAmout = sellAmmount.entrySet().stream()
	                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		LinkedHashMap<Object, Object> sortedBAmout = buyAmmount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		String buyEnrityRank = sortedBAmout.keySet().toString();
		String sellEntityRank = sortedSAmout.keySet().toString();
		
		HashMap<String, Object> rankMap = new HashMap<String, Object>();
		rankMap.put("Buy Orders", buyEnrityRank);
		rankMap.put("Sell Orders", sellEntityRank);
			
		return new Report("Buy/Sell Entity Rank", "Type", "Entities High-to-Low ", rankMap);
	}

	private void updateEnriryAmount(Trade trade, HashMap<String, Double> map) {
		String entity = trade.getEntity();
		
		if (!map.containsKey(entity)) {
			map.put(entity, trade.getAmountUSD());
		} else {
			map.put(entity, map.get(entity) + trade.getAmountUSD());
		}
		
	}
	
}
