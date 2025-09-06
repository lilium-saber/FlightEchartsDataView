package com.mhdx.springweb.model.Sql;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class carrierdelayrate {
    private String uniqueCarrier;
    private Integer flightNum;
}

