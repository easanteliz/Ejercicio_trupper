package com.home.trupper.rest.service;

import com.home.trupper.rest.exception.TrupperPracticeException;
import com.home.trupper.rest.persistence.repository.OrderRepository;
import com.home.trupper.rest.persistence.repository.ProductRepository;
import com.home.trupper.rest.persistence.repository.SucursalRepository;
import com.home.trupper.rest.persistence.repository.entities.ProductEntity;
import com.home.trupper.rest.persistence.repository.entities.SucursalEntity;
import com.home.trupper.rest.request.ProductRequest;
import com.home.trupper.rest.request.SucursalRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DaoService {

    @Autowired
    private  SucursalRepository sucursalRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public SucursalEntity getSucursalData (String name){
        return   sucursalRepository.findByName(name).orElse(null);
    }

    public ProductEntity getProductByCode(String codigo){
        Optional<ProductEntity> productEntityBD = productRepository.findProductEntitiesByCodigo(codigo);

        return productEntityBD.orElse(null);
    }

    public void updateProduct (ProductRequest productRequest){
        ProductEntity productEntity = getProductByCode(productRequest.getCodigo());
        if(productEntity!=null){
            ModelMapper model = new ModelMapper();
            ProductEntity entityModel = model.map(productRequest, ProductEntity.class);
            entityModel.setId(productEntity.getId());
            productRepository.save(entityModel);
            log.info("EntityModel Saved... {}",entityModel);
        }
    }

    public void saveSucursal (SucursalRequest sucursalRequest){

        if(sucursalRequest==null
                ||sucursalRequest.getSucursal()==null
                ||sucursalRequest.getSucursal().getName().isEmpty()){
            throw  new TrupperPracticeException("COD-004","El nombre de la sucursal no puede ser nulo o vacio.....");
        }
        SucursalEntity sucursalEntity = getSucursalData(sucursalRequest.getSucursal().getName());

        ModelMapper model = new ModelMapper();
        SucursalEntity entityModel = model.map(sucursalRequest.getSucursal(), SucursalEntity.class);
        log.info("EntitySucursalModel parsed... {}",entityModel);
        sucursalRepository.save(entityModel);

    }

    public List<ProductEntity> validaProductosSucursal (List<ProductRequest> productRequestList) {
        List<ProductEntity> productEntitiesResponse = new ArrayList<ProductEntity>();

        productRequestList.forEach(productRequest -> {
            ProductEntity producerMapEntity = mapDTOToEntityProduct(productRequest, getProductByCode(productRequest.getCodigo()));
            productEntitiesResponse.add(producerMapEntity);
        });

        return productEntitiesResponse;
    }

    public ProductEntity mapDTOToEntityProduct (ProductRequest productRequest, ProductEntity productEntity) {

        ModelMapper model = new ModelMapper();
        ProductEntity entityModel = model.map(productRequest, ProductEntity.class);
        if(productEntity!=null){
            entityModel.setId(productEntity.getId());
        }

        return entityModel;
    }

}