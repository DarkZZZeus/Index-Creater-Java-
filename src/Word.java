import java.util.LinkedList;

public class Word implements Comparable<Word>{
	
	private String wordText = "";
	private int count = 0;
	private LinkedList<Integer> lineNumbers = new LinkedList<Integer>();
	
	public Word() {}
	
	public Word(String in_word, int lineNumber) {

		wordText = in_word;
		lineNumbers.add(lineNumber);
		
	}
	
	public void CountWord(int lineNumber) {
		if(lineNumbers.contains(lineNumber) == false)
			lineNumbers.add(lineNumber);
	}
	
	public String GetWord() {
		return this.getWordText();
	}	
	
	
	public String getWordText() {
		return wordText;
	}
	public void setWordText(String wordText) {
		this.wordText = wordText;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public LinkedList<Integer> getLineNumbers() {
		return lineNumbers;
	}
	public void setLineNumbers(LinkedList<Integer> lineNumbers) {
		this.lineNumbers = lineNumbers;
	}
	
	public boolean equals(Word x) {
		if(this.getWordText() == x.getWordText()) 
			return true;
		else
			return false;
	}
	
	public int compareTo(Word x) {
		return this.getWordText().compareTo(x.getWordText());
	}

	@Override
	public String toString() {
		String out_line = "";
		String dots = "";
		String lineNumbersText = "";
		int numdots = 24 - (wordText.length() + (Integer.toString(count)).length());
		
		for(int i = 0; i < numdots; i++) {
			dots += ".";
		}
		
		for(int x : lineNumbers) {
			lineNumbersText += (" " + (Integer.toString(x)));
		}
		
		if(this.getCount() == 0) {
			out_line = wordText + "\r\n";
		}
		else if(this.getWordText() == "")
			out_line = "";
		else {
			out_line = wordText + dots + (Integer.toString(count)) + ":" + lineNumbersText + "\r\n";
		}
		
		return out_line;
	}
	
	
	

}
