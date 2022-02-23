package aforo255.com.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import aforo255.com.dao.UserDao;
import aforo255.com.service.UserService;
import ch.qos.logback.core.Context;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer  implements TokenEnhancer{
    @Autowired
    UserService userService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        var userName = userDetail.getUsername();
        addClaims((DefaultOAuth2AccessToken) accessToken, userName);
        return accessToken;
    }

    private void addClaims(DefaultOAuth2AccessToken accessToken, String userName) {
        DefaultOAuth2AccessToken token = accessToken;
        Map<String, Object> additionalInformation = token.getAdditionalInformation();
        if (additionalInformation.isEmpty()) {
            additionalInformation = new LinkedHashMap<String, Object>();
        }

        var user = userService.findByUsername(userName);
        additionalInformation.put("Usuario", user.getUsername());
        additionalInformation.put("apellido", user.getApellido());
        additionalInformation.put("email", user.getEmail());
        additionalInformation.put("nombre", user.getNombre());

        token.setAdditionalInformation(additionalInformation);
    }
}
