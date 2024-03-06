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
public class Storage {
    private long id;
    private String serialNumber;
    private String cardNumber;
    private Timestamp expiredAt;
    private Product product;
    private boolean isUsed;
    private boolean isDelete;
    private Timestamp createdAt;
    private User createdBy;
    private Timestamp updatedAt;
    private User updatedBy;
    private Timestamp deletedAt;
    private User deletedBy;

    public Storage(int i, String name1, int i1, int i2) {
        this.id = i;

    }
}
