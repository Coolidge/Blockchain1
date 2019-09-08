package blockchain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Block implements Serializable {
    private int id;
    private long timestamp;
    private long magicNumber;
    private String preBlock;
    private String currentHash;
    private long durationInSeconds;

    public Block(int id, String preBlockHash, int riddleComplicity) {
        this.timestamp = new Date().getTime();
        this.id = id;
        this.preBlock = preBlockHash;
        solveBlock(riddleComplicity);
    }

    private void solveBlock(int riddleComplicity) {

        String zeros = String.format("%" + riddleComplicity + "s","").replace(' ', '0');
        String hash = "";
        boolean foundMagic = false;
        Random random = new Random();
        while(!foundMagic) {
            this.magicNumber = Math.abs(random.nextLong());
            hash = StringUtil.applySha256(this.toString());
            if(hash.startsWith(zeros)){
                this.currentHash = hash;
                this.durationInSeconds = ((new Date().getTime())-this.timestamp)/1000;
                foundMagic = true;
            }
        }
    }

    public int getId() {
        return this.id;
    }

    public String getPreBlock() {
        return this.preBlock;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("\nId: ").append(this.id);
        str.append("\nTimestamp: ").append(this.timestamp);
        str.append("\nMagic number: ").append(this.magicNumber);
        str.append("\nHash of the previous block: \n").append(this.preBlock);
        str.append("\nHash of the block: \n").append(this.currentHash);
        str.append("\nBlock was generating for ").append(this.durationInSeconds).append(" seconds\n");

        return str.toString();
    }

}
