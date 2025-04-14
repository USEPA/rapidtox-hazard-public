package gov.epa.ccte.api.hazard.web.rest;

import static gov.epa.ccte.api.hazard.config.RapidToxConstants.TREAT_AS_POD;
import gov.epa.ccte.api.hazard.domain.Hazard;
import gov.epa.ccte.api.hazard.repository.HazardRepository;
import gov.epa.ccte.api.hazard.service.HazardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain}s.
 */
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HazardResource {

		private final HazardService hazardService;

		@GetMapping("/test")
		public String test() {
				return "hazard";
		}
		
		@GetMapping("/dtxsid/{dtxsid}")
		public List<HazardDTO> fetchHazardsForDtxsid(@PathVariable("dtxsid") String dtxsid) {
				log.debug("dtxsid={}", dtxsid);
				return hazardService.findHazardsForDtxsid(dtxsid);
		}

		@GetMapping("pod/dtxsid/{dtxsid}/workflow/{workflow}")
		public List<HazardDTO> fetchPodHazardsByDtxsidAndWorkflow(@PathVariable("dtxsid") String dtxsid, @PathVariable("workflow") String workflow) {
				log.debug("dtxsid={}", dtxsid);

				return hazardService.findPodHazardsByDtxsidAndWorkflow(dtxsid, workflow);
		}

		@PostMapping("/")
		public List<HazardDTO> fetchHazardToxPod(@RequestBody List<String> dtxsids) {
				log.debug("dtxsids = {}", dtxsids);

				List<HazardDTO> hazards = hazardService.findHazardToxPodByDtxsids(dtxsids);

				log.debug("{} records found for hazard", hazards.size());

				return hazards;
		}

		@PostMapping("by-workflow/{workflow}")
		public List<HazardDTO> fetchHazardByWorkflow(@RequestBody List<String> dtxsids, @PathVariable("workflow") String workflow) {
				log.debug("dtxsids = {}", dtxsids);
				log.debug("workflow = {}", workflow);

				List<HazardDTO> hazards = hazardService.findHazardByDtxsidsAndWorkflow(dtxsids, workflow);

				log.debug("{} records found for hazard", hazards.size());

				return hazards;
		}
		
		@PostMapping("/ids")
		public List<HazardDTO> fetchHazardsById(@RequestBody List<Integer> hazardIds) {
				log.debug("ids = {}", hazardIds);
				
				List<HazardDTO> hazards = hazardService.findHazardsByIds(hazardIds);
				
				log.debug("{} records found", hazards.size());
				
				return hazards;
		}

}
