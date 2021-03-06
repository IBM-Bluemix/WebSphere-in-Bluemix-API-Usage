// Licensed under the Apache License. See footer for details.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

// Apply an action on a resource. For example, stop or start a virtual machine.
public class ActionResource {
	/* WebSphere Application Server for IBM Cloud API URL.
	 * Available Environments:
	 * Dallas - https://wasaas-broker.us-south.websphereappsvr.cloud.ibm.com/wasaas-broker/api/v1
	 * London - https://wasaas-broker.eu-gb.websphereappsvr.cloud.ibm.com/wasaas-broker/api/v1
	 * Sydney - https://wasaas-broker.au-syd.websphereappsvr.cloud.ibm.com/wasaas-broker/api/v1
	 * Frankfurt - https://wasaas-broker.eu-de.websphereappsvr.cloud.ibm.com/wasaas-broker/api/v1
	 */
	
	private static final String apiEndpoint = "https://wasaas-broker.us-south.websphereappsvr.cloud.ibm.com/wasaas-broker/api/v1";

	public static void main(String[] args) throws IOException{
		// You can see how to get your access token from GetOAuthToken sample class.
		String accessToken = "<YOUR_ACCESS_TOKEN>";
		// The IBM Cloud organization & space to query - case sensitive.
		String org = "<YOUR_ORG>"; // Example: johndoe@ibm.com
		String space = "<YOUR_SPACE>"; // Example: dev
		// You can see how to get the service instance ID from the GetServiceInstances sample class.
		String serviceInstanceID = "<YOUR_SERVICE_INSTANCE_ID>"; // Example: dc8djk2-ddbf-43n33-ba4e-132094dn3imd
		// You can see how to get the resource ID from the GetResources sample class.
		String resourceID = "<YOUR_RESOURCE_ID>"; // Example: 8dsjo03-939jksdf3-93j38f-93jf-39dfji32
		// Query string to stop a virtual machine. Can also be "?action=start"
		String query = "?action=stop";


		// Use TLSv1.2
		System.setProperty("https.protocols", "TLSv1.2");

		// Create the URL.
		URL orgsURL = new URL(apiEndpoint + "/organizations/" + org + "/spaces/" + space + "/serviceinstances/" + serviceInstanceID + "/resources/" + resourceID + query);
		HttpURLConnection con = (HttpURLConnection) orgsURL.openConnection();
		con.setRequestProperty("Authorization", "Bearer " + accessToken);
		con.setRequestMethod("PUT");
		con.setDoOutput(true);

		// Send mock data with PUT to prevent HTTP 411.
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write("mock data");
		out.close();

		BufferedReader br = null;
		if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}
		else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}

		StringBuffer response = new StringBuffer();
		String line;

		while ((line = br.readLine()) != null) {
			response.append(line);
		}
		br.close();

		// Response from the request.
		System.out.println(response.toString());
	}

}
//    ------------------------------------------------------------------------------
//     Licensed under the Apache License, Version 2.0 (the "License");
//     you may not use this file except in compliance with the License.
//     You may obtain a copy of the License at
//
//         http://www.apache.org/licenses/LICENSE-2.0
//
//     Unless required by applicable law or agreed to in writing, software
//     distributed under the License is distributed on an "AS IS" BASIS,
//     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//     See the License for the specific language governing permissions and
//     limitations under the License.
//    ------------------------------------------------------------------------------
