package org.reservahoteles.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Utilidad para codificar contraseñas utilizando el algoritmo BCrypt.
 */
@Component
public class PasswordBCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    /**
     * Codifica una contraseña utilizando el algoritmo BCrypt.
     *
     * @param password la contraseña a codificar
     * @return la contraseña codificada
    */
    public String encodePassword(String password) {
        return encoder.encode(password);
    }

}
