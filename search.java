package searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;
public class search {
    String search;
    Hashtable<String,wordRank[]> multiple;
    
    String[] words,searches;
    
    search(String search){
        this.search=search;
    }
    
    public void cleanSearch(){
        String[] uw={"under","will","would","shall","aux","could","with","then","around","through","these","is","the","th","who","into","and","as","its","each","which","can","also","those","for","there","to","have","has","of","than","off","them","in","it","on","at","an","other","all","some","that","from","was","this","were","by","when","or","a","be","such","not","do","but","his","are"};
        search=search.replaceAll("[^a-zA-Z0-9.\\s]|\\.(?!\\d)|(?<!\\w)-(?!\\w)|(?<!\\w)_(?!\\w)|\\-(?!\\d)", "");
        search=search.toLowerCase();
        search=search.replaceAll("\\s{2,}", " ").trim();
        search=search.replaceAll("_"," ");
        search=search.replaceAll("-"," ");
        searches=search.split(" ");
    }
    public wordRank[] doSearch() throws IOException{
        cleanSearch();
        if(searches.length==1 && !search.isEmpty()){
            System.out.println("Single");
            long start = System.currentTimeMillis();
            wordRank[] array3=oneSearch(search);
            long end = System.currentTimeMillis();
            System.out.println("Execution time was "+(end-start)+" milliseconds.");
            if(array3!=null){
                int i=0;
                for(wordRank key:array3){
                    System.out.println(key.getWord()+" "+key.getPos());
                }
            }else{
                System.out.println("Word Not Found");
                return null;
            }
            return array3;
        }else if(searches.length>1){
            System.out.println("Multiple");
            ArrayList<String> rd=new ArrayList<>(Arrays.asList(searches));
            rd=removeDuplicates(rd);
            searches=rd.toArray(new String[rd.size()]);
            long start = System.currentTimeMillis();
            wordRank[] array3=multipleSearch();
            long end = System.currentTimeMillis();
            if(array3!=null){
                for(wordRank key:array3){
                    System.out.println(key.getWord()+" "+key.getPos());
                }
            }else{
                System.out.println("Word Not Found");
                return null;
            }    
            System.out.println("Execution time was "+(end-start)+" milliseconds.");
            return array3;
        }else{
            System.out.println("Please Enter valid text.");
            return null;
        }
        
    }
    
    public wordRank[] multipleSearch() throws IOException{
        multiple=new <String,wordRank[]> Hashtable();
        for (String s:searches){
            wordRank[] array5=oneSearch(s);
            if(array5!=null)
                multiple.put(s,array5);
        }
        Set<String> keys = multiple.keySet();
        if(keys.size()>0){
            ArrayList<wordRank[]> array6=new <wordRank[]> ArrayList();
            for(String key:keys){
                array6.add(multiple.get(key));
            }
            wordRank[][] array7=new wordRank[array6.size()][];
            for (int i = 0; i < array6.size(); i++) {
                array7[i] = array6.get(i);
            }
            Hashtable<String,Float> result=new <String,Float>Hashtable();
            for(int i=0;i<array7.length;i++){
                for (int j=0;j<array7[i].length;j++){
                    if(result.containsKey(array7[i][j].getWord())){
                        array7[i][j].setPos(array7[i][j].getPos()+result.get(array7[i][j].getWord()));
                    }
                    result.put(array7[i][j].getWord(), array7[i][j].getPos());
                }
            }
            ArrayList<wordRank> array8=new <wordRank> ArrayList();
            Set<String> keys2=result.keySet();
            for(String key:keys2){
                array8.add(new wordRank(result.get(key),key));
            }
            for(int i=0;i<array8.size()-1;i++){
                for(int j=i+1;j<array8.size();j++){
                    if(array8.get(i).getWord().equals(array8.get(j).getWord())){
                        array8.get(i).setPos(array8.get(i).getPos()+array8.get(j).getPos());
                        array8.remove(j);
                    }
                }
            }
            
            wordRank temp;
            wordRank[] array9 = array8.toArray(new wordRank[array8.size()]);
            for (int i=0;i<array9.length-1;i++){
                for(int j=1;j<array9.length-i;j++){
                    if(array9[j-1].getPos()<array9[j].getPos()){
                        temp=array9[j];
                        array9[j]=array9[j-1];
                        array9[j-1]=temp;
                    }
                }
            }
            return array9;
        }else{
            return null;
        }
    }
    
    public wordRank[] oneSearch(String search) throws FileNotFoundException, IOException{
        ArrayList<wordRank> array=new ArrayList();
        String result="";
        
        File file = new File("D:\\Project data\\backward2\\"+search+".txt");
        if(file.exists() && !file.isDirectory()) { 
            //System.out.println("I'm running");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String lineOfText=br.readLine();
            while(lineOfText!=null){
                lineOfText=lineOfText.trim();
                if(!lineOfText.isEmpty()){
                    words=lineOfText.split(" ");
                    float rank=0;
                    for (int i=0;i<words.length;i++){
                        if(i==words.length-1){
                            rank=Float.valueOf(words[i]);
                        }
                        else
                            result+=" "+words[i];
                    }
                    array.add(new wordRank(rank,result.trim()));
                }

                lineOfText=br.readLine();
                result="";   
            }
            wordRank temp;
            wordRank[] array2 = array.toArray(new wordRank[array.size()]);
            for (int i=0;i<array2.length-1;i++){
                for(int j=1;j<array2.length-i;j++){
                    if(array2[j-1].getPos()<array2[j].getPos()){
                        temp=array2[j];
                        array2[j]=array2[j-1];
                        array2[j-1]=temp;
                    }
                }
            }
            return array2;
        }else{
            System.gc();
            return null;
        }
        
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) //removeing the duplicate words
    {
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 

        // Traverse through the first list 
        for (T element : list) { 
  
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    }
    public String article(String title) throws IOException{
        File file = new File("D:\\Project data\\corpus.txt");
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String title2,text;
        text=br.readLine();
        String output="";
        while(text!=null){
            //System.out.println("while");
            title2=text;
            text=br.readLine();
            while(text!=null && !text.isEmpty()){
                output+=text+"\n";
                text=br.readLine();
            }
            //System.out.println(title2);
            if(title2.toLowerCase().trim().equals(title.toLowerCase().trim()))
                return output;
            output="";
            if(text!=null)
                text=br.readLine();
        }
        return null;
    }
}

