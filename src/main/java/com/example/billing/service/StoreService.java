package com.example.billing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.billing.model.Store;
import com.example.billing.modelDTO.StoresDTO;
import com.example.billing.repository.StoresRepository;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoresRepository storesRepository;

    public StoresDTO createStore(StoresDTO storesDTO) {
        // Convert StoresDTO to entity
        Store store = mapToEntity(storesDTO);
        // Save the entity
        Store savedStore = storesRepository.save(store);
        // Convert the saved entity back to DTO and return
        return mapToDTO(savedStore);
    }

    public List<StoresDTO> getAllStores() {
        List<Store> stores = storesRepository.findAll();
        // Convert each entity to DTO and collect into a list
        return stores.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public StoresDTO getStoreById(Long storeId) {
        Optional<Store> storeOptional = storesRepository.findById(storeId);
        return storeOptional.map(this::mapToDTO).orElse(null);
    }

    public StoresDTO updateStore(Long storeId, StoresDTO storesDTO) {
        Optional<Store> storeOptional = storesRepository.findById(storeId);
        if (storeOptional.isPresent()) {
            // Update the entity with the new data
            Store store = storeOptional.get();
            // Update store properties with DTO values
            store.setName(storesDTO.getStoreName());
            store.setAddress(storesDTO.getStoreAddress());
            store.setPhoneNumber(storesDTO.getStorePhoneNumber());
            store.setArea(storesDTO.getStoreArea());
            store.setStreet(storesDTO.getStoreStreet());
            store.setCity(storesDTO.getStoreCity());
            store.setDistrict(storesDTO.getStoreDistrict());
            store.setState(storesDTO.getStoreState());
            store.setLandmark(storesDTO.getStoreLandMark());
            store.setPinCode(storesDTO.getStorePinCode());
            Store updatedStore = storesRepository.save(store);
            // Convert the updated entity back to DTO and return
            return mapToDTO(updatedStore);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteStore(Long storeId) {
        storesRepository.deleteById(storeId);
    }

    // Helper method to convert Store entity to StoresDTO
    private StoresDTO mapToDTO(Store store) {
        StoresDTO storesDTO = new StoresDTO();
        storesDTO.setStoreId(store.getId());
        storesDTO.setStoreName(store.getName());
        storesDTO.setStoreAddress(store.getAddress());
        storesDTO.setStorePhoneNumber(store.getPhoneNumber());
        storesDTO.setStoreArea(store.getArea());
        storesDTO.setStoreStreet(store.getStreet());
        storesDTO.setStoreCity(store.getCity());
        storesDTO.setStoreDistrict(store.getDistrict());
        storesDTO.setStoreState(store.getState());
        storesDTO.setStoreLandMark(store.getLandmark());
        storesDTO.setStorePinCode(store.getPinCode());
        return storesDTO;
    }
    
    // Helper method to convert StoresDTO to Store entity
    private Store mapToEntity(StoresDTO storesDTO) {
        Store store = new Store();
        store.setName(storesDTO.getStoreName());
        store.setAddress(storesDTO.getStoreAddress());
        store.setPhoneNumber(store.getPhoneNumber());
        store.setArea(storesDTO.getStoreArea());
        store.setStreet(storesDTO.getStoreStreet());
        store.setCity(storesDTO.getStoreCity());
        store.setDistrict(storesDTO.getStoreDistrict());
        store.setState(storesDTO.getStoreState());
        store.setLandmark(storesDTO.getStoreLandMark());
        store.setPinCode(storesDTO.getStorePinCode());
        return store;
    }
}
