package com.javainuse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.URISyntaxException;
import java.net.URLEncoder;

class GetExample {
  OkHttpClient client = new OkHttpClient();

  String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
}

@Controller
public class TestController {

	public String siteResponse () throws IOException {
	    GetExample example = new GetExample();
	    String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
	    System.out.println(response);

	    return response;
	}

	@RequestMapping("/welcome.html")
	public ModelAndView firstPage() {
		ModelAndView modelAndView = new ModelAndView("welcome");
	    modelAndView.addObject("message", "Wonderful Life!!!");
	    
	    try {
	    	String siteInfo = siteResponse();
			modelAndView.addObject("site", URLEncoder.encode(siteInfo, "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}


}
