package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.Hazard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface HazardRepository extends JpaRepository<Hazard, Integer> {

    List<Hazard> findByDtxsidInOrderByPreferredNameAscSuperCategoryAsc(List<String> dtxsid);

    List<Hazard> findByDtxsidAndSuperCategoryEquals(String dtxsid, String superCategory);

    List<Hazard> findByDtxsidAndWorkflowAndSuperCategoryEquals(String dtxsid, String workflow, String superCategory);

    public List<Hazard> findByWorkflowOrderByPreferredNameAscSuperCategoryAsc(String workflow);

    public List<Hazard> findByDtxsidInAndWorkflowOrderByPreferredNameAscSuperCategoryAsc(List<String> dtxsid, String workflow);

    public List<Hazard> findByDtxsid(String dtxsid);

		public List<Hazard> findByIdInOrderByPreferredNameAscSuperCategoryAsc(List<Integer> hazardIds);
}
