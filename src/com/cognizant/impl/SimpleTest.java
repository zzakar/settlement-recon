package com.cognizant.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

public class SimpleTest {
	public static void main(String[] args) throws JsonParseException,
			IOException {
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jp = jsonFactory.createJsonParser(new File(
				"E:\\workspace\\SimpleJson\\resources\\simple.json"));
		//JsonGenerator jsonGen = jsonFactory.createJsonGenerator(new FileOutputStream(new File("E:\\workspace\\SimpleJson\\resources\\simple.json")), JsonEncoding.UTF8);
		ObjectMapper mapper =new ObjectMapper();
		jp.setCodec(mapper);
		JsonNode jsonNode = jp.readValueAsTree();
		readJsonData(jsonNode, mapper);
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
