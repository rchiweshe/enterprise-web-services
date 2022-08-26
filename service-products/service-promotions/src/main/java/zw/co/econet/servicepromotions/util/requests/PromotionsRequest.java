package zw.co.econet.servicepromotions.util.requests;

import java.time.LocalDateTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PromotionsRequest {

//    @NotEmpty(message = "The promotion name is required.")
//    @Size(min = 1, max = 100)
    private String name;
//    @NotEmpty(message = "The promotion description is required.")
//    @Size(min = 30, max = 300)
    private String description;
//    @NotNull
//    @FutureOrPresent(message = "The start date must be today or in the future.")
    private LocalDateTime startDate;
//    @NotNull
//    @FutureOrPresent(message = "The end date must be today or in the future.")
    private LocalDateTime endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PromotionsRequest{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
