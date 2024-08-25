package com.ai.first_ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.core.credential.AzureKeyCredential;

@Service
public class TextAnalyticsService {

    private final TextAnalyticsClient client;

    public TextAnalyticsService(@Value("${azure.textanalytics.endpoint}") String endpoint,
                                @Value("${azure.textanalytics.api_key}") String apiKey) {
        this.client = new TextAnalyticsClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(apiKey))
              //  .apiKey(apiKey)
                .buildClient();
    }

    public String analyzeSentiment(String text) {
    	DocumentSentiment result = client.analyzeSentiment(text);
        return result.getSentiment().toString();
    }
}

