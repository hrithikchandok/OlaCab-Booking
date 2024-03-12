package com.MBD.CabBooking.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class DistanceCalculator {
   
	@Cacheable(value = "distanceCache", key = " #city1.compareTo(#city2)<0 ?  #city1.concat('-').concat(#city2) : #city2.concat('-').concat(#city1)")
	public double Distance(String city1,String city2) {

	    	System.out.println("distance is retrived from DB");
		   RestTemplate restTemplate = new RestTemplate();
			  // Define URL
			        String url = "https://distanceto.p.rapidapi.com/distance/route";
			        
			     // Define HTTP method
			        HttpMethod method = HttpMethod.POST; 
			        
			     // Set headers
			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.APPLICATION_JSON);
			        headers.set("X-RapidAPI-Key", "3bc4b9c0ddmshab1f5eef080c818p1a59ffjsn79d2cb3a2dd7"); 
			        headers.set("X-RapidAPI-Host", "distanceto.p.rapidapi.com");
			        

			        String requestBody = String.format("{\"route\": [{\"name\": \"%s\"}, {\"name\": \"%s\"}]}", city1, city2);
			        
			     // Create HttpEntity with headers and body
			        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
			        
			        // Execute the request
			        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
			        
			     // Process response
			        HttpStatusCode statusCode = responseEntity.getStatusCode();
			        String responseBody = responseEntity.getBody();
			        
//			        System.out.println(responseBody);
			     // Parse JSON response using Jackson
			        ObjectMapper objectMapper = new ObjectMapper();
			        double distance=0.0;
			        double duration=0.0;
			        try {
			            JsonNode jsonNode = objectMapper.readTree(responseBody);

			            // Extract distance and duration from the "car" object within the "route" object
			            JsonNode stepsNode = jsonNode.path("steps").get(0); // Assuming there's only one step
			             distance = stepsNode.path("distance").path("car").path("distance").asDouble();
			             duration = stepsNode.path("distance").path("car").path("duration").asDouble();

//			            System.out.println("Distance: " + distance);
//			            System.out.println("Duration: " + duration);
			            
			        } catch (Exception e) {
			            // Handle JSON parsing exception
			            e.printStackTrace();
			        }
			        

			        return distance;
			        
	}

}
