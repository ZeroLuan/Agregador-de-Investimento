package br.com.business.agregadorinvestimentos.repository;

import br.com.business.agregadorinvestimentos.model.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IBillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
