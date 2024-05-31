import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import com.microsoft.graph.authentication.IAuthenticationProvider; 
import com.microsoft.graph.models.SendMailParameters; 
import com.microsoft.graph.requests.GraphServiceClient; 
import com.microsoft.graph.requests.UserSendMailRequestBuilder; 

@Service 
public class MSGraphEmailProvider {    

    @Autowired 
    private IAuthenticationProvider authenticationProvider;  

    public void sendEmail(String recipient, String subject, String body) { 
        
      GraphServiceClient<Request> graphClient = GraphServiceClient.builder().authenticationProvider(authenticationProvider).buildClient(); 

        SendMailParameters parameters = new SendMailParameters(); 
        parameters.message.setSubject(subject); 
        parameters.message.setBody(new ItemBody().content(body)); 
        parameters.message.setToRecipients(Collections.singletonList(new Recipient().emailAddress(new EmailAddress().address(recipient)))); 

        UserSendMailRequestBuilder requestBuilder = graphClient.me().sendMail(parameters); 
        requestBuilder.buildRequest().post(); 
    } 
} 
