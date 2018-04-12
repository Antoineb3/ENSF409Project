/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * All other 'Message' classes extend this class, it makes communication to the server simpler.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public abstract class Message implements Serializable{
	static final long serialVersionUID = 10;	
}
