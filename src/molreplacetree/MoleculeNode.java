/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package molreplacetree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author psyco
 */
public class MoleculeNode {
    
    private String pdbCode = null;
    private String pdbData = null;
    private ArrayList<MoleculeNode> childList = new ArrayList();
    
    public MoleculeNode(String pdbCode, String pdbData){      
        this.pdbCode = pdbCode;        
        this.pdbData = pdbData;
    }


    /**
     * @return the pdbCode
     */
    public String getPdbCode() {
        return pdbCode;
    }

    /**
     * @return the childList
     */
    public ArrayList<MoleculeNode> getChildList() {
        return childList;
    }

    /**
     * @param child
     */
    public void addChild(MoleculeNode child) {
        this.childList.add(child);
    }
    
    public String findParent(){
        Scanner dataReader = new Scanner(this.pdbData);        
        String parent = null;
        
        while(dataReader.hasNextLine()){
            parent = dataReader.nextLine().toString().trim();
            if (parent.contains("STARTING MODEL:")){
                parent = parent.substring(parent.length()- 4, parent.length());                          
                if(!parent.substring(parent.length()- 4, parent.length()).equals("NULL")){
                   return parent;
               }
            }
        }
        return null;
    }
}
