package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo;

import com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity.OnlineGroceryStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryStoreBaseRepo extends JpaRepository<OnlineGroceryStore, Long> {
}
