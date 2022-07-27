package com.email.repositories;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.email.models.EmailModel;

public interface EmailRepositoryInterface extends JpaRepository<EmailModel, UUID> {
	
	
}
