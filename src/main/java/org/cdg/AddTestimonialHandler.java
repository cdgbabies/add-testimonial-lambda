package org.cdg;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cdg.bean.RequestParameters;
import org.cdg.bean.Testimonial;
import org.cdg.exception.InvalidArgsException;
import org.cdg.service.TestimonialService;

import static org.cdg.util.Constants.HttpMethods;
// mvn clean package shade:shade
public class AddTestimonialHandler implements RequestHandler<RequestParameters, String> {

    private TestimonialService testimonialService;
   private Regions REGION = Regions.fromName(System.getenv("region_name"));
   //private Regions REGION = Regions.fromName("us-east-1");
    private AmazonDynamoDB amazonDynamoDB= AmazonDynamoDBClientBuilder.standard()
            .withRegion(REGION)
            .build();
    public AddTestimonialHandler(){
        this.testimonialService= new TestimonialService(amazonDynamoDB);
    }
    public AddTestimonialHandler(TestimonialService testimonialService){
        this.testimonialService=testimonialService;
    }





    public String handleRequest(RequestParameters requestParam, Context context) {

        context.getLogger().log("Input: " + requestParam);
        try{

            return handleRequestMethods(testimonialService,requestParam, context.getLogger());

        }catch(Exception e){
            context.getLogger().log(e.getMessage());
            throw new InvalidArgsException(e.getMessage());

        }


    }

    private String handleRequestMethods(TestimonialService testimonialService,RequestParameters requestParameters,LambdaLogger logger) throws JsonProcessingException {
        HttpMethods method = HttpMethods.fromName(requestParameters.getRequestContext().getHttp().get("method"));
        ObjectMapper objectMapper = new ObjectMapper();

           if(method.equals(HttpMethods.POST)) {
               Testimonial testimonialItem = objectMapper.readValue(requestParameters.getBody(), Testimonial.class);
            /*   if(StringUtils.isNullOrEmpty(testimonialItem.getTestimonial())&& StringUtils.isNullOrEmpty(testimonialItem.getAuthor()))
                   throw new IllegalArgumentException("Testimonial and Author can't be empty");*/
               testimonialService.createTestimonial(testimonialItem, logger);

               return "Added Testimonial";
           }




       throw new RuntimeException("Not Supported Method");
    }



   /* private void initDynamoDbClient() {
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion(REGION)
                .build();
    }*/
}