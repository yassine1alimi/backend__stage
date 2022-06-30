package com.sofrecom.stage.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class BadWordChecker {

    List<String> badWordsList;

    public void parseFile(){
        badWordsList = new ArrayList<String>();
        try{
            FileReader readFile = new FileReader("C:\\Users\\SSD\\Desktop\\Simple Test\\pidevproject-4SAE5-GRP6\\src\\main\\resources\\Word_Filter - Sheet1.csv");
            BufferedReader reader = new BufferedReader(readFile);
            String currentLine = "";
            while ((currentLine = reader.readLine()) != null) {
                int quomaposition = currentLine.indexOf(',');
                String badWord = currentLine.substring(0,quomaposition);
                badWordsList.add(badWord);
                //System.out.println(badWord);

            }
        }
        catch(Exception e){

        }
    }

   public boolean checkWord(String text){
        String filtredWord = text.replaceAll("[^A-Za-z]+", "");

        System.out.println(filtredWord);
        for(String word : badWordsList){
            if(filtredWord.contains(word)){
                System.out.println("this text contains one or many bad words ! ");
                return false;
            }
        }

        return true;
    }

   /* public static void main(String[] args) throws IOException {
       Test d = new Test();
       d.parseFile();
       System.out.println(d.checkWord("y-a-o-i"));
    }*/
}
