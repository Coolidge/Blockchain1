package blockchain;

public class Main {
    public static void main(String[] args) {
        BlockChain blockchain = new BlockChain();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();

        boolean valid = blockchain.validateBlocks();

        if(valid) {
            System.out.println(blockchain.toString());
        }
    }
}
