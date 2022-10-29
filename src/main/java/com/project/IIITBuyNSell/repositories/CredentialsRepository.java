package com.project.IIITBuyNSell.repositories;

import com.project.IIITBuyNSell.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {
}
