package gov.epa.ccte.api.hazard.config;

public class RapidToxConstants {

	private RapidToxConstants() {
		throw new UnsupportedOperationException("pure static class, don't instantiate");
	}

	public static final String TREAT_AS_POD = "Dose Response Summary Value";
	public static final String TREAT_AS_TOX = "Toxicity Value";
	public static final String TREAT_AS_CUSTOM_POD = "Custom " + TREAT_AS_POD;
	public static final String TREAT_AS_CUSTOM_TOX = "Custom " + TREAT_AS_TOX;
}
