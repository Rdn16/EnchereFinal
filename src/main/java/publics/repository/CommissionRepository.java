package publics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import publics.model.Commission;
import publics.model.Enchere;

import java.util.List;

public interface CommissionRepository extends JpaRepository<Commission,String> {

}
