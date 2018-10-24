package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.ProductDTO;

/**
 * Created by cn40580 at 2018-06-16 4:20 PM.
 */
public interface ProductRepository extends JpaRepository<ProductDTO, Long> {

}
