/**
 * 
 */
package SharedObjects;

/**
 * @author Antoine
 *
 */
public class FileContents {
	private String title, extention;
	private byte[] contents;
	
	public FileContents(String title, String extention, byte[] contents) {
		this.title = title;
		this.extention = extention;
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public String getExtention() {
		return extention;
	}

	public byte[] getContents() {
		return contents;
	}
	
	
	
	
}
