package edu.ucsb.cs56.pconrad;

import static spark.Spark.port;

import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;

/**
 * Simple example of using Mustache Templates
 *
 */

public class SparkMustacheDemo01 {

	public static final String CLASSNAME="SparkMustacheDemo01";
	
	public static final Logger log = Logger.getLogger(CLASSNAME);


	public static class Interest {
		private String interest;
		public Interest (String interest) {
			this.interest=interest;
		}
		public String getInterest() { return this.interest;}
	}
	
	public static void main(String[] args) {

        port(getHerokuAssignedPort());
		
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "Sam");

		java.util.ArrayList<Interest> interests = new java.util.ArrayList<Interest>();

		interests.add(new Interest("Weather"));
		interests.add(new Interest("Food"));
		interests.add(new Interest("Location"));
		interests.add(new Interest("Traffic"));

		map.put("interests",interests);
		
        // hello.mustache file is in resources/templates directory
        get("/", (rq, rs) -> new ModelAndView(map, "hello.mustache"),
			new MustacheTemplateEngine());
		
	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

	
}
