package fontys.sem3.it.ticketstore.model.request;

import lombok.Data;
@Data
public class AuthenticationRequest {
        private String username;
        private String password;
}
