import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static HashMap<Character,Integer> frequencies=new HashMap<>();//stores characters and the number of times they appear in text
    static HashMap<Character,String> codes=new HashMap<>();//stores huffman codes

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("world192.txt"));
        String text="";
        while (sc.hasNextLine()){
           text=text+sc.nextLine();
           if(sc.hasNextLine()){text=text+"\n";}
        }
        for(char c:text.toCharArray()){
            if(frequencies.containsKey(c)){
                int integer=frequencies.get(c)+1;
                frequencies.replace(c,integer);
            }else {
                frequencies.put(c,1);
            }
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter 1 to encode or 2 to decode");
        String input=scanner.nextLine();
        Node root=null;String encodedText="";
        while(!input.equals("-1")){
        if(input.equals("1")){
            Queue<Node> q=new PriorityQueue<>();
            frequencies.forEach(((character, f) -> q.add(new Leaf(character,f))));
            while (q.size()>=2){
                Node node=q.poll();
                Node node1=q.poll();
                q.add(new Node(node,node1,node.getF()+node1.getF()));
            }
            root=q.poll();
            generateAndStoreCode(root,"");
            encodedText=getEncoded(text);
            File encodedFile=new File("world192.txt");
            encodedFile.createNewFile();
            FileWriter fileWriter=new FileWriter("world192.txt");
            fileWriter.write(encodedText);
            fileWriter.close();
        }
        else{
            Node currentNode=root;
            StringBuilder stringBuilder=new StringBuilder();
            for (char c:encodedText.toCharArray()) {
                if(c=='0'){currentNode=currentNode.getLeft();}else {currentNode=currentNode.getRight();}
                if(currentNode instanceof Leaf){
                    stringBuilder.append(((Leaf) currentNode).getCharacter());
                    currentNode=root;
                }
            }
            File decodedFile=new File("world192.txt");
            decodedFile.createNewFile();
            FileWriter fileWriter=new FileWriter("world192.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        }
            System.out.println("Enter 1 to encode or 2 to decode or -1 to exit");
            input=scanner.nextLine();
        }

    }
    static String getEncoded(String text){
        StringBuilder stringBuilder=new StringBuilder();
        for (char c:text.toCharArray()) {
            stringBuilder.append(codes.get(c));
        }
        return stringBuilder.toString();
    }
    static void generateAndStoreCode(Node n,String c){
        if(n instanceof Leaf){
            codes.put(((Leaf) n).getCharacter(),c);return;
        }
        generateAndStoreCode(n.getRight(),c.concat("1"));
        generateAndStoreCode(n.getLeft(),c.concat("0"));
    }
}