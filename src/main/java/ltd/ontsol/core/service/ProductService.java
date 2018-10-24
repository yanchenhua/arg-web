package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.ProductDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface ProductService {
    List<ProductDTO> findAll();

    List<ProductDTO> findAll(ProductDTO dto);

    ProductDTO saveOrUpdate(ProductDTO dto);

    ProductDTO findById(Long id);

    void delete(Long id);
}
