package com.project.IIITBuyNSell.repositories;

import com.project.IIITBuyNSell.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ItemRepository extends JpaRepository<Item, BigInteger> {
}
