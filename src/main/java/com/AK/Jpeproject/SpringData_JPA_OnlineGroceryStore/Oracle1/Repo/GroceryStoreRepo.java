package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo;

import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity.OnlineGroceryStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo.GroceryStoreResultSet; //needed only if resultset is part of package

import java.time.LocalDate;
import java.util.List;

/**
 * One repository per entity.
 * You cannot manage multiple entities in a single JpaRepository interface.
 * Create a new repository interface for each entity you want to manage.
 */
/**
 * GroceryStoreRepo is a Spring Data JPA repository interface.
 * It extends JpaRepository, which is a Spring Data interface that provides built-in CRUD (Create, Read, Update, Delete) operations and more for your entity.
 * for manual queries or using JPQL /SQL, use the return type as this example shows
 * <OnlineGroceryStore, Long> means:
 * OnlineGroceryStore is the entity class this repository manages (should be annotated with @Entity).
 * Long is the type of the primary key (ID) of OnlineGroceryStore.
 * By extending JpaRepository, EmployeeJpaSqlRepo automatically inherits methods like save(), findById(), findAll(), deleteById(), etc., without you needing to write their implementations.
 */
public interface GroceryStoreRepo extends JpaRepository<OnlineGroceryStore, Long> {

    // --------JPQL custom query--------
    /**
     * @Query(...)
     * This annotation is used in Spring Data JPA to define a custom JPQL (Java Persistence Query Language) query for a repository method.
     * "SELECT ae FROM OnlineGroceryStore ae WHERE ae.itemName= :itemName"
     * This is a JPQL query (similar to SQL, but uses entity and field names, not table/column names). ae is an alias for the OnlineGroceryStore entity.
     * The query selects the OnlineGroceryStore where its serialNumber field matches the provided parameter :serialNumber.
     * OnlineGroceryStore findByIdJPQL(...) : This method will execute the above query and return the matching OnlineGroceryStore object.
     * @Param("serialNumber") Long serialNumber  , Binds the method parameter serialNumber to the named parameter :serialNumber in the JPQL query.
     * Summary
     * This method allows you to fetch an OnlineGroceryStore by its serialNumber using a custom JPQL query in a Spring Data JPA repository.
     * The query uses entity and field names (not table/column names).
     * The @Param annotation binds the method parameter to the query parameter.
     * @param serialNumber
     * @return
     */

    @Query(value ="SELECT ae FROM OnlineGroceryStore ae WHERE ae.serialNumber = :serialNumber", nativeQuery = false)  //JPQL query structure, pojo/entity
    OnlineGroceryStore getFindBySerialNumber(@Param("serialNumber") Long serialNumber);   //EmployeeEntity findByIdJPQL(Long empId);  //custom method for JPQL

    /**
     * Fetch all the records of an OnlineGroceryStore using JPQL
     * @return
     */
    @Query(value = "SELECT ae from OnlineGroceryStore ae", nativeQuery = false)   //return the full table , one object at a time
    List<OnlineGroceryStore> getOnlineGroceryStore(); // custom

    /**
    *  JPQL doesn't support insert statement . for this it should be taken by default JPARepository
    */

    /**
     * Using JPQL, Add a record, if it doesn't exist or update if exist
        */
//    @Modifying
//    @Query(value ="UPDATE OnlineGroceryStore ae SET ae.itemName=:itemName, ae.itemInsertDate =:itemInsertDate,ae.quantity=: quantity, ae.billAmount= :billAmount,ae.customerName=:customerName," +
//                  "ae.email=:email, ae.phoneNumber =:phoneNumber, ae.remarks =:remarks WHERE ae.serialNumber =:serialNumber")
//    OnlineGroceryStore insertOrUpdateRecord(@Param("serialNumber") Long serialNumber, @Param("itemName") String itemName,@Param("quantity") Double quantity,@Param("billAmount") String billAmount,@Param("customerName") String customerName,
//                                            @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("remarks") String remarks);
//
//    /**
//     * Delete a record using JPQL
//     * @param serialNumber
//     * @return
//     */
//    @Modifying
//    @Query("DELETE FROM OnlineGroceryStore ae WHERE ae.serialNumber=:serialNumber")
//    void deleteBySerialNumber(@Param("serialNumber") Long serialNumber);      //int deleteBySerialNumber(@Param("serialNumber") Long serialNumber);


    //------------SQL------------------

    //@Query(value = "select * from GROCERY_STORE where SERIAL_NUMBER=: serialNumber", nativeQuery = true) //NAMED PARAMETER CAN NOT HAVE SPACE
    @Query(value = "select * from GROCERY_STORE where SERIAL_NUMBER=:serialNumber", nativeQuery = true)
    OnlineGroceryStore getOnlineGroceryStoreBySerialNumberSQL(@Param("serialNumber") Long serialNumber);

//    @Query(value = "select * from GROCERY_STORE where SERIAL_NUMBER=?1", nativeQuery = true)   //POSITIONAL ARG.
//    OnlineGroceryStore getOnlineGroceryStoreBySerialNumberSQL(Long serialNumber);

    @Query(value = "select * from GROCERY_STORE", nativeQuery = true)
    List<OnlineGroceryStore> getOnlineGroceryStoreBySQL(); // custom

    @Modifying
    @Query(value = "DELETE FROM GROCERY_STORE WHERE SERIAL_NUMBER=:serialNumber", nativeQuery = true)
    void deleteRecordFromOnlineGroceryStoreBySQL(@Param("serialNumber") Long serialNumber);  //return type for update or delete with @modifying will always be int or void
//-------------
    @Modifying
    @Query(value = "UPDATE GROCERY_STORE SET ITEM_NAME = :itemName WHERE SERIAL_NUMBER = :serialNumber", nativeQuery = true)
    void updateItem(@Param("itemName") String itemName, @Param("serialNumber") Long serialNumber);

//    @Modifying
//    @Query(value = "INSERT INTO GROCERY_STORE (BILL_AMOUNT, CUSTOMER_NAME, ITEM_NAME, QUANTITY, ITEM_INSERT_DT, PHONE_NUMBER, E_MAIL, REMARKS) " +
//            "VALUES (:itemName, :itemInsertDate, :quantity, :billAmount, :customerName, :phoneNumber, :email, :remarks)", nativeQuery = true)
//    void insertItem(@Param("customerName") Long customerName, @Param("itemName") String itemName);

    @Modifying
    @Query(value = "INSERT INTO GROCERY_STORE (BILL_AMOUNT, CUSTOMER_NAME, ITEM_NAME, QUANTITY, ITEM_INSERT_DT, PHONE_NUMBER, E_MAIL, REMARKS) " +
            "VALUES (:billAmount, :customerName, :itemName, :quantity, :itemInsertDate, :phoneNumber, :email, :remarks)", nativeQuery = true)
    //void insertItem(OnlineGroceryStore incomingOnlineGroceryStore);
    void insertItem(
            @Param("billAmount") Double billAmount,
            @Param("customerName") String customerName,
            @Param("itemName") String itemName,
            @Param("quantity") Integer quantity,
            @Param("itemInsertDate") LocalDate itemInsertDate,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email,
            @Param("remarks") String remarks
    );
//-------------

    @Query(value = "SELECT count(*) from GROCERY_STORE", nativeQuery = true)
    Long getCountOfRecordsInGroceryStore(); // custom


    @Query(value = "select SUM(BILL_AMOUNT) from GROCERY_STORE", nativeQuery = true)
    Double getTotalBillAmountInGroceryStore();

    @Query(value = "SELECT * FROM GROCERY_STORE WHERE ITEM_INSERT_DT BETWEEN :startDate AND :endDate", nativeQuery = true)
   // List<GroceryStoreResultSet> getRecordsWithinDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); // custom
    List<GroceryStoreResultSet>getRecordsWithinDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); // custom
//"SELECT * FROM GROCERY_STORE WHERE ITEM_INSERT_DT BETWEEN '05-OCT-25' AND '06-OCT-25'"


    @Query(value="SELECT MAX(QUANTITY) FROM GROCERY_STORE", nativeQuery = true)
    Integer getMaxQuantityInGroceryStore();


    @Query(value=" SELECT ITEM_INSERT_DT as orderDate, COUNT(QUANTITY) as totalSaleAmount FROM GROCERY_STORE GROUP BY ITEM_INSERT_DT", nativeQuery =true)
    List<GroceryStoreResultSet> getTotalAmtOfSaleByDate();

    @Query(value ="SELECT ITEM_NAME as itemName FROM GROCERY_STORE WHERE PHONE_NUMBER=:phoneNumber AND ITEM_INSERT_DT=:itemInsertDate",nativeQuery = true)
    //GroceryStoreResultSet getItemNameByPhoneAndDate(
    List<GroceryItemNameByPhoneDateResultSet> getItemNameByPhoneAndDate(
            @Param("phoneNumber") String phoneNumber,
            @Param("itemInsertDate") LocalDate itemInsertDate
    );


}


