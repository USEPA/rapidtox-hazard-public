package gov.epa.ccte.api.hazard.web.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HazardDTO {

		private Integer id;
		private String dtxsid;
		private String casrn;
		private String preferredName;
		private Double molecularWeight;
		private String toxvalType;
		private String toxvalSubtype;
		private String riskAssessmentClass;
		private Double toxvalNumeric;
		private String toxvalUnits;
		private String studyType;
		private Double studyDuration;
		private String studyDurationUnits;
		private String exposureRoute;
		private String exposureMethod;
		private String speciesCommon;
		private String source;
		private String effect;
		private String toxvalTypDefn;
		private String superCategory;
		private String sourceUrl;
		private String ssourceUrl;
		private String superSource;
		private String superSourceDescription;
		private String clowderId;
		private String workflow;

}
