import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Create two BSTs
// filteredBST is a BST of stop words from file
// IndexedBST is a BST of Word

public class Indexer{
	
	private String datafile;
	private String filterfile;
	private BinarySearchTree<Word> filteredBST = new BinarySearchTree<Word>();
	private BinarySearchTree<Word> indexedBST = new BinarySearchTree<Word>();
	
	// Create an instance of Indexer
	public Indexer(String filterName, String dataFile) {
		datafile = dataFile;
		filterfile = filterName;
	}
	
	
	public void DoIndex(){
		// Checks if files exist
		if(FileExists(filterfile)) {
			// Construct the filteredBST with FileFilterReader
			filteredBST = FileFilterReader(filterfile);
		}
		if(FileExists(datafile)) {
			// Construct the indexedBST with FileWordReader
			indexedBST = FileWordReader(datafile);
		}
		
		OutputResults(); 
		
	}
	
	private boolean FileExists(String filename) {
		File f = new File(filename);
		return f.exists();
		
	}
	
	private BinarySearchTree<Word> FileFilterReader(String filename){
		BinarySearchTree<Word> temp = new BinarySearchTree<Word>();
		
		try{		
		Scanner sc = new Scanner(new File(filename));
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			line = line.toLowerCase();
			// Check line
			Word StopWord = new Word(line,0);
			temp.insert(StopWord);				
			
		}		
		sc.close();		
		
		// Make word lower case, remove punctuation
		// Create word object for each world in gsl.txt
		// Add to filteredBST
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	private BinarySearchTree<Word> FileWordReader(String filename){
		// Create word object for each word in input.txt
		// If word is in filteredBST, go to next word
		// If word isn't in indexedBST, insert the new word object
		// Else, update the word count and new lineNumber
		
		BinarySearchTree<Word> temp = new BinarySearchTree<Word>();
		int linecount = 1;
		
		try{		
		Scanner sc = new Scanner(new File(filename));
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] words = line.split(" ");
			
			for(String EachWord : words) {
				
				EachWord = EachWord.toLowerCase();
				EachWord = EachWord.replaceFirst("^[^a-zA-Z]+", "");	
				EachWord = EachWord.replaceAll("[^a-zA-Z]+$","");
				
				if(EachWord.matches("^[A-Za-z].*[A-Za-z]$")) {
				
				Word inWord = new Word(EachWord,linecount);
				inWord.setCount(inWord.getCount() + 1);
				
				//Check if Word is in filteredBST;
				if(filteredBST.contains(inWord)) {
					continue;
				}
				else {
					// If the word is already in the tree
					if(temp.contains(inWord)) {
						//Increase its count
						temp.find(inWord).element.setCount(temp.find(inWord).element.getCount() + 1);
						//Add the line number to the queue
						temp.find(inWord).element.CountWord(linecount);
					}
					else
						temp.insert(inWord);
				}					
			}
			}
			linecount = linecount + 1;
		}		
		sc.close();		
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		return temp;
		
	}
	
	private void OutputResults() {
		String filterout = filteredBST.printTree();
		//Output filteredBST		
		try {
		PrintWriter writer = new PrintWriter("filterResults.txt","UTF-8");
		writer.println(filterout);
		writer.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
		
		//Output indexedBST	
		String indexout = indexedBST.printTree();
		try {
		PrintWriter writer = new PrintWriter("indexResults.txt","UTF-8");
		writer.println(indexout);
		writer.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}
	

}
