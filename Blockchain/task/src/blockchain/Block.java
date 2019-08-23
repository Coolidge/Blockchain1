package blockchain;

import java.util.Date;

public class Block {
    static Integer lastIndex = 0;
    int id;
    long timestamp;
    String preBlock;
    String currentHash;

    public Block() {
        this.id = ++lastIndex;
        this.timestamp = new Date().getTime();
    }

    public void setPreBlock(String preBlock) {
        this.preBlock = preBlock;
    }
    public String getPreBlock() {
        return this.preBlock;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash() {
        this.currentHash = StringUtil.applySha256(this.toString());
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("\nId: ").append(this.id);
        str.append("\nTimestamp: ").append(this.timestamp);
        str.append("\nHash of the previous block: \n").append(this.preBlock);
        str.append("\nHash of the block: ").append(this.currentHash).append("\n");

        return str.toString();
    }

}
