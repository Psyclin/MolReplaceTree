/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package molreplacetree;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 *
 * @author psyco
 */
public class MolReplaceTree {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     * @throws java.net.ProtocolException
     */
    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
       
        TreeBuilder treeBuilder = new TreeBuilder();
        MoleculeNode starterNode = treeBuilder.makeNode("3NYH");
        MoleculeNode theKing = treeBuilder.backTrace(starterNode.findParent(), starterNode);
        treeBuilder.buildTree(theKing);
        treeBuilder.exportToFreeMind();
    }
    
}
