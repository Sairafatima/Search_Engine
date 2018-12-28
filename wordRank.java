package searchengine;

public class wordRank {
    private float pos;
    private String word;
    wordRank(float pos, String word){
        this.pos=pos;
        this.word=word;
    }
    float getPos(){
        return this.pos;
    }
    void setPos(float pos){
        this.pos=pos;
    }
    String getWord(){
        return this.word;
    }
}
