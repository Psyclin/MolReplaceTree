/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package molreplacetree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author psyco
 */
public class TreeBuilder {
    
    private ArrayList<MoleculeNode> nodeList = new ArrayList();
    private WebHandler dataCollector = new WebHandler();
    
    public TreeBuilder(){
        
    }
    
    public MoleculeNode makeNode(String pdbCode) throws MalformedURLException, ProtocolException, IOException {       
        return new MoleculeNode(pdbCode, dataCollector.webRequest(pdbCode));
    }
    
   
    public void returnNodeList(){
        for(MoleculeNode node : nodeList){
            System.out.println(node.getPdbCode());                    
        }
    }
    public MoleculeNode backTrace(String pdbCode, MoleculeNode node)throws MalformedURLException, ProtocolException, IOException{
        if (pdbCode == null){
            return node;            
        } 
        MoleculeNode currentNode = makeNode(pdbCode);
        currentNode.addChild(node);
        return backTrace(currentNode.findParent(), currentNode);        
    }
    
    public void buildTree(MoleculeNode theAncientOne){
        MoleculeNode currentNode = theAncientOne;
        
        while(!currentNode.getChildList().isEmpty()){
            nodeList.add(currentNode);
            currentNode = currentNode.getChildList().get(0);
        }        
    }
    
    public void exportToFreeMind() throws FileNotFoundException, UnsupportedEncodingException{
        StringBuilder fileString = new StringBuilder();
        MoleculeNode currentNode = nodeList.get(0);
        int nodeCounter = 0;
        while(!currentNode.getChildList().isEmpty()){
            fileString.append(cleanNode(currentNode));
            currentNode = currentNode.getChildList().get(0);
            nodeCounter++;
        }
        while(nodeCounter != 0){
            fileString.append("</node>");
            nodeCounter--;        
        }                
        PrintWriter writer = new PrintWriter("test.mm", "UTF-8");
        writer.append(fileString.toString());
        writer.close();                                                    
    }   
    
    public String cleanNode(MoleculeNode node){
        StringBuilder nodeText = new StringBuilder();
        nodeText.append("<node TEXT=\"");
        nodeText.append(node.getPdbCode());
        nodeText.append("\">");
        return nodeText.toString();
    }
    }
}
