package org.cdg.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.cdg.bean.Testimonial;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestimonialService {

    private String DYNAMODB_TABLE_NAME = System.getenv("table_name");

    private AmazonDynamoDB amazonDynamoDB;



    public TestimonialService(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;

    }

    public void createTestimonial(Testimonial reviewItem, LambdaLogger logger) {
        Map<String, AttributeValue> attributesMap = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        logger.log("Inserting ID:==" + uuid);


        attributesMap.put("pk", new AttributeValue(""));
        attributesMap.put("author", new AttributeValue(reviewItem.getAuthor()));
        attributesMap.put("testimonial", new AttributeValue(reviewItem.getTestimonial()));
        attributesMap.put("approved", new AttributeValue("N"));

        amazonDynamoDB.putItem(DYNAMODB_TABLE_NAME, attributesMap);

    }




}
