package alla.verkhohliadova.demo_car.repository;


import alla.verkhohliadova.demo_car.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Long>, JpaSpecificationExecutor<Ordered> {
}
