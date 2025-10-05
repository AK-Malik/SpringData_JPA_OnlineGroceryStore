package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Service;

import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity.OnlineGroceryStore;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreBaseRepo;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OnlineGroceryStoreService {

    @Autowired
    private GroceryStoreRepo groceryStoreRepo;
    @Autowired
    private GroceryStoreBaseRepo groceryStoreBaseRepo;  //Not needed.

    @Transactional("oracle1-jpaTransactionManager")
    public ResponseEntity<Void> insertAnItemInGroceryStore(OnlineGroceryStore onlineGroceryStore) {
        if (onlineGroceryStore != null) {
          // groceryStoreBaseRepo.save(onlineGroceryStore);
            groceryStoreRepo.save(onlineGroceryStore);
            System.out.println("Saved a record in GroceryStoreTable: " + onlineGroceryStore);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            System.out.println("Invalid Input: " + onlineGroceryStore + " No record added in GroceryStoreTable");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


//    public ResponseEntity<List<OnlineGroceryStore>> getOnlineGroceryStoreBySQL() {
//
//    }


//    //Fetch all Employee Records
//    @Transactional("oracle1-jpaTransactionManager")
//    public ResponseEntity<List<EmployeeEntity>> fetchAllEmployeeRecordsWithJpql() {
//        List<EmployeeEntity> employeeEntity= employeeJpaSqlRepo.findAllJPQL();
//        return new ResponseEntity<>(employeeEntity, HttpStatus.OK);
//    }

//
//    @Transactional("oracle1-jpaTransactionManager")
//    public ResponseEntity<Void> addEmployee(EmployeeEntity employeeEntity) {
//        employeeRepo.save(employeeEntity);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//@Service
//public class EmployeeService {
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//    @Transactional("oracle1-jpaTransactionManager")
//    public ResponseEntity<Void> addEmployee(EmployeeEntity employeeEntity) {
//        employeeRepo.save(employeeEntity);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }