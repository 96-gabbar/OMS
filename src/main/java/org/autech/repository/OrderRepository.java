package org.autech.repository;

import org.autech.model.GenericOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<GenericOrder, String> {
}
