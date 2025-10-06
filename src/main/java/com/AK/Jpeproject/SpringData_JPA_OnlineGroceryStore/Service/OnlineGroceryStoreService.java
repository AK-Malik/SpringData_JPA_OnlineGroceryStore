package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Service;

import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity.OnlineGroceryStore;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreBaseRepo;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnlineGroceryStoreService {

    @Autowired
    private GroceryStoreRepo groceryStoreRepo;
//    @Autowired
//    private GroceryStoreBaseRepo groceryStoreBaseRepo;  //Not needed.

    /**
     * Insert a record in Grocery_Store Table
     * @param onlineGroceryStore
     * @return
     */

   @Transactional("oracle1-jpaTransactionManager")
    public ResponseEntity<Void> insertAnItemInGroceryStore(OnlineGroceryStore onlineGroceryStore) {
        if (onlineGroceryStore != null) {
          // groceryStoreBaseRepo.save(onlineGroceryStore);
            groceryStoreRepo.save(onlineGroceryStore);
            System.out.println("Saved a record in GroceryStoreTable: " + onlineGroceryStore);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            System.out.println("No record added in GroceryStoreTable, Invalid input. Review the record "+ null);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //@Transactional("oracle1-jpaTransactionManager")
    @Transactional
    public ResponseEntity<OnlineGroceryStore> getRecordFromGroceryStore(Long serialNumber) {
        System.out.println("Attempting to get record from GroceryStoreTable: " + serialNumber);
        if (serialNumber != null) {
            System.out.println("Getting record from GroceryStoreTable: " + serialNumber);
            OnlineGroceryStore groceryStoreRecord = groceryStoreRepo.getOnlineGroceryStoreBySerialNumberSQL(serialNumber);
            return new ResponseEntity<OnlineGroceryStore>(groceryStoreRecord, HttpStatus.OK);
        }
        System.out.println();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
