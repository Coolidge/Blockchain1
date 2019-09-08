package blockchain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {


        System.out.println("Enter how many zeros the hash must starts with: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Integer riddleComplicity = Integer.valueOf(input);

        BlockChain blockchain = new BlockChain(riddleComplicity);
        blockchain.addBlock();

        boolean valid = blockchain.validateBlocks();

        if(valid) {
            System.out.println(blockchain.toString());
        }
    }
}
