package com.example.billing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.billing.model.Store;
import com.example.billing.modelDTO.StoreDTO;
import com.example.billing.repository.StoresRepository;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoresRepository storesRepository;

    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = mapToEntity(storeDTO);
        Store savedStore = storesRepository.save(store);
        return mapToDTO(savedStore);
    }

    public List<StoreDTO> getAllStores() {
        List<Store> stores = storesRepository.findAll();
        return stores.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Long storeId) {
        Optional<Store> storeOptional = storesRepository.findById(storeId);
        return storeOptional.map(this::mapToDTO).orElse(null);
    }

    public StoreDTO updateStore(Long storeId, StoreDTO storeDTO) {
        Optional<Store> storeOptional = storesRepository.findById(storeId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            store.setName(storeDTO.getStoreName());
            store.setAddress(storeDTO.getStoreAddress());
            store.setPhoneNumber(storeDTO.getStorePhoneNumber());
            store.setArea(storeDTO.getStoreArea());
            store.setStreet(storeDTO.getStoreStreet());
            store.setCity(storeDTO.getStoreCity());
            store.setDistrict(storeDTO.getStoreDistrict());
            store.setState(storeDTO.getStoreState());
            store.setLandmark(storeDTO.getStoreLandMark());
            store.setPinCode(storeDTO.getStorePinCode());
            Store updatedStore = storesRepository.save(store);
            return mapToDTO(updatedStore);
        } else {
            return null;
        }
    }

    public void deleteStore(Long storeId) {
        storesRepository.deleteById(storeId);
    }

    private StoreDTO mapToDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(store.getId());
        storeDTO.setStoreName(store.getName());
        storeDTO.setStoreAddress(store.getAddress());
        storeDTO.setStorePhoneNumber(store.getPhoneNumber());
        storeDTO.setStoreArea(store.getArea());
        storeDTO.setStoreStreet(store.getStreet());
        storeDTO.setStoreCity(store.getCity());
        storeDTO.setStoreDistrict(store.getDistrict());
        storeDTO.setStoreState(store.getState());
        storeDTO.setStoreLandMark(store.getLandmark());
        storeDTO.setStorePinCode(store.getPinCode());
        return storeDTO;
    }
    
    private Store mapToEntity(StoreDTO storeDTO) {
        Store store = new Store();
        store.setName(storeDTO.getStoreName());
        store.setAddress(storeDTO.getStoreAddress());
        store.setPhoneNumber(storeDTO.getStorePhoneNumber());
        store.setArea(storeDTO.getStoreArea());
        store.setStreet(storeDTO.getStoreStreet());
        store.setCity(storeDTO.getStoreCity());
        store.setDistrict(storeDTO.getStoreDistrict());
        store.setState(storeDTO.getStoreState());
        store.setLandmark(storeDTO.getStoreLandMark());
        store.setPinCode(storeDTO.getStorePinCode());
        return store;
    }
}
