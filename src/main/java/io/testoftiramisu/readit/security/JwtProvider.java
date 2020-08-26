package io.testoftiramisu.readit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.testoftiramisu.readit.exceptions.ReaditExeption;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {
  private KeyStore keyStore;

  /*
   * In order to generate the keystore:
   * keytool -genkey -keystore readit.jks -alias readit -keyalg RSA -sigalg SHA256withRSA -keysize 2048
   */

  @PostConstruct
  public void init() {
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream inputStream = getClass().getResourceAsStream("/readit.jks");
      keyStore.load(inputStream, "secret".toCharArray());
    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException ex) {
      throw new ReaditExeption("Exception occurred while loading a keystore.");
    }
  }

  public String generateToken(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();
    return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).compact();
  }

  public boolean validateToken(String jwt) {
    parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
    return true;
  }

  public String getUsernameFromJwt(String token) {
    Claims claims = parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  private PublicKey getPublicKey() {
    try {
      return keyStore.getCertificate("readit").getPublicKey();
    } catch (KeyStoreException ex) {
      throw new ReaditExeption("Exception occurred while retrieving public key from a keystore.");
    }
  }

  private PrivateKey getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("readit", "secret".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException ex) {
      throw new ReaditExeption("Exception occurred while retrieving private key from a keystore.");
    }
  }
}
