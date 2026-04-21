package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tvs",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class TV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotBlank(message = "Enter TV brand name") //@NotBlank: Ensures that the field is not null, empty, or only whitespace.
    private String brand;

    @NonNull
    @NotEmpty(message = "Enter TV model") //@NotEmpty: Ensures the field is not null and has a non-zero length (for strings) or size (for collections).
    private String model;

    @NonNull
    @NotNull(message = "TV color couldn't be null") //@NotNull: Validates that the field is not null.
    private String color;

    @NonNull
    @Range(min = 1, max = 10, message = "Time expectancy must be greater than 0 and less than 10") //@Rage: Specifies the value in range and not null. (See also: @Max; @Min)
    private int timeExpectancy;

    @NonNull
    @PositiveOrZero(message = "Price must be positive or zero") //@PositiveOrZero: Ensures the field is a positive number or zero and not null. (See also: @Positive; @Negative; @NegativeOrZero)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private double price;

    @AssertTrue(message = "Available field must be true") //@AssertTrue: Validates that the field is true and not null.
    private boolean available;

    @NonNull
    @PastOrPresent(message = "Produce date must be in past") //@PastOrPresent: Ensures the field represents a date/time in the past or present. Save null if null.
    @NotNull(message = "Produce date couldn't be null") //@NotNull to check date. @PastOrPresent doesn't check on null.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate produceDate;

    @FutureOrPresent(message = "Guarantee expiry date must be in future") //@FutureOrPresent: Ensures the field represents a date/time in the future or the present.
    @NotNull(message = "Produce date couldn't be null") //@NotNull to check date. @PastOrPresent doesn't check on null.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate guaranteeExpiryDate;

    @Valid
    @NotNull(message = "Size couldn't be null")
    @Transient
    private Size size;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @JsonIgnore
    private String json;

    public void setJson() {
        try {
            this.json = new ObjectMapper().writeValueAsString(this.size);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
