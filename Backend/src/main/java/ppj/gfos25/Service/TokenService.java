/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Service;

import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Facades.AccountFacade;

import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class TokenService {

    public class TokenEmail {
        public String token;
        public String email;

        public TokenEmail(String token, String email) {
            this.token = token;
            this.email = email;
        }
    }

    private final String SECRET = "R{,hA:>xYSPLvN~>R\\pz*3feN7EgpM`kfF1e=^22AxnpPI"; // TOPSECRET! Wenn jemand das hier
                                                                                     // liest, dann sind wir rechtlich
                                                                                     // am Arsch.
    private final long DT = 600_000; // Token 600 Sekunden gültig

    @EJB
    private AccountFacade accountFacade;

    public String createNewToken(String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + DT);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withIssuer("GFOSProjekt")
                .withSubject(email)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
        return token;
    }

    public String createNewRefreshToken() {
        SecureRandom sr = new SecureRandom();        
        byte[] bytes = new byte[32]; // 256 bit
        sr.nextBytes(bytes); 
        String token = Hex.encodeHexString(bytes);
        return token;
    }

    public TokenEmail verifyToken(String token) {
        if (token == null) {
            return null;
        }
        if (token.substring(0, 7).equals("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println("Token: " + token);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Date now = new Date();
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("GFOSProjekt")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Date expirationDate = jwt.getExpiresAt();
            if (now.before(expirationDate)) {
                return new TokenEmail(token, jwt.getSubject());
            }
            if (now.getTime() - expirationDate.getTime() < DT) {
                return new TokenEmail(this.createNewToken(jwt.getSubject()), jwt.getSubject());
            }
            return null;
        } catch (JWTVerificationException exception) {
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    // public TokenEmail verifyToken(String token) {
    // try {
    // long t = (System.currentTimeMillis() / DT) * DT; // rundet die aktuelle Zeit
    // auf das nächste DT ab
    // Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
    // JWTVerifier verifier = JWT.require(algorithm)
    // .withIssuer("GFOSProjekt")
    // .build();
    // DecodedJWT jwt = verifier.verify(token);
    // return new TokenEmail(token, jwt.getSubject()); // das gültige Token wieder
    // zurückgeben.
    // } catch (JWTVerificationException ex1){
    // try {
    // // Wenn altes Token gerade (innerhalb eines Zeitfensters von
    // // 2*DT) abgelaufen, automatisch um DT Milisekunden erneuern:
    // long t = (System.currentTimeMillis() / DT) * DT - DT;
    // Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
    // JWTVerifier verifier = JWT.require(algorithm)
    // .withIssuer("GFOSProjekt")
    // .build();
    // DecodedJWT jwt = verifier.verify(token);
    // return new TokenEmail(this.createNewToken(jwt.getSubject()),
    // jwt.getSubject());
    // } catch(JWTVerificationException ex2) {
    // return new TokenEmail(null, null); // altes Token zu lange (> 2*DT)
    // abgelaufen
    // }
    // }
    // }

    public Account getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return accountFacade.getAccountByEmail(jwt.getSubject());
        } catch (JWTDecodeException exception) {
            return null;
        }
    }
}
