package com.ErasmusApplication.ErasmusApp.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PreApproval extends Form {
   // Properties
   //TODO Change this to Date
   private String preApprovalDeadline;

   // Constructors

   // Methods
}
