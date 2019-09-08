package blockchain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {
     private List<Block> chain;
     int riddleComplicity;

     private static final String FILE_NAME = "./chain.obj";

     public BlockChain(int riddleComplicity) throws Exception {

        File file = new File(FILE_NAME);

        if(!file.exists()) {
            file.createNewFile();
            this.chain = new ArrayList<Block>();
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            this.chain = (List<Block>) objectIn.readObject();
            objectIn.close();
            fileIn.close();

            if(!validateBlocks()) {
                throw new Exception();
            }
        }

         this.riddleComplicity = riddleComplicity;
     }

     public void addBlock() throws Exception {

         int lastIndex = chain.size();
         String preBlockHash = null;
         Block preBlock = null;
         int id = 1;
         if(lastIndex == 0) {
             preBlockHash = "0";
         } else {
             preBlock = chain.get(chain.size()-1);
             preBlockHash = preBlock.getCurrentHash();
             id = preBlock.getId()+1;
         }
         Block block = new Block(id, preBlockHash, this.riddleComplicity);

         chain.add(block);

         persist();
     }

     private void persist() throws IOException {
         FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(this.chain);
         objectOut.close();
         fileOut.close();
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
             str.append("\nBlock:");
             str.append(block.toString());
         }

         return str.toString();
     }
}
