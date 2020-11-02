//Rebecca Housey
//CSE 017 
//ALA #9 
import java.util.Scanner; 
import java.io.FileReader;
import java.io.FileNotFoundException; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Collections; 
import java.util.Random;
public class AnimalDB {
    public static void main(String[] args){
        //Reading the txt file and filling animalArray
        String[] animalArray = new String[455]; 
        try{ 
            Scanner fileReader = new Scanner(new FileReader("animals.txt"));
            int i = 0; 
            while(fileReader.hasNext()){
            animalArray[i] = fileReader.nextLine(); 
            i++; 
            }
            fileReader.close(); 
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found."); 
        }
        //calling method - 'shuffle' to shuffle the animalArray
        shuffle(animalArray); 
        //instantiating ArrayList and LinkedList
        ArrayList<String> animalArrayList = new ArrayList<String>(animalArray); 
        LinkedList<String> animalLinkedList = new LinkedList<String>(animalArray); 
        Random r = new Random(); 
        int arrayListComparisons = 0; 
        int linkedListComparisons = 0;        
        System.out.printf("%30s%30s%30s\n", "Animal Name", "#Comparisons(Array List)", "#Comparisons(Linked List"); 
        for(int i = 0; i < 100; i++){
        int randomNumber = r.nextInt(animalArrayList.size()); 
        String randomAnimalName = animalArrayList.get(randomNumber); 
        int arrlistcomp =  animalArrayList.searchComparisons(randomAnimalName); 
        int linklistcomp =  animalLinkedList.searchComparisons(randomAnimalName); 
        arrayListComparisons += arrlistcomp; 
        linkedListComparisons += linklistcomp; 
        System.out.printf("%30s%25d%25d\n", randomAnimalName, arrlistcomp, linklistcomp); 
        }
        double avgArrListComp = arrayListComparisons/100; 
        double avgLinkListComp = linkedListComparisons/100; 
        System.out.printf("%30s%25f%25f","Average # Comparisons", avgArrListComp, avgLinkListComp); 
 }
        //shuffle method for the array
        public static void shuffle(String[] stringArray){
            List<String> stringList = Arrays.asList(stringArray); 
            Collections.shuffle(stringList); 
            stringList.toArray(stringArray); 
            
        }
        
}
