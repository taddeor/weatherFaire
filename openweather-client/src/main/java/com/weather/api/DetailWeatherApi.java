package com.weather.api;

import com.weather.api.invoker.ApiInvoker;
import com.weather.bean.detail.WeatherDetail;
import com.weather.bean.weather.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&APPID=b380f3b1889768d06b34e25ca7bec57e&units=metric
@RequiredArgsConstructor
@Component
public class DetailWeatherApi {
    private final ApiInvoker apiInvoker;
    @Value("${secret.api.key}")
    private String apiKey;

    public WeatherDetail detailCityWeather(Double lon,Double lat,List<String> exclude){
        Object postBody = null;

        // verify the required parameter 'city' is set
        if (lon == null || null == lat ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'city' when calling getClinicByCodeUsingGET");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        String path = UriComponentsBuilder.fromPath("data/2.5/onecall").toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiInvoker.parameterToMultiValueMap(null, "lat", lat));
        queryParams.putAll(apiInvoker.parameterToMultiValueMap(null, "lon", lon));
        queryParams.putAll(apiInvoker.parameterToMultiValueMap(null, "exclude", exclude));
        queryParams.putAll(apiInvoker.parameterToMultiValueMap(null, "units", "metric"));
        queryParams.putAll(apiInvoker.parameterToMultiValueMap(null, "APPID", apiKey));

        final String[] accepts = {
                "application/json"
        };
        final List<MediaType> accept = apiInvoker.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiInvoker.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<WeatherDetail> returnType = new ParameterizedTypeReference<WeatherDetail>() {};
        var weather = apiInvoker.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
        return weather;
    }
}
