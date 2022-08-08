package com.springboot.universitymonolithic.repositories;

import com.springboot.universitymonolithic.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}