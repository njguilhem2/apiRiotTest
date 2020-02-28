package br.com.lolstatis.integrator.integrator.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.StringJoiner;

@Configuration
public class IntegratorRestTemplate extends RestTemplate{

    private static final String URL = "https://br1.api.riotgames.com/lol";
    private static final String HEADER_NAME_TOKEN = "X-Riot-Token";
    private static final String HEADER_TOKEN = "RGAPI-eade7ce0-13d2-416a-bb5c-e6b7d8d5407a";

    @Bean
    public RestTemplate restTemplate()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
    public String uriRanked(String queue, String tier, String division){
        return new StringJoiner("/")
                .add(URL)
                .add("league-exp/v4/entries")
                .add(queue)
                .add(tier)
                .add(division).toString();
    }
    public String uriRotation(){
        return new StringJoiner("/").add(URL).add("platform/v3/champion-rotations").toString();
    }
    public HttpEntity<String> riotToken() {
       HttpHeaders header = new HttpHeaders();
       header.set(HEADER_NAME_TOKEN, HEADER_TOKEN);
       return new HttpEntity<String>("parameters", header);
    }
}
