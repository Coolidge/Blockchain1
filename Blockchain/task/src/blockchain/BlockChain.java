package blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
     private List<Block> chain;

     public BlockChain() {
         chain = new ArrayList<Block>();
     }

     public void addBlock() {
         Block block = new Block();
         int lastIndex = chain.size();
         String preBlock = null;
         if(lastIndex == 0) {
             preBlock = "0";
         } else {
             preBlock = chain.get(chain.size()-1).getCurrentHash();
         }
         block.setPreBlock(preBlock);
         block.setCurrentHash();
     }

     public boolean validateBlocks() {

        String preBlock = "0";
         for (Block block: chain) {
            if(!preBlock.equals(block.getPreBlock())) {
                return false;
            }
            preBlock = block.getCurrentHash();
         }

         return true;
     }

     public String toString() {
         StringBuffer str = new StringBuffer();

         for (Block block: chain) {
             str.append("\nBlock:\n");
             str.append(block.toString());
         }

         return str.toString();
     }
}
