package io.javabrains.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	Bulkhead pattern is used here - separate thread pool for this rest template call
	@HystrixCommand(
			fallbackMethod = "getFallbackUserRating",
//			thread pool name
			threadPoolKey = "userRatingInfo",threadPoolProperties = {
//			size of thread pool
			@HystrixProperty(name = "coreSize",value = "20"),
//			size of waiting queue of this thread pool
			@HystrixProperty(name = "maxQueueSize",value = "10")
	})
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(Arrays.asList(
				new Rating("0", 0)
		));
		return userRating;
	}

}
