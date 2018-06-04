package com.cognizant.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.event.bean.EventsBO;


	@RestController
	public class Recon { 	  
	    
	    private List<EventsBO> getReconsileData()throws IOException
	    {
	    	
	    	List<EventsBO> eventsBO = new ArrayList<EventsBO>();
	    	EventsBO events = new EventsBO();
	    	
			
			events.setComponent("treatment");
			events.setEventId(11111111);
			events.setBusiness("Retail");
			events.setCrdNumber("123456789");
			events.setMerchantName("Sainsburys");
			events.setMerchantNumber("001");
			events.setTxnAmount("0.50");
			events.setWindowId("1");
			eventsBO.add(events);
			
			EventsBO eventsSdi = new EventsBO();
	    	
			
			eventsSdi.setComponent("SDI");
			eventsSdi.setEventId(11111111);
			eventsSdi.setBusiness("Retail");
			eventsSdi.setCrdNumber("123456789");
			eventsSdi.setMerchantName("Sainsburys");
			eventsSdi.setMerchantNumber("001");
			eventsSdi.setTxnAmount("101");
			eventsSdi.setWindowId("1");
			eventsBO.add(eventsSdi);
			
			EventsBO eventsMV = new EventsBO();
	    	
			
			eventsMV.setComponent("Movement");
			eventsMV.setEventId(11111111);
			eventsMV.setBusiness("Retail");
			eventsMV.setCrdNumber("123456789");
			eventsMV.setMerchantName("Sainsburys");
			eventsMV.setMerchantNumber("001");
			eventsMV.setTxnAmount("101");
			eventsMV.setWindowId("1");
			eventsBO.add(eventsMV);
			
			EventsBO eventsAgree = new EventsBO();
	    	
			
			eventsAgree.setComponent("Aggregate");
			eventsAgree.setEventId(11111111);
			eventsAgree.setBusiness("Retail");
			eventsAgree.setCrdNumber("123456789");
			eventsAgree.setMerchantName("Sainsburys");
			eventsAgree.setMerchantNumber("001");
			eventsAgree.setTxnAmount("101");
			eventsAgree.setWindowId("1");
			eventsBO.add(eventsAgree);
			
			EventsBO eventsSettle = new EventsBO();	    	
			
			eventsSettle.setComponent("Settlement");
			eventsSettle.setEventId(11111111);
			eventsSettle.setBusiness("Retail");
			eventsSettle.setCrdNumber("123456789");
			eventsSettle.setMerchantName("Sainsburys");
			eventsSettle.setMerchantNumber("001");
			eventsSettle.setTxnAmount("101");
			eventsSettle.setWindowId("1");
			eventsBO.add(eventsSettle);
			
			EventsBO events1 = new EventsBO();
	    	
			
			events1.setComponent("treatment");
			events1.setEventId(11111111);
			events1.setBusiness("Retail");
			events1.setCrdNumber("1234567890");
			events1.setMerchantName("Sainsburys");
			events1.setMerchantNumber("001");
			events1.setTxnAmount("0.2");
			events1.setWindowId("1");
			eventsBO.add(events1);
			
			EventsBO eventsSdi1 = new EventsBO();
	    	
			
			eventsSdi1.setComponent("SDI");
			eventsSdi1.setEventId(11111111);
			eventsSdi1.setBusiness("Retail");
			eventsSdi1.setCrdNumber("1234567890");
			eventsSdi1.setMerchantName("Sainsburys");
			eventsSdi1.setMerchantNumber("001");
			eventsSdi1.setTxnAmount("100");
			eventsSdi1.setWindowId("1");
			eventsBO.add(eventsSdi1);
			
			EventsBO eventsMV1 = new EventsBO();
	    	
			
			eventsMV1.setComponent("Movement");
			eventsMV1.setEventId(11111111);
			eventsMV1.setBusiness("Retail");
			eventsMV1.setCrdNumber("1234567890");
			eventsMV1.setMerchantName("Sainsburys");
			eventsMV1.setMerchantNumber("001");
			eventsMV1.setTxnAmount("100");
			eventsMV1.setWindowId("1");
			eventsBO.add(eventsMV1);
			
			EventsBO eventsAgree1 = new EventsBO();
	    	
			
			eventsAgree1.setComponent("Aggregate");
			eventsAgree1.setEventId(11111111);
			eventsAgree1.setBusiness("Retail");
			eventsAgree1.setCrdNumber("1234567890");
			eventsAgree1.setMerchantName("Sainsburys");
			eventsAgree1.setMerchantNumber("001");
			eventsAgree1.setTxnAmount("100");
			eventsAgree1.setWindowId("1");
			eventsBO.add(eventsAgree1);
			
			EventsBO eventsSettle1 = new EventsBO();	    	
			
			eventsSettle1.setComponent("Settlement");
			eventsSettle1.setEventId(11111111);
			eventsSettle1.setBusiness("Retail");
			eventsSettle1.setCrdNumber("1234567890");
			eventsSettle1.setMerchantName("Sainsburys");
			eventsSettle1.setMerchantNumber("001");
			eventsSettle1.setTxnAmount("102");
			eventsSettle1.setWindowId("1");
			eventsBO.add(eventsSettle1);
			
	        return eventsBO;
	    }
	    
	    @RequestMapping(value = "/recon", params = "windowId", method = RequestMethod.GET)
	    public String getReconsileJSON(@RequestParam(value="windowId")String windowId) throws JsonParseException, IOException
	    {
	        boolean tempFlag = true;
	        String treatementTxnAmount = null;	       
	        StringBuffer result = new StringBuffer();	        
	    	List<EventsBO> eventsBO = getReconsileData();	    	
	    	Set<String> amount = null;  
	    	Map<String,Map<String,String>> eventCrdNumberData = new HashMap<String,Map<String,String>>();
	    	Map<String,String> eventComponent = null;
	    	Map<String,Set<String>> eventComponentTrnAmt = null;
	    	
	    	
	    	for (int i=0; i<eventsBO.size(); i++) {    		
	    		
	    		if(eventsBO.get(i).getComponent().equalsIgnoreCase("treatment")){
	    			eventComponent = new HashMap<String,String>();
	    			eventComponent.put(eventsBO.get(i).getComponent(), eventsBO.get(i).getTxnAmount());
	    	    }else if(eventsBO.get(i).getComponent().equalsIgnoreCase("SDI")){
	    	    	eventComponent.put(eventsBO.get(i).getComponent(), eventsBO.get(i).getTxnAmount());
	    	    }else if(eventsBO.get(i).getComponent().equalsIgnoreCase("Movement")){
	    	    	eventComponent.put(eventsBO.get(i).getComponent(), eventsBO.get(i).getTxnAmount());
	    	    }else if(eventsBO.get(i).getComponent().equalsIgnoreCase("Aggregate")){
	    	    	eventComponent.put(eventsBO.get(i).getComponent(), eventsBO.get(i).getTxnAmount());
	    	    }else if(eventsBO.get(i).getComponent().equalsIgnoreCase("Settlement")){
	    	    	eventComponent.put(eventsBO.get(i).getComponent(), eventsBO.get(i).getTxnAmount());
	    	    }
	    	    eventCrdNumberData.put(eventsBO.get(i).getCrdNumber(), eventComponent);
	    	}
	    	
	    	for (Map.Entry<String,Map<String,String>> entry : eventCrdNumberData.entrySet()){
	    		
	    		Map<String,String> entryComponent = entry.getValue();
	    		amount = new HashSet<String>();
	    		for (Map.Entry<String,String> entryComponentValues : entryComponent.entrySet()){	    			
	    			if(tempFlag && entryComponentValues.getKey().contains("treatment")){
	    				eventComponentTrnAmt = new HashMap<String,Set<String>>();	    				
	    				tempFlag =true ;
	    				treatementTxnAmount = entryComponentValues.getValue();
	    			}else if(tempFlag && entryComponentValues.getKey().contains("SDI")){
	    				tempFlag =true ;
	    				amount.add(entryComponentValues.getValue());
	    				eventComponentTrnAmt.put(entry.getKey(), amount);
	    			}else if(tempFlag && entryComponentValues.getKey().contains("Movement")){
	    				tempFlag =true ;
	    				amount.add(entryComponentValues.getValue());
	    				eventComponentTrnAmt.put(entry.getKey(), amount);
	    			}else if(tempFlag && entryComponentValues.getKey().contains("Aggregate")){
	    				tempFlag =true ;
	    				amount.add(entryComponentValues.getValue());
	    				eventComponentTrnAmt.put(entry.getKey(), amount);
	    			}else if(tempFlag && entryComponentValues.getKey().contains("Settlement")){
	    				tempFlag =true ;
	    				amount.add(entryComponentValues.getValue());
	    				eventComponentTrnAmt.put(entry.getKey(), amount);
	    			}else{
	    				tempFlag =false ;
	    			}
	    		}
	    		
	    		if(tempFlag && amount.size()==1 ){
	    			Double treatementAmount = Double.parseDouble(treatementTxnAmount);
	    			
	    			Double pricedAmount = 0.0;
	    			
	    			if(treatementAmount < 100)
	    				pricedAmount = treatementAmount - (treatementAmount * 0.02);
	    			else
	    				pricedAmount = treatementAmount - (treatementAmount * 0.05);
	    			result.append("Passed -> " + " Amount -> " + pricedAmount + " CardNumber ->  " + entry.getKey());
	    			
	    		}else{
	    			result.append("Failed -> " + " CardNumber ->  " + entry.getKey());
	    		}
	    		result.append("</br>");
	    	}	
	    	
	    	return result.toString();
	    }
	    
	   
}
	
	
