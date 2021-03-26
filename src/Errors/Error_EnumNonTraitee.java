package Errors;

public class Error_EnumNonTraitee extends Error {
	
	private static final long serialVersionUID = 1L;

	public Error_EnumNonTraitee() {
		super();
	}	

	@Override
	public String getMessage() {
		return "Enumeration Inconnue";
	}
}
