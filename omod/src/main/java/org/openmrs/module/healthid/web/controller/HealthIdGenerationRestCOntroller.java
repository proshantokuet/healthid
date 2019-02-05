package org.openmrs.module.healthid.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/rest/v1/healthid")
@RestController
public class HealthIdGenerationRestCOntroller extends MainResourceController {
	@RequestMapping(value = "/reserved", method = RequestMethod.GET)
	public ResponseEntity<String> getResearvedHealthId() throws Exception {
		/*HttpResponse op1 = HttpUtil.get(opensrpWebUurl + "/rest/api/v1/health-id/reserved/single", "",
			    opensrpWebUsername, opensrpWebPassword);
			JSONObject healthObj = new JSONObject(op1.body());
		return new ResponseEntity<String>("No Data Found", HttpStatus.OK);*/
		
		StringBuffer content = new StringBuffer();
		try {

			URL url = new URL("http://192.168.22.249:8080/opensrp-dashboard/rest/api/v1/health-id/reserved/single");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String inputLine;			
			while ((inputLine = br.readLine()) != null) {
				content.append(inputLine);
			}			
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		
		return new ResponseEntity<String>(content.toString(), HttpStatus.OK);
	}	

}
