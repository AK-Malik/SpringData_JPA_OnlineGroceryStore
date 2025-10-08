package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Service;

import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity.OnlineGroceryStore;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreRepo;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreResultSet;
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
  //  @Autowired
  //  private GroceryStoreResultSet groceryStoreResultSet;  //Not needed.

    /**
     * Insert a record in Grocery_Store Table
     *
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
            System.out.println("No record added in GroceryStoreTable, Invalid input. Review the record " + null);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //@Transactional("oracle1-jpaTransactionManager")
    @Transactional
    public ResponseEntity<OnlineGroceryStore> getRecordFromGroceryStore(Long serialNumber) {
        System.out.println("Attempting to get the record from GroceryStoreTable: " + serialNumber);
        if (serialNumber != null) {       //validate system will automatically check this.
            System.out.println("Getting record from GroceryStoreTable: " + serialNumber);
            OnlineGroceryStore groceryStoreRecord = groceryStoreRepo.getOnlineGroceryStoreBySerialNumberSQL(serialNumber);
            if (groceryStoreRecord != null) {
                System.out.println("Found the required record as: " + groceryStoreRecord);
                return new ResponseEntity<>(groceryStoreRecord, HttpStatus.OK);
            }
            System.out.println("Requested record doesn't exist for serialNumber: " + serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Not a valid request.Please provide valid input to process your request");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Transactional
    public ResponseEntity<List<OnlineGroceryStore>> getAllTheRecordsFromGroceryStore() {
        System.out.println("Attempting to get the records from GroceryStoreTable..");
        if (groceryStoreRepo != null) {
            System.out.println("Printing all the records from GroceryStoreTable..");
//            return new ResponseEntity<>(groceryStoreRepo.findAll(), HttpStatus.OK);
            return new ResponseEntity<>(groceryStoreRepo.getOnlineGroceryStoreBySQL(), HttpStatus.OK);
        }
        System.out.println("No Record found.");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Void> deleteTheRecordFromGroceryStoreTable(Long serialNumber) {
        if (serialNumber != null) {   //to avoid null pointer exception
            System.out.println("Attempting to delete the record from GroceryStoreTable..");
            //checking if the record exists in the database table
            if (groceryStoreRepo.existsById(serialNumber)) {
                System.out.println("Match found, deleting the record from GroceryStoreTable for serialNumber: " + serialNumber);
                //groceryStoreRepo.deleteById(serialNumber);
                //OnlineGroceryStore groceryStoreRecord = groceryStoreRepo.deleteRecordFromOnlineGroceryStoreBySQL();
                // System.out.println("Deleted the record from GroceryStoreTable for serialNumber: " + serialNumber);
                groceryStoreRepo.deleteRecordFromOnlineGroceryStoreBySQL(serialNumber);
                System.out.println("Deleted the record from GroceryStoreTable for serialNumber: " + serialNumber);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            System.out.println("Requested record doesn't exist in grocery_Store Table for serialNumber: " + serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Not a valid request.Please provide valid input to process your request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<Void> insertUpdateRecordInGroceryStore(Long serialNumber, OnlineGroceryStore incomingOnlineGroceryStore) {
        System.out.println("Attempting to update the record in GroceryStoreTable for serialNumber: " + serialNumber + ", " + incomingOnlineGroceryStore);
        if (serialNumber != null) {
            if (incomingOnlineGroceryStore != null) {
                OnlineGroceryStore existingOnlineGroceryStore = groceryStoreRepo.getOnlineGroceryStoreBySerialNumberSQL(serialNumber);
                if (existingOnlineGroceryStore != null) {
                  //  existingOnlineGroceryStore.setSerialNumber(incomingOnlineGroceryStore.getSerialNumber());  remove this line as serial number is primary key and auto incremented
                    existingOnlineGroceryStore.setItemName(incomingOnlineGroceryStore.getItemName());
                    existingOnlineGroceryStore.setQuantity(incomingOnlineGroceryStore.getQuantity());
                    existingOnlineGroceryStore.setBillAmount(incomingOnlineGroceryStore.getBillAmount());
                    existingOnlineGroceryStore.setCustomerName(incomingOnlineGroceryStore.getCustomerName());
                    existingOnlineGroceryStore.setInsertDate(incomingOnlineGroceryStore.getItemInsertDate());
                    existingOnlineGroceryStore.setEmail(incomingOnlineGroceryStore.getEmail());
                    existingOnlineGroceryStore.setPhoneNumber(incomingOnlineGroceryStore.getPhoneNumber());
                    existingOnlineGroceryStore.setRemarks(incomingOnlineGroceryStore.getRemarks());
                    // groceryStoreRepo.save(existingOnlineGroceryStore);
                    groceryStoreRepo.updateItem(incomingOnlineGroceryStore.getItemName(), incomingOnlineGroceryStore.getSerialNumber());
                    System.out.println("Updated the record in GroceryStoreTable for serialNumber: " + serialNumber);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    //groceryStoreRepo.save(incomingOnlineGroceryStore);
                  //  groceryStoreRepo.insertItem(incomingOnlineGroceryStore.getSerialNumber(), incomingOnlineGroceryStore.getItemName());
                   //****QAK? why i can not give pojo directly for the insert
                    //****QAK? why i am using this: if(serialNumber != null) when default postman calls checks if serialNumber is passed as space or char . 400
                   /* groceryStoreRepo.insertItem(incomingOnlineGroceryStore);
                    System.out.println("Inserted the record in GroceryStoreTable for serialNumber: " + serialNumber);
                    */
                    groceryStoreRepo.insertItem(
                            incomingOnlineGroceryStore.getBillAmount(),
                            incomingOnlineGroceryStore.getCustomerName(),
                            incomingOnlineGroceryStore.getItemName(),
                            incomingOnlineGroceryStore.getQuantity(),
                            incomingOnlineGroceryStore.getItemInsertDate(),
                            incomingOnlineGroceryStore.getPhoneNumber(),
                            incomingOnlineGroceryStore.getEmail(),
                            incomingOnlineGroceryStore.getRemarks()
                    );
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }

            }
                System.out.println("invalid input request2.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            System.out.println("invalid input request1.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    //reporting query
    @Transactional
    public ResponseEntity<Long> getCountOfRecordsInGSTable()
    {
        System.out.println("Attempting to get the count of records in GroceryStoreTable..");
       Long recordCount = groceryStoreRepo.getCountOfRecordsInGroceryStore();
        System.out.println("Count of records in GroceryStoreTable: " + recordCount);
        return new ResponseEntity<Long>(recordCount, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Double> getTotalBillAmtInGSTable()
    {
        Double totalBillAmount = groceryStoreRepo.getTotalBillAmountInGroceryStore();
        System.out.println("Total bill amount in GroceryStoreTable: " + totalBillAmount);
        return new ResponseEntity<>(totalBillAmount, HttpStatus.OK);
    }


}

