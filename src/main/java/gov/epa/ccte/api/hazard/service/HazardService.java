
package gov.epa.ccte.api.hazard.service;

import gov.epa.ccte.api.hazard.config.RapidToxConstants;
import gov.epa.ccte.api.hazard.domain.Hazard;
import gov.epa.ccte.api.hazard.domain.SourceDescription;
import gov.epa.ccte.api.hazard.repository.HazardRepository;
import gov.epa.ccte.api.hazard.repository.SourceRepository;
import gov.epa.ccte.api.hazard.web.rest.HazardDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HazardService {
		private final HazardRepository hazardRepo;
		private final SourceRepository sourceRepo;

		public List<HazardDTO> findHazardsForDtxsid(String dtxsid) {
				final List<Hazard> hazards = hazardRepo.findByDtxsid(dtxsid);
				return decorateAndMap(hazards);
		}

		public List<HazardDTO> findPodHazardsByDtxsidAndWorkflow(String dtxsid, String workflow) {
				final List<Hazard> hazards = hazardRepo.findByDtxsidAndWorkflowAndSuperCategoryEquals(dtxsid, workflow, RapidToxConstants.TREAT_AS_POD);
				return decorateAndMap(hazards);
		}

		public List<HazardDTO> findHazardToxPodByDtxsids(List<String> dtxsids) {
				List<Hazard> hazards = hazardRepo.findByDtxsidInOrderByPreferredNameAscSuperCategoryAsc(dtxsids);
				return decorateAndMap(hazards);
		}

		public List<HazardDTO> findHazardByDtxsidsAndWorkflow(List<String> dtxsids, String workflow) {
				final List<Hazard> hazards = hazardRepo.findByDtxsidInAndWorkflowOrderByPreferredNameAscSuperCategoryAsc(dtxsids, workflow);
				return decorateAndMap(hazards);
		}
		
		public List<HazardDTO> findHazardsByIds(List<Integer> hazardIds) {
				return decorateAndMap(hazardRepo.findByIdInOrderByPreferredNameAscSuperCategoryAsc(hazardIds));
		}
		
		private List<HazardDTO> decorateAndMap(List<Hazard> hazards) { 
				// gather all the source names
				final List<String> sourceNames = hazards.stream()
								.map(Hazard::getSuperSource)
								.filter(n -> !n.equals("-"))
								.distinct()
								.collect(Collectors.toList());
				
				// build a map of source names to their descriptions
				final Map<String, String> descriptions = sourceRepo.findByNameIn(sourceNames).stream()
								.collect(Collectors.toMap(SourceDescription::getName, SourceDescription::getDescription));
				
				// map hazard to hazard dto, injecting appropriate source description
				final List<HazardDTO> dtos = hazards.stream()
								.map(h-> hazardAndSourceDescriptionToDto(h, descriptions.get(h.getSuperSource())))
								.collect(Collectors.toList());
                
                log.debug("{} records built", dtos.size());
				
				return dtos;
		}

		private HazardDTO hazardAndSourceDescriptionToDto(Hazard h, String sourceDescription) {
				return HazardDTO.builder()
								.id(h.getId())
								.casrn(h.getCasrn())
								.clowderId(h.getClowderId())
								.dtxsid(h.getDtxsid())
								.effect(h.getEffect())
								.exposureMethod(h.getExposureMethod())
								.exposureRoute(h.getExposureRoute())
								.molecularWeight(h.getMolecularWeight())
								.preferredName(h.getPreferredName())
								.riskAssessmentClass(h.getRiskAssessmentClass())
								.source(h.getSource())
								.sourceUrl(h.getSourceUrl())
								.speciesCommon(h.getSpeciesCommon())
								.ssourceUrl(h.getSsourceUrl())
								.studyDuration(h.getStudyDuration())
								.studyDurationUnits(h.getStudyDurationUnits())
								.studyType(h.getStudyType())
								.superCategory(h.getSuperCategory())
								.superSource(h.getSuperSource())
								.superSourceDescription(sourceDescription) // injecting this
								.toxvalNumeric(h.getToxvalNumeric())
								.toxvalSubtype(h.getToxvalSubtype())
								.toxvalTypDefn(h.getToxvalTypDefn())
								.toxvalType(h.getToxvalType())
								.toxvalUnits(h.getToxvalUnits())
								.workflow(h.getWorkflow())
								.build();
		}

		
}
