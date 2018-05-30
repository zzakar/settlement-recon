package com.cognizant.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import net.minidev.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.event.bean.Event;
import com.cognizant.event.bean.EventsBO;
import com.cognizant.event.bean.Fee;
import com.cognizant.event.bean.Transaction;
import com.google.gson.Gson;

	@RestController
	public class Recon {   
		
		ThreadLocalRandom random;
		
		String url = "http://localhost:8080/events";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost httpPost = new HttpPost(url);
		
		HttpResponse httpResponse;
		
		Gson gson = new Gson();
		
		StringEntity request;
		

	    
	    
	   /* private Event getReconsileData()
	    {
	       	         
	        Transaction transaction = new Transaction("0001","Authorization","native");
	        Fee fee = new Fee(100,90,5,5);
	        Event event = new Event(transaction,fee) ; 
	        
	        return event;
	    }*/
	    
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
			events.setTxnAmount("100");
			events.setWindowId("1");
			eventsBO.add(events);
			
			EventsBO eventsMV = new EventsBO();
	    	
			
			eventsMV.setComponent("Movement");
			eventsMV.setEventId(11111111);
			eventsMV.setBusiness("Retail");
			eventsMV.setCrdNumber("123456789");
			eventsMV.setMerchantName("Sainsburys");
			eventsMV.setMerchantNumber("001");
			eventsMV.setTxnAmount("100");
			eventsMV.setWindowId("1");
			eventsBO.add(eventsMV);
			
			EventsBO eventsAgree = new EventsBO();
	    	
			
			eventsAgree.setComponent("Aggregate");
			eventsAgree.setEventId(11111111);
			eventsAgree.setBusiness("Retail");
			eventsAgree.setCrdNumber("123456789");
			eventsAgree.setMerchantName("Sainsburys");
			eventsAgree.setMerchantNumber("001");
			eventsAgree.setTxnAmount("100");
			eventsAgree.setWindowId("1");
			eventsBO.add(eventsAgree);
			
			EventsBO eventsSettle = new EventsBO();	    	
			
			eventsSettle.setComponent("Settlement");
			eventsSettle.setEventId(11111111);
			eventsSettle.setBusiness("Retail");
			eventsSettle.setCrdNumber("123456789");
			eventsSettle.setMerchantName("Sainsburys");
			eventsSettle.setMerchantNumber("001");
			eventsSettle.setTxnAmount("100");
			eventsSettle.setWindowId("1");
			eventsBO.add(eventsSettle);
			
	        
	        return eventsBO;
	    }
	    
	    @RequestMapping(value = "/recon")
	    public String getReconsileJSON(@RequestParam(value="name", defaultValue="World")String name) throws JsonParseException, IOException
	    {
	        boolean tempFlag = true;
	        String treatementTxnAmount = null;
	        String movementTxnAmount = null;
	        String aggregateTxnAmount = null;
	        String settlementTxnAmount = null;
	        String result = null;
	    	List<EventsBO> eventsBO = getReconsileData();
	    	Set<String> amount = new HashSet<String>();
	    	
	    	for (int i=0; i<eventsBO.size(); i++) 
	    	{ 
	    	    if(tempFlag && eventsBO.get(i).getComponent().equalsIgnoreCase("treatment")){
	    	    	tempFlag =true;
	    	    	treatementTxnAmount = eventsBO.get(i).getTxnAmount();
	    	    	amount.add(treatementTxnAmount);
	    	    }else if(tempFlag && eventsBO.get(i).getComponent().equalsIgnoreCase("Movement")){
	    	    	tempFlag =true;
	    	    	movementTxnAmount = eventsBO.get(i).getTxnAmount();
	    	    	amount.add(movementTxnAmount);
	    	    }else if(tempFlag && eventsBO.get(i).getComponent().equalsIgnoreCase("Aggregate")){
	    	    	tempFlag =true;
	    	    	aggregateTxnAmount = eventsBO.get(i).getTxnAmount();
	    	    	amount.add(aggregateTxnAmount);
	    	    }else if(tempFlag && eventsBO.get(i).getComponent().equalsIgnoreCase("Settlement")){
	    	    	tempFlag =true;
	    	    	settlementTxnAmount = eventsBO.get(i).getTxnAmount();
	    	    	amount.add(settlementTxnAmount);
	    	    }else{
	    	    	tempFlag =false;
	    	    	treatementTxnAmount = "";
	    	        movementTxnAmount = "";
	    	        aggregateTxnAmount = "";
	    	        settlementTxnAmount = "";
	    	    	result =  "Fail";
	    	    }
	    	}
	    	
	    	if(tempFlag && (amount.size()==1) && amount.contains(treatementTxnAmount) ){
	    		result =  "Pass";
	    	}else{
	    		result =  "Fail";
	    	}
	    	
	    	return result;
	    	
	    	/*Event event = getReconsileData();
	    	
	    	JsonFactory jsonFactory = new JsonFactory();
			JsonParser jp = jsonFactory.createJsonParser(event.toString().trim().replaceFirst("\ufeff", ""));
			
			//JsonGenerator jsonGen = jsonFactory.createJsonGenerator(new FileOutputStream(new File("E:\\workspace\\SimpleJson\\resources\\simple.json")), JsonEncoding.UTF8);
			ObjectMapper mapper =new ObjectMapper();
			jp.setCodec(mapper);
			JsonNode jsonNode = jp.readValueAsTree();
			readJsonData(jsonNode, mapper);*/
	    }
	    
	    static void readJsonData(JsonNode jsonNode, ObjectMapper mapper) throws IOException {
			Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.getFields();
			boolean tempFlag = false;
			int sumRecon = 0;
			int senderFee = 0;

			while (ite.hasNext()) {
				Map.Entry<String, JsonNode> entry = ite.next();
				if (entry.getKey().equalsIgnoreCase("transaction")) {
					Iterator<Map.Entry<String, JsonNode>> transOuter = entry
							.getValue().getFields();

					while (transOuter.hasNext()) {
						Map.Entry<String, JsonNode> entryTransOuter = transOuter
								.next();
						if (entryTransOuter.getKey().equalsIgnoreCase("eventType")
								&& entryTransOuter.getValue().toString()
										.equalsIgnoreCase("\"Authorization\"")) {
							tempFlag = true;
						}
					}
				}

				if (entry.getKey().equalsIgnoreCase("fee") && tempFlag) {
					Iterator<Map.Entry<String, JsonNode>> feeOuter = entry
							.getValue().getFields();

					while (feeOuter.hasNext()) {
						Map.Entry<String, JsonNode> entryFeeOuter = feeOuter.next();
						if (entryFeeOuter.getKey().equalsIgnoreCase("sender")) {
							senderFee = entryFeeOuter.getValue().asInt();
						}
						if (entryFeeOuter.getKey().equalsIgnoreCase("reciver")
								|| entryFeeOuter.getKey()
										.equalsIgnoreCase("acqFee")
								|| entryFeeOuter.getKey()
										.equalsIgnoreCase("issFee")) {
							sumRecon = sumRecon + entryFeeOuter.getValue().asInt();
						}
					}

					tempFlag = false;
				}
			}
			if (senderFee == sumRecon) {				
				
				JsonNode root = mapper.readTree(new File("E:\\workspace\\SimpleJson\\resources\\simple.json"));
				String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
				System.out.println("Before Update " + resultOriginal);

				JsonNode nameNode = root.path("transaction");
				((ObjectNode) nameNode).put("eventMaturity", "Reconsilation");
				mapper.writeValue(new File("E:\\workspace\\SimpleJson\\resources\\simple.json"), root);
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
				
				System.out.println("Successfully");
				
				String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
				System.out.println("After Update " + resultUpdate);
			}
		}
}
	
	
