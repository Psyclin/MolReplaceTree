/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package molreplacetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author psyco
 */
public class WebHandler {
    
    public WebHandler(){
    
    }
    
    public String webRequest(String pdbCode) throws MalformedURLException, IOException, ProtocolException{
        StringBuilder urlName = new StringBuilder();
        StringBuilder pdbData = new StringBuilder();
        String inputLine;        
        
        urlName.append("https://files.rcsb.org/download/");
        urlName.append(pdbCode);
        urlName.append(".pdb");        
        
        URL pdbSite = new URL(urlName.toString());
        HttpURLConnection connection = (HttpURLConnection) pdbSite.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));                        
        while ((inputLine = input.readLine()) != null) {
            pdbData.append(inputLine);
            pdbData.append("\n");
        }
        input.close();
        connection.disconnect();    
        return pdbData.toString();
    }
}
