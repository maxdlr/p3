package Security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

public class SecurityConstants {
    @Value("${api.jwtExpirationMs}")
    public static int jwtExpiration = 900000000;

    public static final SecretKey JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
