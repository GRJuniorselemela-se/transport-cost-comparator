package com.example.itmba_transportcostcomparator;

public class TransportCalculator {

    public double calculateDailyCost(double distancePerDay, double costPerKm) {
        return distancePerDay * costPerKm;
    }

    public double calculateMonthlyCost(double dailyCost, int travelDays) {
        return dailyCost * travelDays;
    }

    public double calculateMonthlyDistance(double distancePerDay, int travelDays) {
        return distancePerDay * travelDays;
    }

    public CalculationResult calculateAll(double distancePerDay, double costPerKm, int travelDays) {
        double dailyCost = calculateDailyCost(distancePerDay, costPerKm);
        double monthlyCost = calculateMonthlyCost(dailyCost, travelDays);
        double monthlyDistance = calculateMonthlyDistance(distancePerDay, travelDays);

        return new CalculationResult(dailyCost, monthlyCost, monthlyDistance);
    }

    public static class CalculationResult {
        public final double dailyCost;
        public final double monthlyCost;
        public final double monthlyDistance;

        public CalculationResult(double dailyCost, double monthlyCost, double monthlyDistance) {
            this.dailyCost = dailyCost;
            this.monthlyCost = monthlyCost;
            this.monthlyDistance = monthlyDistance;
        }
    }
}
