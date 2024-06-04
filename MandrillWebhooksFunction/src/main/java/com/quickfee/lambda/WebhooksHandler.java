package com.quickfee.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Mandrill Webhooks Handler for requests to Lambda function.
 */
public class WebhooksHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String requestBody = URLDecoder.decode(input.getBody());
        LambdaLogger logger = context.getLogger();
        logger.log("Handling HTTP POST request for the /webhooks with request body: " + requestBody);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.withStatusCode(200);
        response.withBody(requestBody);
        response.withHeaders(headers);

        return response;
    }

}
