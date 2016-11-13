package com.twitter.service.bridge;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.twitter.dao.TweetDao;

@Path("/TwitterTopics/")
public class TwitterTopicsServiceBridge {
	
	@GET
	@Produces("application/json")
	@Path("topicDesc")
	public Response getTopicsDiscussed(@Context HttpServletRequest request) throws ParseException, JsonGenerationException, JsonMappingException, IllegalArgumentException, UriBuilderException, IOException {
		String dateStr = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(sdf.parse(dateStr).getTime());
		ObjectMapper mapper = new ObjectMapper();
		URI uri = UriBuilder.fromPath("http://localhost:8081/twitterStreamDemo/tweetTopic.jsp")
	            .queryParam("message", mapper.writeValueAsString(TweetDao.getTopicsDiscussed(date)))
	            .build();
	    return Response.seeOther(uri).build();
	}

}
