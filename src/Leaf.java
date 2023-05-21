public class Leaf extends Node{
    private char character;
    public Leaf(char character, int f) {
        super(null,null,f);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
