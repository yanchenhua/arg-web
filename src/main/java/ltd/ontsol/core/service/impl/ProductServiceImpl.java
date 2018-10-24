package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.ProductDTO;
import ltd.ontsol.core.repo.ProductRepository;
import ltd.ontsol.core.service.ProductService;

@Component("productService")
public class ProductServiceImpl implements ProductService {

    private static final Log log = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> findAll(ProductDTO dto) {
        return IterableUtils.toList(repo.findAll(Example.of(dto)));
    }

    @Override
    public List<ProductDTO> findAll() {
        return repo.findAll();
    }

    @Override
    public ProductDTO saveOrUpdate(ProductDTO dto) {
        return repo.save(dto);
    }

    @Override
    public ProductDTO findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
