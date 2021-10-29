package com.kaibacorp.heroesapi.core.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.kaibacorp.heroesapi.core.constants.HeroesConstants.DYNAMO_REGION;
import static com.kaibacorp.heroesapi.core.constants.HeroesConstants.DYNAMO_ENDPOINT;

public class HeroesData {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.
                standard().
                withEndpointConfiguration(
                        new AwsClientBuilder.
                        EndpointConfiguration(DYNAMO_ENDPOINT, DYNAMO_REGION)
                ).
                build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Heroes_Table");
        Item hero1 = new Item().
                    withPrimaryKey("id","1").
                    withString("name","Wonder Woman").
                    withString("universe","DC Comics").
                    withNumber("movies",3);

        Item hero2 = new Item().
                withPrimaryKey("id","2").
                withString("name","Spiderman").
                withString("universe","Marvel").
                withNumber("movies",6);

        Item hero3 = new Item().
                withPrimaryKey("id","3").
                withString("name","Batman").
                withString("universe","DC Comics").
                withNumber("movies",5);

        PutItemOutcome outcome1 = table.putItem(hero1);
        PutItemOutcome outcome2 = table.putItem(hero2);
        PutItemOutcome outcome3 = table.putItem(hero3);
    }
}
