package com.example.prm392_project.model;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    private int id;
    private String name;
    private Timestamp createdAt,deletedAt,updatedAt;
    private boolean isDelete;
    private String image;
    private User createdBy,deletedBy,updatedBy;
}
