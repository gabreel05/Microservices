package br.com.market.demands.service;

import br.com.market.demands.model.Demand;
import br.com.market.demands.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {
    private final DemandRepository demandRepository;

    private final AddressService addressService;

    private final ProductService productService;

    @Autowired
    public DemandService(DemandRepository demandRepository, AddressService addressService, ProductService productService) {
        this.demandRepository = demandRepository;
        this.addressService = addressService;
        this.productService = productService;
    }

    public List<Demand> findAllDemands() {
        List<Demand> demands = demandRepository.findAll();;

        return demands;
    }
}
