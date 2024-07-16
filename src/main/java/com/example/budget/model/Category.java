package com.example.budget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.YearMonth;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private CategoryType categoryType;

    @Column(nullable = false)
    private YearMonth createdAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @PrePersist
    protected void onCreate() {
        this.createdAt = YearMonth.now();
    }

    public Integer getTotalAmount() {
        return transactions.stream().mapToInt(Transaction::getAmount).sum();
    }

}

