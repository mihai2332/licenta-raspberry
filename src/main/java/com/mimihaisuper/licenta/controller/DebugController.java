package com.mimihaisuper.licenta.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.mimihaisuper.licenta.repository.SensorMQ135Repository;
import com.mimihaisuper.licenta.services.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/ping")
public class DebugController {
    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Autowired
    PersistenceService persistenceService;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Object> getPing() {
//        createTable();
        return ResponseEntity.ok("tableCreated");
    }

    @GetMapping(value = "/record")
    public ResponseEntity<Object> postRecord(){
        persistenceService.postMQ135Message("noua valoare");
        return  ResponseEntity.ok("recordCreated");
    }



    private void createTable() {
        String tableName = "SensorMQ135";
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH), // Partition key
                            new KeySchemaElement("date", KeyType.RANGE)), // Sort key
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S),
                            new AttributeDefinition("date", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
    }
}
