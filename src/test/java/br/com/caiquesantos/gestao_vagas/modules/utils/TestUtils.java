package br.com.caiquesantos.gestao_vagas.modules.utils;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.time.*;
import java.util.*;

public class TestUtils {
    public static String objectToString(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withSubject(idCompany.toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return token;
    }
}
