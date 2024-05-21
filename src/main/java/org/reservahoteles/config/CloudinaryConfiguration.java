package org.reservahoteles.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class CloudinaryConfiguration {

    @Value("${cloud.name}")
    private String cloudName;

    @Value("${cloud.key}")
    private String apiKey;

    @Value("${cloud.secret}")
    private String apiSecret;

    @Value("${cloud.url}")
    private String cloudinaryUrl;

    @Bean
    public Cloudinary cloudinary() {
        log.info("cloud_name :: " + cloudName);
//        Dotenv dotenv = Dotenv.load();
//        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
//        cloudinary.config.secure = true;
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);

        Cloudinary cloudinary = Singleton.getCloudinary();
        cloudinary.config.apiKey = apiKey;
        cloudinary.config.apiSecret = apiSecret;
        cloudinary.config.cloudName = cloudName;

//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", cloudName,
//                "api_key", apiKey,
//                "api_secret", apiSecret));
//        SingletonManager manager = new SingletonManager();
//        manager.setCloudinary(cloudinary);
//        manager.init();
        return cloudinary;
    }
}
