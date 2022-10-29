package com.project.IIITBuyNSell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private BigInteger id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDescription;

    @Column(name = "item_price")
    private BigInteger itemPrice;

    @Column(name = "item_category")
    private String itemCategory;

    @Builder.Default
    @Column(name = "is_lost_found")
    private Boolean isLostFound = false;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "email_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
