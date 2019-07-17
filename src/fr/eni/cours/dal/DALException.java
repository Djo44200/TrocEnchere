package fr.eni.cours.dal;

public class DALException extends Exception {

	private static final long serialVersionUID = 683304188675533865L;
/**
 * @author vroch2019
 * **/
 
	//Constructeurs
	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}

	//Methodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche DAL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
	
	
}
