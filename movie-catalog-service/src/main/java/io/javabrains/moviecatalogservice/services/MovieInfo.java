package io.javabrains.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	Circuit breaker is used here - to break the circuit for this rest template call
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",commandProperties = {
//			After how much time, circuit will break and get timed-out
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//			Max threshold volume, it can handle
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
//			Max threshold volume, after which circuit will break
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "3"),
//			After how much time, circuit will be back to normal after breaking
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class); 
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}

}
