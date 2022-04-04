package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Group;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.groupName = : name")
    Optional<Group> findByGroupName(@Param("name") String name);

    @Query("select case when count (g)>0 then true else false end from Group g where g.groupName = :name")
    Boolean existsByName(@Param("name") String groupName);
}




